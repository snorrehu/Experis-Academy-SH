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
    private String fileName;

    public static void main(String[] args) {
        TextReader frame = new TextReader();
    }

    public TextReader() {

        this.setTitle("Text Reader Application");
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Declaring Panels
        JPanel filenamePanel = new JPanel();
        JPanel outputPanel = new JPanel();
        JPanel wordSearchPanel = new JPanel();

        //Setting panel layouts
        filenamePanel.setLayout(new BorderLayout());
        outputPanel.setLayout(new BorderLayout());
        wordSearchPanel.setLayout(new BorderLayout());

        // Add input area
        JTextArea inputField = new JTextArea();
        JTextArea wordSearchField = new JTextArea();

        //Add text output area
        JTextArea outputArea = new JTextArea(10,30);
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        outputArea.setEditable(false);



        // Declare analyze button and add listener
        JButton analyzeButton = new JButton("Analyze text file");
        analyzeButton.setPreferredSize(new Dimension(10,20));
        analyzeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputArea.setText(null);
                fileName  = inputField.getText();
                FileAnalyzer fileAnalyzer = new FileAnalyzer(fileName);
                outputArea.setText(fileAnalyzer.getData());
            }
        });

        //Declare search button and add listener
        JButton searchButton = new JButton("Search for word");
        searchButton.setPreferredSize(new Dimension(10,20));
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String word = wordSearchField.getText();
                System.out.println("Searching in file: " + fileName);
                FileAnalyzer fileAnalyzer = new FileAnalyzer(fileName,word);
                outputArea.setText("Number of matching words: " + fileAnalyzer.getWordcount());
            }
        });

        //Adding stuff to fileNamePanel
        filenamePanel.add(inputField,BorderLayout.NORTH);
        filenamePanel.add(analyzeButton,BorderLayout.SOUTH);

        //Adding stuff to wordSearchPanel:
        wordSearchPanel.add(wordSearchField,BorderLayout.NORTH);
        wordSearchPanel.add(searchButton,BorderLayout.SOUTH);

        //Adding stuff to outputPanel
        outputPanel.add(outputScrollPane);

        //Adding panels to frame
        this.add(filenamePanel,BorderLayout.NORTH);
        this.add(wordSearchPanel,BorderLayout.CENTER);
        this.add(outputPanel,BorderLayout.SOUTH);


        setVisible(true);

    }
}
