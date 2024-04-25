package org.example.definitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.pageObjects.LandingPageObject;
import org.example.pageObjects.LoginPageObject;
import org.example.pageObjects.PageObjectManager;
import org.junit.Assert;

public class LandingPageDefinations {

    private ApplicationHooks hooks;
    public LoginPageObject loginPage;

    PageObjectManager pageObjectManager;
    public LandingPageObject landingPageObject;
    public LandingPageDefinations(ApplicationHooks hooks) {
        this.hooks = hooks;
        this.loginPage =PageObjectManager.getLoginPage(hooks.getDriver());
        this.landingPageObject = PageObjectManager.getLandingPage(hooks.getDriver());
    }

    @Given("User go to Login Page")
    public void userGoToLoginPage() {
//        Assert.assertTrue(landingPageObject.LandingPageIsAvailable());
        landingPageObject.clickIconLogin();
    }

    @Then("User login successfully with normal account")
    public void userLoginSuccessfullyWithNormalAccount() {

        Assert.assertTrue(landingPageObject.isLoginSuccess());
    }
    @And("User login successfully with integrated account")
    public void userLoginSuccessfullyWithIntegratedAccount() throws InterruptedException {
        landingPageObject.waitingLanindPage();
//        Thread.sleep(30000);
        Assert.assertTrue(landingPageObject.isLoginSuccess());
    }

    @Then("Landing page is display without logged")
    public void landingPageIsDisplayWithoutLogged() {
        Assert.assertFalse(landingPageObject.isDislpayLoginIcon());
//        Assert.assertTrue(landingPageObject.notdisplayMyGarage());
    }

    @And("Landing page is display with logged")
    public void landingPageIsDisplayWithLogged() {
        Assert.assertFalse(landingPageObject.notdisplayMyGarage());
    }

}

