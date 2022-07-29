package django_helpdesk_demo.Pages;

import com.typesafe.config.Config;
import core.SeleniumBasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import readProperties.ConfigProvider;

public class MainPage extends SeleniumBasePage {

    /*
    Нежелательное объявление элементов и их поиск.
    При таком способе элементы ищуться и присваиваются в переменные сразу. Но существуют элементы которые отображаются на
    странице только в процессе взаимодействия со страницей.
    Также старница может не успеть прогрузится.

    Объявлять локаторы таким способом можно, но использовать findElement по этому локатору можно только в момент взаимодействия.
     */
    //private final By queueList = By.id("id_queue");
    //private final By queueList2 = By.xpath("//select[@id='id_queue']");
    //private WebElement queueListElement = driver.findElement(queueList);

    /*
    Лучший способ. Инициализация элемента происходит тогда,когда к нему обращаемся, автоматически.
     */
    @FindBy(xpath = "//select[@id='id_queue']")
    private WebElement queueList;

    @FindBy(xpath = "//select[@id='id_queue']//option[@value='1']")
    private WebElement queueFirstValue;

    @FindBy(id = "id_title")
    private WebElement title;

    @FindBy(id = "id_body")
    private WebElement body;

    @FindBy(xpath = "//*[@id='id_due_date']")
    private WebElement dateField;

    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']//a[text()='23']")
    private WebElement dateValue;

    @FindBy(id="id_submitter_email")
    private WebElement email;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(id = "userDropdown")
    private WebElement loginButton;

    public MainPage(){
        driver.get(ConfigProvider.URL);
        PageFactory.initElements(driver,this);
    }

    public MainPage createTicket(String titleValue, String bodyValue, String emailValue){
        queueList.click();
        queueFirstValue.click();
        title.sendKeys(titleValue);
        body.sendKeys(bodyValue);
        dateField.click();
        dateValue.click();
        email.sendKeys(emailValue);
        submitButton.click();
        return this;
    }

    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }

}
