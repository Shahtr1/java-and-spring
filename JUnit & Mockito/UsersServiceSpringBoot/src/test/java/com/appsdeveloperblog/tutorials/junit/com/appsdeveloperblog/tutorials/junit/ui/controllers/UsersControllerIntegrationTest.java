package com.appsdeveloperblog.tutorials.junit.com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.security.SecurityConstants;
import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// To make spring application create and run application context that creates and involves all layers
// (web layer, service layer and data layer) add @SpringBootTest annotation
// It tells the spring boot to look for main application class and it will use it to start spring application context
// All beans will be created
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
// by default mock will be used, it means only beans related to web layer will be created

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
//        properties = {"server.port=8081","hostname=192.168.56.3"})
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
//@TestPropertySource(locations = "/application-test.properties",properties = {"server.port=8081"})
// the port above will have higher property
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersControllerIntegrationTest {

    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String authorizationToken;

    @Test
    void contextLoads(){
        System.out.println("server.port=" + serverPort);
        System.out.println("localServerPort=" + localServerPort);
    }

    @Test
    @DisplayName("User can be created")
    @Order(1)
    void testCreateUser_whenValidDetailsProvided_returnsUserDetails() throws JSONException {
//        Arrange
        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("firstName","Shahrukh");
        userDetailsRequestJson.put("lastName","Tramboo");
        userDetailsRequestJson.put("email","tramboos123@gmail.com");
        userDetailsRequestJson.put("password","shahrukh");
        userDetailsRequestJson.put("repeatPassword","shahrukh");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(userDetailsRequestJson.toString(),httpHeaders);

//        Act
        ResponseEntity<UserRest> createdUserDetailsEntity =  testRestTemplate.postForEntity("/users",
                request, UserRest.class);
        UserRest createdUserDetails = createdUserDetailsEntity.getBody();

//        Assert
        assertEquals(HttpStatus.OK, createdUserDetailsEntity.getStatusCode());
        assertEquals(userDetailsRequestJson.getString("firstName"),
                createdUserDetails.getFirstName(),
                "Returned user's first name seems to be incorrect");
        assertEquals(userDetailsRequestJson.getString("lastName"),
                createdUserDetails.getLastName(),
                "Returned user's last name seems to be incorrect");
        assertEquals(userDetailsRequestJson.getString("email"),
                createdUserDetails.getEmail(),
                "Returned user's email seems to be incorrect");
        assertFalse(createdUserDetails.getUserId().trim().isEmpty(),
                "User id should not be empty");

    }

    @Test
    @DisplayName("GET /users requires JWT")
    @Order(2)
    void testGetUsers_whenMissingJWT_returns403(){
//        Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity httpEntity = new HttpEntity(null,headers);

//        Act
        ResponseEntity<List<UserRest>> response = testRestTemplate.exchange("/users", HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<>() {
                });

//        Assert
        assertEquals(HttpStatus.FORBIDDEN,response.getStatusCode(), "HTTP Status 403 should have been returned");
    }

    @Test
    @DisplayName("/login works")
    @Order(3)
    void testUserLogin_whenValidCredentialsProvided_returnsJWTinAuthorizationHeader() throws JSONException {
//        Arrange
        JSONObject loginCredentials = new JSONObject();
        loginCredentials.put("email","tramboos123@gmail.com");
        loginCredentials.put("password","shahrukh");

        HttpEntity<String> request = new HttpEntity<>(loginCredentials.toString());

//        Act
        ResponseEntity response = testRestTemplate.postForEntity("/users/login",
                request, null);
        authorizationToken = response.getHeaders().getValuesAsList(SecurityConstants.HEADER_STRING).get(0);

//        Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(), "HTTP status code should be 200");
        assertNotNull(authorizationToken,
                "Response should contain Authorization header with JWT");
        assertNotNull(response.getHeaders().getValuesAsList("UserID").get(0),
                "Response should contain UserID in a response header");
    }

    @Test
    @Order(4)
    @DisplayName("GET /users works")
    void testGetUsers_whenValidJWTProvided_returnsUsers(){
//        Arrange
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.setBearerAuth(authorizationToken);

        HttpEntity requestEntity = new HttpEntity(httpHeaders);

//        Act
        ResponseEntity<List<UserRest>> response = testRestTemplate.exchange("/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<>() {
                });

//        Assert
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "HTTP status code should be 200");

        assertTrue(response.getBody().size() == 1,
                "There should be exactly 1 user on the list");

    }

}
