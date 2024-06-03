package Scenarios;

import DriverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import util.ConfigReader;
import java.util.Properties;

public class Hooks {

    public DriverFactory driverFactory;
    public WebDriver driver;
    Properties prop;
    public ConfigReader configReader;
    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_Prop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browser = prop.getProperty("browser");
        String url = prop.getProperty("url");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_Driver(browser);
        driver.get(url);
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }
}
