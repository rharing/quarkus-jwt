package nl.rockstars.roha.forum.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import nl.rockstars.roha.forum.model.Role;
import nl.rockstars.roha.forum.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created on 05/02/2023.
 */
@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    /**
     * Adds a new user in the database
     *
     * @param username the user name
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param roles    list of roles
     */
    @Transactional
    public User add(String username, String password, Set<Role> roles) {
        User user = new User(username, password, roles);
        this.persist(user);
        return user;
    }

    /**
     * Adds a new user in the database with default role of user
     *
     * @param username the user name
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     */
    public User add(String username, String password) {
        return this.add(username, password, Collections.singleton(Role.USER));
    }

    public Optional<User> findByUsername(String username) {
        return this.find("username", username).firstResultOptional();
    }
}
