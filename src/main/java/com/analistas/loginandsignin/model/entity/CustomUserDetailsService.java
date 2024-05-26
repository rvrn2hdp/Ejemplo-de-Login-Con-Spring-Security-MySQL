package com.analistas.loginandsignin.model.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.analistas.loginandsignin.model.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * This method is responsible for loading user details from the user repository
     * based on the provided username.
     *
     * @param  username  the email of the user to be loaded
     * @return           the UserDetails object representing the user
     * @throws UsernameNotFoundException if the user is not found in the repository
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find the user in the repository based on the provided username (email)
        User user = userRepository.findByEmail(username);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Return a new CustomUserDetails object representing the user
        return new CustomUserDetails(user);
    }

}
