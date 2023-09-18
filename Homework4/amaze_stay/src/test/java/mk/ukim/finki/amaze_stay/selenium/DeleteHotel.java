//package mk.ukim.finki.amaze_stay.selenium;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.time.Duration;
//import java.util.NoSuchElementException;
//import java.util.logging.*;
//import static org.assertj.core.api.Assertions.assertThat;
//import java.util.concurrent.TimeUnit;
//import java.util.List;
//
//public class DeleteHotel {
//
//    public static void main(String[] args) {
//
//        WebDriver unitDriver = new HtmlUnitDriver();
//
//        unitDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//        String appUrl = "http://localhost:8080/login";
//        unitDriver.get(appUrl);
//
//        WebElement usernameInput = unitDriver.findElement(By.id("username"));
//        WebElement passwordInput = unitDriver.findElement(By.id("password"));
//        usernameInput.sendKeys("admin");
//        passwordInput.sendKeys("admin");
//
//        WebElement loginButton = unitDriver.findElement(By.id("submit"));
//        loginButton.click();
//
//        System.out.println("SUCCESSFUL LOGIN");
//
//        String expectedTitle = "AmazeStay";
//        String actualTitle = unitDriver.getTitle();
//
//        if (!actualTitle.equals(expectedTitle)) {
//            throw new AssertionError("Page title mismatch. Expected: " + expectedTitle + ", Actual: " + actualTitle);
//        }
//
//        System.out.println("Title of the page is -> " + actualTitle);
//
//        String hotelNameToDelete = "Hotel Aleksandar Palace";
//
//// Find the hotel element by searching for its name
//        WebElement hotelElement = unitDriver.findElement(By.xpath("//p[contains(text(), '" + hotelNameToDelete + "')]"));
//        System.out.println(hotelElement);
//// Locate the delete button within the hotel element
////        WebElement deleteButton = hotelElement.findElement(By.xpath(".//following-sibling::div/a[contains(@class, 'delete-hotel')]/i[contains(@class, 'fa-trash')]"));
////
////// Click the delete button to perform the deletion
////        deleteButton.click();
////
////// Wait for the deletion to complete (you may need to add a WebDriverWait here)
////// Check if the hotel element is no longer present on the page
////        if (!isElementPresent(unitDriver, By.xpath("//p[contains(text(), '" + hotelNameToDelete + "')]"))) {
////            System.out.println("Deletion was successful. Hotel '" + hotelNameToDelete + "' is no longer present.");
////        } else {
////            System.out.println("Deletion was not successful. Hotel '" + hotelNameToDelete + "' is still present.");
////        }
//
//        unitDriver.quit();
//    }
//
//    private static boolean isElementPresent(WebDriver driver, By by) {
//        try {
//            driver.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//
//
//}


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

