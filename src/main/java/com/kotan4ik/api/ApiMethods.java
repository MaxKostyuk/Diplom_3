package com.kotan4ik.api;


import com.kotan4ik.api.models.User;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class ApiMethods {
    private static final String REGISTER_BASE = "auth/register";
    private static final String LOGIN_BASE = "auth/login";
    private static final String USER_BASE = "auth/user";

    static {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/api/";
    }

    @Step("Create user API request with email = {email}, password = {password}, name = {name}")
    public static Response createUser(String email, String password, String name) {
        User user = new User(email, password, name);

        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .post(REGISTER_BASE);
    }

    @Step("Logging in API request for user with email = {email}, password = {password} and name = {name}")
    public static Response loginUser(String email, String password, String name) {
        User user = new User(email, password, name);

        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_BASE);
    }

    @Step("Delete user API request for user with token = {token}")
    public static Response deleteUser(String token) {
        return RestAssured.given()
                .auth()
                .oauth2(token)
                .delete(USER_BASE);
    }

    @Step("Parsing response to get token")
    public static String getTokenFromResponse(Response response) {
        String tokenString = response.path("accessToken").toString();
        return tokenString.split(" ")[1];
    }

    @Step("Deleting user by name = {name}, email = {email} and password = {password}")
    public static void deleteUserByItsData(String name, String email, String password) {
        Response response = loginUser(email, password, name);
        String token = getTokenFromResponse(response);
        deleteUser(token);
    }

    public static boolean userExists(String name, String email, String password) {
        User user = new User(email, password, name);

        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .post(LOGIN_BASE)
                .path("success");
    }
}
