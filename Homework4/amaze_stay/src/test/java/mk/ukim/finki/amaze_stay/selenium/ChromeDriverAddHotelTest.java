package mk.ukim.finki.amaze_stay.selenium;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

public class ChromeDriverAddHotelTest {

    public static void main(String[] args) {

//Setting system properties of ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C://Users//Sarvanoski//Downloads//chromedriver-win64//chromedriver-win64//chromedriver.exe");

//Creating an object of ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

//Deleting all the cookies
        driver.manage().deleteAllCookies();
        options.addArguments("disable-infobars");

//Specifiying pageLoadTimeout and Implicit wait
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//launching the specified URL
        driver.get("http://localhost:8080/login");

//Logging in to the site
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        WebElement submit = driver.findElement(By.id("submit"));
        submit.click();

//Locating the add-hotel button on the homescreen
        WebElement add_hotel_button = driver.findElement(By.id("add-hotel"));
        add_hotel_button.click();

        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//Submitting the form;
        driver.findElement(By.id("name")).sendKeys("Hotel Russia");
        driver.findElement(By.id("latitude")).sendKeys("41.992520");
        driver.findElement(By.id("longitude")).sendKeys("21.465177");
        driver.findElement(By.id("website")).sendKeys("https://hotelrussia.mk/");
        driver.findElement(By.id("address")).sendKeys("ASNOM Blvd 1, Skopje 1000");
        driver.findElement(By.id("phoneNumber")).sendKeys("+389 2 240 0030");
        driver.findElement(By.id("email")).sendKeys("info@hotelrussia.mk");
        driver.findElement(By.id("description")).sendKeys("Good hotel to stay at, close to River Vardar.");
        driver.findElement(By.id("imagePath")).sendKeys("https://cf.bstatic.com/xdata/images/hotel/max1024x768/35884211.jpg?k=6def26316f9a346a6502294f27cc5687c940779e8e6b1c75b8fb75d58bd29e30&o=&hp=1");
        driver.findElement(By.id("bookingLink")).sendKeys("https://www.booking.com/hotel/mk/russia.en-gb.html?aid=356980&label=gog235jc-1DCAsokwFCBnJ1c3NpYUgzWANokwGIAQGYAQm4ARfIAQzYAQPoAQGIAgGoAgO4AoGWh6gGwAIB0gIkNjQxODcwOWYtMGY1MS00ZGY2LWI5NjQtZmY4YWNjMTIyYTBh2AIE4AIB&sid=04425f807bde8c7e47274532cb85e166&dist=0&keep_landing=1&sb_price_type=total&type=total&");
//Scrolling to locate button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

//Submitting the form
        //new WebDriverWait(getWebDriver(), 10).until(ExpectedConditions.elementToBeClickable(By.id("submit"))).click();
        WebElement submit_button = driver.findElement(By.id("submit"));
        submit_button.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(submit_button).click().build().perform();

//Locating the image of the newly added hotel on the homepage
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement added_hotel_image = driver.findElement(By.xpath("/html/body/section/div/section/div[13]/div/img"));
//Checking whether the found element matches the one we are looking for
        if(added_hotel_image.isDisplayed())
            System.out.println("The hotel has been Added to the list!");
        else
            throw new AssertionError("Unable to add hotel");
    }
}