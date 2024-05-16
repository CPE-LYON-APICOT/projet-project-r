module r.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires javafx.media;
    opens r.project to javafx.fxml;
    exports r.project;
}
