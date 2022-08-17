package com.app.configs;

import com.app.handlers.LoginSuccessHandler;
import com.app.handlers.LogoutSuccessHandler;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.app.repository",
    "com.app.service"
})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler loginSuccessHalder;
    @Autowired
    private org.springframework.security.web.authentication.logout.LogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dbjsshftw",
                        "api_key", "367528995653435",
                        "api_secret", "DBbNZAo0tRmI4giI6L4lCw5RoXM",
                        "secure", true));
        return cloudinary;
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public org.springframework.security.web.authentication.logout.LogoutSuccessHandler logoutSucessHandler() {
        return new LogoutSuccessHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/").failureUrl("/?error");
        http.formLogin().successHandler(this.loginSuccessHalder);

        http.logout().logoutSuccessHandler(this.logoutSuccessHandler);

        http.exceptionHandling()
                .accessDeniedPage("/?accessDenied");

        http.authorizeRequests().antMatchers("/").permitAll()
                                .antMatchers("/admin/**").access("hasAnyAuthority('ADMIN')");
        
        http.csrf().disable();
    }
}
