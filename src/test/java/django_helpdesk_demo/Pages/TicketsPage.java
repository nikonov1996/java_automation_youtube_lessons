package django_helpdesk_demo.Pages;

import core.SeleniumBasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TicketsPage extends SeleniumBasePage {

    @FindBy(id = "search_query")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='tickettitle']")
    private WebElement ticket;

    public TicketsPage(){
        PageFactory.initElements(driver,this);
    }

    public TicketPage findTicket(String strValue){
       searchField.sendKeys(strValue, Keys.ENTER);
       ticket.click();
       return new TicketPage();
    }

}
