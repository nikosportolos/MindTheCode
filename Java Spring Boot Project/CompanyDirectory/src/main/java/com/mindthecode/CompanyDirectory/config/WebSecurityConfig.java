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
                .antMatchers("/deleteCompany/").hasRole("")
                .antMatchers("/deleteBusinessUnit/").hasRole("")
                .antMatchers("/deleteDepartment/").hasRole("")
                .antMatchers("/deleteUnit/").hasRole("")
                .antMatchers("/deletePosition/").hasRole("")
                .antMatchers("/deleteEmployee/").hasRole("")
                .antMatchers("/deleteTask/").hasRole("")

                // Delete multiple entities
                .antMatchers("/deleteCompanies/").hasRole("")
                .antMatchers("/deleteBusinessUnits/").hasRole("")
                .antMatchers("/deleteDepartments/").hasRole("")
                .antMatchers("/deleteUnits/").hasRole("")
                .antMatchers("/deletePositions/").hasRole("")
                .antMatchers("/deleteEmployees/").hasRole("")
                .antMatchers("/deleteTasks/").hasRole("")

                // Add single entity
                .antMatchers("/addCompany/").hasRole("")
                .antMatchers("/addBusinessUnit/").hasRole("")
                .antMatchers("/addDepartment/").hasRole("")
                .antMatchers("/addUnit/").hasRole("")
                .antMatchers("/addPosition/").hasRole("")
                .antMatchers("/addEmployee/").hasRole("")
                .antMatchers("/addTask/").hasRole("")

                // Add multiple entities
                .antMatchers("/addCompanies/").hasRole("")
                .antMatchers("/addBusinessUnits/").hasRole("")
                .antMatchers("/addDepartments/").hasRole("")
                .antMatchers("/addUnits/").hasRole("")
                .antMatchers("/addPositions/").hasRole("")
                .antMatchers("/addEmployees/").hasRole("")
                .antMatchers("/addTasks/").hasRole("")

                // Update single entity
                .antMatchers("/updateCompany/").hasRole("")
                .antMatchers("/updateBusinessUnit/").hasRole("")
                .antMatchers("/updateDepartment/").hasRole("")
                .antMatchers("/updateUnit/").hasRole("")
                .antMatchers("/updatePosition/").hasRole("")
                .antMatchers("/updateEmployee/").hasRole("")
                .antMatchers("/updateTask/").hasRole("")

                // Update multiple entities
                .antMatchers("/updateCompanies/").hasRole("")
                .antMatchers("/updateBusinessUnits/").hasRole("")
                .antMatchers("/updateDepartments/").hasRole("")
                .antMatchers("/updateUnits/").hasRole("")
                .antMatchers("/updatePositions/").hasRole("")
                .antMatchers("/updateEmployees/").hasRole("")
                .antMatchers("/updateTasks/").hasRole("")

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

                .and().csrf().ignoringAntMatchers("/h2-console/**")

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
