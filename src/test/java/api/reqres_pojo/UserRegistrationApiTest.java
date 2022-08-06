package api.reqres_pojo;

import api.reqres_pojo.pojo.UserFailRegisterData;
import api.reqres_pojo.pojo.UserRegisterRequestData;
import api.reqres_pojo.pojo.UserSuccessRegisterData;
import api.reqres_pojo.spec.Specification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserRegistrationApiTest {
    private final static String BASE_URL = "https://reqres.in/";

    @Test
    public void checkUserSuccessRegistrationTest(){
        Specification.installSpecification(Specification.requestSpec(BASE_URL),Specification.responseSpecSuccess());
        UserSuccessRegisterData expectedRegisterResponse = new UserSuccessRegisterData(4,"QpwL5tke4Pnpja7X4");
        UserRegisterRequestData userRegisterRequestData = new UserRegisterRequestData("eve.holt@reqres.in", "pistol");

        UserSuccessRegisterData actualRegisterResponse = given()
                .body(userRegisterRequestData)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UserSuccessRegisterData.class);

        Assert.assertNotNull(actualRegisterResponse);
        Assert.assertEquals(expectedRegisterResponse,actualRegisterResponse);
    }

    @Test
    public void checkUserUnsuccessRegistrationTest(){
        Specification.installSpecification(Specification.requestSpec(BASE_URL),Specification.responseSpec400());
        UserRegisterRequestData userRegisterRequestData = new UserRegisterRequestData("sydney@fife","");
        UserFailRegisterData expectedFailResponse = new UserFailRegisterData("Missing password");

        UserFailRegisterData actualFailResponse = given()
                .body(userRegisterRequestData)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UserFailRegisterData.class);

        Assert.assertEquals(expectedFailResponse,actualFailResponse);
    }
}
