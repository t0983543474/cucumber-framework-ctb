package org.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.utils.GlobalConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class ApplicationHooks {

    private WebDriver driver;

    @Before
    public void setUp() {
        setDriver();
    }
    public final static int TIMEOUT = 10;

    public void setDriver ()  {

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

    }

    public WebDriver getDriver() {
        return driver;
    }

//    @After
//    public void tearDown() {
//
//        getDriver().quit();
//    }
    @After
    public  void tearDown(Scenario scenario) {

        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        getDriver().quit();
    }

//    @AfterStep
//    public void addScreenshot(Scenario scenario) {
//
//        WebDriver driver =  getDriver();
//        if(scenario.isFailed()) {
//            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "image");
//        }
//
//    }

}