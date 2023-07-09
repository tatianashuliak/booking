package page;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BookingMainPage {
    private static final String MAIN_PAGE_URL = "https://www.booking.com/searchresults.en-gb.html";
    private static final String CITY_FIELD = "//input[@name='ss']";
    private static final long DURATION_OF_EXPLICITLY_WAIT = 10;
    private static final String SEARCH_BUTTON = "//button[@type='submit']";

    public BookingMainPage openMainPage() {
        open(MAIN_PAGE_URL);
        return this;
    }

    public void enterCityToCityField(String city) {
        $(By.xpath(CITY_FIELD)).shouldBe(Condition.visible, Duration.ofSeconds(DURATION_OF_EXPLICITLY_WAIT)).sendKeys(city);
    }

    public void clickSearchButton() {
        $(By.xpath(SEARCH_BUTTON)).click();
    }

}
