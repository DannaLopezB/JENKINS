package pe.edu.vallegrande.eggs.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class EggProductionTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8086";
    }

    @Test
    public void whenGetAllEggProduction_thenOK() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/egg-production")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    public void whenDeleteEggProductionById_thenOK() {
        int id = 3;
        given()
                .when()
                .delete("/egg-production/{id}", id)
                .then()
                .statusCode(anyOf(is(200), is(204))); // Aceptamos 200 o 204
    }

    @Test
    public void whenRestoreEggProductionById_thenOK() {
        int id = 1;
        given()
                .when()
                .patch("/egg-production/restore/{id}", id)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(id))
                .body("active", equalTo(true));
    }

    @Test
    public void whenCreateEggProduction_thenCreated() {
        // Datos válidos según tu modelo actual (sin hensId)
        String newEggProduction = """
            {
                "quantityEggs": 100,
                "eggsKilo": 10,
                "priceKilo": 8.5,
                "registrationDate": "2025-04-28"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(newEggProduction)
                .when()
                .post("/egg-production")
                .then()
                .statusCode(anyOf(is(200), is(201)))
                .contentType(ContentType.JSON)
                .body("id", notNullValue())
                .body("quantityEggs", equalTo(100))
                .body("eggsKilo", equalTo(10))
                .body("priceKilo", equalTo(8.5f))
                .body("registrationDate", equalTo("2025-04-28"));
    }
}
