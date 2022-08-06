package api.reqres.pojo;

import hh.Model.Resume;

import java.util.Objects;

public class UserSuccessRegisterData {
    private Integer id;
    private String token;

    public UserSuccessRegisterData(Integer id, String token) {
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserSuccessRegisterData that = (UserSuccessRegisterData) obj;
        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(token, that.token);
    }
}
