package edu.ntnu.g60.views;

import java.util.List;

import edu.ntnu.g60.models.actions.Action;
import edu.ntnu.g60.models.game.GameManager;
import edu.ntnu.g60.models.passage.Link;
import edu.ntnu.g60.models.passage.Passage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayGame {

    private static final int PADDING = 20;

    private Scene scene;

    public PlayGame(Stage primaryStage, Passage passage, int WIDTH, int HEIGHT) {

        Label label1 = new Label("Play tha game!");
        Label label3 = new Label(passage.getTitle() + "\n" + passage.getContent());

        List<Link> links = passage.getLinks();

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0, 0, 20, 0));  

        for (int i = 0; i < links.size(); i++) {
            final int index = i;
            Button linkButton = new Button(links.get(i).getText());
            linkButton.setOnAction(e -> {

                Link selectedLink = links.get(index);

                for (Action action : selectedLink.getActions()) {
                    action.execute(GameManager.getInstance().getGame().getPlayer());
                }

                Passage newPassage = GameManager.getInstance().getGame().go(links.get(index));
                PlayGame newView = new PlayGame(primaryStage, newPassage, WIDTH, HEIGHT);
                primaryStage.setScene(newView.getScene());
            });
            gridPane.add(linkButton, i, 0);
        }

        VBox vBox = new VBox(label1, label3);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        vBox.setSpacing(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setBottom(gridPane);

        scene = new Scene(borderPane, WIDTH, HEIGHT);
    }

    /**
     * Returns the scene for this view.
     */
    public Scene getScene() {
        return scene;
    }
}