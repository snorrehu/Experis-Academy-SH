import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileAnalyzer {
    private String fileName;
    private double fileSize;
    private int numberOfLines;
    private int wordcount;

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
    public FileAnalyzer(String directory, String word){
        Scanner scanner = null;
        this.wordcount = 0;
        try {
            scanner = new Scanner(new File(directory));
        } catch (IOException e) {

        }
        while (scanner.hasNextLine()) {
            while (scanner.hasNext()){
                if(word==scanner.next()){
                    System.out.println("Found it");
                    wordcount++;
                }
            }
        }
    }


    public String getData(){
        String output = "Filename: "+ fileName + "\n" + "Number of lines: " + numberOfLines + "\n" + "File size: " + fileSize + "kB" + "\n";
        return output;
    }

    public int getWordcount(){
        return wordcount;
    }
}
