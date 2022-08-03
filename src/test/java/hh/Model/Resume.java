package hh.Model;

import java.util.Objects;

public class Resume {
    private String gender;
    private String city;
    private Integer age;
    private Boolean isPhoneConfirm;
    private Boolean isReadyToRelocate;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Resume that = (Resume) obj;
        if (!Objects.equals(gender, that.gender)) return false;
        if (!Objects.equals(city, that.city)) return false;
        if (!Objects.equals(age, that.age)) return false;
        if (!Objects.equals(isPhoneConfirm, that.isPhoneConfirm)) return false;
        return Objects.equals(isReadyToRelocate, that.isReadyToRelocate);
    }

    public Resume withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Resume withCity(String city) {
        this.city = city;
        return this;
    }

    public Resume withAge(Integer age) {
        this.age = age;
        return this;
    }

    public Resume withPhoneConfirm(Boolean phoneConfirm) {
        isPhoneConfirm = phoneConfirm;
        return this;
    }

    public Resume withReadyToRelocate(Boolean readyToRelocate) {
        isReadyToRelocate = readyToRelocate;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public Boolean getPhoneConfirm() {
        return isPhoneConfirm;
    }

    public Boolean getReadyToRelocate() {
        return isReadyToRelocate;
    }
}
