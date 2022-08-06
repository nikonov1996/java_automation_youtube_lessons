package api.reqres_pojo.pojo;

public class UserTimeResponseData extends UserTimeData {

    private String updatedAt;
    public UserTimeResponseData(String name, String job, String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public String getUpdateAt() {
        return updatedAt;
    }
}
