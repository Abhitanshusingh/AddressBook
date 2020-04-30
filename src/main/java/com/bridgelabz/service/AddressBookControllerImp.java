package com.bridgelabz.service;

import com.bridgelabz.model.PersonInformation;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AddressBookControllerImp implements IAddressBook {
    ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<PersonInformation> readFileData(String filePath) throws IOException {
        return objectMapper.readValue(new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
        });
    }

    public void writeFileData(ArrayList<PersonInformation> list, String filePath) throws IOException {
        objectMapper.writeValue(new File(filePath), list);
    }

    @Override
    public void addPersonInformation(PersonInformation personInformation, String filePath) {
        try {
            //Read data
            ArrayList<PersonInformation> data = readFileData(filePath);
            data.add(personInformation);
            //Write data
            writeFileData(data, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updatePersonData(PersonInformation personInformation, String filePath, String uniqueData) {
        int indexNumber = 0;
        try {
            ArrayList<PersonInformation> data = readFileData(filePath);
            for (PersonInformation personData : data) {
                if (personData.getPhoneNumber().equals(uniqueData)) {
                    indexNumber = data.indexOf(personData);
                    personData.setFirstName(personInformation.getFirstName());
                    personData.setLastName(personInformation.getLastName());
                    personData.setAddress(personInformation.getAddress());
                    personData.setCity(personInformation.getCity());
                    personData.setState(personInformation.getState());
                    personData.setZip(personInformation.getZip());
                    personData.setPhoneNumber(personInformation.getPhoneNumber());
                }
            }
            writeFileData(data, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexNumber;
    }

    @Override
    public void deletePersonData(String filePath, String uniqueData) {
        try {
            ArrayList<PersonInformation> data = readFileData(filePath);
            PersonInformation removedata = null;
            for (PersonInformation personData : data) {
                if (personData.getPhoneNumber().equals(uniqueData)) {
                    removedata = personData;
                    break;
                }
            }
            data.remove(removedata);
            writeFileData(data, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortPersonDataByFirstName(String filePath) {
        try {
            ArrayList<PersonInformation> data = readFileData(filePath);
            data.sort(Comparator.comparing(PersonInformation::getFirstName));
            writeFileData(data, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortPersonDataByZipCode(String filePath) {
        try {
            ArrayList<PersonInformation> data = readFileData(filePath);
            data.sort(Comparator.comparing(PersonInformation::getZip));
            writeFileData(data, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
