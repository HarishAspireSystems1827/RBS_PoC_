package com.rbs.step_definitions;

import com.rbs.pageobjects.CheckoutPage;
import com.rbs.pageobjects.MyAccountPage;

import cucumber.api.java.en.Then;

public class StepDef_Then {

	@Then("^I Validate Placed Order in Order History Page$")
	public void validatePlacedOrderIn_OrderHistoryPage() throws Throwable {
		CheckoutPage checkoutPageObject = new CheckoutPage(Hooks.driver).get();
		checkoutPageObject.validatePlacedOrderInOrderHistoryPage();
	}

	@Then("^I Validate Placed Order in Order Confirmation Page$")
	public void validatePlacedOrderIn_OrderConfirmationPage() throws Throwable {
		CheckoutPage checkoutPageObject = new CheckoutPage(Hooks.driver).get();
		checkoutPageObject.validatePlacedOrder();
	}

	@Then("^I Validate Edit Details Message$")
	public void validateEditDetailsPage() throws Throwable {
		MyAccountPage myAccountPageObject = new MyAccountPage(Hooks.driver).get();
		myAccountPageObject.validateInformationSavedMessage();
	}

	@Then("^I Validate Edited Firstname$")
	public void validateEditFirstname() throws Throwable {
		MyAccountPage myAccountPageObject = new MyAccountPage(Hooks.driver).get();
		myAccountPageObject.validateEditedFirstname();
	}

}
