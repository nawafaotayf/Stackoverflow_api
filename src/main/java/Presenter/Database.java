package Presenter;

import Model.UserDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    /*
        Don't forget to change this to your database details
    */

    String url = "jdbc:mysql://localhost:3306/stackoverflow";
    String username = "root";
    String password = "admin";
    Connection connection;
    Statement statement;

    public Connection sqlConnection(){
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public String saveInBookmark(Connection connection, int id) {
        UserRequests userRequests = new UserRequests();
        String saveUserQuery = "INSERT INTO `stackoverflow`.`users`" +
                "(`user_id`, `display_name`, `reputation`, `account_id`, `location`," +
                " `user_type`, `profile_image`, `view_count`, `question_count`, `answer_count`)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(saveUserQuery)){
            statement.setString(1, String.valueOf(userRequests.userDetails(id).getUser_id()));
            statement.setString(2, String.valueOf(userRequests.userDetails(id).getDisplay_name()));
            statement.setString(3, String.valueOf(userRequests.userDetails(id).getReputation()));
            statement.setString(4, String.valueOf(userRequests.userDetails(id).getAccount_id()));
            statement.setString(5, String.valueOf(userRequests.userDetails(id).getLocation()));
            statement.setString(6, String.valueOf(userRequests.userDetails(id).getUser_type()));
            statement.setString(7, String.valueOf(userRequests.userDetails(id).getProfile_image()));
            statement.setString(8, String.valueOf(userRequests.userDetails(id).getView_count()));
            statement.setString(9, String.valueOf(userRequests.userDetails(id).getQuestion_count()));
            statement.setString(10, String.valueOf(userRequests.userDetails(id).getAnswer_count()));

            int rowInserted = statement.executeUpdate();
            if(rowInserted>0){
                System.out.println("Data inserted!");
            }
            return "add to bookmarked";
        } catch (Exception e) {
            return "This user already in the bookmarked!";
        }
    }
    public List<UserDetails> showBookMarkList(Connection connection){
        String selectQuery = "SELECT * FROM stackoverflow.users";
        List<UserDetails> userDetail = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserDetails userDetails = new UserDetails();
                userDetails.setUser_id(resultSet.getInt("user_id"));
                userDetails.setDisplay_name(resultSet.getString("display_name"));
                userDetails.setReputation(resultSet.getInt("reputation"));
                userDetails.setAccount_id(resultSet.getInt("account_id"));
                userDetails.setLocation(resultSet.getString("location"));
                userDetails.setUser_type(resultSet.getString("user_type"));
                userDetails.setProfile_image(resultSet.getString("profile_image"));
                userDetails.setView_count(resultSet.getInt("view_count"));
                userDetails.setQuestion_count(resultSet.getInt("question_count"));
                userDetails.setAnswer_count(resultSet.getInt("answer_count"));
                userDetail.add(userDetails);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return userDetail;
    }

    public void deleteUserFromBookmark(Connection connection, int id){
        String deleteQuery = "DELETE FROM stackoverflow.users WHERE user_id = ?";

        try(PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            int deleted = statement.executeUpdate();

            if(deleted>0){
                System.out.println("deleted");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


}
