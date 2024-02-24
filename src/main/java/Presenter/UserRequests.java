package Presenter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import Model.User;
import Model.UserDetails;
import com.google.gson.*;

public class UserRequests {

    public List<User> userList() throws Exception {
        String apiUrl = "https://api.stackexchange.com/2.3/users?order=desc&sort=reputation&site=stackoverflow";
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Encoding", "gzip");
        InputStream inputStream = new GZIPInputStream(connection.getInputStream());
        Reader reader =  new InputStreamReader(inputStream, "UTF-8");
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
        JsonArray items = jsonObject.getAsJsonArray("items");
        List <User> users = new ArrayList<>();
        Gson gson = new Gson();
        for(JsonElement item: items){
            users.add(gson.fromJson(item, User.class));
        }
        return users;
    }
    public UserDetails userDetails(int id) throws Exception {
        String apiUrl = "https://api.stackexchange.com/2.3/users/"+id+"?order=desc&sort=reputation&site=stackoverflow&filter=!*Mg4PjfXdyDn18p.";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Encoding", "gzip");
        InputStream inputStream = new GZIPInputStream(connection.getInputStream());
        Reader reader =  new InputStreamReader(inputStream, "UTF-8");
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(reader).getAsJsonObject();
        JsonArray items = jsonObject.getAsJsonArray("items");

        Gson gson = new Gson();
        return gson.fromJson(items.get(0), UserDetails.class);
    }

}

