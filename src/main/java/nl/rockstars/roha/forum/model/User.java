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

}