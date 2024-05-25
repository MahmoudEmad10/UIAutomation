Feature: Purchase Checkout Flow

  Scenario Outline: Successful checkout with different users
    Given I open the browser and navigate to the site
    When I login with username "<username>" and password "<password>"
    Then I should be on the products page
    When I add the most expensive two products to the cart
    And I click on the cart button
    Then I should be on the cart page and see the selected products
    When I click on the checkout button
    Then I should be on the checkout page
    When I fill the checkout form with "John" "Doe" "12345"
    And I click on the continue button
    Then I should be on the overview page
    And the items total amount before taxes should be correct
    And the URL should be "https://www.saucedemo.com/checkout-step-two.html"
    When I click on the finish button
    Then I should see the "THANK YOU FOR YOUR ORDER" and "Your order has been dispatched, and will arrive just as fast as the pony can get there!" messages

    Examples:
      | username              | password      |
      | standard_user         | secret_sauce  |
