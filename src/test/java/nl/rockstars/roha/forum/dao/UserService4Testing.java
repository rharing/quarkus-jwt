package nl.rockstars.roha.forum.dao;

import io.quarkus.arc.Priority;
import nl.rockstars.roha.forum.model.Role;
import nl.rockstars.roha.forum.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * Created on 12/02/2023.
 */
@Priority(1)
@Alternative
@ApplicationScoped
public class UserService4Testing extends UserService {
    public UserService4Testing() {
    }

    public UserService4Testing(UserRepository userRepository) {
        super(userRepository);
    }

    @PostConstruct
    @Transactional
    public void init() {
        this.create("user","user");
        this.create("admin","admin", Arrays.asList(Role.USER, Role.ADMIN));
    }
}
