package org.entando.demo.customer.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import org.entando.demo.customer.domain.AppUser;
import org.entando.demo.customer.repository.AppUserRepository;
import org.entando.demo.customer.service.KeyCloakService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.entando.domain.AppUser}.
 */
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

    @Value("${keycloak.resource}")
    private String CLIENT_ID;

    @Value("${keycloak.adminClientUsername}")
    private String ADMIN_CLIENT_USERNAME;

    @Value("${keycloak.adminClientPassword}")
    private String ADMIN_CLIENT_PASSWORD;

    private final AppUserRepository appUserRepository;

    private final KeyCloakService keyClockService;

    public AppUserResource(AppUserRepository appUserRepository, KeyCloakService keyClockService) {
        this.appUserRepository = appUserRepository;
        this.keyClockService = keyClockService;
    }

    /**
     * {@code POST  /users} : Create a new appUser.
     *
     * @param appUser the appUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appUser, or with
     * status {@code 400 (Bad Request)} if the appUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody AppUser appUser) throws URISyntaxException {
        int statusId = 0;

        Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl(ADMIN_CLIENT_SERVER_URL)
            .realm(REALM)
            .grantType(OAuth2Constants.PASSWORD)
            .clientId(CLIENT_ID)
            .clientSecret(clientSecret)
//            .username(ADMIN_CLIENT_USERNAME)
//            .password(ADMIN_CLIENT_PASSWORD)
            .build();

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
                .headers(HeaderUtil
                    .createEntityCreationAlert(applicationName, true, ENTITY_NAME, userId))
                .body(userResource.toRepresentation());
        } else if (statusId == 409) {
            /**
             * Status 409 is given when the user we're trying to save on keycloak has the same username or email of another user
             */
            System.out.println("Username==" + appUser.getUsername() + " already present in keycloak");
            return ResponseEntity.status(statusId)
                .headers(HeaderUtil
                    .createFailureAlert(applicationName, true, ENTITY_NAME, String.valueOf(statusId), "Already registered"))
                .body(appUser);
        } else {
            System.out.println("Username==" + appUser.getUsername() + " could not be created in keycloak");
            return ResponseEntity.status(statusId)
                .headers(HeaderUtil
                    .createFailureAlert(applicationName, true, ENTITY_NAME, String.valueOf(statusId), "Forbidden"))
                .body(appUser);
        }

    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<?> getTokenUsingCredentials(@RequestBody UserCredentials userCredentials) {
        String responseToken = null;
        try {
            responseToken = keyClockService.getToken(userCredentials);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseToken, HttpStatus.OK);

    }

//    /**
//     * {@code PUT  /users} : Updates an existing appUser.
//     *
//     * @param appUser the appUser to update.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appUser, or with status
//     * {@code 400 (Bad Request)} if the appUser is not valid, or with status {@code 500 (Internal Server Error)} if the
//     * appUser couldn't be updated.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PutMapping("/users")
//    public ResponseEntity<AppUser> updateUser(@Valid @RequestBody AppUser appUser) throws URISyntaxException {
//        log.debug("REST request to update AppUser : {}", appUser);
//        if (appUser.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
//        }
//        AppUser result = appUserRepository.save(appUser);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, appUser.getId().toString()))
//            .body(result);
//    }

//    /**
//     * {@code GET  /users} : get all the users.
//     *
//     * @param pageable the pagination information.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of users in body.
//     */
//    @GetMapping("/users")
//    public ResponseEntity<List<AppUser>> getAllUsers(Pageable pageable) {
//        log.debug("REST request to get a page of Users");
//        Page<AppUser> page = appUserRepository.findAll(pageable);
//        HttpHeaders headers = PaginationUtil
//            .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

//    /**
//     * {@code GET  /users/:id} : get the "id" appUser.
//     *
//     * @param id the id of the appUser to retrieve.
//     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appUser, or with status {@code
//     * 404 (Not Found)}.
//     */
//    @GetMapping("/users/{id}")
//    public ResponseEntity<AppUser> getUser(@PathVariable Long id) {
//        log.debug("REST request to get AppUser : {}", id);
//        Optional<AppUser> appUser = appUserRepository.findById(id);
//        return ResponseUtil.wrapOrNotFound(appUser);
//    }

//    /**
//     * {@code DELETE  /users/:id} : delete the "id" appUser.
//     *
//     * @param id the id of the appUser to delete.
//     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
//     */
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        log.debug("REST request to delete AppUser : {}", id);
//        appUserRepository.deleteById(id);
//        return ResponseEntity.noContent()
//            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
//    }
}
