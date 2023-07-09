package page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HotelsPage {
    private final static String HOTEL_NAME = "//div[@data-testid='title']";


    public List<String> getListOfHotelsNames() {
        List<String> hotelsNames = new ArrayList<>();
        for (SelenideElement element : $$(By.xpath(HOTEL_NAME))) {
            hotelsNames.add(element.getText());
        }
        System.out.println(hotelsNames);
        return hotelsNames;
    }

    public String getRatingOfHotel(String hotel) {
        String HOTEL_RATING = String.format("//div[contains(text(), '%s')]/ancestor::div[@data-testid='property-card']//div[contains(@aria-label, 'Scored')]", hotel);
        return $(By.xpath(HOTEL_RATING)).getText();
    }
}
