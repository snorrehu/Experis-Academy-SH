import javax.xml.crypto.Data;
import java.sql.*;

public class DatabaseHandler {
    private static String url = "jdbc:sqlite:C:/Users/snorr/IdeaProjects/Experis-Academy-SH/FirstSQLLiteApp/Contacts.sqlite";

    //Create tables
    public static void createNewTable(String tableName) {
        String sql = null;

        if(tableName.equals("Contacts")) {
            sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ( \n"
                    + "ContactID integer PRIMARY KEY , \n"
                    + "FirstName text, \n"
                    + "LastName text, \n"
                    + "BirthDate text \n"
                    + ");";
        }
        else if(tableName.equals("Phone_numbers")) {
            sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ( \n"
                    + "numberID integer PRIMARY KEY , \n"
                    + "PhoneNumber text, \n"
                    + "ContactID integer , \n"
                    + "infoType text, \n"
                    //Link tables together
                    + "FOREIGN KEY (ContactID) REFERENCES Contacts(ContactID)"
                    + ");";
        }
        else if(tableName.equals("Email_addresses")) {
            sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ( \n"
                    + "emailID integer PRIMARY KEY , \n"
                    + "EmailAddress text, \n"
                    + "ContactID integer , \n"
                    + "infoType text, \n"
                    //Link tables together
                    + "FOREIGN KEY (ContactID) REFERENCES Contacts(ContactID)"
                    + ");";
        }
        else if(tableName.equals("Street_addresses")) {
            sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ( \n"
                    + "addressID integer PRIMARY KEY , \n"
                    + "StreetAddresses text, \n"
                    + "ContactID integer , \n"
                    + "infoType text, \n"
                    //Link tables together
                    + "FOREIGN KEY (ContactID) REFERENCES Contacts(ContactID)"
                    + ");";
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            System.out.println("Table created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Queery for contact information
    public void contactQueery(String contactName){
        String sqlContacts = "SELECT * FROM Contacts \n"
                + "WHERE FirstName ="+ contactName + " OR LastName = " + contactName;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmtContacts  = conn.createStatement();
             ResultSet rsContacts    = stmtContacts.executeQuery(sqlContacts)){

            // Loop through the result set and print info for each result
            while (rsContacts.next()) {
                //Get every number stored for each contact:
                String sqlNumbers = "SELECT * FROM Phone_numbers \n"
                        + "WHERE ContactID = " + rsContacts.getString("ContactID");
                Statement stmtNumbers = conn.createStatement();
                ResultSet rsNumbers = stmtNumbers.executeQuery(sqlNumbers);

                //Get every email stored for each contact:
                String sqlEmails = "SELECT * FROM Email_addresses \n"
                        + "WHERE ContactID = " + rsContacts.getString("ContactID");
                Statement stmtEmails = conn.createStatement();
                ResultSet rsEmails = stmtEmails.executeQuery(sqlEmails);

                //Get every street address stored for each contact:
                String sqlAddresses = "SELECT * FROM Street_addresses \n"
                        + "WHERE ContactID = " + rsContacts.getString("ContactID");
                Statement stmtAddresses = conn.createStatement();
                ResultSet rsAddresses = stmtAddresses.executeQuery(sqlAddresses);

                //Print info from Contacts table
                System.out.println("ContactID: " + rsContacts.getInt("ContactID") +  "\n" +
                        "Name: " + rsContacts.getString("FirstName") + " " + rsContacts.getString("LastName") + "\n" +
                        "Birth Date: " + rsContacts.getString("BirthDate") );

                //Print all numbers registered to contact
                System.out.println("Phone numbers:");
                while (rsNumbers.next()) {
                    System.out.println(rsNumbers.getString("PhoneNumber") + "\t(" + rsNumbers.getString("infoType") + ")");
                }

                //Print all email addresses registered to contact
                System.out.println("Email addresses:");
                while (rsEmails.next()) {
                    System.out.println(rsEmails.getString("EmailAddress") + "\t(" + rsEmails.getString("infoType") + ")");
                }

                //Print all email addresses registered to contact
                System.out.println("Street addresses:");
                while (rsAddresses.next()) {
                    System.out.println(rsAddresses.getString("StreetAddresses") + "\t(" + rsAddresses.getString("infoType") + ")");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Inserting new contacttest
    public void insertNewContact(String fullName, String birthDate, String father, String mother) {

        boolean motherIsRegistered = false;
        boolean fatherIsRegistered = false;

        String[] fullNameArray = fullName.split(" ");
        String[] fatherArray = father.split(" ");
        String[] motherArray = mother.split(" ");

        String firstName = fullNameArray[0];
        String lastName = fullNameArray[1];
        String fatherFirstName = fatherArray[0];
        String fatherLastName = fatherArray[1];
        String motherFirstName = motherArray[0];
        String motherLastName = motherArray[1];



        String contactTableSql = "INSERT INTO Contacts(firstName,lastName,birthDate) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmtContact = conn.prepareStatement(contactTableSql)) {

            //Prepare the contact table statement:
            pstmtContact.setString(1, firstName);
            pstmtContact.setString(2, lastName);
            pstmtContact.setString(3, birthDate);

            //Execute the table updates
            pstmtContact.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Inserting new info into table:
    public void storeNewInfo(String infoString, int contactKey, String infoType, String table) {
        String columnString = null;
        if(table.equals("Street_addresses")){
            columnString = "StreetAddresses";
        }
        else if (table.equals("Email_addresses")){
            columnString = "EmailAddress";
        }
        else if (table.equals("Phone_numbers")){
            columnString = "PhoneNumber";
        }
        String tableSql = "INSERT INTO " + table + "(" + columnString +",ContactID,infoType) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(tableSql)) {

            //Prepare the number table statement:
            pstmt.setString(1, infoString);
            pstmt.setInt(2, contactKey);
            pstmt.setString(3, infoType);

            //Execute the table updates
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Get ContactID
    public int getContactKey(String contactName){
        int key = 0;

        String keyQueeryString =
                "SELECT * FROM Contacts \n"
                + "WHERE FirstName ="+ contactName + " OR LastName = " + contactName;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(keyQueeryString)){

            //Get the key
            key = rs.getInt("ContactID");

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return key;
    }


}
