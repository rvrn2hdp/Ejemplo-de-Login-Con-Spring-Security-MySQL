// package com.analistas.loginandsignin.model.repository;

// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
// import org.springframework.test.annotation.Rollback;

// import com.analistas.loginandsignin.model.entity.UserEntity;

// @DataJpaTest
// @AutoConfigureTestDatabase(replace = Replace.NONE)
// @Rollback(false)
// public class UserREpositoryTests {

//     @Autowired
//     private TestEntityManager entityManager;

//     @Autowired
//     private UserRepository repository;

//     // test methos go below...

//     @Test
//     public void testCreateUser() {
//         UserEntity user = new UserEntity();
//         user.setUsername("testuser");
//         user.setPassword("test123");
//         user.setEmail("p8Zqy@example.com");

//         UserEntity savedUser = repository.save(user);

//         UserEntity existUser = entityManager.find(UserEntity.class, savedUser.getId());

//         Assertions.assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
//     }
// }