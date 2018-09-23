//package net.sqlitetutorial;


import javax.xml.crypto.Data;

public class Main{
    public static String contactTable = "Contacts";
    public static String phoneNumberTable = "Phone_numbers";
    public static String emailAddressTable = "Email_addresses";
    public static String streetAddressTable = "Street_addresses";
    public static String relationsTable = "Relations";

    public static void main(String[] args){
        //To interact with database: Create a new DatabaseHandler object, to stuff, destroy object.

        //Create handler object for the database:
        DatabaseHandler dbHandler = new DatabaseHandler();

        /*
        //Create new tables (if they don't exist already)

        dbHandler.createNewTable(contactTable);
        dbHandler.createNewTable(phoneNumberTable);
        dbHandler.createNewTable(emailAddressTable);
        dbHandler.createNewTable(streetAddressTable);
        dbHandler.createNewTable(relationsTable);

        //Insert new contact
        dbHandler.insertNewContact("Snorre Hukkelås", "05.04.1990");
        dbHandler.insertNewContact("Are Hukkelås", "08.06.1969");
        dbHandler.insertNewContact("Inge Hukkelås", "23.09.1943");
        dbHandler.insertNewContact("Heidi Hukkelås", "05.12.1970");
*/


        //Get contact key
        //System.out.println(dbHandler.getContactKey("'Hukkelås'"));

        //Store new info:
        //dbHandler.storeNewInfo("95275778",dbHandler.getContactKey("'Snorre'"),"Private",phoneNumberTable);

        //Queery for contact info
        dbHandler.contactQueery("95275778");





    }
}
