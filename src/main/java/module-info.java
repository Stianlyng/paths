module edu.ntnu.g60 {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    
    opens edu.ntnu.g60.frontend to javafx.fxml;
    exports edu.ntnu.g60.frontend;
}
