module com.example.cronometrofx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.cronometrofx to javafx.fxml;
    exports com.example.cronometrofx;
    exports com.example.cronometrofx.Controller;
    opens com.example.cronometrofx.Controller to javafx.fxml;
}