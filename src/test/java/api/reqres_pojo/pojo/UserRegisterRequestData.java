package api.reqres_pojo.pojo;

public class UserRegisterRequestData {

    private String email;
    private String password;

    public UserRegisterRequestData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
