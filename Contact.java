package mohsen.coder;

import java.util.ArrayList;

public class Contact {
    private String name;
    private String address;
    private ArrayList<String> phoneNumber = new ArrayList<>();

    public Contact(String name, String phone, String address) {
        this.name = name;
        this.address = address;
        this.phoneNumber.add(phone);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.add(phoneNumber);
    }
}
