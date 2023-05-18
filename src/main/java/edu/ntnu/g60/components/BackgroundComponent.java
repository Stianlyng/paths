package edu.ntnu.g60.components;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class BackgroundComponent {

    public static Background createBackground(String fileName) {
        Image bgImage = new Image(BackgroundComponent.class.getResource("/images/backgrounds/" + fileName).toExternalForm());
        BackgroundSize backgroundSize = new BackgroundSize(bgImage.getWidth(), bgImage.getHeight(), true, true, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }
}