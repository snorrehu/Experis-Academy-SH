//package net.sqlitetutorial;


import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;

public class Main extends MyGUIForm{
    public static String contactTable = "Contacts";
    public static String phoneNumberTable = "Phone_numbers";
    public static String emailAddressTable = "Email_addresses";
    public static String streetAddressTable = "Street_addresses";
    public static String relationsTable = "Relations";

    public static void main(String[] args){
        //Create handler object for the database:
        DatabaseHandler dbHandler = new DatabaseHandler();


        //Create new tables (if they don't exist already)

        dbHandler.createNewTable(contactTable);
        dbHandler.createNewTable(phoneNumberTable);
        dbHandler.createNewTable(emailAddressTable);
        dbHandler.createNewTable(streetAddressTable);
        dbHandler.createNewTable(relationsTable);

        //Insert new contact
        //dbHandler.insertNewContact("Snorre Hukkelås", "05.04.1990");
        //dbHandler.insertNewContact("Are Hukkelås", "08.06.1969");
        //dbHandler.insertNewContact("Inge Hukkelås", "23.09.1943");
        //dbHandler.insertNewContact("Heidi Hukkelås", "05.12.1970");



        //Get contact key
        //System.out.println(dbHandler.getContactKey("'Hukkelås'"));

        //Store new info:
        //dbHandler.storeNewInfo("95275778",dbHandler.getContactKey("'Snorre'"),"Private",phoneNumberTable);
        //dbHandler.storeNewInfo("snorre.hukkelaas@gmail.com",dbHandler.getContactKey("'Snorre'"),"Private",emailAddressTable);

        //Delete single element (remember to put '' around elements except phone numbers)
        //dbHandler.deleteElement("Email_addresses","'snorre.hukkelaas@gmail.com'");

        //Delete contact and all rows containing the corresponding contact id
        //dbHandler.deleteContact(dbHandler.getContactKey("'Snorre'"));

        //Queery for contact info
        //dbHandler.contactQueery("'Snorre'");

        //***********************************************GUI************************************************************
        JFrame frame = new JFrame("Contact Book");
        frame.setContentPane(new MyGUIForm().getPanelMain());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 600));
        frame.pack();
        frame.setVisible(true);
    }
}
