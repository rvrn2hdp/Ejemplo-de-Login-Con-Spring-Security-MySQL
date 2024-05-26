package com.analistas.loginandsignin.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.analistas.loginandsignin.model.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserREpositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    // test methos go below...

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("p8Zqy@example.com");
        user.setPassword("test123");
        user.setFirstName("Test");
        user.setLastName("User");

        User savedUser = repository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        Assertions.assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
}