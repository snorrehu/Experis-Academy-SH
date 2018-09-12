import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

/*
Open: opens a specified file (the file will be .txt)

        Display statistics for the file:
        -The name of the file that I chose.
        - How big the file is.
        - How many lines the file has.
        - Let me search if a specific word exists in the file,
        - How many time the specific word is found in the file.
        - The search must ignore case.
*/

public class TextReader extends JFrame {

    public static void main(String[] args) {
        TextReader frame = new TextReader();
    }

    public TextReader() {

        this.setTitle("Text Reader Application");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());



        // Declaring Panels
        JPanel inputPanel = new JPanel();
        JPanel outputPanel = new JPanel();

        //Setting panel layouts
        inputPanel.setLayout(new BorderLayout());

        // Add input area
        JTextArea inputField = new JTextArea();



        //Add text output area
        JTextArea outputArea = new JTextArea(10,30);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputArea.setEditable(false);

        // Declare button and add listener
        JButton analyzeButton = new JButton("Analyze text file");
        analyzeButton.setPreferredSize(new Dimension(10,20));
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(null);
                String directory = inputField.getText();
                FileAnalyzer fileAnalyzer = new FileAnalyzer(directory);
                outputArea.setText(fileAnalyzer.getData());
            }
        });
        //Adding stuff to inputPanel
        inputPanel.add(inputField,BorderLayout.NORTH);
        inputPanel.add(analyzeButton, BorderLayout.SOUTH);

        //Adding stuff to outputPanel
        outputPanel.add(outputScrollPane);

        //Adding panels to frame
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(outputPanel, BorderLayout.CENTER);




        setVisible(true);

    }
}
