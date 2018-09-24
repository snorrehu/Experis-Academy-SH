import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    //Text Areas
    private JTextArea outputTextArea;


    //Buttons
    private JButton newContactButton;
    private JButton addEmailAddressButton;
    private JButton addPhoneNumberButton;
    private JButton addAddressButton;
    private JButton searchForContactButton;
    private JPanel outputAreaPanel;
    private JButton deleteContactButton;

    private JButton deleteElementButton;
    private JTextField userInfoTextField_2;


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

        userInfoTextField.setBorder(null);
        userInfoTextField_2.setBorder(null);
        //Create compound border around output area

        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        Border compound;
        compound = BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel);

        outputTextArea.setBorder(compound);
    }

    public JPanel getPanelMain() {
        return panelMain;
    }
}
