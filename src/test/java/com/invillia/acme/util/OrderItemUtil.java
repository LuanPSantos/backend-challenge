package com.invillia.acme.util;

import com.invillia.acme.model.OrderItem;

import static com.invillia.acme.InvilliaApplicationTests.APPLICATION_JSON;
import static io.restassured.RestAssured.given;

public class OrderItemUtil {

    public static OrderItem postOrderItem() {
        return postOrderItem("Description", 5, 10d);
    }

    public static OrderItem postOrderItem(String description, Integer quantity, Double unitPrice) {
        return given()
                .contentType(APPLICATION_JSON)
                .body(new OrderItem()
                        .setDescription(description)
                        .setQuantity(quantity)
                        .setUnitPrice(unitPrice)
                )
                .when()
                .post("/order-items")
                .then()
                .extract()
                .as(OrderItem.class);
    }
}
