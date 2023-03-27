package edu.ntnu.g60.frontend;

import java.io.FileNotFoundException;
import edu.ntnu.g60.Passage;
import javafx.stage.Stage;


public class LvlScene {
    static Stage stage = ApplicationFront.getStage();

    public static void scene(int lvl){
        try {
            stage.setScene(LoadingScene.scene());
        } catch (FileNotFoundException e1) {
            
            e1.printStackTrace();
        }
        
        ApplicationObjects.delay(2000, () -> {
            try {
                stage.setScene(FirstScene.scene(lvl));
            } catch (FileNotFoundException e1) {
                
                e1.printStackTrace();
            }
        });

        ApplicationFront.setLineNumber(1);
        String type = Passage.getTypeOfTextAtLineNumber(ApplicationFront.getLineNumber());
        ApplicationFront.setAmountOfLines(Passage.getAmountOfTextLines());

        ApplicationObjects.delay(2000, () -> {
            try {
                stage.setScene(NewTalkingScene.scene(type, lvl));
            } catch (FileNotFoundException e1) {
                
                e1.printStackTrace();
            }
        });
    }

}
