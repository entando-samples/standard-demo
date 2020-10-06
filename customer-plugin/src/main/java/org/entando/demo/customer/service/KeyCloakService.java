package org.entando.demo.customer.service;

import com.entando.domain.UserCredentials;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyCloakService {

	@Value("${keycloak.credentials.secret}")
	private String SECRETKEY;

	@Value("${keycloak.resource}")
	private String CLIENTID;

	@Value("${keycloak.auth-server-url}")
	private String AUTHURL;

	@Value("${keycloak.realm}")
	private String REALM;

	public String getToken(UserCredentials userCredentials) {
		String responseToken = null;
		try {
			String username = userCredentials.getUsername();
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("grant_type", "password"));
			urlParameters.add(new BasicNameValuePair("client_id", CLIENTID));
			urlParameters.add(new BasicNameValuePair("username", username));
			urlParameters.add(new BasicNameValuePair("password", userCredentials.getPassword()));
			urlParameters.add(new BasicNameValuePair("client_secret", SECRETKEY));

			responseToken = sendPost(urlParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseToken;
	}

	public String getByRefreshToken(String refreshToken) {
		String responseToken = null;
		try {
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("grant_type", "refresh_token"));
			urlParameters.add(new BasicNameValuePair("client_id", CLIENTID));
			urlParameters.add(new BasicNameValuePair("refresh_token", refreshToken));
			urlParameters.add(new BasicNameValuePair("client_secret", SECRETKEY));

			responseToken = sendPost(urlParameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseToken;
	}

	// after logout user from the keycloak system. No new access token will be
	// issued.
//	public void logoutUser(String userId) {
//
//		UsersResource userRessource = getKeycloakUserResource();
//
//		userRessource.get(userId).logout();
//
//	}

	// Reset passowrd
//	public void resetPassword(String newPassword, String userId) {
//
//		UsersResource userResource = getKeycloakUserResource();
//
//		// Define password credential
//		CredentialRepresentation passwordCred = new CredentialRepresentation();
//		passwordCred.setTemporary(false);
//		passwordCred.setType(CredentialRepresentation.PASSWORD);
//		passwordCred.setValue(newPassword.toString().trim());
//
//		// Set password credential
//		userResource.get(userId).resetPassword(passwordCred);
//
//	}

//	private UsersResource getKeycloakUserResource() {
//
//		Keycloak kc = KeycloakBuilder.builder().serverUrl(AUTHURL).realm("master").username("admin").password("admin")
//				.clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
//				.build();
//
//		RealmResource realmResource = kc.realm(REALM);
//		UsersResource userRessource = realmResource.users();
//
//		return userRessource;
//	}

//	private RealmResource getRealmResource() {
//
//		Keycloak kc = KeycloakBuilder.builder().serverUrl(AUTHURL).realm("master").username("admin").password("admin")
//				.clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
//				.build();
//
//		RealmResource realmResource = kc.realm(REALM);
//
//		return realmResource;
//
//	}

	private String sendPost(List<NameValuePair> urlParameters) throws Exception {

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(AUTHURL + "/realms/" + REALM + "/protocol/openid-connect/token");

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();

	}

}
