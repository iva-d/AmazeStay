package mk.ukim.finki.amaze_stay.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.concurrent.TimeUnit;

public class AddHotel {

        public static void main(String[] args) {

            WebDriver unitDriver = new HtmlUnitDriver();

            unitDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            String appUrl = "http://localhost:8080/login";
            unitDriver.get(appUrl);

            //Testing login before going on to 'add hotel' action
            WebElement usernameInput = unitDriver.findElement(By.id("username"));
            WebElement passwordInput = unitDriver.findElement(By.id("password"));
            usernameInput.sendKeys("admin");
            passwordInput.sendKeys("admin");

            WebElement loginButton = unitDriver.findElement(By.id("submit"));
            loginButton.click();

            System.out.println("SUCCESSFUL LOGIN");

            String expectedTitle = "AmazeStay";
            String actualTitle = unitDriver.getTitle();

            if (!actualTitle.equals(expectedTitle)) {
                throw new AssertionError("Page title mismatch. Expected: " + expectedTitle + ", Actual: " + actualTitle);
            }

            System.out.println("Title of the page is -> " + actualTitle);

            WebElement add_hotel_button = unitDriver.findElement(By.id("add-hotel"));
            add_hotel_button.click();

            WebElement hotel_name_input = unitDriver.findElement(By.id("name"));
            hotel_name_input.sendKeys("Hotel Russia");

            WebElement latitude_input = unitDriver.findElement(By.id("latitude"));
            latitude_input.sendKeys("41.992520");

            WebElement longitude_input = unitDriver.findElement(By.id("longitude"));
            longitude_input.sendKeys("21.465177");

            WebElement website_input = unitDriver.findElement(By.id("website"));
            website_input.sendKeys("https://hotelrussia.mk/");

            WebElement address_input = unitDriver.findElement(By.id("address"));
            address_input.sendKeys("ASNOM Blvd 1, Skopje 1000");

            WebElement phone_number_input = unitDriver.findElement(By.id("phoneNumber"));
            phone_number_input.sendKeys("+389 2 240 0030");

            WebElement email_input = unitDriver.findElement(By.id("email"));
            email_input.sendKeys("info@hotelrussia.mk");

            WebElement description_input = unitDriver.findElement(By.id("description"));
            description_input.sendKeys("Good hotel to stay at, close to River Vardar.");

            WebElement image_path_input = unitDriver.findElement(By.id("imagePath"));
            image_path_input.sendKeys("https://cf.bstatic.com/xdata/images/hotel/max1024x768/35884211.jpg?k=6def26316f9a346a6502294f27cc5687c940779e8e6b1c75b8fb75d58bd29e30&o=&hp=1");

            WebElement booking_link_input = unitDriver.findElement(By.id("bookingLink"));
            booking_link_input.sendKeys("https://www.booking.com/hotel/mk/russia.en-gb.html?aid=356980&label=gog235jc-1DCAsokwFCBnJ1c3NpYUgzWANokwGIAQGYAQm4ARfIAQzYAQPoAQGIAgGoAgO4AoGWh6gGwAIB0gIkNjQxODcwOWYtMGY1MS00ZGY2LWI5NjQtZmY4YWNjMTIyYTBh2AIE4AIB&sid=04425f807bde8c7e47274532cb85e166&dist=0&keep_landing=1&sb_price_type=total&type=total&");

            WebElement submit_button = unitDriver.findElement(By.id("submit"));
            submit_button.click();

            WebElement added_hotel_image = unitDriver.findElement(By.xpath("/html/body/section/div/section/div[12]/div/img"));

            if(added_hotel_image.isDisplayed())
                System.out.println("The hotel has been Added to the list!");
            else
                throw new AssertionError("Unable to add hotel");

            unitDriver.quit();
        }
}
