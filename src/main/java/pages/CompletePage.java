package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletePage {
    private WebDriver driver;

    public CompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCompletePageDisplayed() {
        return driver.getCurrentUrl().contains("checkout-complete.html");
    }

    public String getThankYouMessage() {
        return driver.findElement(By.className("complete-header")).getText();
    }

    public String getOrderDispatchedMessage() {
        return driver.findElement(By.className("complete-text")).getText();
    }
}
