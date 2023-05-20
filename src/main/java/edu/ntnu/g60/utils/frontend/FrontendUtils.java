package edu.ntnu.g60.utils.frontend;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.concurrent.Task;

public class FrontendUtils {
    
    /**
     * Delays the execution of a given continuation by the specified number of milliseconds.
     * @param millis the number of milliseconds to delay
     * @param continuation the task to execute after the delay
    */
    public static void delay(long millis, Runnable continuation){
        Task<Void> sleeper = new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }


    
    public static String[] splitTextIntoFourLines(String[] passages, int conversationPaneNumber) {
        String[] contents = new String[passages.length];
        for (int i = 0; i < passages.length; i++) {
            int braceIndex = passages[i].indexOf('{');
            contents[i] = (braceIndex >= 0) ? passages[i].substring(braceIndex + 3) : "";
        }
    
        String line = contents[conversationPaneNumber];
        String[] words = line.split("\\s+");
        int numGroups = (int) Math.ceil(words.length / 10.0);
    
        List<String[]> wordGroups = IntStream.range(0, numGroups)
                .mapToObj(i -> {
                    int startIndex = i * 10;
                    int endIndex = Math.min(startIndex + 10, words.length);
                    String[] group = Arrays.copyOfRange(words, startIndex, endIndex);
                    if (group.length < 10) {
                        group = Arrays.copyOf(group, 10);
                    }
                    return group;
                })
                .collect(Collectors.toList());
    
        String[] textLines = IntStream.range(0, Math.min(numGroups, 4))
                .mapToObj(i -> String.join(" ", wordGroups.get(i)))
                .toArray(String[]::new);
    
        if (textLines.length < 4) {
            textLines = Arrays.copyOf(textLines, 4);
        }
        return textLines;
    }
}
