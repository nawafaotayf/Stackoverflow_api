package Model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class User {
    private String display_name;
    private int user_id;
    private int reputation;
    private Long last_access_date;

    public User(){

    }
    public User(String display_name, int user_id, int reputation, Long last_access_date) {
        this.display_name = display_name;
        this.user_id = user_id;
        this.reputation = reputation;
        this.last_access_date = last_access_date;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public Long getLast_access_date() {
        return last_access_date;
    }

    public void setLast_access_date(Long last_access_date) {
        this.last_access_date = last_access_date;
    }

    @Override
    public String toString() {
        String columnNames = String.format("%-10s\t%-10s\t%-10s\t%-10s\n",
                "userID", "display name", "Reputation","last access date"
        );
        String values = String.format("%-10s\t%-10s\t%-10s\t%-10s\n",
                user_id, display_name, reputation,last_access_date);

        return columnNames + values;

    }
}
