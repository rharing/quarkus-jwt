package nl.rockstars.roha.forum.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 *
 * @author ard333
 */
@Entity
@Table(name = "forum_user")
 @ToString @EqualsAndHashCode(callSuper = false)
@Data
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles;

	public User() {
	}

	public User(String username, String password, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	// this is just an example, you can load the user from the database (via PanacheEntityBase)
	public static User findByUsername(String username) {

		//if using Panache pattern (extends or PanacheEntity PanacheEntityBase)
		//return find("username", username).firstResult();

		Map<String, User> data = new HashMap<>();

		//username:passwowrd -> user:user
		data.put("user", new User("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", Collections.singleton(Role.USER)));

		//username:passwowrd -> admin:admin
		data.put("admin", new User("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", Collections.singleton(Role.ADMIN)));

		if (data.containsKey(username)) {
			return data.get(username);
		} else {
			return null;
		}
	}

}