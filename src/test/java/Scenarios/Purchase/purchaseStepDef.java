package Scenarios.Purchase;

import DriverFactory.DriverFactory;
import DriverFactory.Util;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.*;

public class purchaseStepDef {

    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
    CartPage cartPage = new CartPage(DriverFactory.getDriver());
    CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());
    OverviewPage overviewPage = new OverviewPage(DriverFactory.getDriver());
    CompletePage completePage = new CompletePage(DriverFactory.getDriver());
    Util util = new Util(DriverFactory.getDriver());

    @When("I login with username {string} and password {string}")
    public void login(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be on the products page")
    public void verifyLogin() {
        Assert.assertTrue(loginPage.isLoggedIn());
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
        Assert.assertTrue(cartPage.isCartPageDisplayed());
    }

    @When("I click on the checkout button")
    public void clickCheckoutButton() {
        cartPage.checkout();
    }

    @Then("I should be on the checkout page")
    public void verifyCheckoutPage() {
        Assert.assertTrue(checkoutPage.isCheckoutPageDisplayed());
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
        Assert.assertTrue(overviewPage.isOverviewPageDisplayed());
    }

    @Then("the items total amount before taxes should be correct")
    public void verifyItemsTotalAmount() {
        Assert.assertTrue(overviewPage.isItemTotalCorrect());
    }

    @Then("the URL should be {string}")
    public void verifyURL(String expectedURL) {
       String actualUrl = util.getCurrentURL();
        Assert.assertEquals(actualUrl,expectedURL,"Url mismatch");
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

}
