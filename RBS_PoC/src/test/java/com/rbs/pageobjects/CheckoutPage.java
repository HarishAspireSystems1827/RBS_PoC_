package com.rbs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.rbs.Support.Log;
import com.rbs.Support.Utils;
import com.rbs.step_definitions.Hooks;

/**
 * Page Object to handle Checkout Page Web Elements and Methods to handle
 * Checkout Page
 * 
 * @author Harish Subramani
 *
 */
public class CheckoutPage extends LoadableComponent<CheckoutPage> {

	public static String orderNumber;
	public static String productName;
	private WebDriver driver;
	private boolean isPageLoaded;

	/**********************************************************************************************
	 ********************************* WebElements of Checkout Page *******************************
	 **********************************************************************************************/

	@CacheLookup
	@FindBy(how = How.CSS, using = "p[class*='cart_navigation']>a[title='Proceed to checkout']")
	public static WebElement proceedToAddressPage;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "button[name='processAddress']")
	public static WebElement proceedToShippingPage;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "div[id='uniform-cgv']")
	public static WebElement chckTermsAndCondition;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "button[name='processCarrier']")
	public static WebElement proceedToPaymentPage;
	
	@FindBy(how = How.CSS, using = "td[class='cart_description'] p[class='product-name']>a, span[class='price']>strong, a[href*='history'][title='Back to orders'], tr[class='first_item '] td[class*='history_link'] a")
	public static WebElement chckPageLoad;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "td[class='cart_description'] p[class='product-name']>a")
	public static WebElement lblProductName;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "p[class='address_add submit'] a[title='Add']")
	public static WebElement btnAddAddress;

	@CacheLookup
	@FindBy(how = How.CSS, using = "div[class='delivery_option item']")
	public static WebElement deliveryOptionTable;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "p[class='payment_module'] a[class='bankwire']")
	public static WebElement btnPayByBankWire;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "p[class*='cart_navigation '] button[type='submit']")
	public static WebElement btnConfirmOrder;	
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "body[id='order-confirmation']")
	public static WebElement htmlBodyOrderConfirmation;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "a[href*='history'][title='Back to orders']")
	public static WebElement linkBackToOrder;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "span[class='price']>strong")
	public static WebElement costPriceInOrderConfirmationPage;	
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "table[id='order-list']")
	public static WebElement orderList;	
		
	@CacheLookup
	@FindBy(how = How.CSS, using = "tr[class='first_item '] td[class*='history_link'] a")
	public static WebElement firstOrderNumber;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "tr[class='first_item '] span[class='price']")
	public static WebElement firstOrderCost;	
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "tr[class='first_item '] td[class='history_method']")
	public static WebElement firstOrderPaymentType;	
	
	/**********************************************************************************************
	 ********************************* WebElements of Checkout Page *******************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 */
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// CheckoutPage

	@Override
	protected void isLoaded() {

		Utils.waitForPageLoad(driver);

		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);

		if (isPageLoaded && !(Utils.waitForElement(driver, chckPageLoad))) {
			// Log.fail("Page did not open up. Site might be down.", driver);
		}
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForElement(driver, chckPageLoad);
	}// load

	/**
	 * validate Added Product by comparing Product Name
	 */
	public void validateAddedProduct() {
		Assert.assertEquals(lblProductName.getText(), PDP.productName, "Product Name Matches the expected -" + PDP.productName);
		Log.passExtentReport(Hooks.driver, "Product Name Matches the expected -" + PDP.productName, Hooks.extentedReport);
	}
	
	/**
	 * Proceed To Address Page
	 */
	public void proceedToAddressPage() {
		Utils.waitForElement(driver, proceedToAddressPage);
		proceedToAddressPage.click();
		Utils.waitForElement(driver, btnAddAddress);
		Log.info("Navigated to Address Page!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * Proceed To Shipping Page
	 */
	public void proceedToShippingPage() {
		Utils.waitForElement(driver, proceedToShippingPage);
		proceedToShippingPage.click();
		Utils.waitForElement(driver, deliveryOptionTable);
		Log.info("Navigated to Shipping Page!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * Proceed To Payment Page
	 */
	public void proceedToPaymentPage() {
		Utils.waitForElement(driver, chckTermsAndCondition);
		chckTermsAndCondition.click();
		
		Utils.waitForElement(driver, proceedToPaymentPage);
		proceedToPaymentPage.click();
		
		Utils.waitForElement(driver, btnPayByBankWire);
		Log.info("Accepted Terms & Condition and navigated to Payment Page!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * Pay by Bank Wire
	 */
	public void payByBankWire() {
		Utils.waitForElement(driver, btnPayByBankWire);
		btnPayByBankWire.click();
		
		Utils.waitForElement(driver, btnConfirmOrder);
		Log.info("Paid by Bank Wire and Navigated to Order Confirmation Page!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * Place Order
	 */
	public void placeOrder() {
		Utils.waitForElement(driver, btnConfirmOrder);
		btnConfirmOrder.click();
		
		Utils.waitForElement(driver, costPriceInOrderConfirmationPage);
		Utils.waitForElement(driver, linkBackToOrder);
		orderNumber = driver.findElement(By.cssSelector("div[class='box']")).getText().split("reference", 10)[1].split(" ")[1].trim();
		Log.info("Order Placed Successfully. Order Number -" + orderNumber, Hooks.driver, Hooks.extentedReport, true);
	}
	
	/**
	 * validate Placed Order
	 */
	public void validatePlacedOrder() {
		Utils.waitForElement(driver, costPriceInOrderConfirmationPage);		
		Assert.assertEquals(costPriceInOrderConfirmationPage.getText(), PDP.orderTotalCost, "Validated Order Total in Order Confirmation Page!");
		Assert.assertEquals(driver.getPageSource().contains("Your order will be sent as soon as we receive payment."), true, "Validated Order Confirmation message!");
		Log.info("Verified Order Cost (in Order Confirmation Page) - " + PDP.orderTotalCost, Hooks.driver, Hooks.extentedReport);
		Log.passExtentReport(Hooks.driver, "Placed order validated in order confirmation page!", Hooks.extentedReport);
	}
	
	/**
	 * Navigate To Order History from Order Confirmation Page
	 */
	public void navigateToOrderHistoryFromOrderConfirmationPage() {
		Utils.waitForElement(driver, linkBackToOrder);
		linkBackToOrder.click();
		
		Utils.waitForElement(driver, orderList);		
		Log.info("Navigated to Order History Page!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * Validate Placed Order In Order History Page
	 * Verify - Order number, Order Cost and Order Payment Method
	 */
	public void validatePlacedOrderInOrderHistoryPage() {
		Assert.assertEquals(firstOrderNumber.getText(), orderNumber, "Order Number validated in Order History Page!");
		Assert.assertEquals(firstOrderCost.getText(), PDP.orderTotalCost, "Order Total Cost validated in Order History Page!");
		Assert.assertEquals(firstOrderPaymentType.getText(), "Bank wire", "Order Number validated in Order History Page!");
		Log.info("Verified Order number (in Order History Page) - " + orderNumber, Hooks.driver, Hooks.extentedReport);
		Log.info("Verified Order Cost (in Order History Page) - " + PDP.orderTotalCost, Hooks.driver, Hooks.extentedReport);
		Log.info("Verified Order Payment Method (in Order History Page) - Bank Wire", Hooks.driver, Hooks.extentedReport);		
		Log.passExtentReport(Hooks.driver, "Verified Order number, Order Cost and Order Payment Method!", Hooks.extentedReport);
	}
}
