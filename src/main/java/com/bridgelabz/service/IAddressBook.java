package com.bridgelabz.service;

import com.bridgelabz.model.PersonInformation;

import java.io.IOException;

public interface IAddressBook {
    public void addPersonInformation(PersonInformation personInformation, String filePath);

    public int updatePersonData(PersonInformation personInformation,String filePath,String uniqueData);

    public void deletePersonData(PersonInformation personInformation,String filePath,String uniqueData) throws IOException;
}
