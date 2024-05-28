package com.analistas.loginandsignin.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
// import com.analistas.loginandsignin.model.entity.CustomUserDetails;
import com.analistas.loginandsignin.model.entity.UserEntity;
import com.analistas.loginandsignin.model.repository.UserRepository;

import java.util.List;
import java.util.ArrayList;

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

        // Find the user in the repository based on the provided username (username)
        UserEntity user = userRepository.findByUsername(username);

        // If the user is not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

        // Add the user's roles to the list of authorities
        user.getRoles().forEach(role -> {
            authoritiesList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
        });

        // Add the user's permissions to the list of authorities
        user.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .forEach(permission -> { authoritiesList.add(new SimpleGrantedAuthority(permission.getName()));
            });

        // Return a new CustomUserDetails object representing the user
        return new User(user.getUsername(),
                        user.getPassword(),
                        user.isEnabled(),
                        user.isAccountNonExpired(),
                        user.isCredentialsNonExpired(),
                        user.isAccountnonLocked(),
                        authoritiesList);
        
        // Return a new CustomUserDetails object representing the user
        // return new CustomUserDetails(user);
    }

}
