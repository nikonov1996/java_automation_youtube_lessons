package api.reqres;

import api.reqres.spec.Specification;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.json.Json;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UsersApiTest extends BaseApiTest{

    @Test
    public void checkAvatarFileNameTest(){
        Specification.installSpecification(Specification.requestSpec(config.getString("base_url")),Specification.responseSpecSuccess());
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page",equalTo(2))
                .body("data.id",notNullValue())
                .body("data.email",notNullValue())
                .body("data.first_name",notNullValue())
                .body("data.last_name",notNullValue())
                .body("data.avatar",notNullValue())
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> actualEmails = jsonPath.get("data.email");
        List<Integer> actualIds = jsonPath.get("data.id");
        List<String> actualAvatars = jsonPath.get("data.avatar");

        for (int i = 0; i < actualIds.size(); i++) {
            Assert.assertTrue(actualAvatars.get(i).contains(actualIds.get(i).toString()));
        }

        Assert.assertTrue(actualEmails.stream().allMatch(x->x.endsWith("@reqres.in")));

    }


}
