package com.mindthecode.CompanyDirectory.config;

import javax.sql.DataSource;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("adm1n")).roles("ADMIN", "COMPANY_MANAGER", "BUSINESS_UNIT_MANAGER", "DEPARTMENT_MANAGER", "UNIT_MANAGER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .authorizeRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/h2-console/**").permitAll()

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
                .antMatchers("/getTasksByDifficulty ").permitAll()
                .antMatchers("/getTasksByDiffAndNumOfEmployees  ").permitAll()

                // Delete single entity
                .antMatchers("/deleteCompany/").permitAll()
                .antMatchers("/deleteBusinessUnit/").permitAll()
                .antMatchers("/deleteDepartment/").permitAll()
                .antMatchers("/deleteUnit/").permitAll()
                .antMatchers("/deletePosition/").permitAll()
                .antMatchers("/deleteEmployee/").permitAll()
                .antMatchers("/deleteTask/").permitAll()

                // Delete multiple entities
                .antMatchers("/deleteCompanies/").permitAll()
                .antMatchers("/deleteBusinessUnits/").permitAll()
                .antMatchers("/deleteDepartments/").permitAll()
                .antMatchers("/deleteUnits/").permitAll()
                .antMatchers("/deletePositions/").permitAll()
                .antMatchers("/deleteEmployees/").permitAll()
                .antMatchers("/deleteTasks/").permitAll()

                // Add single entity
                .antMatchers("/addCompany/").permitAll()
                .antMatchers("/addBusinessUnit").permitAll()
                .antMatchers("/addDepartment/").permitAll()
                .antMatchers("/addUnit/").permitAll()
                .antMatchers("/addPosition/").permitAll()
                .antMatchers("/addEmployee/").permitAll()
                .antMatchers("/addTask/").permitAll()

                // Add multiple entities
                .antMatchers("/addCompanies/").permitAll()
                .antMatchers("/addBusinessUnits").permitAll()
                .antMatchers("/addDepartments/").permitAll()
                .antMatchers("/addUnits/").permitAll()
                .antMatchers("/addPositions/").permitAll()
                .antMatchers("/addEmployees/").permitAll()
                .antMatchers("/addTasks/").permitAll()

                // Update single entity
                .antMatchers("/updateCompany/").permitAll()
                .antMatchers("/updateBusinessUnit/").permitAll()
                .antMatchers("/updateDepartment/").permitAll()
                .antMatchers("/updateUnit/").permitAll()
                .antMatchers("/updatePosition/").permitAll()
                .antMatchers("/updateEmployee/").permitAll()
                .antMatchers("/updateTask/").permitAll()

                // Update multiple entities
                .antMatchers("/updateCompanies/").permitAll()
                .antMatchers("/updateBusinessUnits/").permitAll()
                .antMatchers("/updateDepartments/").permitAll()
                .antMatchers("/updateUnits/").permitAll()
                .antMatchers("/updatePositions/").permitAll()
                .antMatchers("/updateEmployees/").permitAll()
                .antMatchers("/updateTasks/").permitAll()

                .anyRequest().permitAll()
//                .anyRequest().authenticated()

                .and()
                .formLogin()
                // .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .permitAll()

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .deleteCookies("my-remember-me-cookie")
                .permitAll()

                .and()
                .rememberMe()
                //.key("my-secure-key")
                .rememberMeCookieName("my-remember-me-cookie")
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(24 * 60 * 60)

                /**
                 *** Temporary authentication bypass
                 */
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                // Delete single entity
                .and().csrf().ignoringAntMatchers("/deleteCompany/")
                .and().csrf().ignoringAntMatchers("/deleteBusinessUnit/")
                .and().csrf().ignoringAntMatchers("/deleteDepartment/")
                .and().csrf().ignoringAntMatchers("/deleteUnit/")
                .and().csrf().ignoringAntMatchers("/deletePosition/")
                .and().csrf().ignoringAntMatchers("/deleteEmployee/")
                .and().csrf().ignoringAntMatchers("/deleteTask/")

                // Delete multiple entities
                .and().csrf().ignoringAntMatchers("/deleteCompanies/")
                .and().csrf().ignoringAntMatchers("/deleteBusinessUnits/")
                .and().csrf().ignoringAntMatchers("/deleteDepartments/")
                .and().csrf().ignoringAntMatchers("/deleteUnits/")
                .and().csrf().ignoringAntMatchers("/deletePositions/")
                .and().csrf().ignoringAntMatchers("/deleteEmployees/")
                .and().csrf().ignoringAntMatchers("/deleteTasks/")

                // Add single entity
                .and().csrf().ignoringAntMatchers("/addCompany/")
                .and().csrf().ignoringAntMatchers("/addBusinessUnit")
                .and().csrf().ignoringAntMatchers("/addDepartment/")
                .and().csrf().ignoringAntMatchers("/addUnit/")
                .and().csrf().ignoringAntMatchers("/addPosition/")
                .and().csrf().ignoringAntMatchers("/addEmployee/")
                .and().csrf().ignoringAntMatchers("/addTask/")

                // Add multiple entities
                .and().csrf().ignoringAntMatchers("/addCompanies/")
                .and().csrf().ignoringAntMatchers("/addBusinessUnits")
                .and().csrf().ignoringAntMatchers("/addDepartments/")
                .and().csrf().ignoringAntMatchers("/addUnits/")
                .and().csrf().ignoringAntMatchers("/addPositions/")
                .and().csrf().ignoringAntMatchers("/addEmployees/")
                .and().csrf().ignoringAntMatchers("/addTasks/")

                // Update single entity
                .and().csrf().ignoringAntMatchers("/updateCompany/")
                .and().csrf().ignoringAntMatchers("/updateBusinessUnit/")
                .and().csrf().ignoringAntMatchers("/updateDepartment/")
                .and().csrf().ignoringAntMatchers("/updateUnit/")
                .and().csrf().ignoringAntMatchers("/updatePosition/")
                .and().csrf().ignoringAntMatchers("/updateEmployee/")
                .and().csrf().ignoringAntMatchers("/updateTask/")

                // Update multiple entities
                .and().csrf().ignoringAntMatchers("/updateCompanies/")
                .and().csrf().ignoringAntMatchers("/updateBusinessUnits/")
                .and().csrf().ignoringAntMatchers("/updateDepartments/")
                .and().csrf().ignoringAntMatchers("/updateUnits/")
                .and().csrf().ignoringAntMatchers("/updatePositions/")
                .and().csrf().ignoringAntMatchers("/updateEmployees/")
                .and().csrf().ignoringAntMatchers("/updateTasks/")
                /**
                 * End of temp fix
                 */

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
