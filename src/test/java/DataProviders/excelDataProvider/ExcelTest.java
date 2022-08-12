package DataProviders.excelDataProvider;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ExcelTest {


    @Test(dataProvider = "usersFromXlsx",dataProviderClass = ExcelDataProviders.class)
    public void test(String id,String name){
        System.out.println("Hello " + name + " with id " + id);
    }

    @Test(dataProvider = "dataFromXlsx",dataProviderClass = ExcelDataProviders.class)
    public void checkLoginSuccessTest(String email, String password, String expectedToken){
        loginDataPojo user = new loginDataPojo().withEmail(email).withPassword(password);
        Response response = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String actualToken = jsonPath.getString("token");
        Assert.assertEquals(actualToken,expectedToken);

    }
}
