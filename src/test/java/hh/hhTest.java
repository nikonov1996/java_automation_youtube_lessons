package hh;

import core.SeleniumBaseTest;
import hh.Model.Resume;
import hh.Pages.HhResumePage;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static hh.Pages.HhResumePage.*;

public class hhTest extends SeleniumBaseTest {
    private final static String URL = "https://hh.ru/applicant/resumes/view?resume=1edf0c93ff095811d20039ed1f6a3638497073";

    @Test
    public void checkHashMapUserAttr(){
        HhResumePage resumePage = new HhResumePage(URL);
        Map<String,Object> expectedUser = new HashMap<>(){{
            put(GENDER,"М");
            put(AGE, 26);
            put(CITY, "Санкт-Петербург");
            put(CONFIRM_PHONE, true);
            put(READY_TO_RELOCATE,false);
        }};
        Map<String,Object> actualUser = resumePage.getUserAttributes();

        Assert.assertEquals(expectedUser,actualUser);
    }

    @Test
    public void checkUserAttrByClass(){
        HhResumePage resumePage = new HhResumePage(URL);
        Resume expectedAttrs = new Resume()
                .withGender("М")
                .withAge(26)
                .withCity("Санкт-Петербург")
                .withPhoneConfirm(true)
                .withReadyToRelocate(false);

        Resume actualAttrs = new Resume()
                .withGender(resumePage.getGenderHard())
                .withAge(resumePage.getAge())
                .withCity(resumePage.getCity())
                .withPhoneConfirm(resumePage.isPhoneConfirm())
                .withReadyToRelocate(resumePage.isReadyToRelocate());

        // Просто так сравнить два класса нельзя, так как метод equals сравнивает ссылки на объекты,а они разные
        // Поэтому первый способ это переопределить метод equals в классе-модели Resume
        Assert.assertEquals(expectedAttrs,actualAttrs);

        // Второй способ БЕЗ переопределения метода equals в классе-модели Resume
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedAttrs,actualAttrs));

        // Третий способ - сравнить не сами объекты а отдельно поля двух классов
        Assert.assertEquals(expectedAttrs.getAge(),actualAttrs.getAge());
        Assert.assertEquals(expectedAttrs.getCity(),actualAttrs.getCity()); // и т.д.

    }
}
