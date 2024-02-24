package View;

import Model.User;
import Presenter.Database;
import Presenter.UserFile;
import Presenter.UserRequests;
import View.HomePage.HomePage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.List;

import static javafx.application.Application.launch;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
//        UserFile userFile = new UserFile();
//        userFile.UserListFile();
//        userFile.UserDetailsFile(6309);
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        HomePage homePage = new HomePage();
        homePage.start();

    }
    }