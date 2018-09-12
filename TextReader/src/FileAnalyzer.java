import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalyzer {
    private String fileName;
    private double fileSize;
    private int numberOfLines;
    private String errorMessage=null;

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
            errorMessage = "File not found.";
        }
    }


    public String getData(){
        String output = "Filename: "+ fileName + "\n" + "Number of lines: " + numberOfLines + "\n" + "File size: " + fileSize + "kB" + "\n";
        return output;
    }
}
