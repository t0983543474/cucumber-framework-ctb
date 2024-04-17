package org.example.definitions;

import io.cucumber.java.en.And;
import org.example.pageObjects.*;
import org.example.utils.TestSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginPageDefinitions {

    TestSetUp setUp;
    public LoginPageObject loginPage;
    public LandingPageObject landingPageObject;
    public HyundaiLoginPageObject hyundaiLoginPage;


    public LoginPageDefinitions(TestSetUp setUp)  {
        this.setUp = setUp;
        this.loginPage = setUp.pageObjectManager.getLoginPage();
        this.landingPageObject = setUp.pageObjectManager.getLandingPage();
        this.hyundaiLoginPage = setUp.pageObjectManager.getHyundaiPage();
    }



    // CTB


    @When("User input email {string} not exists and find account")
    public void userInputEmailNotExistsAndFindAccount(String email) {
        loginPage.findAcount(email);
    }

    @And("User select not account huyndai yet option")
    public void userSelectNotAccountHuyndaiYetOption() {
        loginPage.clickNoIDontWant();

    }

    @And("User enter normal acc username {string} and password is {string}")
    public void userEnterNormalAccUsernameAndPasswordIs(String email, String pass) {
        loginPage.loginWithNormalAccount(email, pass);
    }

    @And("Redirect to huyndai signin page and then user input integrated account with email is {string} and password is {string}")
    public void redirectToHuyndaiSigninPageAndThenUserInputIntegratedAccountWithEmailIsAndPasswordIs(String email, String password) {
        hyundaiLoginPage.loginWithIntegratedAccount(email, password);
    }


}