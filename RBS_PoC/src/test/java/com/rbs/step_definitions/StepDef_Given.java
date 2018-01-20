package com.rbs.step_definitions;

import com.rbs.pageobjects.LoginPage;
import cucumber.api.java.en.Given;

public class StepDef_Given {

	@Given("^I Login to Practice Site$")
	public void LoginToPracticeSite() throws Throwable {
		LoginPage loginPageObject = new LoginPage(Hooks.driver, Hooks.appURL).get();
		loginPageObject.doLogin();
	}
}
