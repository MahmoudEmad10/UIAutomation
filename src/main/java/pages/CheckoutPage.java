package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCheckoutPageDisplayed() {
        return driver.getCurrentUrl().contains("checkout-step-one.html");
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    }

    public void continueToOverview() {
        driver.findElement(By.id("continue")).click();
    }
}
