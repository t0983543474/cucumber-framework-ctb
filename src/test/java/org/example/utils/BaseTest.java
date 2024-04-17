package org.example.utils;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    public final static int TIMEOUT = 10;

    public WebDriver WebDriverManager ()  {

        String browser = System.getProperty("BROWSER");

        if (driver == null) {

            if (browser== null){
                browser = System.getenv("BROWSER");
                if (browser == null){
                    browser = "chrome";
                }
            }
            switch (browser){
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
            driver.manage().window().maximize();
            driver.get(GlobalConstants.TEST_ENV);

        }
        return driver;
    }
}