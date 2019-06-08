package com.WebAutomation;

import static org.junit.Assert.assertFalse;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PlaceOrder {

	private WebDriver driver;
	private Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	@Test
	public void verifyPlaceOrder() {

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		openUrl("https://shop.mercedes-benz.com/en-gb/collection/");
		String tempItemNumber, tempItemPrice, tempItemQty = "1";
		Boolean validationItemNumber, vaidationItemPrice;

		// if cookies modal is present then close
		clsoeCookiesModal();
		// User can see header Log in |
		Assert.assertEquals(getText("cssSelector", ObjectRepository.lnkLogin), "Log in");

		// Click on first recommendation
		clickOnElement("xpath", ObjectRepository.firstSearchResult);

		// Save Item Number and item price and click Add to cart
		String itemNumber = getText("xpath", ObjectRepository.Idp.itemNumberOnIDP);
		String itemPrice = getText("xpath", ObjectRepository.Idp.itemPriceOnIDP);
		clickOnElement("xpath", ObjectRepository.Idp.btnAddToCart);

		// Validate Item Number and price on Add to cart modal and click Go to shopping
		// basket
		tempItemNumber = getText("xpath", ObjectRepository.AddToCartModal.itemNumber);
		tempItemPrice = getText("xpath", ObjectRepository.AddToCartModal.itemPrice);
		validationItemNumber = tempItemNumber.contains(itemNumber);
		vaidationItemPrice = tempItemPrice.contentEquals(itemPrice);
		if (validationItemNumber && vaidationItemPrice) {
			clickOnElement("xpath", ObjectRepository.AddToCartModal.goToShoppingBasket);
		} else
			assertFalse("ItemNumber of ItemPrice Valdiation failed", false);

		// Validate Item Number and price on cart and click proceed
		syncBrowser();
		waitForElement(By.xpath(ObjectRepository.Cart.lnkNextFooter));
		tempItemNumber = getText("xpath", ObjectRepository.Cart.itemNumber);
		tempItemPrice = getText("xpath", ObjectRepository.Cart.itemPrice);
		validationItemNumber = tempItemNumber.contains(itemNumber);
		vaidationItemPrice = tempItemPrice.contentEquals(itemPrice);
		if (validationItemNumber && vaidationItemPrice) {
			clickOnElement("xpath", ObjectRepository.Cart.lnkNextHeader);
		} else
			assertFalse("ItemNumber of ItemPrice Valdiation failed on cart", false);

		// Checkout as guest
		syncBrowser();
		waitForElement(By.xpath(ObjectRepository.Cart.lnkNextFooter));
		enterValues("id", ObjectRepository.Cart.txtUserEmail, TestData.inputUserEmail);
		clickOnElement("xpath", ObjectRepository.Cart.btnPlaceOrderAsGuest);
		clickOnElement("xpath", ObjectRepository.Cart.radioBtnMale);
		enterValues("id", ObjectRepository.Cart.fName, TestData.fName);
		enterValues("id", ObjectRepository.Cart.lName, TestData.lName);
		enterValues("id", ObjectRepository.Cart.number, TestData.number);
		enterValues("id", ObjectRepository.Cart.street, TestData.street);
		enterValues("id", ObjectRepository.Cart.town, TestData.town);
		enterValues("id", ObjectRepository.Cart.postalCode, TestData.postalCode);
		enterValues("id", ObjectRepository.Cart.dateOfBirth, TestData.dateOfBirth);
		enterValues("xpath", ObjectRepository.Cart.monthOfBirth, TestData.monthOfBirth);
		enterValues("xpath", ObjectRepository.Cart.yearOfBirth, TestData.yearOfBirth);
		/* Verify email address */
		Assert.assertEquals(getValue("name", ObjectRepository.Cart.paymentEmail), TestData.inputUserEmail);

		// select paypal and proceed
		clickOnElement("xpath", ObjectRepository.Cart.lnkNextHeader);
		clickOnElement("xpath", ObjectRepository.Cart.radioBtnPaymentMtdPaypal);
		clickOnElement("xpath", ObjectRepository.Cart.lnkNextHeader);

		// validate summary page with itemnumber, payment method and price
		Assert.assertEquals(getText("xpath", ObjectRepository.Summary.itemNumber), tempItemNumber);
		Assert.assertEquals(getText("xpath", ObjectRepository.Summary.paymentMethod).toLowerCase(),
				TestData.paymentMethod);
		Assert.assertEquals(getText("xpath", ObjectRepository.Summary.itemPrice), itemPrice);
	}

	/* generic methods to perform certain actions */
	public void openUrl(String url) {
		driver.get(url);
	}

	public boolean waitForElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement elementPresent = wait.until(ExpectedConditions.presenceOfElementLocated(element));
		if (elementPresent == null) {
			return false;
		}
		return true;
	}

	public void syncBrowser() {
		new WebDriverWait(driver, 60).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

	public void clickOnElement(String selectBy, String objectReference) {
		waitForElement(getSelector(selectBy, objectReference));
		action.moveToElement(driver.findElement(getSelector(selectBy, objectReference))).click().perform();
	}

	public void clsoeCookiesModal() {
		driver.findElement(By.id(ObjectRepository.btnCloseCookies)).click();
	}

	public String getText(String selectBy, String objectReference) {
		return driver.findElement(getSelector(selectBy, objectReference)).getText();
	}

	public String getValue(String selectBy, String objectReference) {
		return driver.findElement(getSelector(selectBy, objectReference)).getAttribute("value");
	}

	public void enterValues(String selectBy, String objectReference, String inputValue) {
		driver.findElement(getSelector(selectBy, objectReference)).sendKeys(inputValue);
	}

	public By getSelector(String selectBy, String objectReference) {
		By selector = null;
		switch (selectBy) {
		case "xpath":
			selector = By.xpath(objectReference);
			break;
		case "cssSelector":
			selector = By.cssSelector(objectReference);
			break;
		case "id":
			selector = By.id(objectReference);
			break;
		case "className":
			selector = By.className(objectReference);
			break;
		case "name":
			selector = By.name(objectReference);
			break;
		default:
			Assert.assertFalse(false, "Unable to Find Element");
		}
		return selector;
	}
}