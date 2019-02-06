package com.invillia.acme.util;

import com.invillia.acme.model.Store;

import static com.invillia.acme.InvilliaApplicationTests.APPLICATION_JSON;
import static io.restassured.RestAssured.given;

public class StoreUtil {

    public static Store postStore() {
        return postStore("Name", "Address");
    }

    public static Store postStore(String name, String address) {
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
