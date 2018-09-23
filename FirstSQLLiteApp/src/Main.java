//package net.sqlitetutorial;


import javax.xml.crypto.Data;

public class Main{
    public static void main(String[] args){
        //To interact with database: Create a new DatabaseHandler object, to stuff, destroy object.

        //Create handler object for the database:
        DatabaseHandler dbHandler = new DatabaseHandler();

        /*
        //Create new tables (if they don't exist already)

        dbHandler.createNewTable("Contacts");
        dbHandler.createNewTable("Phone_numbers");
        dbHandler.createNewTable("Email_addresses");
        dbHandler.createNewTable("Street_addresses");

        //Insert new contact
        dbHandler.insertNewContact("Snorre Hukkelås", "05.04.1990", "Inge Hukkelås", "Agnethe Guldbrandsen");
        */

        //Get contact key
        //System.out.println(dbHandler.getContactKey("'Hukkelås'"));

        //Register phone number
        //dbHandler.storePhoneNumber("97262829",dbHandler.getContactKey("'Hukkelås'"),"Work");

        //Register email address
        //dbHandler.storeEmailAddress("snorre.hukkelaas@gmail.com",dbHandler.getContactKey("'Snorre'"),"Private");
        //dbHandler.storeNewInfo("Jølsengata 19",dbHandler.getContactKey("'Hukkelås'"),"Home","Street_addresses");

        //Queery for contact info
        dbHandler.contactQueery("'Snorre'");





    }
}
