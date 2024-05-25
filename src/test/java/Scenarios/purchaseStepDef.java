package Scenarios;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;

import java.io.FileReader;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class purchaseStepDef {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OverviewPage overviewPage;
    private CompletePage completePage;

    @Given("I open the browser and navigate to the site")
    public void openBrowserAndNavigateToSite() throws Exception {
        // Load configuration
        String configFile = "src/test/resources/config.properties";
        java.util.Properties prop = new java.util.Properties();
        prop.load(new FileReader(configFile));
        String browser = prop.getProperty("browser");
        String url = prop.getProperty("url");

        // Setup WebDriver
        if (browser.equalsIgnoreCase("chrome")) {
          WebDriverManager.chromedriver().clearDriverCache();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().clearDriverCache();
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        // Initialize pages
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        overviewPage = new OverviewPage(driver);
        completePage = new CompletePage(driver);

        // Open URL
        driver.get(url);
    }

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be on the products page")
    public void verifyLogin() {
        assert loginPage.isLoggedIn();
    }

    @When("I add the most expensive two products to the cart")
    public void addMostExpensiveProductsToCart() {
        inventoryPage.addMostExpensiveItemsToCart();
    }

    @When("I click on the cart button")
    public void clickCartButton() {
        inventoryPage.goToCart();
    }

    @Then("I should be on the cart page and see the selected products")
    public void verifyCartPage() {
        assert cartPage.isCartPageDisplayed();
    }

    @When("I click on the checkout button")
    public void clickCheckoutButton() {
        cartPage.checkout();
    }

    @Then("I should be on the checkout page")
    public void verifyCheckoutPage() {
        assert checkoutPage.isCheckoutPageDisplayed();
    }

    @When("I fill the checkout form with {string} {string} {string}")
    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        checkoutPage.fillCheckoutForm(firstName, lastName, postalCode);
    }

    @When("I click on the continue button")
    public void clickContinueButton() {
        checkoutPage.continueToOverview();
    }

    @Then("I should be on the overview page")
    public void verifyOverviewPage() {
        assert overviewPage.isOverviewPageDisplayed();
    }

    @Then("the items total amount before taxes should be correct")
    public void verifyItemsTotalAmount() {
        assert overviewPage.isItemTotalCorrect();
    }

    @Then("the URL should be {string}")
    public void verifyURL(String expectedURL) {
        assert driver.getCurrentUrl().equals(expectedURL);
    }

    @When("I click on the finish button")
    public void clickFinishButton() {
        overviewPage.finishCheckout();
    }

    @Then("I should see the {string} and {string} messages")
    public void verifyCompletionMessages(String thankYouMessage, String orderDispatchedMessage) throws InterruptedException {
        Thread.sleep(2000);
        assert completePage.getThankYouMessage().equals(thankYouMessage);
        Thread.sleep(2000);
        assert completePage.getOrderDispatchedMessage().equals(orderDispatchedMessage);
        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
