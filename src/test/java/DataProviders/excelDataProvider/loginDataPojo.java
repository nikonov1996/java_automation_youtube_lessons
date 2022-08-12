package DataProviders.excelDataProvider;

public class loginDataPojo {
    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public loginDataPojo withEmail(String email) {
        this.email = email;
        return this;
    }

    public loginDataPojo withPassword(String password) {
        this.password = password;
        return this;
    }
}
