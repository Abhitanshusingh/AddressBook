package com.bridgelabz.service;

import com.bridgelabz.model.PersonInformation;

public interface IAddressBook {
    public void addPersonInformation(PersonInformation personInformation, String filePath);

    public int updatePersonData(PersonInformation personInformation, String filePath, String uniqueData);

    public void deletePersonData(String filePath, String uniqueData);

    public void sortPersonDataByFirstName(String filePath);

    public void sortPersonDataByZipCode(String filePath);

    public boolean printPersonEntriesData(String filePath);

    public boolean createNewAddressBook(String filepath, String addressBookName);
}
