package hh.Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResumePage {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");
    private final SelenideElement city = $x("//span[@data-qa='resume-personal-address']");
    private final SelenideElement locateData = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final SelenideElement phoneValidTick = $x("//div[@data-qa='resume-contacts-phone']/span[1]");

    public static String GENDER = "gender";
    public static String AGE = "age";
    public static String CITY = "city";
    public static String CONFIRM_PHONE = "phone is confirm";
    public static String READY_TO_RELOCATE = "ready to relocate";

    public Map<String,Object> getUserAttributes(){
        // Первый способ записи значений в hash карту:
/*        Map<String,Object> attrs = new HashMap<>();
        attrs.put(GENDER,getGender());
        attrs.put(AGE,getAge());
        attrs.put(CITY,getCity());
        attrs.put(CONFIRM_PHONE,isPhoneConfirm());
        attrs.put(READY_TO_RELOCATE,isReadyToRelocate());
 */
        //Второй способ записи значений в hash карту:
        return new HashMap<>(){{
            put(GENDER,getGenderHard());
            put(AGE,getAge());
            put(CITY,getCity());
            put(CONFIRM_PHONE,isPhoneConfirm());
            put(READY_TO_RELOCATE,isReadyToRelocate());
        }};

    }

    public HhResumePage(String url){
        Selenide.open(url);
    }

    public boolean isReadyToRelocate(){
        return !locateData.getText().split(", ")[1].equals("не готов к переезду");
    }

    public boolean isPhoneConfirm(){
        return phoneValidTick.isDisplayed();
    }

    public String getCity() {
        return city.getText();
    }

    public int getAge(){
        return Integer.parseInt(age.getText().replaceAll("\\D+",""));
    }

    // Первый способ получения первого символа из строки
    public String getGender(){
        String genderValue = gender.getText();
        if(genderValue == "Мужчина"){
            return "М";
        }else if(genderValue == "Женщина"){
            return "Ж";
        }else{
            return "Error gender value";
        }
    }

    // Второй способ получения первого символа из строки
    public String getGenderHard(){
        return gender.getText().equals("Мужчина") ? "М" : "Ж";
    }

}
