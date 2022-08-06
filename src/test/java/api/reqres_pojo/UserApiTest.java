package api.reqres_pojo;

import api.reqres_pojo.pojo.UserData;
import api.reqres_pojo.spec.Specification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserApiTest {

    private final static String BASE_URL = "https://reqres.in/";

    // Вариант 2 : с использованием спецификаций библиотеки RestAssured
    @Test
    public void checkAvatarFileNameModernTest(){
        Specification.installSpecification(Specification.requestSpec(BASE_URL),Specification.responseSpecSuccess());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        // Перебор по каждому пользователю
        users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        // Проверяем что все email заканчиваются корректно
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }

    @Test
    public void checkUserByIdTest(){
        Specification.installSpecification(Specification.requestSpec(BASE_URL),Specification.responseSpecSuccess());
        UserData user = given()
                .when()
                .get("api/users/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("data",UserData.class);

        Assert.assertTrue(user.getAvatar().contains(user.getId().toString()));
    }

    @Test
    public void checkUserNotFoundTest(){
        Specification.installSpecification(Specification.requestSpec(BASE_URL),Specification.responseSpec404());
        UserData user = given()
                .when()
                .get("api/users/23")
                .then().log().all()
                .extract().body().jsonPath().getObject("data",UserData.class);
    }


}
