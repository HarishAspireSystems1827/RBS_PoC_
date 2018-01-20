package com.rbs.step_definitions;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.rbs.Support.Log;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/com.rbs.features/", glue = { "com.rbs.step_definitions" }, format = {
		"pretty", "html:target/cucumber-report/chrome", "json:target/cucumber-report/chrome/cucumber.json",
		"junit:target/cucumber-report/chrome/cucumber.xml" })
public class RunCukesTest extends AbstractTestNGCucumberTests {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		Log.startExtentReport();
	}

	/*
	 * After suite will be responsible to close the report properly at the end You
	 * an have another afterSuite as well in the derived class and this one will be
	 * called in the end making it the last method to be called in test exe
	 */
	@AfterSuite
	public void afterSuite() {
		Log.endExtentReport();
	}

}
