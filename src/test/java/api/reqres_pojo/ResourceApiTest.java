package api.reqres_pojo;

import api.reqres_pojo.pojo.ResourceData;
import api.reqres_pojo.spec.Specification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static java.util.Collections.sort;

public class ResourceApiTest  extends BaseApiTest{


    @Test
    public void checkThatResourceListSortedTest(){
        Specification.installSpecification(Specification.requestSpec(config.getString("base_url")),Specification.responseSpecSuccess());
        List<ResourceData> resourceData = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ResourceData.class);

        // Получаем актуальный список годов
        List<Integer> actualYears = resourceData.stream().map(ResourceData::getYear).collect(Collectors.toList());
        // Создаем ожидаемый результат, сортируя актуальный список годов
        List<Integer> expectedYears = actualYears.stream().sorted().collect(Collectors.toList());

        Assert.assertEquals(actualYears,expectedYears);


    }
}
