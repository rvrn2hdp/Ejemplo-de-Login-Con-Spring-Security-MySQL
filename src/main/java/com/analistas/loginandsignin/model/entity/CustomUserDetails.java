// package com.analistas.loginandsignin.model.entity;

// import java.util.Collection;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// public class CustomUserDetails implements UserDetails {

//     private User user;

//     public CustomUserDetails(User user) {
//         this.user = user;
//     }

//     /**
//      * Returns the authorities granted to the user. This method is intended to
//      * return the authorities granted to the user and therefore should return
//      * an empty collection if the user has not been granted any authorities.
//      *
//      * @return The authorities granted to the user, or an empty collection if the
//      *         user has not been granted any authorities.
//      */
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         // This implementation always returns an empty collection. It is intended
//         // to be overridden by subclasses to return the authorities granted to
//         // the user.
//         return null;
//     }

//     @Override
//     public String getPassword() {
//         return this.user.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return this.user.getUsername();
//     }

//     /**
//      * Returns {@code true} if the user's account has not expired, {@code false}
//      * otherwise. In this implementation, the account is never expired and this
//      * method always returns {@code true}.
//      * 
//      * @return {@code true} if the user's account has not expired, {@code false}
//      *         otherwise
//      */
//     @Override
//     public boolean isAccountNonExpired() {
//         // This implementation always returns true. It is intended to be
//         // overridden by subclasses to check if the user's account has expired.
//         return true;
//     }

//     /**
//      * Returns {@code true} if the user's account is not locked, {@code false}
//      * otherwise. In this implementation, the account is never locked and this
//      * method always returns {@code true}.
//      *
//      * @return {@code true} if the user's account is not locked, {@code false}
//      *         otherwise
//      */
//     @Override
//     public boolean isAccountNonLocked() {
//         // This implementation always returns true. It is intended to be
//         // overridden by subclasses to check if the user's account has been locked.
//         return true;
//     }

//     /**
//      * Returns {@code true} if the user's credentials (such as password) have not
//      * expired, {@code false} otherwise.
//      * In this implementation, the credentials are never expired and this method
//      * always returns {@code true}.
//      *
//      * @return {@code true} if the user's credentials have not expired,
//      *         {@code false} otherwise
//      */
//     @Override
//     public boolean isCredentialsNonExpired() {
//         // This implementation always returns true. It is intended to be
//         // overridden by subclasses to check if the user's credentials have expired.
//         return true;
//     }

//     /**
//      * Returns {@code true} if the user is enabled, {@code false} otherwise.
//      * In this implementation, the user is always enabled and this method always
//      * returns {@code true}.
//      *
//      * @return {@code true} if the user is enabled, {@code false} otherwise
//      */
//     @Override
//     public boolean isEnabled() {
//         // This implementation always returns true. It is intended to be
//         // overridden by subclasses to check if the user is enabled.
//         return true;
//     }

//     public String getFullName() {
//         return this.user.getUsername() + " " + this.user.getEmail();
//     }

// }
