module co.edu.uptcSoft {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;

    opens co.edu.uptcSoft.view to javafx.fxml;
    opens co.edu.uptcSoft.model to com.google.gson;
    exports co.edu.uptcSoft.view;
}
