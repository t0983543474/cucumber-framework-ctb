package org.example.pageObjects;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    public LoginPageObject loginPage;
    public WebDriver driver;


    public PageObjectManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public LoginPageObject getLoginPage()
    {

        loginPage= new LoginPageObject(driver);
        return loginPage;
    }


    //CTB
    public LandingPageObject getLandingPage(){
        return new LandingPageObject(driver);
    }
    public HyundaiLoginPageObject getHyundaiPage(){
        return new HyundaiLoginPageObject(driver);
    }
}