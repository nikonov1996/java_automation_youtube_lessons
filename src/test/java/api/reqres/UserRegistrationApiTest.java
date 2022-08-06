package api.reqres;

import api.reqres_pojo.spec.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserRegistrationApiTest extends BaseApiTest{
    @Test
    public void checkUserRegisterSuccessTest(){
        Specification.installSpecification(Specification.requestSpec(config.getString("base_url")),Specification.responseSpecSuccess());
        Map<String,String> user = new HashMap<>();
        user.put("email","eve.holt@reqres.in");
        user.put("password", "pistol");
        // Вариант без Response
        given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("id",equalTo(4))
                .body("token",equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void checkUserRegisterSuccessTest1(){
        Specification.installSpecification(Specification.requestSpec(config.getString("base_url")),Specification.responseSpecSuccess());
        Map<String,String> user = new HashMap<>();
        user.put("email","eve.holt@reqres.in");
        user.put("password", "pistol");
        // Вариант с использованием Response
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("id",equalTo(4))
                .body("token",equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();
        JsonPath actualJson = response.jsonPath();
        int actualId = actualJson.get("id");
        String actualToken = actualJson.get("token");
        Assert.assertEquals(4, actualId);
        Assert.assertEquals("QpwL5tke4Pnpja7X4", actualToken);
    }

    @Test
    public void checkUserRegisterUnsuccessTest(){
        Specification.installSpecification(Specification.requestSpec(config.getString("base_url")),Specification.responseSpec400());
        Map<String,String> user = new HashMap<>();
        user.put("email","sydney@fife");
        given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .body("error",equalTo("Missing password"));
    }

    @Test
    public void checkUserRegisterUnsuccessTest1(){
        Specification.installSpecification(Specification.requestSpec(config.getString("base_url")),Specification.responseSpec400());
        Map<String,String> user = new HashMap<>();
        user.put("email","sydney@fife");

        // Вариант с использованием Response
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath actualJson = response.jsonPath();
        String actualErrorValue = actualJson.get("error");
        Assert.assertEquals("Missing password" , actualErrorValue);
    }
}
