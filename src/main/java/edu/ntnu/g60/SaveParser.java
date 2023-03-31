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
        
        switch (number){
            case 1:
                line = reader.readLine().trim().replace("1, ", "");
            case 2:
                reader.readLine();
                line = reader.readLine().trim().replace("2, ", "");
            case 3:
                reader.readLine();
                reader.readLine();
                line = reader.readLine().trim().replace("3, ", "");
            default:
        }
        
        Link link = new Link("", line);
        reader.close();
        return link;
    }
}
