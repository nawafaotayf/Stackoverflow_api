module com.example.stackoverflow_api {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;


    opens View to javafx.fxml;
    opens View.HomePage to javafx.fxml;
    opens View.UserDetailsPage to javafx.fxml;
    opens View.BookMarkPage to javafx.fxml;
    opens Presenter to com.google.gson, javafx.fxml;
    opens Model to com.google.gson, javafx.base;

    exports View;

}
