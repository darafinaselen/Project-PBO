module video {
    requires javafx.controls;
    requires javafx.fxml;

    opens video to javafx.fxml;
    exports video;
}
