package View.UserDetailsPage;

import Model.User;
import Model.UserDetails;
import Presenter.Database;
import Presenter.UserFile;
import Presenter.UserRequests;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;


public class UserDetailsPage {

        @FXML
        private ImageView userimg;

        @FXML
        private Label accountId;

        @FXML
        private Label answerCount;

        @FXML
        private Label questionCount;

        @FXML
        private Label ID;

        @FXML
        private Label userLocation;

        @FXML
        private Label name;

        @FXML
        private Label reputation;

        @FXML
        private Label userType;

        @FXML
        private Label viewCount;

        @FXML
        private Button addToFileBtn;

        public void userDetails(int id) throws Exception {
                UserFile userFile = new UserFile();
                UserRequests userRequests = new UserRequests();
                ID.setText(String.valueOf(userRequests.userDetails(id).getUser_id()));
                name.setText(String.valueOf(userRequests.userDetails(id).getDisplay_name()));
                accountId.setText(String.valueOf(userRequests.userDetails(id).getAccount_id()));
                reputation.setText(String.valueOf(userRequests.userDetails(id).getReputation()));
                userLocation.setText(String.valueOf(userRequests.userDetails(id).getLocation()));
                userType.setText(String.valueOf(userRequests.userDetails(id).getUser_type()));
                viewCount.setText(String.valueOf(userRequests.userDetails(id).getView_count()));
                questionCount.setText(String.valueOf(userRequests.userDetails(id).getQuestion_count()));
                answerCount.setText(String.valueOf(userRequests.userDetails(id).getAnswer_count()));
                Image image = new Image(userRequests.userDetails(id).getProfile_image());
                userimg.setImage(image);
                addToFileBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                                try {
                                        userFile.UserDetailsFile(id);
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Information Dialog");
                                        alert.setHeaderText(null);
                                        alert.setContentText("user add to User Details.sofusers");
                                        alert.showAndWait();
                                }
                                catch (Exception e){

                                }
                        }
                });
        }

}
