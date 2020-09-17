package ua.univer.maven.Model;

public class User {
    private int user_id;
    private String user_email;
    private String password;
    private String phone_number;
    private String userabil;

    public User(int user_id, String user_email, String password, String phone_number, String userabil) {
        this.user_id = user_id;
        this.user_email = user_email;
        this.password = password;
        this.phone_number = phone_number;
        this.userabil = userabil;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUserabil() {
        return userabil;
    }

    public void setUserabil(String userabil) {
        this.userabil = userabil;
    }

    @Override
    public String toString() {
        return "User id=" + user_id +
                ", email :" + user_email +
                ", Phone number :" + phone_number +
                ", Type of the user :" + userabil;
    }
}
