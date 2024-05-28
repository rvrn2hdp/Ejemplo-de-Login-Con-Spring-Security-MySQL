package com.analistas.loginandsignin;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.analistas.loginandsignin.model.entity.PermissionEntity;
import com.analistas.loginandsignin.model.entity.RoleEntity;
import com.analistas.loginandsignin.model.entity.RoleEnum;
import com.analistas.loginandsignin.model.entity.UserEntity;
import com.analistas.loginandsignin.model.repository.UserRepository;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class LoginandsigninApplication {

        public static void main(String[] args) {
                SpringApplication.run(LoginandsigninApplication.class, args);
        }

        // @Bean
        // CommandLineRunner init(UserRepository userRepository) {

        //         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //         return args -> {
        //                 /* Create PERMISSIONS */
        //                 PermissionEntity createPermission = PermissionEntity.builder()
        //                                 .name("CREATE")
        //                                 .build();

        //                 PermissionEntity readPermission = PermissionEntity.builder()
        //                                 .name("READ")
        //                                 .build();

        //                 PermissionEntity updatePermission = PermissionEntity.builder()
        //                                 .name("UPDATE")
        //                                 .build();

        //                 PermissionEntity deletePermission = PermissionEntity.builder()
        //                                 .name("DELETE")
        //                                 .build();

        //                 PermissionEntity refactorPermission = PermissionEntity.builder()
        //                                 .name("REFACTOR")
        //                                 .build();

        //                 /* Create ROLES */
        //                 RoleEntity roleAdmin = RoleEntity.builder()
        //                                 .roleEnum(RoleEnum.ADMIN)
        //                                 .permissions(Set.of(createPermission, readPermission, updatePermission,
        //                                                 deletePermission))
        //                                 .build();

        //                 RoleEntity roleUser = RoleEntity.builder()
        //                                 .roleEnum(RoleEnum.USER)
        //                                 .permissions(Set.of(createPermission, readPermission))
        //                                 .build();

        //                 RoleEntity roleInvited = RoleEntity.builder()
        //                                 .roleEnum(RoleEnum.GUEST)
        //                                 .permissions(Set.of(readPermission))
        //                                 .build();

        //                 RoleEntity roleDeveloper = RoleEntity.builder()
        //                                 .roleEnum(RoleEnum.MODERATOR)
        //                                 .permissions(Set.of(createPermission, readPermission, updatePermission,
        //                                                 deletePermission, refactorPermission))
        //                                 .build();

        //                 /* Create USERS */

        //                 UserEntity userAdmin = UserEntity.builder()
        //                                 .username("admin")
        //                                 .password(encoder.encode("admin"))
        //                                 .email("admin@localhost")
        //                                 .isEnabled(true)
        //                                 .accountNonExpired(true)
        //                                 .accountnonLocked(true)
        //                                 .credentialsNonExpired(true)
        //                                 .roles(Set.of(roleAdmin))
        //                                 .build();

        //                 UserEntity userUser = UserEntity.builder()
        //                                 .username("user")
        //                                 .password(encoder.encode("user"))
        //                                 .email("user@localhost")
        //                                 .isEnabled(true)
        //                                 .accountNonExpired(true)
        //                                 .accountnonLocked(true)
        //                                 .credentialsNonExpired(true)
        //                                 .roles(Set.of(roleUser))
        //                                 .build();

        //                 UserEntity userInvited = UserEntity.builder()
        //                                 .username("invited")
        //                                 .password(encoder.encode("invited"))
        //                                 .email("invited@localhost")
        //                                 .isEnabled(true)
        //                                 .accountNonExpired(true)
        //                                 .accountnonLocked(true)
        //                                 .credentialsNonExpired(true)
        //                                 .roles(Set.of(roleInvited))
        //                                 .build();

        //                 UserEntity userDeveloper = UserEntity.builder()
        //                                 .username("developer")
        //                                 .password(encoder.encode("developer"))
        //                                 .email("developer@localhost")
        //                                 .isEnabled(true)
        //                                 .accountNonExpired(true)
        //                                 .accountnonLocked(true)
        //                                 .credentialsNonExpired(true)
        //                                 .roles(Set.of(roleDeveloper))
        //                                 .build();

        //                 userRepository.saveAll(List.of(userAdmin, userUser, userInvited, userDeveloper));
        //         };
        // }

}