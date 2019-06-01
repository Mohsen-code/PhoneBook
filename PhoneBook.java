package mohsen.coder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PhoneBook {
    private static List<Contact> contacts = new ArrayList<>();

    // add contact to the list
    public static boolean addContact(Contact contact) {
        if (!existContact(contact)) {
            contacts.add(contact);
            return true;
        }
        return false;
    }

    // remove contact from the list
    public static boolean removeContact(Contact contact) {
        if (existContact(contact)) {
            contacts.remove(contact);
            return true;
        }
        return false;
    }

    // edit contact
    public static boolean editContact(Contact oldContact, Contact newContact) {
        if (existContact(oldContact)) {
            contacts.set(contacts.indexOf(oldContact), newContact);
            return true;
        }
        return false;
    }

    // get contacts list
    public static List<Contact> getContacts() {
        return contacts;
    }

    // get contact
    public static Contact getContact(String info) {
        ArrayList<String> userPhoneNumbers;
        if (Pattern.matches("[0-9]{11}", info)) {
            for (int i = 0; i < contacts.size(); i++) {
                userPhoneNumbers = contacts.get(i).getPhoneNumber();
                for (int j = 0; j < userPhoneNumbers.size(); j++) {
                    if (userPhoneNumbers.get(i).equals(info)) {
                        return contacts.get(i);
                    }
                }
            }
            return null;
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getName().equals(info)) {
                    return contacts.get(i);
                }
            }
            return null;
        }
    }

    private static boolean existContact(Contact contact) {
        return contacts.indexOf(contact) >= 0;
    }

}
