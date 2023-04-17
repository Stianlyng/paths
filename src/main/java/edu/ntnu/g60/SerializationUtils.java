package edu.ntnu.g60;

import java.io.*;

/**
 * Class for serializing and deserializing objects to and from files.
 * 
 * @author Stian Lyng
 */
public class SerializationUtils {

    /**
     * Serializes an object to a file.
     * 
     * @param obj The object to serialize.
     * @param file The file to serialize to.
     * @return True if the object was successfully serialized, false otherwise.
     * @throws IOException
     */
    public static boolean serializeToFile(Object obj, String path) throws IOException {
        File file = new File(path);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(obj);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Deserializes an object from a file.
     * 
     * @param file The file to deserialize from.
     * @return The deserialized object.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserializeFromFile(String path) throws IOException, ClassNotFoundException {
        File file = new File(path);
    
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

