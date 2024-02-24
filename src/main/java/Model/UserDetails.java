package Model;

public class UserDetails extends User{
    private int account_id;
    private String location;
    private String user_type;
    private String profile_image;
    private int view_count;
    private int question_count;
    private int answer_count;

    public UserDetails(){

    }

    public UserDetails(String display_name, int user_id, int reputation, long last_access_date, int account_id, String location, String user_type, String profile_image, int view_count, int question_count, int answers_count) {
        super(display_name, user_id, reputation, last_access_date);
        this.account_id = account_id;
        this.location = location;
        this.user_type = user_type;
        this.profile_image = profile_image;
        this.view_count = view_count;
        this.question_count = question_count;
        this.answer_count = answers_count;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getQuestion_count() {
        return question_count;
    }

    public void setQuestion_count(int question_count) {
        this.question_count = question_count;
    }

    public int getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(int answer_count) {
        this.answer_count = answer_count;
    }

    @Override
    public String toString() {
        String columnNames = String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n",
                "userId", "accountId", "displayName", "reputation",
                "location", "userType", "VIEW_COUNT", "QUESTION_COUNT",
                "ANSWER_COUNT", "PROFILE_IMAGE");

        String values = String.format("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n",
                getUser_id(), account_id, getDisplay_name(),
                getReputation(),
                location,
                user_type,
                view_count,
                question_count,
                answer_count,
                profile_image);

        return columnNames + values;
    }
}
