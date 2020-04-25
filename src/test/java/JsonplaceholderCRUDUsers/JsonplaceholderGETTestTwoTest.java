package JsonplaceholderCRUDUsers;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

//GIVEN - konfiguracja
//WHEN - wysyłanie requestu
//THEN - asercje

public class JsonplaceholderGETTestTwoTest extends JsonplaceholderCommon {

    @Test
    public void jsonplaceholderReadAllUsers() {

        Response response = given()
                .when()
                .get(BASE_URL + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println(response.asString());

        JsonPath json = response.jsonPath();
        List<String> names = json.getList("name");
        assertEquals(10, names.size());

//        names.stream()
//                .forEach(System.out::println);
    }

    @Test
    public void jsonplaceholderReadOneUser() {
        given()
                .when()
                .get(BASE_URL + USERS + "/1")
                .then()
                .statusCode(HttpStatus.SC_OK)
//                .body("name", Matchers.equalTo("Leanne Graham"))
                .body("name", equalTo("Leanne Graham"))
                .body("username", equalTo("Bret"))
                .body("email", equalTo("Sincere@april.biz"))
                .body("address.street", equalTo("Kulas Light"));
//
//        JsonPath json = response.jsonPath();
//
//        Assertions.assertEquals("Leanne Graham", json.get("name"));
//        Assertions.assertEquals("Bret", json.get("username"));
//        Assertions.assertEquals("Sincere@april.biz", json.get("email"));
//        Assertions.assertEquals("Kulas Light", json.get("address.street"));
//
//
//        System.out.println(response.asString());
    }

    @Test
    public void jsonplaceholderReadOneUserWithPathVariable() {

        Response response = given()
                .pathParam("userId", 1)
                .when()
                .get(BASE_URL + USERS + "/{userId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

//      Assertions.assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Leanne Graham", json.get("name"));
        assertEquals("Bret", json.get("username"));
        assertEquals("Sincere@april.biz", json.get("email"));
        assertEquals("Kulas Light", json.get("address.street"));
    }

    @Test
    public void jsonplaceholderReadAllUsersWithQueryParams() {
        Response response = given()
                .queryParam("username", "Bret")
                .when()
                .get(BASE_URL + USERS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        JsonPath json = response.jsonPath();

        assertEquals("Leanne Graham", json.getList("name").get(0));
        assertEquals("Bret", json.getList("username").get(0));
        assertEquals("Sincere@april.biz", json.getList("email").get(0));
        assertEquals("Kulas Light", json.getList("address.street").get(0));
    }
}
