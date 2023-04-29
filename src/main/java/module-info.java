module edu.ntnu.g60 {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.media;
<<<<<<< src/main/java/module-info.java
    requires com.fasterxml.jackson.databind;

    opens edu.ntnu.g60.frontend to javafx.fxml;
    exports edu.ntnu.g60.frontend;

    exports edu.ntnu.g60.entities to com.fasterxml.jackson.databind;
}
=======
    
    opens edu.ntnu.g60;
    exports edu.ntnu.g60.views to javafx.graphics;
}
>>>>>>> src/main/java/module-info.java