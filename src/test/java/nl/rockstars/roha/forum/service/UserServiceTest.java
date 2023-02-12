package nl.rockstars.roha.forum.service;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import nl.rockstars.roha.forum.dao.UserRepository;
import nl.rockstars.roha.forum.model.Role;
import nl.rockstars.roha.forum.model.User;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created on 11/02/2023.
 */
@QuarkusTest
@TestTransaction
class UserServiceTest {

    @Inject
    UserService userService;
    @Inject
    UserRepository userRepository;

    @Test
    public void happyPath(){
        assertEquals(2, userRepository.count());


        Optional<User> optionalUser = userService.login("user", "user");
        assertFalse(optionalUser.isEmpty());
    }
}