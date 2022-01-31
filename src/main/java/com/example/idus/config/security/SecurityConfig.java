package com.example.idus.config.security;

import com.example.idus.config.security.jwt.CustomEntryPoint;
import com.example.idus.config.security.jwt.JwtAuthenticationFilter;
import com.example.idus.config.security.jwt.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().disable();
        http.httpBasic().disable();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
        http.addFilter(new JwtAuthorizationFilter(authenticationManager()));
        http.exceptionHandling().authenticationEntryPoint(new CustomEntryPoint());
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers("/swagger-ui/index.html", "/v3/api-docs", "/swagger-resources/**", "/webjars/**", "/swagger/**").permitAll()
                .anyRequest().access("hasRole('ROLE_USER')");
    }
}

