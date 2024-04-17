package org.example.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static BasePage getBasePageObject() {
        return new BasePage();
    }

    private By getByLocatorType(String locatorType) {
        By by = null;
        if (locatorType.startsWith("ID") || locatorType.startsWith("Id") || locatorType.startsWith("id")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("XPATH") || locatorType.startsWith("Xpath") || locatorType.startsWith("xpath")) {
            by = By.xpath(locatorType.substring(6));
        } else if (locatorType.startsWith("CSS") || locatorType.startsWith("Css") || locatorType.startsWith("css")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("NAME") || locatorType.startsWith("Name") || locatorType.startsWith("name")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("TAGNAME") || locatorType.startsWith("TagName") || locatorType.startsWith("tagname")) {
            by = By.tagName(locatorType.substring(8));
        } else if (locatorType.startsWith("CLASSNAME") || locatorType.startsWith("ClassName") || locatorType.startsWith("classname")) {
            by = By.className(locatorType.substring(10));
        } else if (locatorType.startsWith("PARTIALLINKTEXT") || locatorType.startsWith("PartialLinkText") || locatorType.startsWith("partiallinktext")) {
            by = By.partialLinkText(locatorType.substring(16));
        } else if (locatorType.startsWith("LINKTEXT") || locatorType.startsWith("LinkText") || locatorType.startsWith("linktext")) {
            by = By.linkText(locatorType.substring(9));
        } else {
            throw new RuntimeException("Locator not correct format");
        }
        return by;
    }

    private String getDynamicXpath(String locatorType, String... value) {


        if (locatorType.startsWith("XPATH") || locatorType.startsWith("Xpath") || locatorType.startsWith("xpath")) {
            locatorType = String.format(locatorType, (Object[]) value);
        }

        return locatorType;
    }

    public WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocatorType(locator));
    }

    public List<WebElement> getWebElements(WebDriver driver, String locator) {
        return driver.findElements(getByLocatorType(locator));
    }

    protected void sendKeyToElement(WebDriver driver, String xpathLcaotor, String value) {
        WebElement element = getWebElement(driver, xpathLcaotor);
        element.clear();
        element.sendKeys(value);
    }

    protected void sendKeyToElement(WebDriver driver, String locatorType, String value, String... dynamicValue) {
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        WebElement element = getWebElement(driver, locatorType);
        element.clear();
        element.sendKeys(value);
    }

    protected void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    protected String getTextValue(WebDriver driver, String location){
        return getWebElement(driver, location).getText();
    }
    protected void uploadFilesBySendKey(WebDriver driver, String locatorXpath, String... fileNames) {

        String filePath = GlobalConstants.FILEPATH_UPLOAD;
        String fullNameFiles = "";
        for (String fileName : fileNames) {
            fullNameFiles += filePath + fileName + "\n";
        }
        fullNameFiles = fullNameFiles.trim();
        System.out.println(fullNameFiles);
        WebElement element = getWebElement(driver, locatorXpath);
        element.sendKeys(fullNameFiles);
    }

    protected void selectItemDefaultDropdowList(WebDriver driver, String locator, String item) {
        Select select = new Select(getWebElement(driver, locator));
        select.selectByVisibleText(item);
    }

    protected String getSelectedItemDefaultDropdowList(WebDriver driver, String locator) {
        Select select = new Select(getWebElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    protected void selectItemInCustomDropdow(WebDriver driver, String parentElement, String childXpath, String expectedText) {
        getWebElement(driver, parentElement).click();
//        driver.findElement(By.xpath(parentElement)).click();
        sleepSecond(1);
        WebDriverWait expliciWait = new WebDriverWait(driver, Duration.ofSeconds(longtime));
        List<WebElement> allItems = expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocatorType(childXpath)));

        for (WebElement item : allItems) {
            if (item.getText().equals(expectedText)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepSecond(1);
                item.click();
                break;
            }
        }
    }


    protected boolean isElementDisplay(WebDriver driver, String locator) {

        boolean isdisplay = true;
        overiteImplicitTimeout(driver, shorttime);

        List<WebElement> elements = getWebElements(driver, locator);

        overiteImplicitTimeout(driver, longtime);
        if(elements.size()==0) {

            isdisplay=  false;
        }else if(elements.size()>0 && elements.get(0).isDisplayed()) {

            isdisplay=  true;
        }else {

            isdisplay = false;
        }

        return isdisplay;
    }

    protected void  waitForElementVisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocatorType(locator)));
    }

    protected void  waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocatorType(locatorType)));
    }
    protected void  waitForElementInvisibleCustom(WebDriver driver, String locatorType, long time) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocatorType(locatorType)));
    }

    protected void  waitForElementInvisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocatorType(locatorType)));
    }

    protected void  waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicvalue) {
        locatorType = getDynamicXpath(locatorType, dynamicvalue);
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocatorType(locatorType)));
    }

    protected void  waitForAllElementVisible(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocatorType(locatorType)));
    }

    protected void  waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValue) {
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocatorType(locatorType)));
    }

    protected void  waitForAllElementInvisible(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver,   Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, locator)));
    }

    protected void  waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValue) {
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        WebDriverWait explicitWait = new WebDriverWait(driver,   Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElements(driver, locatorType)));
    }

    protected void  waitForElementClickAble(WebDriver driver, String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver,   Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocatorType(locator)));
    }

    protected void  waitForElementClickAble(WebDriver driver, String locatorType, String... dynamicValue) {
        locatorType = getDynamicXpath(locatorType, dynamicValue);
        WebDriverWait explicitWait = new WebDriverWait(driver,   Duration.ofSeconds(longtime));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocatorType(locatorType)));
    }



    private void overiteImplicitTimeout(WebDriver driver, long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    private long shorttime = 5;
    private long longtime = 30;

    protected void sleepSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}