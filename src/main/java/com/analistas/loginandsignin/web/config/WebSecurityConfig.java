package com.analistas.loginandsignin.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.analistas.loginandsignin.model.entity.CustomUserDetailsService;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * Creates a bean of type UserDetailsService which is responsible for
     * loading user details from a data source.
     *
     * @return UserDetailsService instance
     */
    @Bean
    public UserDetailsService userDetailsService() {
        // Returning an instance of CustomUserDetailsService as it implements
        // UserDetailsService and is responsible for loading user details from
        // a data source.
        return new CustomUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a bean of type DaoAuthenticationProvider which is responsible for
     * authenticating users by verifying their credentials.
     *
     * @return DaoAuthenticationProvider instance
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Creating an instance of DaoAuthenticationProvider
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Setting the userDetailsService bean which is responsible for loading user
        // details from a data source
        authProvider.setUserDetailsService(userDetailsService());

        // Setting the passwordEncoder bean which is responsible for encoding and
        // decoding passwords
        authProvider.setPasswordEncoder(passwordEncoder());

        // Returning the DaoAuthenticationProvider instance
        return authProvider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
        tokenRepo.setDataSource(dataSource);
        return tokenRepo;
    }

    /**
     * Configures the HttpSecurity object to enable authentication and authorization
     * for different endpoints.
     *
     * @param http The HttpSecurity object to configure
     * @return The configured HttpSecurity object
     * @throws Exception If there is an error while configuring the HttpSecurity
     *                   object
     */
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        // Configuring the authentication provider
        http.authenticationProvider(authenticationProvider());

        // Configuring authorization for different endpoints
        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/users", "/example")
                        .authenticated() // Requires authentication for "/users" endpoint
                    .anyRequest()
                        .permitAll() // Allows access to all other endpoints
        );

        // Configuring the form login behavior
        http.formLogin(login -> login
                .usernameParameter("email") // Specifying the username parameter as "email"
                .defaultSuccessUrl("/users") // Redirecting to "/users" after successful login
                .permitAll() // Allows access to the login form
        );

        // Configuring the remember me behavior
        http.rememberMe(rememberMe -> rememberMe
                .tokenRepository(persistentTokenRepository()) // Setting the token repository
                .key("uniqueAndSecret") // Cookies will survive if the server is restarted
                .rememberMeParameter("remember-me") // Specifying the remember me parameter as "remember-me"
                .rememberMeCookieName("remember-me-cookie") // Setting the name of the remember me cookie
                .tokenValiditySeconds(7 * 24 * 60 * 60) // expiration time in seconds: 7 days, 86400 seconds in a day
        // .userDetailsService(userDetailsService()) // Setting the userDetailsService
        // bean
        // .alwaysRemember(true) // Always remember the user
        );

        // Configuring the logout behavior
        http.logout(logout -> logout
                .logoutSuccessUrl("/") // Redirecting to "/" after logout
                .permitAll() // Allows access to the logout page
        );

        // Returning the configured HttpSecurity object
        return http.build();

    }
}
