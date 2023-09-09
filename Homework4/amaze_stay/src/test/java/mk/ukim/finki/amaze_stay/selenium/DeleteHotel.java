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

public class DeleteHotel {

    public static void main(String[] args) {

        WebDriver unitDriver = new HtmlUnitDriver();

        unitDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String appUrl = "http://localhost:8080/login";
        unitDriver.get(appUrl);

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

        unitDriver.quit();
    }

}
