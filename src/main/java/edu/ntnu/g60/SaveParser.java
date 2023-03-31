package edu.ntnu.g60;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class SaveParser {
    
    
    public static Link getSaveFile(int number) throws IOException {
        String path = "src/main/resources/textFiles/";
        BufferedReader reader = new BufferedReader(new FileReader(path + "saves" + ".txt"));
        String line = "";
        reader.readLine();
        String[] parts = new String[3];
        String text = "";
        String refrence = "";
        
        switch (number){
            case 1:
                parts = line.split(",");    
                text = parts[1];
                refrence = parts[2];
       
            case 2:
                reader.readLine();
                parts = line.split(",");    
                text = parts[1];
                refrence = parts[2];
            case 3:
                reader.readLine();
                reader.readLine();
                parts = line.split(",");    
                text = parts[1];
                refrence = parts[2];
            default:
        }
        
        Link link = new Link(text, refrence);
        reader.close();
        return link;
    }

    public static void overwriteLine(Link link){
        
    }
}
