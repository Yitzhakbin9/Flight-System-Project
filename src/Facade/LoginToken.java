package Facade;

public class LoginToken {

    private int user_id;
    private String name;
    private int role; // 1-customer ; 2-airline , 3-admin


    public LoginToken(int id, String name, int role) {
        this.user_id = id;
        this.name = name;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "LoginToken{" +
                "id=" + user_id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
