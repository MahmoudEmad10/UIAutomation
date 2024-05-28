package Scenarios.Purchase;

import Scenarios.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.*;

public class purchaseStepDef {
    private Hooks hooks = new Hooks();
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OverviewPage overviewPage;
    private CompletePage completePage;

    @Before
    public void setUp() {
        hooks.setUp();
        this.driver = hooks.getTestSetup().getDriver();
        this.loginPage = hooks.getTestSetup().getLoginPage();
        this.inventoryPage = hooks.getTestSetup().getInventoryPage();
        this.cartPage = hooks.getTestSetup().getCartPage();
        this.checkoutPage = hooks.getTestSetup().getCheckoutPage();
        this.overviewPage = hooks.getTestSetup().getOverviewPage();
        this.completePage = hooks.getTestSetup().getCompletePage();
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
    public void verifyCompletionMessages(String thankYouMessage, String orderDispatchedMessage) {
        assert completePage.getThankYouMessage().equals(thankYouMessage);
        assert completePage.getOrderDispatchedMessage().equals(orderDispatchedMessage);
    }

    @After
    public void tearDown() {
        hooks.tearDown();
    }
}
