package org.example.pageObjects;

import org.example.pageUIs.LandingPageUI;
import org.example.utils.BasePage;
import org.openqa.selenium.WebDriver;

public class LandingPageObject extends BasePage {
    public WebDriver driver;
    public LandingPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean LandingPageIsAvailable(){
        waitForElementInvisible(driver, LandingPageUI.OVERLAY);
        return !isElementDisplay(driver, LandingPageUI.OVERLAY);
    }
    public void waitingLanindPage(){
        waitForElementInvisibleCustom(driver, LandingPageUI.OVERLAY, 40);
    }
    public void clickIconLogin(){
        waitForElementClickAble(driver, LandingPageUI.ICON_LOGIN);
        clickToElement(driver, LandingPageUI.ICON_LOGIN);
    }

    public boolean isLoginSuccess(){
        waitForElementVisible(driver, LandingPageUI.MY_GARAGE);
        return isElementDisplay(driver, LandingPageUI.MY_GARAGE);
    }

    public boolean isDislpayLoginIcon() {
        waitForElementVisible(driver, LandingPageUI.ICON_LOGIN);
        return isElementDisplay(driver, LandingPageUI.ICON_LOGIN);
    }

    public boolean notdisplayMyGarage()
    {
        waitForElementInvisible(driver, LandingPageUI.MY_GARAGE);
        return !isElementDisplay(driver, LandingPageUI.MY_GARAGE);
    }
}
