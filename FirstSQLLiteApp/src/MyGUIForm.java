import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
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
    private JTextField userIDInputTextField;
    private JTextField elementToDeleteTextField;
    private JTextField emailUserIDTextField;
    private JTextField userInfoTextField_2;

    //Text Areas
    private JTextArea outputTextArea;


    //Buttons
    private JButton newContactButton;
    private JButton addEmailAddressButton;
    private JButton addPhoneNumberButton;
    private JButton searchForContactButton;
    private JPanel outputAreaPanel;
    private JButton deleteContactButton;
    private JButton deleteElementButton;
    private JButton clearButton;
    private JTextField emailTypeTextFIeld;
    private JTextField phoneUserIDTextField;
    private JTextField phoneNumberTypeTextField;
    private JButton addAddressButton;
    private JTextField addresUserIDTextField;


    public MyGUIForm(){
        TextPrompt firstNamePropmpt = new TextPrompt("First Name", firstNameTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt lastNamePropmpt = new TextPrompt("Last Name", lastNameTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt birthDatePropmpt = new TextPrompt("Birth Date", birthDateTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt phoneNumberPrompt = new TextPrompt("Phone Number", phoneNumberTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt emailPrompt = new TextPrompt("Email Address", emailTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt streetAddressPrompt = new TextPrompt("Street Address", streetAddressTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt postalCodePrompt = new TextPrompt("Postal Code", postalCodeTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt cityPrompt = new TextPrompt("City", cityTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt idPrompt = new TextPrompt("User ID", userIDInputTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt deletePromt = new TextPrompt("Element to delete", elementToDeleteTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt emailUserIDPrompt = new TextPrompt("To ID", emailUserIDTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt emailTypePrompt = new TextPrompt("Type (Private/Work etc.)", emailTypeTextFIeld, TextPrompt.Show.FOCUS_LOST);
        TextPrompt phoneNumberTypePrompt = new TextPrompt("Type (Private/Work etc.)", phoneNumberTypeTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt phoneIDPrompt = new TextPrompt("To ID", phoneUserIDTextField, TextPrompt.Show.FOCUS_LOST);
        TextPrompt addresIDPrompt = new TextPrompt("To ID", addresUserIDTextField, TextPrompt.Show.FOCUS_LOST);

        //Set borders
        userInfoTextField.setBorder(null);
        userInfoTextField_2.setBorder(null);

        //Create compound border around output area
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border compound;
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);
        outputTextArea.setBorder(compound);

        //**********************************Listeners and database interaction******************************************
        DatabaseHandler dbHandler = new DatabaseHandler();

        //Add new contact
        newContactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String birthDate = birthDateTextField.getText();
                dbHandler.insertNewContact(firstName + " " + lastName, birthDate);
                JOptionPane.showMessageDialog(null, "Contact added!");
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
    }





    public JPanel getPanelMain() {
        return panelMain;
    }
}
