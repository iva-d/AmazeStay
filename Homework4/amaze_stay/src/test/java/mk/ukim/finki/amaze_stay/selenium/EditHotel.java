package mk.ukim.finki.amaze_stay.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.concurrent.TimeUnit;


public class EditHotel {
    public static void main(String[] args) {
        WebDriver unitDriver = new HtmlUnitDriver();

        try {
            unitDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            String appUrl = "http://localhost:8080/login";
            unitDriver.get(appUrl);

            WebElement usernameInput = unitDriver.findElement(By.id("username"));
            WebElement passwordInput = unitDriver.findElement(By.id("password"));
            usernameInput.sendKeys("admin");
            passwordInput.sendKeys("admin");

            WebElement loginButton = unitDriver.findElement(By.id("submit"));
            loginButton.click();

            System.out.println("Login was successful");

            String expectedTitle = "AmazeStay";
            String actualTitle = unitDriver.getTitle();

            if (!actualTitle.equals(expectedTitle)) {
                throw new AssertionError("Page title mismatch. Expected: " + expectedTitle + ", Actual: " + actualTitle);
            }
            String hotelId = "63a5aa7eff3a2f5a6669d627";

            String editButtonXPath = "//a[contains(@class, 'edit-hotel') and contains(@href, '/hotels/edit-form/" + hotelId + "')]";

            WebElement editButton = unitDriver.findElement(By.xpath(editButtonXPath));
            editButton.click();

            WebElement editForm = unitDriver.findElement(By.tagName("form"));

            if (editForm.isDisplayed()) {
                System.out.println("Edit form is displayed.");
            } else {
                throw new AssertionError("Edit form not displayed.");
            }

            WebElement hotelNameInput = unitDriver.findElement(By.id("name"));
            hotelNameInput.clear();
            hotelNameInput.sendKeys("Hotel Mirror 2");

            WebElement imagePathInput = unitDriver.findElement(By.id("imagePath"));
            imagePathInput.clear();
            imagePathInput.sendKeys("https://cf.bstatic.com/xdata/images/hotel/max1280x900/331379270.jpg?k=3aa9fdf764b4e86aad85714e4332ef086a062e965cb717aa17e8bd66f7eb8997&o=&hp=1");


            WebElement submitButton = unitDriver.findElement(By.id("submit"));
            submitButton.click();

            WebElement updatedHotelImage = unitDriver.findElement(By.xpath("/html/body/section/div/section/div[1]/div/img"));

            if (updatedHotelImage.isDisplayed()) {
                System.out.println("The hotel has been successfully edited!");
            } else {
                throw new AssertionError("Unable to edit hotel.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unitDriver.quit();
        }
    }
}
