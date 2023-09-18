
package mk.ukim.finki.amaze_stay.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import java.util.concurrent.TimeUnit;

public class DeleteHotel {
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

            String expectedTitle = "AmazeStay";
            String actualTitle = unitDriver.getTitle();

            if (!actualTitle.equals(expectedTitle)) {
                throw new AssertionError("Page title mismatch. Expected: " + expectedTitle + ", Actual: " + actualTitle);
            }

            String hotelIdToDelete = "6501df393724a66b1b377cd7";

            // Modify the XPath to locate the delete button for the specific hotel by its ID
            String deleteButtonXPath = "//a[contains(@class, 'delete-hotel') and contains(@href, '/hotels/delete/" + hotelIdToDelete + "')]";

            WebElement deleteButton = unitDriver.findElement(By.xpath(deleteButtonXPath));
            deleteButton.click();

            boolean isHotelPresent = isElementPresent(unitDriver, By.xpath("//*[contains(@href, '/hotels/" + hotelIdToDelete + "')]"));

            if (!isHotelPresent) {
                System.out.println("The hotel has been successfully deleted!");
            } else {
                throw new AssertionError("Hotel deletion failed.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            unitDriver.quit();
        }
    }

    private static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}

