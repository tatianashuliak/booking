package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import page.BookingMainPage;
import page.HotelsPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BookingStep {

    private String city;
    private BookingMainPage bookingMainPage = new BookingMainPage();
    private HotelsPage hotelsPage = new HotelsPage();

    @Before
    public void init() {
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverRunner.setWebDriver(driver);
        getWebDriver().manage().window().maximize();
    }

    @After
    public void close() {
        getWebDriver().quit();
    }

    @Given("User is looking for hotel in {string} city")
    public void userIsLookingForHotelInLondonCity(String city) {
        this.city = city;
    }

    @When("User does search")
    public void userDoesSearch() {
        bookingMainPage.openMainPage()
                .enterCityToCityField(city);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        bookingMainPage.clickSearchButton();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String hotel) {
        List<String> hotelsNames = hotelsPage.getListOfHotelsNames();
        Assert.assertTrue(hotelsNames.contains(hotel));
    }

    @Then("Rating of {string} is {string}")
    public void ratingOfHotelIsCorrect(String hotel, String rating) {
        String ratingOfHotel = hotelsPage.getRatingOfHotel(hotel);
        Assert.assertEquals(rating, ratingOfHotel);
    }
}
