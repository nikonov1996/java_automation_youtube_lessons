package wiki;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import core.SelenideBaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class WikiTest extends SelenideBaseTest {
    private final static String BASE_URL = "https://ru.wikipedia.org/wiki/Java";

    @Test
    public void workWithElementCollectionByForCycle(){
        Selenide.open(BASE_URL);
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();

        // Достаем ссылки из каждого элемента коллекции разными циклами
        // Через цикл for
        for(int i = 0;i< hrefs.size();i++){
            links.add(hrefs.get(i).getAttribute("href"));
        }

        // Через цикл foreach
        /*
        for (SelenideElement element:hrefs) {
            links.add(element.getAttribute("href"));
        }
        */

        // Перебор полученных ссылок циклами

        // for
        /*
        for (int i=0;i<links.size();i++){
            Selenide.open(links.get(i));
            Assert.assertEquals(
                    links.get(i),
                    WebDriverRunner.getWebDriver().getCurrentUrl());
        }
        */

        // while
        while (links.size()>0){
            int randomNumber = new Random().nextInt(links.size());
            Selenide.open(links.get(randomNumber));
            Assert.assertEquals(
                    links.get(randomNumber),
                    WebDriverRunner.getWebDriver().getCurrentUrl());
            links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());
        }

    }

    @Test
    public void workWithElementCollectionByStreamApi(){
        Selenide.open(BASE_URL);
        ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
        List<String> links = new ArrayList<>();

        // Достаем ссылки из каждого элемента коллекции через Stream API
        hrefs.stream().forEach(x->links.add(x.getAttribute("href")));

        //Перебор полученных ссылок
        links.stream().forEach(x->Selenide.open(x));
        // links.stream().forEach(Selenide::open); // аналогичная запись

        // получение списка с длиной ссылки
        List<Integer> linksLenght = hrefs.stream().map(x->x.getAttribute("href").length()).collect(Collectors.toList());

    }
}
