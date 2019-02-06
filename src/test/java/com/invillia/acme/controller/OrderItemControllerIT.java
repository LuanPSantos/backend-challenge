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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderItemControllerIT {

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void should_save_a_order_item() {

        given()
                .contentType(APPLICATION_JSON)
                .body(new OrderItem()
                        .setDescription("Description")
                        .setQuantity(5)
                        .setUnitPrice(10d)
                )
                .when()
                .post("/order-items")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("description", equalTo("Description"))
                .body("quantity", equalTo(5))
                .body("unitPrice", equalTo(10.0f));
    }

    @Test
    public void should_not_save_a_order_item_by_validation_error() {

        given()
                .contentType(APPLICATION_JSON)
                .body(new OrderItem()
                        .setQuantity(5)
                        .setUnitPrice(10d)
                )
                .when()
                .post("/order-items")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("message", equalTo("Ocorreu um erro de validação!"));
    }
}
