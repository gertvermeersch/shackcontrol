package com.stormlabs.shackcontrol.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${shackcontrol.user}")
    private String USER;

    @Value("${shackcontrol.password}")
    private String PASSWORD;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String encodedPassword = passwordEncoder().encode(PASSWORD);
        manager.createUser(User.withUsername(USER).password(encodedPassword).roles("USER").build());
        System.out.println("USER: " + USER + ", PASSWORD: " + PASSWORD);
        return manager;
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userDetailsService());
//    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/ws", "/ws/**/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.PUT, "/switches", "/switches/**/*").authenticated()
//                .antMatchers(HttpMethod.GET, "/switches", "/switches/**/*").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
