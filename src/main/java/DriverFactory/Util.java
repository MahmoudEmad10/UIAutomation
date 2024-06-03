package DriverFactory;

import org.openqa.selenium.WebDriver;

public class Util {
    WebDriver driver;
    public Util (WebDriver driver){
        this.driver=driver;
    }
    public String getCurrentURL(){
       return driver.getCurrentUrl();
    }
}
