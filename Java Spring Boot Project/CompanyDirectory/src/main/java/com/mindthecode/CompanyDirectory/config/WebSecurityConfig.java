package com.mindthecode.CompanyDirectory.config;

import javax.sql.DataSource;

import com.mindthecode.CompanyDirectory.common.Enums;
import com.mindthecode.CompanyDirectory.strategy.user_roles.GetUserRoleStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    GetUserRoleStrategyFactory factory;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("adm1n")).roles(factory.makeStrategyForCriteria(Enums.UserRole.ADMIN).execute())
                .and()
                .withUser("companyManager").password(passwordEncoder().encode("123")).roles(Enums.UserRole.BUSINESS_UNIT_MANAGER.toString())
                .and()
                .withUser("buManager").password(passwordEncoder().encode("123")).roles(Enums.UserRole.BUSINESS_UNIT_MANAGER.toString())
                .and()
                .withUser("deptManager").password(passwordEncoder().encode("123")).roles(Enums.UserRole.DEPARTMENT_MANAGER.toString())
                .and()
                .withUser("unitManager").password(passwordEncoder().encode("123")).roles(Enums.UserRole.UNIT_MANAGER.toString())
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()

                .and()
                .authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/h2-console").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.ADMIN).execute())
                .antMatchers("/h2-console/**").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.ADMIN).execute())

                // Select entities
                .antMatchers("/companies").permitAll()
                .antMatchers("/businessUnits").permitAll()
                .antMatchers("/departments").permitAll()
                .antMatchers("/units").permitAll()
                .antMatchers("/positions").permitAll()
                .antMatchers("/employees").permitAll()
                .antMatchers("/tasks").permitAll()

                // Select by id
                .antMatchers("/company/**").permitAll()
                .antMatchers("/businessUnit/**").permitAll()
                .antMatchers("/department/**").permitAll()
                .antMatchers("/unit/**").permitAll()
                .antMatchers("/position/**").permitAll()
                .antMatchers("/employee/**").permitAll()
                .antMatchers("/task/**").permitAll()

                .antMatchers("/getTasksByNumOfEmployees").permitAll()
                .antMatchers("/getTasksByDifficulty").permitAll()
                .antMatchers("/getTasksByDiffAndNumOfEmployees").permitAll()

                // Delete single entity
                .antMatchers("/deleteCompany/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.COMPANY_MANAGER).execute())
                .antMatchers("/deleteBusinessUnit/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.BUSINESS_UNIT_MANAGER).execute())
                .antMatchers("/deleteDepartment/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.DEPARTMENT_MANAGER).execute())
                .antMatchers("/deleteUnit/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deletePosition/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deleteEmployee/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deleteTask/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())

                // Delete multiple entities
                .antMatchers("/deleteCompanies/").hasAnyRole(Enums.UserRole.ADMIN.toString(), Enums.UserRole.COMPANY_MANAGER.toString())
                .antMatchers("/deleteBusinessUnits/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.BUSINESS_UNIT_MANAGER).execute())
                .antMatchers("/deleteDepartments/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.DEPARTMENT_MANAGER).execute())
                .antMatchers("/deleteUnits/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deletePositions/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deleteEmployees/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deleteTasks/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())

                // Delete all entities
                .antMatchers("/deleteAllCompanies/").hasAnyRole(Enums.UserRole.ADMIN.toString(), Enums.UserRole.COMPANY_MANAGER.toString())
                .antMatchers("/deleteAllBusinessUnits/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.BUSINESS_UNIT_MANAGER).execute())
                .antMatchers("/deleteAllDepartments/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.DEPARTMENT_MANAGER).execute())
                .antMatchers("/deleteAllUnits/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deleteAllPositions/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deleteAllEmployees/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/deleteAllTasks/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())

                // Add single entity
                .antMatchers("/addCompany/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.COMPANY_MANAGER).execute())
                .antMatchers("/addBusinessUnit").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.BUSINESS_UNIT_MANAGER).execute())
                .antMatchers("/addDepartment/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.DEPARTMENT_MANAGER).execute())
                .antMatchers("/addUnit/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/addPosition/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/addEmployee/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/addTask/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())

                // Add multiple entities
                .antMatchers("/addCompanies/").hasAnyRole(Enums.UserRole.ADMIN.toString(), Enums.UserRole.COMPANY_MANAGER.toString())
                .antMatchers("/addBusinessUnits").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.BUSINESS_UNIT_MANAGER).execute())
                .antMatchers("/addDepartments/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.DEPARTMENT_MANAGER).execute())
                .antMatchers("/addUnits/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/addPositions/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/addEmployees/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/addTasks/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())

                // Update single entity
                .antMatchers("/updateCompany/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.COMPANY_MANAGER).execute())
                .antMatchers("/updateBusinessUnit/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.BUSINESS_UNIT_MANAGER).execute())
                .antMatchers("/updateDepartment/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.DEPARTMENT_MANAGER).execute())
                .antMatchers("/updateUnit/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/updatePosition/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/updateEmployee/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/updateTask/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())

                // Update multiple entities
                .antMatchers("/updateCompanies/").hasAnyRole(Enums.UserRole.ADMIN.toString(), Enums.UserRole.COMPANY_MANAGER.toString())
                .antMatchers("/updateBusinessUnits/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.BUSINESS_UNIT_MANAGER).execute())
                .antMatchers("/updateDepartments/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.DEPARTMENT_MANAGER).execute())
                .antMatchers("/updateUnits/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/updatePositions/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/updateEmployees/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())
                .antMatchers("/updateTasks/").hasAnyRole(factory.makeStrategyForCriteria(Enums.UserRole.UNIT_MANAGER).execute())

                .anyRequest().authenticated()

                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("my-remember-me-cookie")
                .permitAll()

//                .and()
//                .rememberMe()
//                //.key("my-secure-key")
//                .rememberMeCookieName("my-remember-me-cookie")
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(24 * 60 * 60)

                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
        ;
    }

    private PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        return tokenRepositoryImpl;
    }

}
