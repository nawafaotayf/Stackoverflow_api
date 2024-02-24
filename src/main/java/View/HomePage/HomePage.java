package View.HomePage;

import Model.User;
import Presenter.UserFile;
import View.UserDetailsPage.UserDetailsPage;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import Presenter.UserRequests;
import Presenter.Database;


public class HomePage implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private URL fxmlFile;

    private UserRequests userRequests;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, Integer> userID;

    @FXML
    private TableColumn<User, Integer> userReputation;

    @FXML
    private TableColumn<User, Long> userLastAccessDate;

    @FXML
    private TableColumn<User, User> userDetailsButton;
    @FXML
    private TableColumn<User, User>userBookMarkedButton;

    public void start() throws Exception {
           /*
           i use (URL) because i couldn't use getClass().getResource("/home_page.fxml").
           its give me always an ""java.lang.NullPointerException: Location is required"" error.
            */
        fxmlFile = new File("src/main/java/View/HomePage/home_page.fxml").toURI().toURL();
        root = FXMLLoader.load(fxmlFile);
        stage = new Stage();
        scene = new Scene(root);

        stage.setTitle("Stack OverFlow");
        stage.setScene(scene);
        stage.show();
    }
    
    public void addFileToList() throws Exception {
        UserFile userFile = new UserFile();
        userFile.UserListFile();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("users add to User List.sofusers");
        alert.showAndWait();
    }

    public void switchToBookMark(ActionEvent event) throws IOException {
        fxmlFile = new File("src/main/java/View/BookMarkPage/book_mark.fxml").toURI().toURL();
        root = FXMLLoader.load(fxmlFile);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UserRequests user = new UserRequests();
            List<User> users = user.userList();
            ObservableList<User> userList = FXCollections.observableArrayList();
            for(User userNames :users) {
                userList.add(new User(userNames.getDisplay_name(), userNames.getUser_id(), userNames.getReputation(), userNames.getLast_access_date()));
            }
                userName.setCellValueFactory(new PropertyValueFactory<User, String>("display_name"));
                userID.setCellValueFactory(new PropertyValueFactory<User, Integer>("user_id"));
                userReputation.setCellValueFactory(new PropertyValueFactory<User, Integer>("reputation"));
                userLastAccessDate.setCellValueFactory(new PropertyValueFactory<User, Long>("last_access_date"));

                userDetailsButton.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
                userDetailsButton.setCellFactory(param -> new TableCell<User, User>(){
                    private final Button button = new Button("Details");
                            @Override
                            protected void updateItem(User user, boolean empty) {
                                super.updateItem(user, empty);
                                if (user == null){
                                    setGraphic(null);
                                    return;
                                }
                                setGraphic(button);
                                button.setOnAction(event -> {
                                    try {
                                        fxmlFile = new File("src/main/java/View/UserDetailsPage/user_details.fxml").toURI().toURL();
                                        FXMLLoader loader = new FXMLLoader(fxmlFile);
                                        root = loader.load();
                                        UserDetailsPage contorller = loader.getController();
                                        contorller.userDetails(user.getUser_id());

                                         stage = new Stage();
                                         scene= new Scene(root);
                                         stage.setScene(scene);
                                         stage.show();
                                    }
                                    catch (Exception e){
                                        System.out.println(e);
                                    }
                                        }
                                        );
                            }
                        });
            userBookMarkedButton.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
            userBookMarkedButton.setCellFactory(param -> new TableCell<User, User>(){
                private final Button button = new Button("BookMark");
                @Override
                protected void updateItem(User user, boolean empty) {
                    super.updateItem(user, empty);
                    if (user == null){
                        setGraphic(null);
                        return;
                    }
                    setGraphic(button);
                    button.setOnAction(event -> {
                                try {
                                    Database database = new Database();
                                    Connection connection = database.sqlConnection();
                                    if (connection != null) {
                                       String re = database.saveInBookmark(connection, user.getUser_id());
                                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                       alert.setTitle("add bookmark");
                                       alert.setContentText(re);
                                       alert.setHeaderText(null);
                                       alert.showAndWait();

                                    }
                                }

                                catch (Exception e){
                                    System.out.println(e);
                                }
                            }
                    );
                }
            });

                userTable.setItems(userList);
        } catch (Exception e) {
            System.out.println(e);;
        }
    }

}



