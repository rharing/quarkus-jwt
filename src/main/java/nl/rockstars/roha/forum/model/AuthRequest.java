package nl.rockstars.roha.forum.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ard333
 */
@NoArgsConstructor @AllArgsConstructor @ToString
public class  AuthRequest {
	
	public String username;
	public String password;

}
