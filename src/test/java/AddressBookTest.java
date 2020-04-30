import com.bridgelabz.model.PersonInformation;
import com.bridgelabz.service.AddressBookControllerImp;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddressBookTest {

    AddressBookControllerImp addressBookControllerImp;
    ObjectMapper objectMapper;
    PersonInformation personInformation;
    private static final String filePath = "./src/main/resources/AddressBookData.json";
    @Before
    public void SetUp(){
        addressBookControllerImp = new AddressBookControllerImp();
        objectMapper = new ObjectMapper();
        personInformation = new PersonInformation();
    }

    @Test
    public void givenPersonalInformation_whenStoreInFile_shouldReturnTrue(){
        try {
            PersonInformation personInformation = new PersonInformation
                    ("Abhitanshu","Baghel","Shahdol","Madhya Pradesh",484110L,7000458100L);
            addressBookControllerImp.addPersonInformation(personInformation,filePath);
            ArrayList<PersonInformation> data = objectMapper.readValue
                    (new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
            });
            Assert.assertEquals(personInformation.getPhoneNumber(), data.get(1).getPhoneNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}