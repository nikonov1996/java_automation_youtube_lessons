package core;

import org.openqa.selenium.WebDriver;

abstract public class SeleniumBasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver){
        driver = webDriver;
    }

}
