package APITests.BaseURLs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utilities.ConfigReader;

import java.util.HashMap;
import java.util.Map;


public class PetStoreAPIBaseUrl {
    public RequestSpecification specification;
    private int petId = Integer.parseInt(ConfigReader.getProperties("petId"));
    private static String petName = ConfigReader.getProperties("petName");
    public PetStoreAPIBaseUrl(){
        specification = new RequestSpecBuilder().setBaseUri(ConfigReader.getProperties("baseUrlPetStore")).build();
    }
    public Map<String , Object> requestBodyPetStore(String name , int id){
        HashMap<String , Object> reqBody = new HashMap<>();
        reqBody.put("id" , id);
        reqBody.put("name" , name);
        return reqBody;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}


