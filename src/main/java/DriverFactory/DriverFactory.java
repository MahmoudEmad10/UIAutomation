package DriverFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

public class DriverFactory {

    public WebDriver driver;

    public static ThreadLocal<WebDriver> tlDriver= new ThreadLocal<>();
    /*
    this method is used to initialize the driver and return tldriver
     */
    public WebDriver init_Driver (String browser){
        System.out.println("Browser value is: "+ browser);
        if (browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver ().clearDriverCache ();
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver());
        }
        else if (browser.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            tlDriver.set(new EdgeDriver());
        }
        else {
            System.out.println("Wrong Browser Value");
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static WebDriver getDriver(){
        return tlDriver.get();
    }

}
