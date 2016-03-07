package com.psu.est.config;

import com.psu.est.filter.CsrfHeaderFilter;
import com.psu.est.handler.SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;

/**
 * Created by danielkarkee on 2/6/16.
 */
@Configuration
@EnableWebSecurity
@ComponentScan({
        "com.psu.est.handler"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("employeeDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private SuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/resources/**", "/index**", "/login**").permitAll()
                .antMatchers("/technician/**").access("hasRole('TECHNICIAN')")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll().usernameParameter("username").passwordParameter("password").successHandler(successHandler)
                .and().csrf().csrfTokenRepository(csrfTokenRepository())
                .and().logout().permitAll()
                .and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
}
