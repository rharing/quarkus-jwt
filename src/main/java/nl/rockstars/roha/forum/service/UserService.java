package nl.rockstars.roha.forum.service;

import nl.rockstars.roha.forum.dao.UserRepository;
import nl.rockstars.roha.forum.model.Role;
import nl.rockstars.roha.forum.model.User;
import nl.rockstars.roha.forum.util.PBKDF2Encoder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Created on 11/02/2023.
 */
@ApplicationScoped
@Transactional
public class UserService {
    @Inject
    UserRepository userRepository;
    @Inject
    PBKDF2Encoder encoder;

    public UserService() {
    }

    public UserService(UserRepository userRepository, PBKDF2Encoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String username, String password){
        return this.create(username, password, Collections.singletonList(Role.USER));
    }
    public User create(String username, String password, Collection<Role> roles){
        User user = new User(username, encoder.encode(password),new HashSet<>(roles));
        userRepository.persist(user);
        return user;
    }

    public Optional<User> login(String username, String password){
        Optional<User> result = Optional.empty();
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()){
            if( byUsername.get().getPassword().equals(encoder.encode(password))){
               result = byUsername;
            }
        }
        return result;
    }
}
