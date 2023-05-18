module edu.ntnu.g60 {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    requires transitive javafx.web;
    requires org.apache.commons.io;
    
    requires com.fasterxml.jackson.databind;
    opens edu.ntnu.g60;
    exports edu.ntnu.g60.views to javafx.graphics;
    exports edu.ntnu.g60.entities to com.fasterxml.jackson.databind;
    exports edu.ntnu.g60.models.actions;
    exports edu.ntnu.g60.models.game;
    exports edu.ntnu.g60.models.goals;
    exports edu.ntnu.g60.models.passage;
    exports edu.ntnu.g60.models.player;
    exports edu.ntnu.g60.models.story;
}
