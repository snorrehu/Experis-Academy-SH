import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileAnalyzer {
    private String fileName;
    private double fileSize;
    private int numberOfLines;
    private int wordCount;
    String fileString;
    String[] words;

    //Analyze file
    public FileAnalyzer(String directory){
        try {

            //Open file
            FileReader fileReader = new FileReader(directory);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            File file = new File(directory);

            //Get filename
            this.fileName = file.getName();
            double bytes = file.length();
            double kilobytes = bytes/1024;

            this.fileSize = kilobytes;

            //Read the file line by line:
            while ((bufferedReader.readLine()) != null) {
                numberOfLines++;
            }
            //Close the readers
            bufferedReader.close();
            fileReader.close();

         //Catch the exception if the file isn't found
        }catch (IOException e1){
            System.out.println("File not found.");
        }
    }

    //Search for words
    public FileAnalyzer(String directory, String searchWord) {

        try {

            BufferedReader textBuffer = new BufferedReader(new FileReader(directory));

            StringBuilder builder = new StringBuilder();
            String nextLine = textBuffer.readLine();

            while (nextLine != null) {
                builder.append(nextLine);
                builder.append(System.lineSeparator());
                nextLine = textBuffer.readLine();
            }

            //-------SOMETHING GOES WRONG HERE:------
            fileString = builder.toString().toLowerCase();
            words = fileString.split(" \\s+");
            System.out.print(words);
            //---------------------------------------

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file");
            System.exit(0);
        } catch (IOException e){
            System.out.println("An I/O Error Occured");
            System.exit(0);
        }

        if(words.length>0){
            for (String word:words) {
                if(word==searchWord){
                    wordCount++;
                }
            }
        }

    }

    public String getData () {
        String output = "Filename: " + fileName + "\n" + "Number of lines: " + numberOfLines + "\n" + "File size: " + fileSize + "kB" + "\n";
        return output;
    }

    public int getWordCount () {
        return wordCount;
    }
}
