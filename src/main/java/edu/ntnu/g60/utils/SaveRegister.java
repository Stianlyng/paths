package edu.ntnu.g60.utils;

import java.io.IOException;

import edu.ntnu.g60.utils.serialization.SerializationUtils;

public class SaveRegister {
    private static final String PATH = "src/main/resources/textFiles/saves.ser";
    public static Save[] saves = new Save[3];

    public static Save getSave(int number) throws ClassNotFoundException, IOException{
        Save[] getSaves = (Save[]) SerializationUtils.deserializeFromFile(PATH);
        return getSaves[number - 1];
    }

    public static void setSave(Save newSave, int number) throws IOException, ClassNotFoundException{
        saves = (Save[]) SerializationUtils.deserializeFromFile(PATH);
        saves[number - 1] = newSave;
        SerializationUtils.serializeToFile(saves, PATH);
    }

    public static void setDefaultSaves() throws IOException{
        SerializationUtils.serializeToFile(saves, PATH);
    }

    //funker ikke. hele metoden bare kaster en exception. prøv å putt metoden i en excetion
    public static boolean saveExists(int number) throws ClassNotFoundException, IOException {
        Save save = getSave(number);
        if(save == null){
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        setDefaultSaves();
    }
}
