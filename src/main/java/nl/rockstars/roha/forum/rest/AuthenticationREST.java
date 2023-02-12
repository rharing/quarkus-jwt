package nl.rockstars.roha.forum.rest;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import nl.rockstars.roha.forum.dao.UserRepository;
import nl.rockstars.roha.forum.model.AuthRequest;
import nl.rockstars.roha.forum.model.AuthResponse;
import nl.rockstars.roha.forum.model.User;
import nl.rockstars.roha.forum.service.UserService;
import nl.rockstars.roha.forum.util.PBKDF2Encoder;
import nl.rockstars.roha.forum.util.TokenUtils;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

/**
 *
 * @author ard333
 */
@Path("/auth")
public class AuthenticationREST {

	@Inject
	PBKDF2Encoder passwordEncoder;

	@Inject
	UserService userService;
	@ConfigProperty(name = "nl.rockstars.roha.forum.quarkusjwt.jwt.duration") public Long duration;
	@ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

	@PermitAll
	@POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
	public Response login(AuthRequest authRequest) {
		Optional<User> optionalUser = userService.login(authRequest.username, authRequest.password);
		if (optionalUser.isPresent()) {
			try {
				User u = optionalUser.get();
				return Response.ok(new AuthResponse(TokenUtils.generateToken(u.getUsername(), u.getRoles(), duration, issuer))).build();
			} catch (Exception e) {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} else {
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
