import java.util.ArrayList;

public class Contact {
    private String firstName;
    private String lastName;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> emailAddress;
    private String homeAddress;
    private String birthDate;
    private ArrayList<String> parents;
    private ArrayList<String> siblings;

    public ArrayList<String> getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public ArrayList<String> getParents() {
        return parents;
    }

    public String getLastName() {
        return lastName;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public ArrayList<String> getSiblings() {
        return siblings;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    //*******************************************CRUD OPERATIONS********************************************************

    public void registerNewContact(){

    }

    public void updateContact(){


    }

    public void deleteContact(String name){


    }

    public void getContactInformation(String name){


    }




}
