package org.example.pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {


    public static LoginPageObject getLoginPage(WebDriver driver) {
        return  new LoginPageObject(driver);
    }

    //CTB

    public static LandingPageObject getLandingPage(WebDriver driver) {
        return  new LandingPageObject(driver);
    }
    public static HyundaiLoginPageObject getHyundaiPage(WebDriver driver) {
        return  new HyundaiLoginPageObject(driver);
    }
}