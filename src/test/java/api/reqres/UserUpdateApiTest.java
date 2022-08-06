package api.reqres;

import api.reqres.pojo.UserTimeData;
import api.reqres.pojo.UserTimeResponseData;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;

import static io.restassured.RestAssured.given;

public class UserUpdateApiTest extends BaseApiTest{

    @Test
    public void checkUserUpdateTime(){
        Specification.installSpecification(Specification.requestSpec(config.getString("base_url")),Specification.responseSpecSuccess());
        UserTimeData user = new UserTimeData("morpheus","zion resident");
        UserTimeResponseData actualResponse = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponseData.class);
        // Регулярное выражение, которое берет 8 символов с конца
        String regex = "(.{8})$";
        // Получаем текущее время и удаляем 11 символов с конца
        String expectedTime = Clock.systemUTC().instant().toString().replaceAll("(.{11})$","");
        // В значении apdatedAt ответа сервера, удаляем 8 символов с конца
        String actualTime = actualResponse.getUpdateAt().replaceAll(regex,"");
        // Сравниваем полученные значения времени
        Assert.assertEquals(expectedTime,actualTime);

        //Данный тест плавающий, чтобы не падал обрезал до минут. Но в секундах время в ответе и время с сервера не совпадают часто
    }
}
