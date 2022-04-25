module com.example.primefinderclient.primefinderclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.primefinderclient.primefinderclient to javafx.fxml;
    exports com.example.primefinderclient.primefinderclient;
}