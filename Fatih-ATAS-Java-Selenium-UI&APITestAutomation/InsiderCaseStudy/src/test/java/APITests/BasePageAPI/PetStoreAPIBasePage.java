package APITests.BasePageAPI;

import APITests.BaseURLs.PetStoreAPIBaseUrl;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class PetStoreAPIBasePage {
    public PetStoreAPIBaseUrl petStore;
    public static String petName;
    public int petId;
    @BeforeClass
    public void beforeClass() {
        petStore = new PetStoreAPIBaseUrl();
        petName = petStore.getPetName();
        petId = petStore.getPetId();
    }
}
