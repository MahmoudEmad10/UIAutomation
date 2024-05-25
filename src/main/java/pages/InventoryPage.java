package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {
    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addMostExpensiveItemsToCart() {
        List<WebElement> prices = driver.findElements(By.className("inventory_item_price"));
        prices.sort((p1, p2) -> Double.compare(Double.parseDouble(p2.getText().replace("$", "")),
                Double.parseDouble(p1.getText().replace("$", ""))));

        prices.get(0).findElement(By.xpath("../../..")).findElement(By.tagName("button")).click();
        prices.get(1).findElement(By.xpath("../../..")).findElement(By.tagName("button")).click();
    }

    public void goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
