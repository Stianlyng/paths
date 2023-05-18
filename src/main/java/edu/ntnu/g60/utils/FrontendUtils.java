package edu.ntnu.g60.utils;

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

}
