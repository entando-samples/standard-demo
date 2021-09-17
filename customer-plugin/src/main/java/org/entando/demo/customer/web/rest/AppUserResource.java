package org.entando.demo.customer.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.ws.rs.core.Response;
import org.entando.demo.customer.domain.AppUser;
import org.entando.demo.customer.web.response.ForgotPasswordResponse;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AppUserResource {

	private final Logger log = LoggerFactory.getLogger(AppUserResource.class);

	private static final String ENTITY_NAME = "signupAppUser";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	@Value("${keycloak.realm}")
	private String REALM;

	@Value("${keycloak.auth-server-url}")
	private String ADMIN_CLIENT_SERVER_URL;

	@Value("${keycloak.credentials.clientid}")
	private String CLIENT_ID;

	@Value("${keycloak.credentials.secret}")
	private String CLIENT_SECRET;

	private static String EMAIL_MESSAGE_SUCCESS = "Email has been sent successfully";
	private static String EMAIL_MESSAGE_ERROR = "User does not exist with given email";

	public AppUserResource() {

	}

	/**
	 * {@code GET  /reset-password} : reset password for existing user.
	 *
	 * @param email Id of existing user.
	 * @return the {@link ForgotPasswordResponse} with status {@code 200 (Created)} and with
	 *         ForgotPasswordResponse, or with status {@code 404 (Bad Request)} if the
	 *         User does not exist.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@CrossOrigin(origins = "*")
	@GetMapping("/reset-password")
	public ForgotPasswordResponse resetPassword(@Valid @RequestParam String email) throws URISyntaxException {
		/**
		 * making connection with Keyclock with provided credentials
		 * */
		Keycloak keycloak = KeycloakBuilder.builder().serverUrl(ADMIN_CLIENT_SERVER_URL).realm(REALM)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS).clientId(CLIENT_ID).clientSecret(CLIENT_SECRET).build();
		// making connection with keycloack REALM 
		RealmResource realmResource = keycloak.realm(REALM);

		UsersResource usersRessource = realmResource.users();
		
		//Getting all list of users from keycloack realm
		List<UserRepresentation> userAll = usersRessource.list();

		// filtering user with given email id
		List<UserRepresentation> user = userAll.stream()
				.filter((usr) -> (usr.getEmail() != null && usr.getEmail().equalsIgnoreCase(email)))
				.collect(Collectors.toList());  

		ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
		
		if (!user.isEmpty() ) {
			// sending reset password mail 
			usersRessource.get(user.get(0).getId()).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
			forgotPasswordResponse.setStatus(1);
			forgotPasswordResponse.setMessage(EMAIL_MESSAGE_SUCCESS);
		} else {
			forgotPasswordResponse.setStatus(0);
			forgotPasswordResponse.setMessage(EMAIL_MESSAGE_ERROR);
		}

		return forgotPasswordResponse;
	}

	/**
	 * {@code POST  /users} : Create a new appUser.
	 *
	 * @param appUser the appUser to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new appUser, or with status {@code 400 (Bad Request)} if the
	 *         appUser has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@CrossOrigin(origins = "*")
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody AppUser appUser) throws URISyntaxException {
		int statusId = 0;
		
		Keycloak keycloak = KeycloakBuilder.builder().serverUrl(ADMIN_CLIENT_SERVER_URL).realm(REALM)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS).clientId(CLIENT_ID).clientSecret(CLIENT_SECRET).build();

		UserRepresentation user = new UserRepresentation();
		user.setEnabled(true);
		user.setUsername(appUser.getUsername());
		user.setFirstName(appUser.getFirstname());
		user.setLastName(appUser.getLastname());
		user.setEmail(appUser.getEmail());
//        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

		// Get realm
		RealmResource realmResource = keycloak.realm(REALM);
		UsersResource usersRessource = realmResource.users();

		// Create user (requires manage-users role)
		Response response = usersRessource.create(user);
		System.out.println("Response: " + response.getStatusInfo());
		System.out.println(response.getLocation());
		statusId = response.getStatus();
		String userId = "0";

		if (statusId == 201) {
			userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
			System.out.printf("User created with userId: %s%n", userId);

			// Get realm role "tester" (requires view-realm role)
//        RoleRepresentation testerRealmRole = realmResource.roles().get("manage-users").toRepresentation();

			// Assign realm role tester to user
//        userRessource.get(userId).roles().realmLevel().add(Arrays.asList(testerRealmRole));

			// Get client
//        ClientRepresentation app1Client = realmResource.clients().findByClientId("app-javaee-petclinic").get(0);

			// Get client level role (requires view-clients role)
//        RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()).roles().get("user").toRepresentation();

			// Assign client level role to user
//        userRessource.get(userId).roles().clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));

			// Define password credential
			CredentialRepresentation passwordCred = new CredentialRepresentation();
			passwordCred.setTemporary(false);
			passwordCred.setType(CredentialRepresentation.PASSWORD);
			passwordCred.setValue(appUser.getPassword());

			// Set password credential
			UserResource userResource = usersRessource.get(userId);
			userResource.resetPassword(passwordCred);

			return ResponseEntity.created(new URI("/api/users/" + userId))
					.headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, userId))
					.body(userResource.toRepresentation());
		} else if (statusId == 409) {
			/**
			 * Status 409 is given when the user we're trying to save on keycloak has the
			 * same username or email of another user
			 */
			System.out.println("Username==" + appUser.getUsername() + " already present in keycloak");
			return ResponseEntity.status(statusId).headers(HeaderUtil.createFailureAlert(applicationName, true,
					ENTITY_NAME, String.valueOf(statusId), "Already registered")).body(appUser);
		} else {
			System.out.println("Username==" + appUser.getUsername() + " could not be created in keycloak");
			return ResponseEntity.status(statusId).headers(HeaderUtil.createFailureAlert(applicationName, true,
					ENTITY_NAME, String.valueOf(statusId), "Forbidden")).body(appUser);
		}

	}

}
