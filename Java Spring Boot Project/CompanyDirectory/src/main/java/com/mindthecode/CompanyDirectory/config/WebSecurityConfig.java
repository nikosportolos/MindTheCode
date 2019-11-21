package com.mindthecode.CompanyDirectory.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
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
                .csrf().disable()
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
                .antMatchers("/deleteCompany/").hasAnyRole("ADMIN")
                .antMatchers("/deleteBusinessUnit/").hasAnyRole("ADMIN")
                .antMatchers("/deleteDepartment/").hasAnyRole("ADMIN")
                .antMatchers("/deleteUnit/").hasAnyRole("ADMIN")
                .antMatchers("/deletePosition/").hasAnyRole("ADMIN")
                .antMatchers("/deleteEmployee/").hasAnyRole("ADMIN")
                .antMatchers("/deleteTask/").hasAnyRole("ADMIN")

                // Delete multiple entities
                .antMatchers("/deleteCompanies/").hasAnyRole("ADMIN")
                .antMatchers("/deleteBusinessUnits/").hasAnyRole("ADMIN")
                .antMatchers("/deleteDepartments/").hasAnyRole("ADMIN")
                .antMatchers("/deleteUnits/").hasAnyRole("ADMIN")
                .antMatchers("/deletePositions/").hasAnyRole("ADMIN")
                .antMatchers("/deleteEmployees/").hasAnyRole("ADMIN")
                .antMatchers("/deleteTasks/").hasAnyRole("ADMIN")

                // Add single entity
                .antMatchers("/addCompany/").hasAnyRole("ADMIN")
                .antMatchers("/addBusinessUnit").hasAnyRole("ADMIN")
                .antMatchers("/addDepartment/").hasAnyRole("ADMIN")
                .antMatchers("/addUnit/").hasAnyRole("ADMIN")
                .antMatchers("/addPosition/").hasAnyRole("ADMIN")
                .antMatchers("/addEmployee/").hasAnyRole("ADMIN")
                .antMatchers("/addTask/").hasAnyRole("ADMIN")

                // Add multiple entities
                .antMatchers("/addCompanies/").hasAnyRole("ADMIN")
                .antMatchers("/addBusinessUnits").hasAnyRole("ADMIN")
                .antMatchers("/addDepartments/").hasAnyRole("ADMIN")
                .antMatchers("/addUnits/").hasAnyRole("ADMIN")
                .antMatchers("/addPositions/").hasAnyRole("ADMIN")
                .antMatchers("/addEmployees/").hasAnyRole("ADMIN")
                .antMatchers("/addTasks/").hasAnyRole("ADMIN")

                // Update single entity
                .antMatchers("/updateCompany/").hasAnyRole("ADMIN")
                .antMatchers("/updateBusinessUnit/").hasAnyRole("ADMIN")
                .antMatchers("/updateDepartment/").hasAnyRole("ADMIN")
                .antMatchers("/updateUnit/").hasAnyRole("ADMIN")
                .antMatchers("/updatePosition/").hasAnyRole("ADMIN")
                .antMatchers("/updateEmployee/").hasAnyRole("ADMIN")
                .antMatchers("/updateTask/").hasAnyRole("ADMIN")

                // Update multiple entities
                .antMatchers("/updateCompanies/").hasAnyRole("ADMIN")
                .antMatchers("/updateBusinessUnits/").hasAnyRole("ADMIN")
                .antMatchers("/updateDepartments/").hasAnyRole("ADMIN")
                .antMatchers("/updateUnits/").hasAnyRole("ADMIN")
                .antMatchers("/updatePositions/").hasAnyRole("ADMIN")
                .antMatchers("/updateEmployees/").hasAnyRole("ADMIN")
                .antMatchers("/updateTasks/").hasAnyRole("ADMIN")

                .anyRequest().authenticated()

                .and()
                .formLogin()
                .defaultSuccessUrl("/home")
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
