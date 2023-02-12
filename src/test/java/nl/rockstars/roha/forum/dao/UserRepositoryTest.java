package nl.rockstars.roha.forum.dao;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import nl.rockstars.roha.forum.model.Role;
import nl.rockstars.roha.forum.model.User;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created on 05/02/2023.
 */

@QuarkusTest
@TestTransaction
class UserRepositoryTest {

    @Inject
    EntityManager entityManager;
    @Inject
    UserRepository userRepository;

    @Test
    public void testingRelations() {
        User user = new User("user", "user", Collections.singleton(Role.USER));
        userRepository.persist(user);
        entityManager.flush();
        entityManager.clear();

        user = userRepository.findByUsername("user").orElseThrow();
        assertNotNull(user.getRoles());
        assertFalse(user.getRoles().isEmpty());


    }

}