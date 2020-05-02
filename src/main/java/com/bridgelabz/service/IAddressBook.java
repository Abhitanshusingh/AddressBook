package com.bridgelabz.service;

import com.bridgelabz.model.PersonInformation;

import java.util.ArrayList;

public interface IAddressBook {
    public void addPersonInformation(PersonInformation personInformation, String filePath);
    /*
     *   This method is used to add
     *   person information in json file.
     */

    public int updatePersonData(PersonInformation personInformation, String filePath, String uniqueData);
    /*
     *   This method is used to update
     *   person information in json file.
     */

    public void deletePersonData(String filePath, String uniqueData);
    /*
     *   This method is used to delete
     *   person information from json file.
     */

    public void sortPersonDataByLastName(String filePath);
    /*
     *   This method is used to sort data by
     *   last name if two person last name
     *   same then it sort first name.
     */

    public void sortPersonDataByZipCode(String filePath);
    /*
     *   This method is used to sort data by
     *   zip code.
     */

    public boolean printPersonEntriesData(String filePath);
    /*
     *   This method is used to print entries
     *   data of json file.
     */

    public boolean createNewAddressBook(String addressBookName);
    /*
     *   This method is used to create new
     *   json file.
     */

    public boolean openExistingAddressBook(String addressBookName);
    /*
     *   This method is used to open existing
     *   data of from address book.
     */

    public boolean saveAddressBook(String filePath, ArrayList<PersonInformation> data);
    /*
     *   This method is used to save data
     *   of address book.
     */

    public boolean saveAsAddressBook(String filePath, PersonInformation personInformation);
    /*
     *   This method is used to save as data
     *   of address book.
     */
    public void quitAddressBook();
    /*
     *   This method is used to exit
     *   from address book.
     */
}
