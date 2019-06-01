/*
* Created By Mohsen Fallahnejad
* Telegram ID: @Mohsen_js
* Instagram: Mohsen.coder
* License: MIT
* */

package mohsen.coder;

import mohsen.coder.Contact;
import mohsen.coder.PhoneBook;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int num;
        boolean exit = false;

        optionMenu();

        while (!exit) {
            System.out.print("Enter an option number: ");
            num = sc.nextInt();
            sc.nextLine();
            switch (num) {
                case 0:
                    optionMenu();
                    break;
                case 1:
                    addContact();
                    break;
                case 2:
                    addAnotherPhoneNumber();
                    break;
                case 3:
                    editContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    showContacts();
                    break;
                case 6:
                    exit = true;
                    break;
            }
        }

    }

    // option menu
    private static void optionMenu() {
        System.out.println("Choose an option: \n" +
                "\t 0 - Show Option Menu \n" +
                "\t 1 - Add Contact \n" +
                "\t 2 - Add Another Phone Number To A Contact \n" +
                "\t 3 - Edit Contact \n" +
                "\t 4 - Remove Contact \n" +
                "\t 5 - Show Contacts List \n" +
                "\t 6 - Exit");
    }

    // add contact
    private static void addContact() {
        System.out.print("Enter Contact Name: ");
        String name = sc.nextLine();

        while (!Pattern.matches("[a-zA-Z]+", name)) {
            System.out.print("Enter correct name: ");
            name = sc.nextLine();
        }

        if (PhoneBook.getContact(name) != null) {
            System.out.println("This Contact Is Exist");
            return;
        }

        System.out.print("Enter Contact Phone Number: ");
        String phone = sc.nextLine();
        while (!Pattern.matches("[0-9]{11}", phone)) {
            System.out.print("Enter correct phone number: ");
            phone = sc.nextLine();
        }

        System.out.print("Enter Contact Address: ");
        String address = sc.nextLine();
        while (!Pattern.matches("[a-zA-Z]+", address)) {
            System.out.print("Enter correct address: ");
            address = sc.nextLine();
        }

        Contact contact = new Contact(name, phone, address);
        PhoneBook.addContact(contact);

        System.out.println("Contact registration completed successfully");
    }

    // add more number to a contact
    private static void addAnotherPhoneNumber() {

        String name = getContactName("Enter Contact's name: ");

        if (!contactExist(name)) {
            System.out.println("This contact is not exist!!");
            return;
        }

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        while (!Pattern.matches("[0-9]{11}", phone)) {
            System.out.print("Enter correct phone number: ");
            phone = sc.nextLine();
        }

        for (Contact contact : PhoneBook.getContacts()) {
            for (String number : contact.getPhoneNumber()) {
                if (number.equals(phone)) {
                    System.out.println("This phone number is exist!");
                    return;
                }
            }
        }

        Contact user = PhoneBook.getContact(name);
        user.setPhoneNumber(phone);
    }

    // get contact name
    private static String getContactName(String text){
        System.out.print(text);
        String name = sc.nextLine();

        while (!Pattern.matches("[a-zA-Z]+", name)) {
            System.out.print("Enter correct name: ");
            name = sc.nextLine();
        }

        return name;
    }

    // check contact exist
    private static boolean contactExist(String name){

        for (int i=0; i<PhoneBook.getContacts().size();i++){
            if (PhoneBook.getContacts().get(i).getName().equals(name)){
                return true;
            }
        }

        return false;
    }

    // edit contact
    private static void editContact(){
        String name = getContactName("Enter contact's name: ");

        if (!contactExist(name)) {
            System.out.println("This contact is not exist!!");
            return;
        }

        System.out.println("Which information would you like to edit?\n" +
                "\t 7- Edit name\n" +
                "\t 8- Edit PhoneNumbers\n" +
                "\t 9- Edit Address\n");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option){
            case 7:
                String newName = getContactName("Enter new contact's name: ");
                if (!contactExist(name)) {
                    System.out.println("This contact is not exist!!");
                    return;
                }
                Contact contact = PhoneBook.getContact(name);
                contact.setName(newName);
                System.out.println("done!!");
                break;
            case 8:
                System.out.print("Enter which phone number that you want to edit: ");
                String oldNumber = sc.nextLine();
                boolean oldNumberExist = false;
                for (String num:
                        PhoneBook.getContacts().get(PhoneBook.getContacts().indexOf(PhoneBook.getContact(name))).getPhoneNumber()) {
                    if (num.equals(oldNumber)){
                        oldNumberExist = true;
                        break;
                    }
                }

                if (!oldNumberExist){
                    System.out.println("This phone number is not exist!!");
                    return;
                }

                System.out.print("Enter new phone number: ");
                String newPhoneNumber = sc.nextLine();

                while (!Pattern.matches("[0-9]{11}", newPhoneNumber)) {
                    System.out.print("Enter correct phone number: ");
                    newPhoneNumber = sc.nextLine();
                }

                for (String num:
                        PhoneBook.getContacts().get(PhoneBook.getContacts().indexOf(PhoneBook.getContact(name))).getPhoneNumber()) {
                    if (num.equals(oldNumber)){
                        PhoneBook.getContacts().get(PhoneBook.getContacts()
                                .indexOf(PhoneBook.getContact(name))).getPhoneNumber()
                                .set(PhoneBook.getContacts().get(PhoneBook.getContacts()
                                        .indexOf(PhoneBook.getContact(name))).getPhoneNumber().indexOf(oldNumber), newPhoneNumber);
                        break;
                    }
                }

                System.out.println("done!!");
                break;
            case 9:
                System.out.println("Enter new address: ");
                String newAddress = sc.nextLine();

                while (Pattern.matches("[0-9]+", newAddress)) {
                    System.out.print("Enter correct address: ");
                    newAddress = sc.nextLine();
                }

                Contact contact1 = PhoneBook.getContact(name);

                if (contact1.getAddress().equals(newAddress)) {
                    System.out.println("This address is exist!!");
                    return;
                }

                contact1.setAddress(newAddress);
                System.out.println("done!!");
                break;
        }

    }

    // remove contact
    private static void removeContact(){
        String name = getContactName("Enter contact's name: ");

        if (!contactExist(name)){
            System.out.println("This contact is not exist!!");
            return;
        }

        Contact contact = PhoneBook.getContact(name);

        PhoneBook.getContacts().remove(contact);

        System.out.println("Contact removed successfully");
    }

    // show all contacts
    private static void showContacts(){
        if (PhoneBook.getContacts().size() > 0){
            for (Contact contact:
                 PhoneBook.getContacts()) {
                System.out.println("\t -> Name: " + contact.getName());
                System.out.println("\t -> Phone Numbers: ");
                if (contact.getPhoneNumber().size() > 0){
                    for (int j=0; j<contact.getPhoneNumber().size();j++){
                        System.out.println("\t\t" + j + "- " + contact.getPhoneNumber().get(j));
                    }
                }
                System.out.println("\t -> Address: " + contact.getAddress());
                System.out.println("------------------");
            }
        }
    }
}
