package com.WebAutomation;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceOrder {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    	driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    @Test
    public void verifyPlaceOrder() {

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        openUrl("https://shop.mercedes-benz.com/en-gb/collection/");
        
     // if cookes modal is presnet close
        clsoeCookiesModal();
     // User can see header Log in | 
        Assert.assertEquals(driver.findElement(By.cssSelector(".ng-scope:nth-child(2) > .hidden-xs")).getText(), "Log in");
     // search for item
        //waitForElement();
        clickOnElementXpath(ObjectRepository.searchIcon);
        driver.findElement(By.id(ObjectRepository.txtSearchBox)).sendKeys(TestData.itemToSearch+Keys.RETURN);
    }
    public void openUrl(String url) {
    	driver.get(url);
    }
    public boolean waitForElement(By element) {
    	WebDriverWait wait = new WebDriverWait(driver, 20);
    	WebElement elementPresent= wait.until(ExpectedConditions.presenceOfElementLocated(element));
    	if (elementPresent==null) {
    		return false;
    	}
    	return true;
    }
    public void clickOnElementXpath(String xpathExpression) {
    	driver.findElement(By.xpath(xpathExpression))
    		.click();
    }
    public void clickOnElementCss(String selector) {
    	driver.findElement(By.cssSelector(selector))
    		.click();
    }
    public void clsoeCookiesModal() {
    	driver.findElement(By.id(ObjectRepository.btnCloseCookies)).click();
    }
}