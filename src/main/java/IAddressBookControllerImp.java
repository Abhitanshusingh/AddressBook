import java.util.Scanner;

public class AddressBookControllerImp implements AddressBook {
    Scanner scanner = new Scanner(System.in);

    @Override
    public void askUser() {
        System.out.println("1. Add a person");
        System.out.println("2. Edit");
        System.out.println("3. Delete a person");
        System.out.println("4. Quit");

        int choice = scanner.nextInt();

    }
}