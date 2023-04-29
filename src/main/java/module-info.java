module edu.ntnu.g60 {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
    
    opens edu.ntnu.g60;
    exports edu.ntnu.g60.views to javafx.graphics;
}
