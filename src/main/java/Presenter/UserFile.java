package Presenter;

import Model.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserFile {
    public void UserListFile() throws Exception{
        File fileName = new File("User List.sofusers");
        FileWriter fileWriter = new FileWriter("User List.sofusers");
        UserRequests userRequests = new UserRequests();
        if(fileName.createNewFile()){
            System.out.println(fileName.getName());
        }
        fileWriter.write(String.valueOf(userRequests.userList()));
        fileWriter.close();

    }
    public void UserDetailsFile(int id) throws Exception {
        File fileName = new File("User Details.sofusers");
        FileWriter fileWriter = new FileWriter("User Details.sofusers", true);
        UserRequests userRequests = new UserRequests();
        if(fileName.createNewFile()){
            System.out.println(fileName.getName());
            System.out.println("test");
        }
        fileWriter.write(String.valueOf(userRequests.userDetails(id)));
        fileWriter.close();
    }

}
