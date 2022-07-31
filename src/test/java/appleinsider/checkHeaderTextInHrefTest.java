package appleinsider;

import appleinsider.Pages.MainPage;
import appleinsider.Pages.SearchResultPage;
import core.SelenideBaseTest;
import org.junit.Assert;
import org.junit.Test;

public class checkHeaderTextInHrefTest extends SelenideBaseTest {
    private final static String BASE_URL = "https://appleinsider.ru/";
    private final static String SEARCH_STRING = "Чем iPhone 13 отличается от iPhone 12";
    private final static String EXPECTED_STRING = "iphone-13";

    @Test
    public void checkArticleHref(){
        Assert.assertTrue(new MainPage(BASE_URL)
                .searchByText(SEARCH_STRING)
                .getHrefFromFirstArticle()
                .contains(EXPECTED_STRING));
    }
}
