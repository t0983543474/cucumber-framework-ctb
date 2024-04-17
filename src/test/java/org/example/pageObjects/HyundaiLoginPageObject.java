package org.example.pageObjects;

import org.example.pageUIs.HyundaiLoginPageUI;
import org.example.utils.BasePage;
import org.openqa.selenium.WebDriver;

public class HyundaiLoginPageObject extends BasePage {
    WebDriver driver;
    public HyundaiLoginPageObject(WebDriver driver){
        this.driver = driver;
    }
    public void loginWithIntegratedAccount(String email, String password){
        waitForElementVisible(driver, HyundaiLoginPageUI.EMAIL_INPUT);
        sendKeyToElement(driver, HyundaiLoginPageUI.EMAIL_INPUT,email);

        waitForElementVisible(driver, HyundaiLoginPageUI.PASSWORD_INPUT);
        sendKeyToElement(driver, HyundaiLoginPageUI.PASSWORD_INPUT,password);

        waitForElementClickAble(driver, HyundaiLoginPageUI.SIGNIN_BTN);
        clickToElement(driver, HyundaiLoginPageUI.SIGNIN_BTN);
    }
}
