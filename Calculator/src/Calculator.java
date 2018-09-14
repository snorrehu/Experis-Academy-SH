import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame{
    private static String buttons[] = {"9","8","7","6","5","4","3","2","1","0","=","ANS","-","/","*","+"};

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

        //Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(4,4,4,4));
        for(String element:buttons){
            //Create new button
            JButton button = new JButton(element);
            //Set action command name for button
            button.setActionCommand(element);
            //Add listener
            button.addActionListener(listener);
            //Add to panel
            buttonPanel.add(button);
        }

        this.add(displayPanel,BorderLayout.NORTH);
        this.add(buttonPanel,BorderLayout.CENTER);

        setVisible(true);
    }
}


