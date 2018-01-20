package com.rbs.step_definitions;

import com.rbs.pageobjects.CheckoutPage;
import com.rbs.pageobjects.MyAccountPage;

import cucumber.api.java.en.And;

public class StepDef_And {

	@And("^Do the payment and Place the order$")
	public void DoPayment() throws Throwable {
		CheckoutPage checkoutPageObject = new CheckoutPage(Hooks.driver).get();
		checkoutPageObject.validateAddedProduct();
		checkoutPageObject.proceedToAddressPage();
		checkoutPageObject.proceedToShippingPage();
		checkoutPageObject.proceedToPaymentPage();
		checkoutPageObject.payByBankWire();
		checkoutPageObject.placeOrder();
	}

	@And("^Navigate to Personal Information Page$")
	public void PersonalInformation() throws Throwable {
		MyAccountPage myAccountPageObject = new MyAccountPage(Hooks.driver).get();
		myAccountPageObject.navigateToPersonalInformationPage();
	}
}
