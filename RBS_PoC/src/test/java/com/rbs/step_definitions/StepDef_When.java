package com.rbs.step_definitions;

import com.rbs.pageobjects.CheckoutPage;
import com.rbs.pageobjects.MyAccountPage;
import com.rbs.pageobjects.PDP;

import cucumber.api.java.en.When;

public class StepDef_When {

	@When("^I Navigate to Order History Page from Order Confirmation Page$")
	public void navigateToHistoryPage() throws Throwable {
		CheckoutPage checkoutPageObject = new CheckoutPage(Hooks.driver).get();
		checkoutPageObject.navigateToOrderHistoryFromOrderConfirmationPage();
	}

	@When("^I add tees to cart and Proceed to Shopping Cart$")
	public void addTeesToCart() throws Throwable {
		PDP pdpPageObject = new PDP(Hooks.driver).get();
		pdpPageObject.goToTeesPage();
		pdpPageObject.addItemToCart();
		pdpPageObject.navigateToShoppingCartPage();
	}

	@When("^Go to Personal Information Page and Edit Firstname$")
	public void editFirstName() throws Throwable {
		MyAccountPage myAccountPageObject = new MyAccountPage(Hooks.driver).get();
		myAccountPageObject.navigateToPersonalInformationPage();
		myAccountPageObject.editFirstname();
	}

	@When("^Navigate to My Account Page$")
	public void goToMyAccountPage() throws Throwable {
		MyAccountPage myAccountPageObject = new MyAccountPage(Hooks.driver).get();
		myAccountPageObject.navigateToMyAccountPage();
	}

}
