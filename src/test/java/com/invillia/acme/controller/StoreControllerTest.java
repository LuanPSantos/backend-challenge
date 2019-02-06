package com.invillia.acme.controller;

import com.invillia.acme.model.Store;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static com.invillia.acme.InvilliaApplicationTests.APPLICATION_JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_save_a_store() {

        given()
                .contentType(APPLICATION_JSON)
                .body(new Store()
                        .setName("Store")
                        .setAddress("Address")
                )
                .when()
                .post("/stores")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue());
    }

    @Test
    public void should_not_save_a_store_by_validation_error() {

        given()
                .contentType(APPLICATION_JSON)
                .body(new Store()
                        .setAddress("Address")
                )
                .when()
                .post("/stores")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Ocorreu um erro de validação!"));
    }

    @Test
    public void should_update_a_store() {

        Store store = postStore();

        given()
                .contentType(APPLICATION_JSON)
                .body(store
                        .setName("Name")
                        .setAddress("Address 2")
                )
                .when()
                .pathParam("id", store.getId())
                .put("/stores/{id}")
                .then()
                .statusCode(HttpStatus.ACCEPTED.value())
                .body("id", notNullValue())
                .body("id", equalTo(store.getId().intValue()))
                .body("name", equalTo("Name"))
                .body("address", equalTo("Address 2"));
    }

    @Test
    public void should_not_update_a_store_by_validation_error() {
        Store store = postStore();

        given()
                .contentType(APPLICATION_JSON)
                .body(store
                        .setAddress(null)
                )
                .when()
                .pathParam("id", store.getId())
                .put("/stores/{id}")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Ocorreu um erro de validação!"));
    }

    @Test
    public void should_get_stores_by_name() {
        Store store = postStore("Name 1", "Address 1");
         postStore("Name 2", "Address 2");

        given()
                .accept(APPLICATION_JSON)
                .when()
                .queryParam("name", store.getName())
                .queryParam("page", "1")
                .queryParam("size", "1")
                .get("/stores")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content.size", equalTo(1))
                .body("content.get(0).name", equalTo(store.getName()))
                .body("content.get(0).address", equalTo(store.getAddress()));
    }

    @Test
    public void should_get_stores_by_address() {
        Store store = postStore("Name 1", "Address 1");
        postStore("Name 2", "Address 2");

        given()
                .accept(APPLICATION_JSON)
                .when()
                .queryParam("address", store.getAddress())
                .queryParam("page", "1")
                .queryParam("size", "1")
                .get("/stores")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content.size", equalTo(1))
                .body("content.get(0).name", equalTo(store.getName()))
                .body("content.get(0).address", equalTo(store.getAddress()));
    }

    @Test
    public void should_get_stores_paging() {
        postStore("Name 1", "Address 1");
        postStore("Name 2", "Address 2");
        postStore("Name 3", "Address 3");

        given()
                .accept(APPLICATION_JSON)
                .when()
                .queryParam("page", "1")
                .queryParam("size", "3")
                .get("/stores")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("content.size", equalTo(3));
    }

    private Store postStore() {
        return postStore("Name", "Address");
    }

    private Store postStore(String name, String address) {
        return given()
                .contentType(APPLICATION_JSON)
                .body(new Store()
                        .setName(name)
                        .setAddress(address)
                )
                .when()
                .post("/stores")
                .then()
                .extract()
                .as(Store.class);
    }
}
