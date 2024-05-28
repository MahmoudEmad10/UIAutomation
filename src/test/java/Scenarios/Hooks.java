package Scenarios;

import DriverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.*;
import util.TestSetup;

public class Hooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private TestSetup testSetup;

    @Before(order = 0)
    public void setUp() {
        driverFactory = new DriverFactory();
        driver.get(driverFactory.getUrl());
       //testSetup = new TestSetup(driver);
    }

    @After(order = 1)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public TestSetup getTestSetup() {
        return testSetup;
    }
}
