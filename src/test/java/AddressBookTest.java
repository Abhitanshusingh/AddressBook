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
    public void SetUp() {
        addressBookControllerImp = new AddressBookControllerImp();
        objectMapper = new ObjectMapper();
        personInformation = new PersonInformation();
    }

    @Test
    public void givenPersonalInformation_whenStoreInFile_shouldReturnTrue() {
        try {
            PersonInformation personInformation = new PersonInformation("Abhitanshu", "Baghel", "TT nagar", "Shahdol", "Madhya Pradesh", "484110", "7000458100");
            addressBookControllerImp.addPersonInformation(personInformation, filePath);
            ArrayList<PersonInformation> data = objectMapper
                    .readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                    });
            Assert.assertEquals(personInformation.getPhoneNumber(), data.get(data.size() - 1).getPhoneNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonalInformation_whenUpdateData_shouldReturnTrue() {
        try {
            String uniqueData = "7000458100";
            PersonInformation personInformation = new PersonInformation("Laksh", "Mehta", "BTM 2nd stage", "Bangalore", "Karnataka", "746061", "7134329292");
            int indexNumber = addressBookControllerImp.updatePersonData(personInformation, filePath, uniqueData);
            ArrayList<PersonInformation> data = objectMapper
                    .readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                    });
            Assert.assertEquals(personInformation.getFirstName(), data.get(indexNumber).getFirstName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenPersonInformation_whenDeleteData_shouldRetunTrue() throws IOException {
        String uniqueData = "7000458100";
        ArrayList<PersonInformation> beforeDeletedData = objectMapper
                .readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                });
        int beforeDeletedSize = beforeDeletedData.size();
        addressBookControllerImp.deletePersonData(filePath, uniqueData);
        ArrayList<PersonInformation> afterDeletedData = objectMapper
                .readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                });
        int afterDeletedSize = afterDeletedData.size() + 1;
        Assert.assertEquals(beforeDeletedSize, afterDeletedSize);
    }

    @Test
    public void givenPersonInformation_whenSortedData_shouldReturnTrue() throws IOException {
        PersonInformation personInformation1 = new PersonInformation("Bhanu", "Baba", "BTM 2nd stage", "Bangalore", "Karnataka", "746061", "7134329292");
        PersonInformation personInformation2 = new PersonInformation("Modi", "Bali", "BTM 2nd stage", "Bangalore", "Karnataka", "746061", "7134329292");
        PersonInformation personInformation3 = new PersonInformation("Aayush", "sahu", "BTM 2nd stage", "Bangalore", "Karnataka", "746061", "7134329292");

        addressBookControllerImp.addPersonInformation(personInformation1, filePath);
        addressBookControllerImp.addPersonInformation(personInformation2, filePath);
        addressBookControllerImp.addPersonInformation(personInformation3, filePath);
        addressBookControllerImp.sortPersonData(filePath);

        ArrayList<PersonInformation> data = objectMapper
                .readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                });
        Assert.assertEquals("Aayush", data.get(0).getFirstName());
        Assert.assertEquals("Modi", data.get(data.size() - 1).getFirstName());
    }
}
