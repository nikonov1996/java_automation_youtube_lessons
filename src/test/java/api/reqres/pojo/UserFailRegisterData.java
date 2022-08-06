package api.reqres.pojo;

import java.util.Objects;

public class UserFailRegisterData {
    private String error;

    public UserFailRegisterData(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserFailRegisterData that = (UserFailRegisterData) obj;
        return Objects.equals(error, that.error);

    }
}
