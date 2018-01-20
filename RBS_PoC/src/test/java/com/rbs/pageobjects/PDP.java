package com.rbs.pageobjects;

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
 * Page Object to handle PDP Web Elements and Methods to handle PDP
 * 
 * @author Harish Subramani
 *
 */
public class PDP extends LoadableComponent<PDP> {

	public static String productName;
	public static String orderTotalCost;
	private WebDriver driver;
	private boolean isPageLoaded;

	/**********************************************************************************************
	 ********************************* WebElements of PDP *****************************************
	 **********************************************************************************************/

	@CacheLookup
	@FindBy(how = How.CSS, using = "ul[class*='enabled']>li>a[title='T-shirts']")
	public static WebElement linkTeesMenu;

	@CacheLookup
	@FindBy(how = How.CSS, using = "h5[itemprop='name'] a[class='product-name']")
	public static WebElement linkProduct;

	@CacheLookup
	@FindBy(how = How.CSS, using = "p[id='add_to_cart'] button[name='Submit']")
	public static WebElement btnAddToCart;

	@CacheLookup
	@FindBy(how = How.CSS, using = "a[href*='order'][title='Proceed to checkout']")
	public static WebElement btnProceedToCheckout;

	@CacheLookup
	@FindBy(how = How.CSS, using = "p[class*='cart_navigation']>a[title='Proceed to checkout']")
	public static WebElement btnShoppingCartValidate;

	@CacheLookup
	@FindBy(how = How.CSS, using = "h1[itemprop='name']")
	public static WebElement lblProductName;

	@CacheLookup
	@FindBy(how = How.CSS, using = "span[id='total_price']")
	public static WebElement totalCost;
	
	/**********************************************************************************************
	 ********************************* WebElements of PDP *****************************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 */
	public PDP(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// PDP

	@Override
	protected void isLoaded() {

		Utils.waitForPageLoad(driver);

		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);

		if (isPageLoaded && !(Utils.waitForElement(driver, linkTeesMenu))) {
			// Log.fail("Page did not open up. Site might be down.", driver);
		}
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForElement(driver, linkTeesMenu);
	}// load

	/**
	 * Go to Tees Page
	 */
	public void goToTeesPage() {

		Utils.waitForElement(driver, linkTeesMenu);
		linkTeesMenu.click();
		Log.info("Navigated to Tees Page!", Hooks.driver, Hooks.extentedReport);
	}

	/**
	 * Add Item to cart
	 */
	public void addItemToCart() {

		Utils.waitForElement(driver, linkProduct);
		linkProduct.click();
		productName = lblProductName.getText();		
		Log.info("Selected Item. Item Name - " + productName, Hooks.driver, Hooks.extentedReport);
		
		Utils.waitForElement(driver, btnAddToCart);
		btnAddToCart.click();

		Utils.waitForElement(driver, btnProceedToCheckout);
		Log.info("Added item to cart!", Hooks.driver, Hooks.extentedReport, true);
	}

	/**
	 * Navigate To Shopping Cart Page
	 */
	public void navigateToShoppingCartPage() {

		Utils.waitForElement(driver, btnProceedToCheckout);
		btnProceedToCheckout.click();

		Utils.waitForElement(driver, btnShoppingCartValidate);
		orderTotalCost = totalCost.getText();
		Log.info("Navigated to Shopping Cart Page!", Hooks.driver, Hooks.extentedReport, true);
	}
}
