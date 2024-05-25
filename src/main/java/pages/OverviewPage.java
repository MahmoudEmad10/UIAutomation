package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage {
    private WebDriver driver;

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOverviewPageDisplayed() {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }

    public boolean isItemTotalCorrect() {
        // Implement logic to verify item total before taxes
        return true;
    }

    public void finishCheckout() {
        driver.findElement(By.id("finish")).click();
    }
}
