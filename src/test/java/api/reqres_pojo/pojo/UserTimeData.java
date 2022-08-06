package api.reqres_pojo.pojo;

public class UserTimeData {
    private String name;
    private String job;

    public UserTimeData(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
