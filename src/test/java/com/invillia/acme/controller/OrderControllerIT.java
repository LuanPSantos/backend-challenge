package com.invillia.acme.controller;

import com.invillia.acme.model.Order;
import com.invillia.acme.model.OrderItem;
import com.invillia.acme.model.Store;
import com.invillia.acme.model.constant.OrderStatus;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.invillia.acme.InvilliaApplicationTests.APPLICATION_JSON;
import static com.invillia.acme.util.OrderItemUtil.postOrderItem;
import static com.invillia.acme.util.StoreUtil.postStore;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIT {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }


    @Test
    public void should_save_a_order_with_items() {

        Store store = postStore();
        OrderItem item1 = postOrderItem();
        OrderItem item2 = postOrderItem();

        given()
                .contentType(APPLICATION_JSON)
                .body(new Order()
                        .setAddress("Address")
                        .setStatus(OrderStatus.PENDING)
                        .setStore(store)
                        .setItems(Arrays.asList(item1,item2))
                )
                .when()
                .post("/orders")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("address", equalTo("Address"))
                .body("confirmationDate", nullValue())
                .body("status", equalTo("PENDING"))
                .body("items.size", equalTo(2))
                .body("store.name", equalTo(store.getName()));
    }

    @Test
    public void should_not_save_a_order_with_items_by_validation_error() {

        Store store = postStore();
        OrderItem item1 = postOrderItem();
        OrderItem item2 = postOrderItem();

        given()
                .contentType(APPLICATION_JSON)
                .body(new Order()
                        .setStatus(OrderStatus.PENDING)
                        .setStore(store)
                        .setItems(Arrays.asList(item1,item2))
                )
                .when()
                .post("/orders")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Ocorreu um erro de validação!"));
    }
}
