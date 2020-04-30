package com.bridgelabz.service;

import com.bridgelabz.model.PersonInformation;

public interface IAddressBook {
    public void addPersonInformation(PersonInformation personInformation, String filePath);

    public void updatePersonData(PersonInformation personInformation,String filePath,String uniqueData);
}
