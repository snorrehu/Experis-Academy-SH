import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.sql.*;

public class DatabaseHandler {
    //"jdbc:sqlite:C:/Users/snorr/IdeaProjects/Experis-Academy-SH/FirstSQLLiteApp/Contacts.sqlite"

    private static String url = null;

    DatabaseHandler(){
        String cwd = System.getProperty("user.dir");
        System.out.println("Current working directory : " + cwd);
        url = "jdbc:sqlite:" + cwd +"/Contacts.sqlite";
    }

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
                    + "StreetAddress text, \n"
                    + "PostalCode text, \n"
                    + "City text, \n"
                    + "ContactID integer , \n"
                    + "infoType text, \n"
                    //Link tables together
                    + "FOREIGN KEY (ContactID) REFERENCES Contacts(ContactID)"
                    + ");";
        }
        else if(tableName.equals("Relations")) {
            sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ( \n"
                    + "relationID integer PRIMARY KEY , \n"
                    + "ContactID_1 integer, \n"
                    + "ContactID_2 integer , \n"
                    + "relationType text \n"
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

    //Queery for contact information by name
    public String contactQueery(String contactInfo){
        StringBuilder stringBuilder = new StringBuilder();
        String searchOutputString = null;
        boolean numeric = true;
        String sqlContacts = null;
        int contactID = 0;

        //Check if phone number or name is entered
        try {
            int num = Integer.parseInt(contactInfo);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        if(numeric){
            String sqlNumbers = "SELECT ContactID FROM Phone_numbers \n"
                    + "WHERE PhoneNumber = " + contactInfo;
            try (Connection conn = DriverManager.getConnection(url);
                 Statement stmt  = conn.createStatement();
                 ResultSet rs    = stmt.executeQuery(sqlNumbers)){
                //Get contact id
                contactID = rs.getInt("ContactID");
                sqlContacts = "SELECT * FROM Contacts \n"
                        + "WHERE  ContactID ="+ contactID;

            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        else{
            sqlContacts = "SELECT * FROM Contacts \n"
                    + "WHERE  FirstName ="+ contactInfo + " OR LastName = " + contactInfo;
        }

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

                //Get every relation stored for each contact:
                String sqlRelations = "SELECT * FROM Relations \n"
                        + "WHERE ContactID_2 = " + rsContacts.getString("ContactID");
                Statement stmtRelations = conn.createStatement();
                ResultSet rsRelations = stmtRelations.executeQuery(sqlRelations);

                //Print info from Contacts table
                stringBuilder.append("ContactID: " + rsContacts.getInt("ContactID") +  "\n" +
                        "Name: " + rsContacts.getString("FirstName") + " " + rsContacts.getString("LastName") + "\n" +
                        "Birth Date: " + rsContacts.getString("BirthDate") + "\n" );

                //Print all numbers registered to contact
                stringBuilder.append("Phone numbers:\n");
                while (rsNumbers.next()) {
                    stringBuilder.append(rsNumbers.getString("PhoneNumber") + "\t(" + rsNumbers.getString("infoType") + ")\n");
                }

                //Print all email addresses registered to contact
                stringBuilder.append("Email addresses:\n");
                while (rsEmails.next()) {
                    stringBuilder.append(rsEmails.getString("EmailAddress") + "\t(" + rsEmails.getString("infoType") + ")\n");
                }

                //Print all email addresses registered to contact
                stringBuilder.append("Street addresses:\n");
                while (rsAddresses.next()) {
                    stringBuilder.append(rsAddresses.getString("StreetAddress") + "\t(" + rsAddresses.getString("infoType") + ")\n"
                    + rsAddresses.getString("PostalCode") + " " + rsAddresses.getString("City")  + "\n");
                }

                //Print relations data
                stringBuilder.append("Relations: \n");
                while (rsRelations.next()) {
                    stringBuilder.append(getContactFullName(Integer.parseInt(rsRelations.getString("ContactID_1")))+ " is this persons' " + rsRelations.getString("relationType") + ".\n");
                }

                stringBuilder.append("\n");
                searchOutputString = stringBuilder.toString();

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return searchOutputString;
    }

    //Inserting new contact
    public void insertNewContact(String fullName, String birthDate) {

        boolean motherIsRegistered = false;
        boolean fatherIsRegistered = false;

        String[] fullNameArray = fullName.split(" ");

        String firstName = fullNameArray[0];
        String lastName = fullNameArray[1];

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
        if(table.equals("Phone_numbers")){
            columnString = "PhoneNumber";
        }
        else if (table.equals("Email_addresses")){
            columnString = "EmailAddress";
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
    //Inserting new info into table:
    public void storeNewAddress(String address, int contactKey, String addressType, String postalCode, String city) {
        String tableSql = "INSERT INTO " + "Street_addresses(StreetAddress,PostalCode,City,ContactID,infoType) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(tableSql)) {

            //Prepare the number table statement:
            pstmt.setString(1, address);
            pstmt.setString(2,postalCode );
            pstmt.setString(3, city);
            pstmt.setInt(4, contactKey);
            pstmt.setString(5, addressType);

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

    //Get ContactID
    public String getContactFullName(int id){
        String fullName = null;

        String keyQueeryString =
                "SELECT * FROM Contacts \n"
                        + "WHERE ContactID ="+ id;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(keyQueeryString)){

            //Get the name
            fullName = rs.getString("FirstName") + " " + rs.getString("LastName");

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return fullName;
    }

    //Deleting single elements
    public void deleteElement(String tableToDeleteFrom, String thingToDelete, int id){
        String deleteKey = null;
        if(tableToDeleteFrom.equals("Email_addresses")){
            deleteKey = "EmailAddress";
        }
        else if(tableToDeleteFrom.equals("Phone_numbers")){
            deleteKey = "PhoneNumber";
        }
        String sql = "DELETE FROM " + tableToDeleteFrom + " WHERE " + deleteKey + " = " + thingToDelete + " AND ContactID = " + id;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete contact
    public void deleteContact(int contactID){
        String sql = "DELETE FROM Contacts  WHERE ContactID = " + contactID;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql2 = "DELETE FROM Phone_numbers WHERE ContactID = " + contactID;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql2)){
            pstmt.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql3 = "DELETE FROM Email_addresses WHERE ContactID = " + contactID;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql3)){
            pstmt.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql4 = "DELETE FROM Street_addresses WHERE ContactID = " + contactID;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql4)){
            pstmt.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql5 = "DELETE FROM Relations WHERE ContactID_1 = " + contactID + " OR ContactID_2 = " + contactID;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql5)){
            pstmt.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //Delete address
    public void deleteAddress(String streetAddress, String postalCode, String city, int id){

        String sql = "DELETE FROM Street_addresses WHERE StreetAddress =  "+streetAddress+" AND postalCode = " + postalCode + " AND City = " + city + " AND ContactID = " + id;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Add relations
    public void addRelation(int id_1, int id_2, String relation){
        String reversedOrderRelation = null;
        if(relation.equals("child")){
            reversedOrderRelation = "parent";
        }
        else if(relation.equals("parent")){
            reversedOrderRelation = "child";
        }
        else if(relation.equals("sibling")){
            reversedOrderRelation = "sibling";
        }

        String tableSql_1 = "INSERT INTO " + "Relations(ContactID_1,ContactID_2,relationType) VALUES(?,?,?)";

        String tableSql_2 = "INSERT INTO " + "Relations(ContactID_1,ContactID_2,relationType) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt1 = conn.prepareStatement(tableSql_1);
             PreparedStatement pstmt2 = conn.prepareStatement(tableSql_2)) {

            //Prepare the first number table statement:
            pstmt1.setInt(1, id_1);
            pstmt1.setInt(2,id_2);
            pstmt1.setString(3, relation);

            //Prepare the second number table statement:
            pstmt2.setInt(1, id_2);
            pstmt2.setInt(2,id_1);
            pstmt2.setString(3, reversedOrderRelation);

            //Execute the table updates
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Delete relations
    public void deleteRelation(int id_1, int id_2){
        String sql_1 = "DELETE FROM Relations WHERE ContactID_1 =  "+id_1+" AND ContactID_2 = " + id_2 ;
        String sql_2 = "DELETE FROM Relations WHERE ContactID_1 =  "+id_2+" AND ContactID_2 = " + id_1 ;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt1 = conn.prepareStatement(sql_1);
             PreparedStatement pstmt2 = conn.prepareStatement(sql_2)){
            pstmt1.executeUpdate();
            pstmt2.executeUpdate();

        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
