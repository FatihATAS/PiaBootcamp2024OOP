package APITests.Tests;

import APITests.BasePageAPI.PetStoreAPIBasePage;
import APITests.BaseURLs.PetStoreAPIBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PetStoreAPITest extends PetStoreAPIBasePage {


    @Test
    public void testCreatePet() {
        petStore.specification.pathParam("pp1" , "pet");
        Map<String , Object> reqBody = petStore.requestBodyPetStore(petName , petId);
        Response response = given().when().spec(petStore.specification)
                .contentType(ContentType.JSON).body(reqBody).post("/{pp1}");

        response.then().assertThat().
                statusCode(200).
                body("id" , notNullValue(),
                        "name",equalTo(petName));
        int petId = response.jsonPath().getInt("id");
        petStore.setPetId(petId);
    }

    @Test(dependsOnMethods = "testCreatePet")
    public void testGetPetByID() {
        petStore.specification.pathParams("pp1" , "pet" , "pp2" , petId);
        Response response = given().when().spec(petStore.specification).get("/{pp1}/{pp2}");
        response.then().assertThat()
                .statusCode(200)
                .body("id" , equalTo(24),
                        "name" , equalTo(petName));
    }

    @Test (dependsOnMethods = "testCreatePet")
    public void testUpdatePetByID() {
        String url = "https://petstore.swagger.io/v2/pet";
        Map<String , Object> reqBody = petStore.requestBodyPetStore("garip kont" , 24);
        Response response = given().when().contentType(ContentType.JSON)
                .body(reqBody)
                .put(url);
        response.then().assertThat().
                statusCode(200)
                .body("name" , equalTo("garip kont"));
        petName = response.jsonPath().getString("name");
    }

    @Test (dependsOnMethods = "testUpdatePetByID")
    public void testDeletePetByID() {
        petStore.specification.pathParams("pp1" , "pet" , "pp2" , petId);
        Response response = given().when().spec(petStore.specification).delete("/{pp1}/{pp2}");
        response.then().assertThat()
                .statusCode(200)
                .body("code" , equalTo(200) , "message" , equalTo(""+petId));
        /*
        Response Body'den gelen "message" değeri string bir değer döndüğü için
        {""+petId} ile integer olan petId değeri String'e dönüştürüldü
         */
    }

    @Test(priority = 1)
    public void testInvalidCreatePet() {
        String url = "https://petstore.swagger.io/v2/pet";
        Response response = given().when()
                .contentType(ContentType.JSON).post(url);
        response.then().assertThat().
                statusCode(405)
                .body("message" , equalTo("no data"));
    }

    @Test(priority = 1)
    public void testInvalidGetPetByID() {
        petStore.specification.pathParams("pp1" , "pet" , "pp2" , petId);
        Response response = given().when().spec(petStore.specification).get("/{pp1}/{pp2}");
        response.then().assertThat()
                .statusCode(404);
    }

    @Test(priority = 1)
    public void testInvalidUpdatePetByID() {
        /*
        Swagger.io da reqBody gereklidir denilmiştir.
        Ancak bu testte reqBody boş gönderilmesine rağmen
        Response Status Code 404 (Not Found) olarak dönmesi gerekirken
        Response Status Code 200 (Success) olarak dönüştür.
        Dolayısı ile testimiz fail vermektedir.
        */
        String url = "https://petstore.swagger.io/v2/pet";
        Map<String , Object> reqBody = new HashMap<>();
        Response response = given().when().contentType(ContentType.JSON)
                .body(reqBody)
                .put(url);
        response.then().assertThat().statusCode(404);
    }

    @Test(priority = 1) /*
    createPet ve DeletePetByID testleri bittikten sonra bu test
    çalışması gerektiği için en son çalışması için priorty değeri +1 değeri verildi
    */
    public void testInvalidDeletePetByID() {
        petStore.specification.pathParams("pp1" , "pet" , "pp2" , petId);
        Response response = given().when().spec(petStore.specification).delete("/{pp1}/{pp2}");
        response.then().statusCode(404);
    }
}
