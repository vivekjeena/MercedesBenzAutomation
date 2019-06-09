package com.WebAutomation;

public class ObjectRepository {

	// xPath
	public static String searchIcon = "//span[@class='header-search-open-trigger-label ng-binding']";
	public static String firstSearchResult = "//div[@class='utils-product-cms-carousel-image']";

	// id
	public static String btnCloseCookies = "button-text";
	public static String txtSearchBox = "searchTerm";
	public static String newsLetterEmail = "footerNewsletterEmail";

	// css
	public static String lnkLogin = ".ng-scope:nth-child(2) > .hidden-xs";

	public static class Idp {
		// Xpath
		public static String itemNumberOnIDP = "//span[@data-testid='pdp-shortdescription-articlenumber']";
		public static String itemPriceOnIDP = "//span[@data-testid='pdp-buy-box-add-to-basket-price']";
		public static String btnAddToCart = "//button[@data-ng-click='addToCart(product)']";

		// Css

		// id

		// class
	}

	public static class AddToCartModal {
		// Xpath
		public static String itemNumber = "(//p[@data-testid='co-orderline-product-article-number'])[2]";
		public static String itemPrice = "(//span[@data-testid='co-orderline-baseprice-value'])[2]";
		public static String itemQty = "//span[@data-testid='co-orderline-quantity-value']";
		public static String goToShoppingBasket = "//button[@data-track-click-as='open cart']";

		// Css

		// id

		// class

	}

	public static class Cart {
		// Xpath
		public static String itemNumber = "//p[@data-testid='co-orderline-product-article-number']";
		public static String itemPrice = "//span[@data-testid='co-orderline-baseprice-value']";
		public static String itemQty = "//input[@data-ng-model='quantityModel']";
		public static String lnkNextFooter = "//button[@data-testid='co-func-footer-forward']";
		public static String lnkNextHeader = "//button[@data-testid='co-func-header-forward']";
		public static String btnPlaceOrderAsGuest = "//button[@data-testid='co-order-process-login-guest-user-cta']";

		public static String radioBtnMale = "//input[@name='co_payment_address-salutationCode-radio'][@value='slt_mr']";
		public static String radioBtnFemale = "//input[@name='co_payment_address-salutationCode-radio'][@value='slt_ms']";
		public static String monthOfBirth = "//input[@placeholder='MM']";
		public static String yearOfBirth = "//input[@placeholder='YYYY']";
		public static String radioBtnPaymentMtdPaypal = "//input[@name='payment-methods'][@value='paypal']";
		public static String radioBtnPaymentMtdCreditcard = "//input[@name='payment-methods'][@value='CREDITCARD']";

		// css

		// id
		public static String txtUserEmail = "dcp-login-guest-user-email";
		public static String fName = "co_payment_address-firstName";
		public static String lName = "co_payment_address-lastName";
		public static String number = "co_payment_address-line2";
		public static String street = "co_payment_address-line1";
		public static String town = "co_payment_address-town";
		public static String postalCode = "co_payment_address-postalCode";
		public static String dateOfBirth = "dateOfBirth";

		// class

		// name
		public static String paymentEmail = "co_payment_address-email";

	}

	public static class Summary {
		// xpath
		public static String paymentMethod = "//div[@data-testid='dcp-co-order-data-body-group-content-paymentmethod']";
		public static String itemNumber = Cart.itemNumber;
		public static String itemPrice = "//span[@data-testid='co-orderline-baseprice-value']";
		public static String itemQty = "(//span[@data-testid='co-orderline-quantity-value'])[2]";
	}
}
