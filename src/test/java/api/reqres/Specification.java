package api.reqres;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    public static RequestSpecification requestSpec( String base_url){
        return new RequestSpecBuilder()
                .setBaseUri(base_url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecSuccess(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpec404(){
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

    public static ResponseSpecification responseSpec400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    public static void installSpecification(RequestSpecification reqSpec,ResponseSpecification resSpec){
        RestAssured.requestSpecification = reqSpec;
        RestAssured.responseSpecification = resSpec;
    }
}
