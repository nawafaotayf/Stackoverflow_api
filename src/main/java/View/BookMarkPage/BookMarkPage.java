package View.BookMarkPage;

import Model.User;
import Model.UserDetails;
import Presenter.Database;
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
import java.util.List;
import java.util.ResourceBundle;

public class BookMarkPage implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    private URL fxmlFile;

    @FXML
    private TableView<UserDetails> bookmarkTable;
    @FXML
    private TableColumn<UserDetails, Integer> accountId;

    @FXML
    private TableColumn<UserDetails, Integer> answerCount;

    @FXML
    private TableColumn<UserDetails, UserDetails> delete;

    @FXML
    private TableColumn<UserDetails, Integer> id;

    @FXML
    private TableColumn<UserDetails, String> name;

    @FXML
    private TableColumn<UserDetails, Integer> questionCount;

    @FXML
    private TableColumn<UserDetails, Integer> reputation;

    @FXML
    private TableColumn<UserDetails, String> userLocation;

    @FXML
    private TableColumn<UserDetails, String> userType;

    public void switchToHomePage(ActionEvent event) throws IOException {
        fxmlFile = new File("src/main/java/View/HomePage/home_page.fxml").toURI().toURL();
        root = FXMLLoader.load(fxmlFile);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database database = new Database();
        List<UserDetails> userDetailsList = database.showBookMarkList(database.sqlConnection());
        ObservableList<UserDetails> userDetails = FXCollections.observableArrayList(userDetailsList);
        id.setCellValueFactory(new PropertyValueFactory<UserDetails, Integer>("user_id"));
        name.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("display_name"));
        reputation.setCellValueFactory(new PropertyValueFactory<UserDetails, Integer>("reputation"));
        accountId.setCellValueFactory(new PropertyValueFactory<UserDetails, Integer>("account_id"));
        userLocation.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("location"));
        userType.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("user_type"));
        questionCount.setCellValueFactory(new PropertyValueFactory<UserDetails, Integer>("question_count"));
        answerCount.setCellValueFactory(new PropertyValueFactory<UserDetails, Integer>("answer_count"));
        delete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delete.setCellFactory(param -> new TableCell<UserDetails, UserDetails>(){
            private final Button button = new Button("DeBookMark");
            @Override
            protected void updateItem(UserDetails user, boolean empty) {
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
                                    database.deleteUserFromBookmark(connection, user.getUser_id());
                                    userDetails.remove(user);

                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("User has been debookmark");
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
        bookmarkTable.setItems(userDetails);
    }
}

