package appleinsider.Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement searchButton = $x("//form");
    private final SelenideElement searchInput = $x("//input[@name='s']");

    public MainPage(String url){
        Selenide.open(url);
    }

    public void clickOnSearch(){
        searchButton.click();
    }

    /**
     * Выполняется ввод текста в поле поиска и нажатие Enter
     * @param text значение поиска
     */
    public SearchResultPage searchByText(String text){
        searchInput.setValue(text);
        searchInput.sendKeys(Keys.ENTER);
        return new SearchResultPage();
    }

}
