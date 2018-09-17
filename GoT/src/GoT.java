import netscape.javascript.JSObject;

import java.net.*;
import java.io.*;
import org.json.*;

import java.util.ArrayList;
import java.util.Scanner;

/*
    It should allow me to lookup a character by number. (Display that characters info.)
    It should then allow me to display the names of all sworn members of the previous characters house.
    It must lookup all povCharacters in the books published by "Bantam Books", it must then display this information in a grid (Which characters are point-of-view in which books?)
 */

public class GoT{
    public static void main(String[] args) throws Exception {
        //Get user input:
        //System.out.print("Enter character number: ");
        //Scanner sc = new Scanner(System.in);
        //String number = sc.nextLine();
        //String url = "https://anapioficeandfire.com/api/characters/" + number;
        //getCharacter(url);
        getPov();
    }


    public static void getPov() {
        String url = "https://anapioficeandfire.com/api/books/";
        ArrayList<String> books = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        JSONArray jsonArray = null;
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
            }
            jsonArray = new JSONArray(stringBuilder.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                ArrayList<String> characters = new ArrayList<>();
                JSONObject bookObject = jsonArray.getJSONObject(i);

                if(bookObject.getString("publisher").equals("Bantam Books")){
                    //Add book to list
                    books.add(bookObject.getString("name"));
                    JSONArray povArray = bookObject.getJSONArray("povCharacters");
                    for(int x = 0; x < povArray.length(); x++){
                        JSONObject charObject = jsonObjectMaker(povArray.getString(x));
                        //Add character to list
                        characters.add(charObject.getString("name"));
                        System.out.println(charObject.getString("name"));
                        Thread.sleep(100);
                    }
                }
            }
        }catch (IOException e){
            System.out.println(e);
        }catch (JSONException e){
            System.out.println(e);
        }catch (InterruptedException e){
            System.out.println(e);
        }





    }

    //***************************************Get Character**************************************************************

    public static void getCharacter(String url) {

        try {
            JSONObject object = jsonObjectMaker(url);
            System.out.println(object.getString("name"));
            System.out.println("Do you wish to display sworn members of this characters previous house? (yes/no)");
            Scanner sc = new Scanner(System.in);
            String answer = sc.nextLine();

            if (answer.equals("yes")) {
                StringBuilder stringBuilder2 = new StringBuilder();
                String line2;

                JSONArray urlArray = object.getJSONArray("allegiances");

                //Set new url:
                String url2 = urlArray.get(0).toString();
                JSONObject object2 = jsonObjectMaker(url2);
                JSONArray urlArray2 = object2.getJSONArray("swornMembers");

                //Iterate through each url and print names:
                for (int i = 0; i < urlArray2.length(); i++){
                    JSONObject character = jsonObjectMaker(urlArray2.getString(i));
                    System.out.println(character.getString("name"));
                    Thread.sleep(100);
                }
            } else {
                System.out.println("Good bye.");
            }
        }catch (JSONException e){
            System.out.println(e);
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }

    //******************************************Makes JSON Object from string*******************************************
    public static JSONObject jsonObjectMaker(String url){
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        JSONObject object = null;
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
            }
            object = new JSONObject(stringBuilder.toString());

        }catch (IOException e){
            System.out.println(e);
        }catch (JSONException e){
            System.out.println(e);
        }
        return object;
    }
    //******************************************************************************************************************
}

