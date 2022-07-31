package appleinsider.Pages;

import com.codeborne.selenide.ElementsCollection;
import core.SeleniumBasePage;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchResultPage {
    private final ElementsCollection articleTitles = $$x("//h2//a");

    /**
     * Возвращает значение href из первой статьи
     * @return
     */
    public String getHrefFromFirstArticle(){
        return articleTitles.first().getAttribute("href");
    }
}
