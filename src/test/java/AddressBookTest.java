import com.bridgelabz.model.PersonInformation;
import com.bridgelabz.utility.AddressBookControllerImp;
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
    public void givenPersonInformation_whenSortedDataByFirstName_shouldReturnTrue() throws IOException {
        PersonInformation personInformation1 = new PersonInformation("Bhanu", "Baba", "BTM 2nd stage", "Bangalore", "Karnataka", "746061", "7134329292");
        PersonInformation personInformation2 = new PersonInformation("Modi", "Bali", "BTM 1nd stage", "Bangalore", "Karnataka", "707121", "9494938403");
        PersonInformation personInformation3 = new PersonInformation("Aayush", "sahu", "Jaya Nagar", "Bangalore", "Karnataka", "765051", "9394958568");

        addressBookControllerImp.addPersonInformation(personInformation1, filePath);
        addressBookControllerImp.addPersonInformation(personInformation2, filePath);
        addressBookControllerImp.addPersonInformation(personInformation3, filePath);
        addressBookControllerImp.sortPersonDataByFirstName(filePath);

        ArrayList<PersonInformation> data = objectMapper
                .readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                });
        Assert.assertEquals("Aayush", data.get(0).getFirstName());
        Assert.assertEquals("Modi", data.get(data.size() - 1).getFirstName());
    }

    @Test
    public void givenPersonInformation_whenSortedDataByZipCode_shouldReturnTrue() throws IOException {
        PersonInformation personInformation1 = new PersonInformation("Himanshu", "Baghel", "MP Nagar", "Madhya Pradesh", "Bhopal", "741001", "7134329292");
        PersonInformation personInformation2 = new PersonInformation("Abhishek", "Baghel", "Vijay Nagar", "Madhya Pradesh", "Indore", "899091", "7134329292");
        PersonInformation personInformation3 = new PersonInformation("Manas", "Parihar", "Indrapuri", "Madhya Pradesh", "Bhopal", "632311", "7134329292");

        addressBookControllerImp.addPersonInformation(personInformation1, filePath);
        addressBookControllerImp.addPersonInformation(personInformation2, filePath);
        addressBookControllerImp.addPersonInformation(personInformation3, filePath);
        addressBookControllerImp.sortPersonDataByZipCode(filePath);

        ArrayList<PersonInformation> data = objectMapper
                .readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                });
        Assert.assertEquals("632311", data.get(0).getZip());
        Assert.assertEquals("899091", data.get(data.size() - 1).getZip());
    }

    @Test
    public void givenPersonInformation_whenPrintEntriesData_shouldReturnTrue() {
        boolean isPrinted = addressBookControllerImp.printPersonEntriesData(filePath);
        Assert.assertTrue(isPrinted);
    }

    @Test
    public void givenPersonInformation_whenCreateNewAddressBook_shouldReturnTrue() {
        String addressBookName = "Abhitanshu";
        boolean isFileCreated = addressBookControllerImp.createNewAddressBook(filePath, addressBookName);
        Assert.assertTrue(isFileCreated);
    }

    @Test
    public void givenPersonInformation_whenOpenExistingAddressBook_shouldReturnTrue() {
        String addressBookName = "Abhitanshu";
        boolean isFileExist = addressBookControllerImp.openExistingAddressBook(filePath, addressBookName);
        Assert.assertTrue(isFileExist);
    }

    @Test
    public void givenPersonInformation_whenAddressBookNotExisting_shouldReturnFalse() {
        String addressBookName = "Abhi";
        boolean isFileExist = addressBookControllerImp.openExistingAddressBook(filePath, addressBookName);
        Assert.assertFalse(isFileExist);
    }

    @Test
    public void givenPersonInformation_whenOpenExistingAddressBook_shouldReturnTrue1() throws IOException {
        String addressBookName = "./src/main/resources/Abhitanshu.json";
        boolean isFileExist = addressBookControllerImp.openExistingAddressBook(addressBookName, addressBookName);
        ArrayList<PersonInformation> data = objectMapper
                .readValue(new File(addressBookName), new TypeReference<ArrayList<PersonInformation>>() {
                });
        Assert.assertEquals("Baghel",data.get(0).getLastName());
    }
}
