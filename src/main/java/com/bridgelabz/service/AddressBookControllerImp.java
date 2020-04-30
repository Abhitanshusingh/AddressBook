package com.bridgelabz.service;

import com.bridgelabz.model.PersonInformation;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.*;
import java.util.ArrayList;

public class AddressBookControllerImp implements IAddressBook {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void addPersonInformation(PersonInformation personInformation, String filePath) {
        try {
            //Read data
            ArrayList<PersonInformation> data = objectMapper.readValue
                    (new File(filePath), new TypeReference<ArrayList<PersonInformation>>() {
                    });
            data.add(personInformation);
            //Write data
            objectMapper.writeValue(new File(filePath), data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
