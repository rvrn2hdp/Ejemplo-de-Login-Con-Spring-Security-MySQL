package com.analistas.loginandsignin.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.analistas.loginandsignin.model.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    /**
     * Creates a bean of type CustomUserDetailsService, which is responsible for
     * loading user details from a data source. This bean is used to authenticate
     * users and authorize their actions.
     *
     * @return CustomUserDetailsService instance
     */
    @Bean
    public CustomUserDetailsService userDetailsService() {
        // Instantiate and return an instance of CustomUserDetailsService
        return new CustomUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a bean of type AuthenticationProvider, which is responsible for
     * authenticating users using the provided user details service and password encoder.
     *
     * @param userDetailsService The user details service used to load user details from a data source.
     * @return AuthenticationProvider instance
     */
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService) {
        // Create a new instance of DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Set the password encoder to be used for encoding and decoding passwords
        provider.setPasswordEncoder(passwordEncoder());
        // Set the user details service to be used for loading user details
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }

    /**
     * Creates a bean of type PersistentTokenRepository, which is responsible for
     * storing and retrieving persistent login tokens. This bean is used to enable
     * "Remember Me" functionality in the application.
     *
     * @return PersistentTokenRepository instance
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        // Create a new instance of JdbcTokenRepositoryImpl
        JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
        // Set the data source to be used for storing and retrieving persistent login tokens
        tokenRepo.setDataSource(dataSource);
        // Return the created token repository
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

        // Configuring authorization for different endpoints
        http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/users", "/example")
                        .authenticated() // Requires authentication for "/users" endpoint
                    .anyRequest()
                        .permitAll() // Allows access to all other endpoints
        );

        // Configuring the form login behavior
        http.formLogin(login -> login
                .usernameParameter("username") // Specifying the username parameter as "email"
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
