package com.example.AirlineProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT \"Username\", \"Password\",\"enabled\" from \"Users\" where \"Username\"=?")
                .authoritiesByUsernameQuery("SELECT \"Users\".\"Username\",CONCAT('ROLE_',\"User_Roles\".\"role_name\") as role FROM \"Users\"\n" +
                        "JOIN \"User_Roles\"\n" +
                        "on \"Users\".\"User_Role\" = \"User_Roles\".\"Id\"\n" +
                        "WHERE \"Username\" = ?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

// Customer - 1
// Airline Company - 2
// Administrator - 3
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/administrator/**").hasRole("Administrator")
                .antMatchers("/airline/**").hasAnyRole("Administrator", "Airline Company")
                .antMatchers("/customer/**").hasAnyRole("Administrator", "Customer")
                .antMatchers("/**").permitAll()
                .and().formLogin();
    }

}
