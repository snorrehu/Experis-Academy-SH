import org.w3c.dom.events.EventException;

import javax.print.attribute.standard.JobMediaSheetsCompleted;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyGUIForm {
    private JPanel panelMain;

    //Text fields
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField birthDateTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField streetAddressTextField;
    private JTextField postalCodeTextField;
    private JTextField cityTextField;
    private JTextField userInfoTextField;
    private JTextField userIDToDeleteTextField;
    private JTextField elementToDeleteTextField;
    private JTextField emailUserIDTextField;
    private JTextField userInfoTextField_2;
    private JTextField addresUserIDTextField;
    private JTextField addressTypeTextField;
    private JTextField emailTypeTextFIeld;
    private JTextField phoneUserIDTextField;
    private JTextField phoneNumberTypeTextField;

    //Text Areas
    private JTextArea outputTextArea;


    //Buttons
    private JButton newContactButton;
    private JButton addEmailAddressButton;
    private JButton addPhoneNumberButton;
    private JButton searchForContactButton;
    private JButton deleteContactButton;
    private JButton deleteAddressButton;
    private JButton clearButton;
    private JButton addAddressButton;
    private JButton deleteEmailAddressButton;
    private JButton deletePhoneNumberButton;
    private JPanel outputAreaPanel;
    private JButton addRelationButton;
    private JTextField relationID_1_textField;
    private JTextField relationTypeTextField;
    private JTextField relationID_2_textField;
    private JTextField isContactWithIDTextField;
    private JTextField contactWithIDTextField;
    private JTextField sTextField;
    private JButton deleteRelationButton;
    private JTextPane outputTextPane;


    public MyGUIForm(){
        TextPrompt firstNamePropmpt = new TextPrompt("First Name", firstNameTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt lastNamePropmpt = new TextPrompt("Last Name", lastNameTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt birthDatePropmpt = new TextPrompt("Birth Date", birthDateTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt phoneNumberPrompt = new TextPrompt("Phone Number", phoneNumberTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt emailPrompt = new TextPrompt("Email Address", emailTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt streetAddressPrompt = new TextPrompt("Street Address", streetAddressTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt postalCodePrompt = new TextPrompt("Postal Code", postalCodeTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt cityPrompt = new TextPrompt("City", cityTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt idPrompt = new TextPrompt("Contact ID", userIDToDeleteTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt emailUserIDPrompt = new TextPrompt("To ID", emailUserIDTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt emailTypePrompt = new TextPrompt("Type (Private/Work etc.)", emailTypeTextFIeld, TextPrompt.Show.FOCUS_LOST);
        TextPrompt phoneNumberTypePrompt = new TextPrompt("Type (Private/Work etc.)", phoneNumberTypeTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt phoneIDPrompt = new TextPrompt("To ID", phoneUserIDTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt addresIDPrompt = new TextPrompt("To ID", addresUserIDTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt addressTypePrompt = new TextPrompt("Type (Private/Work etc.)", addressTypeTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt contact1Propmt = new TextPrompt("Contact ID", relationID_1_textField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt contact2Propmt = new TextPrompt("Contact ID", relationID_2_textField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt contact3Propmt = new TextPrompt("Relation (parent/child/sibling)", relationTypeTextField, TextPrompt.Show.FOCUS_LOST);

        //Set borders
        userInfoTextField.setBorder(null);
        userInfoTextField_2.setBorder(null);
        contactWithIDTextField.setBorder(null);
        isContactWithIDTextField.setBorder(null);
        sTextField.setBorder(null);


        //Create compound border around output area
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border compound;
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
        outputTextArea.setBorder(compound);


        //JScrollPane jScrollPane = new JScrollPane(outputTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //outputAreaPanel.add(jScrollPane);

        //**********************************Listeners and database interaction******************************************
        DatabaseHandler dbHandler = new DatabaseHandler();

        //Add new contact
        newContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String firstName = firstNameTextField.getText();
                    String lastName = lastNameTextField.getText();
                    String birthDate = birthDateTextField.getText();
                    dbHandler.insertNewContact(firstName + " " + lastName, birthDate);
                    JOptionPane.showMessageDialog(null, "Contact added!");

                }catch (ArrayIndexOutOfBoundsException ev){
                    System.out.println(ev.getMessage());
                    JOptionPane.showMessageDialog(null, "Please enter all three fields!");
                }



            }
        });

        //Search for contact
        searchForContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Refresh the text area
                outputTextArea.setText(null);

                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String outputText = null;

                if(firstName!= null){
                    outputText = dbHandler.contactQueery("'" + firstName + "'");
                    outputTextArea.setText(outputText);
                    return;
                }
            }
        });

        //Clear output field
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText(null);
            }
        });

        //Add email address
        addEmailAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(emailUserIDTextField.getText());
                String emailAddress = emailTextField.getText();
                String emailType = emailTypeTextFIeld.getText();
                dbHandler.storeNewInfo(emailAddress,id,emailType,"Email_addresses");
                JOptionPane.showMessageDialog(null, "Email address added for contact with ID: " + id);
            }
        });

        //Add phone number
        addPhoneNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(phoneUserIDTextField.getText());
                String phoneNumber = phoneNumberTextField.getText();
                String phoneNumberType = phoneNumberTypeTextField.getText();
                dbHandler.storeNewInfo(phoneNumber,id,phoneNumberType,"Phone_numbers");
                JOptionPane.showMessageDialog(null, "Phone number added for contact with ID: " + id);
            }
        });

        //Add address
        addAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(addresUserIDTextField.getText());
                String streetAddress = streetAddressTextField.getText();
                String addressType = addressTypeTextField.getText();
                String postalCode = postalCodeTextField.getText();
                String city = cityTextField.getText();
                dbHandler.storeNewAddress(streetAddress,id,addressType,postalCode,city);
                JOptionPane.showMessageDialog(null, "Address added for contact with ID: " + id);
            }
        });

        //Delete contact
        deleteContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(userIDToDeleteTextField.getText());
                dbHandler.deleteContact(id);
                JOptionPane.showMessageDialog(null, "All records of contact with ID: " + id + " has been deleted.");
            }
        });

        //Delete email address
        deleteEmailAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailAddress = emailTextField.getText();
                int id = Integer.parseInt(emailUserIDTextField.getText());
                dbHandler.deleteElement("Email_addresses","'"+emailAddress+"'",id);
                JOptionPane.showMessageDialog(null, "Email address: " + emailAddress + " has been deleted.");
            }
        });

        //Delete phone number
        deletePhoneNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phoneNumber = phoneNumberTextField.getText();
                int id = Integer.parseInt(phoneUserIDTextField.getText());
                dbHandler.deleteElement("Phone_numbers",phoneNumber,id);
                JOptionPane.showMessageDialog(null, "Phone number: " + phoneNumber + " for Contact ID: "+id+ " has been deleted.");
            }
        });

        //Delete address
        deleteAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String streetAddress = "'" + streetAddressTextField.getText() + "'";
                String postalCode = "'" + postalCodeTextField.getText() + "'";
                String city = "'" + cityTextField.getText() + "'";
                int id = Integer.parseInt(addresUserIDTextField.getText());
                dbHandler.deleteAddress(streetAddress,postalCode,city,id);
                JOptionPane.showMessageDialog(null, "Address: '"
                        + streetAddressTextField.getText() + " " + postalCodeTextField.getText()
                        + " " + cityTextField.getText()
                        + "'  deleted for Contact ID: "+id+ " has been deleted.");
            }
        });

        //Add relation
        addRelationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_1 = Integer.parseInt(relationID_1_textField.getText());
                int id_2 = Integer.parseInt(relationID_2_textField.getText());

                String relation = relationTypeTextField.getText();
                dbHandler.addRelation(id_1,id_2,relation);
                JOptionPane.showMessageDialog(null, "Relation added!");
            }
        });

        //Delete relation
        deleteRelationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_1 = Integer.parseInt(relationID_1_textField.getText());
                int id_2 = Integer.parseInt(relationID_2_textField.getText());
                dbHandler.deleteRelation(id_1,id_2);
                JOptionPane.showMessageDialog(null, "Relation deleted!");
            }
        });
    }





    public JPanel getPanelMain() {
        return panelMain;
    }
}
