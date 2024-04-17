package org.example.pageObjects;

import org.example.pageUIs.LoginPageUI;
import org.example.utils.BasePage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {

    public WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    // CTB

    public void findAcount(String email){
        waitForElementVisible(driver, LoginPageUI.FIND_EMAIL_INPUT);
        sendKeyToElement(driver, LoginPageUI.FIND_EMAIL_INPUT, email);

        waitForElementClickAble(driver, LoginPageUI.NEXT_BTN);
        clickToElement(driver, LoginPageUI.NEXT_BTN);
    }
    public void clickNoIDontWant(){
        waitForElementClickAble(driver, LoginPageUI.NO_I_DONT_BTN);
        clickToElement(driver, LoginPageUI.NO_I_DONT_BTN);
    }

    public void loginWithNormalAccount(String email, String pass){
        waitForElementVisible(driver, LoginPageUI.EMAIL_INPUT);
        sendKeyToElement(driver, LoginPageUI.EMAIL_INPUT, email);

        waitForElementVisible(driver, LoginPageUI.PASSWORD_INPUT);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_INPUT, pass);

        waitForElementClickAble(driver, LoginPageUI.SIGN_IN_BTN);
        clickToElement(driver, LoginPageUI.SIGN_IN_BTN);
    }
}