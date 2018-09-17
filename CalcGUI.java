import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame{
    private static String numberButtons[] = {"9","8","7","6","5","4","3","2","1"};

    private static String bottomButtons[] = {"0","SAVE","=","CLEAR","MEM"};

    private static String operatorButtons[] = {"-","/","*","+"};

    public static void main(String[] args){
        new Calculator();
    }

    Calculator() {
        this.setSize(300, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Displaypanel
        JPanel displayPanel = new JPanel();
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);

        Font f = new Font("Arial",Font.BOLD,14);
        displayArea.setFont(f);

        displayPanel.add(displayArea);

        //Create button listener
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println(e.getActionCommand()+" clicked");
                displayArea.setText(e.getActionCommand());
            }
        };

        //Number button panel
        JPanel numberButtonPanel = new JPanel(new GridLayout(3,3,4,4));
        for(String element:numberButtons){
            //Create new button
            JButton button = new JButton(element);
            //Set action command name for button
            button.setActionCommand(element);
            //Add listener
            button.addActionListener(listener);
            //Add to panel
            numberButtonPanel.add(button);
        }

        //Operator button panel
        JPanel operatorButtonPanel = new JPanel(new GridLayout(4,1,4,4));
        for(String element:operatorButtons){
            //Create new button
            JButton button = new JButton(element);
            //Set action command name for button
            button.setActionCommand(element);
            //Add listener
            button.addActionListener(listener);
            //Add to panel
            operatorButtonPanel.add(button);
        }

        //Number button panel
        JPanel bottomPanel = new JPanel();
        for(String element:bottomButtons){
            //Create new button
            JButton button = new JButton(element);
            //Set action command name for button
            button.setActionCommand(element);
            //Add listener
            button.addActionListener(listener);
            //Add to panel
            bottomPanel.add(button);
        }

        this.add(displayPanel,BorderLayout.NORTH);
        this.add(numberButtonPanel,BorderLayout.CENTER);
        this.add(operatorButtonPanel,BorderLayout.EAST);
        this.add(bottomPanel,BorderLayout.SOUTH);

        setVisible(true);
    }
}


