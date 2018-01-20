package com.rbs.pageobjects;

import java.util.Random;

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
 * Page Object to handle My Account Page Web Elements and Methods to handle My
 * Account Page
 * 
 * @author Harish Subramani
 *
 */
public class MyAccountPage extends LoadableComponent<MyAccountPage>{

	public static String editedFirstName;
	public static String productName;
	private WebDriver driver;
	private boolean isPageLoaded;

	/**********************************************************************************************
	 ********************************* WebElements of My Account Page *****************************
	 **********************************************************************************************/

	@CacheLookup
	@FindBy(how = How.CSS, using = "a[href*='identity'][title='Information'], p[class='alert alert-success'], input[id='firstname']")
	public static WebElement pageCheck;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "a[href*='identity'][title='Information']")
	public static WebElement btnPersonalInformation;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "button[name='submitIdentity']")
	public static WebElement btnSaveEditDetails;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "input[id='firstname']")
	public static WebElement txtFirstname;
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "input[id='old_passwd']")
	public static WebElement txtPassword;	
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "p[class='alert alert-success']")
	public static WebElement lblEditedSavedMsg;	
	
	@CacheLookup
	@FindBy(how = How.CSS, using = "a[href*='my-account']")
	public static WebElement goToMyAccountPage;	
	
	/**********************************************************************************************
	 ********************************* WebElements of My Account Page *****************************
	 **********************************************************************************************/

	/**
	 * constructor of the class
	 * 
	 * @param driver
	 *            : Webdriver
	 * 
	 */
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver, Utils.maxElementWait);
		PageFactory.initElements(finder, this);
	}// MyAccountPage

	@Override
	protected void isLoaded() {

		Utils.waitForPageLoad(driver);

		if (!isPageLoaded) {
			Assert.fail();
		}

		Utils.waitForPageLoad(driver);

		if (isPageLoaded && !(Utils.waitForElement(driver, pageCheck))) {
			// Log.fail("Page did not open up. Site might be down.", driver);
		}
	}// isLoaded

	@Override
	protected void load() {
		isPageLoaded = true;
		Utils.waitForElement(driver, pageCheck);
	}// load
	
	/**
	 * Navigate To Personal Information Page
	 */
	public void navigateToPersonalInformationPage() {
		Utils.waitForElement(driver, btnPersonalInformation);
		btnPersonalInformation.click();
		
		Utils.waitForElement(driver, btnSaveEditDetails);		
		Log.info("Navigated to Personal Information Page!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * Navigate To My Account Page
	 */
	public void navigateToMyAccountPage() {
		Utils.waitForElement(driver, goToMyAccountPage);
		goToMyAccountPage.click();
		
		Utils.waitForElement(driver, btnPersonalInformation);		
		Log.info("Navigated to My Account Page!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * Edit Firstname
	 */
	public void editFirstname() {
		
		Random random = new Random();
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randomChars = "";
		for (int i = 0; i < 3; i++) {
		    randomChars += alphabet.charAt(random.nextInt(alphabet.length()));
		}
		
		editedFirstName = "Someonecone" + randomChars;
		Utils.waitForElement(driver, txtFirstname);
		txtFirstname.click();
		txtFirstname.clear();
		txtFirstname.sendKeys(editedFirstName);
		
		txtPassword.click();
		txtPassword.clear();
		txtPassword.sendKeys(Hooks.testData.get("password"));
		
		Utils.waitForElement(driver, btnSaveEditDetails);
		btnSaveEditDetails.click();
		
		Utils.waitForElement(driver, lblEditedSavedMsg);
		Log.info("Edited firstname - " + editedFirstName, Hooks.driver, Hooks.extentedReport);
		Log.info("Edited and saved firstname!", Hooks.driver, Hooks.extentedReport);
	}
	
	/**
	 * validate Information Saved Message
	 */
	public void validateInformationSavedMessage() {
		Utils.waitForElement(driver, lblEditedSavedMsg);		
		Assert.assertEquals(lblEditedSavedMsg.getText(), "Your personal information has been successfully updated.", "Validated personal information saved message!");
		Log.passExtentReport(Hooks.driver, "Validated personal information saved message!", Hooks.extentedReport);
	}
	
	/**
	 * validate Edited Firstname
	 */
	public void validateEditedFirstname() {
		Utils.waitForElement(driver, txtFirstname);		
		Assert.assertEquals(txtFirstname.getAttribute("value").toLowerCase(), editedFirstName.toLowerCase(), "Validated Edited Firstname in Personal Information Page!");
		Log.passExtentReport(Hooks.driver, "Validated Edited Firstname in Personal Information Page!", Hooks.extentedReport);
	}
	
}
