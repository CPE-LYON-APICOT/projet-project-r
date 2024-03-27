module r.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens r.project to javafx.fxml;
    exports r.project;
}
