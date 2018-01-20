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
 * Page Object to handle Login Page Web Elements and Methods to handle Login Page
 * @author Harish Subramani
 *
 */
public class LoginPage extends LoadableComponent <LoginPage> {

	private String appURL;
	private WebDriver driver;
	private boolean isPageLoaded;
	
	/**********************************************************************************************
	 ********************************* WebElements of Login Page **********************************
	 **********************************************************************************************/

	@CacheLookup
	@FindBy(how = How.CSS, using = "a[href*='my-account'][class='login']")
	public static WebElement btnSignIn;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "input[id='email']")
	public static WebElement txtEmail;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "input[id='passwd']")
	public static WebElement txtPassword;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "button[id='SubmitLogin']")
	public static WebElement btnLogin;

	@CacheLookup
	@FindBy(how = How.CSS, using = "a[title='View my customer account'], a[title='Orders']")
	public static WebElement lblValidateLogin;
	
	/**********************************************************************************************
	 ********************************* WebElements of Login Page - Ends ***************************
	 **********************************************************************************************/
	
	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 * @param url
	 *            : Application URL
	 */
	public LoginPage(WebDriver driver, String url) {
		appURL = url;
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
		Log.info("Successfully Launched Site!", Hooks.driver, Hooks.extentedReport);
	}// LoginPage

	@Override
	protected void isLoaded() {

		Utils.waitForPageLoad(driver);

		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);		

		if (isPageLoaded && !(Utils.waitForElement(driver, btnSignIn))) {
//			Log.fail("Home Page did not open up. Site might be down.", driver);
		}				
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		driver.get(appURL);
		Utils.waitForElement(driver, btnSignIn);
	}// load	
	
	/**
	 * Do Login to the site
	 */
	public void doLogin() {		
		Utils.waitForElement(driver, btnSignIn);
		btnSignIn.click();	
		
		Utils.waitForElement(driver, txtEmail);
		txtEmail.sendKeys(Hooks.testData.get("username"));
		txtPassword.sendKeys(Hooks.testData.get("password"));
		btnLogin.click();
		
		Utils.waitForElement(driver, lblValidateLogin);
		Log.info("Logged into the Application!", Hooks.driver, Hooks.extentedReport, true);
	}
}
