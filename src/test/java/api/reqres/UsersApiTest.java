package api.reqres;

import api.reqres.pojo.UserData;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class UsersApiTest {

    private final static String BASE_URL = "https://reqres.in/";

    // Вариант 1 : без Specifications
    @Test
    public void checkAvatarFileNameTest(){
        List<UserData> users = given()
                .when()
                .contentType(ContentType.JSON)
                .get(BASE_URL + "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        // Перебор по каждому пользователю
        users.forEach(x->Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));

        // Проверяем что все email заканчиваются корректно
        Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));

        // Получаем списки с ссылкой на изображение и с айдишниками
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());

        // В цикле проверяем что в ссылке на изображение присутствует коректный айдишник пользователя
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }

    }


}
