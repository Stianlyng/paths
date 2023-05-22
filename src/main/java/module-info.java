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
    requires java.logging;
}
