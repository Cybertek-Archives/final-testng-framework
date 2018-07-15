package com.cbt.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.cbt.utilities.Driver;

public class TestBase {
	protected WebDriver driver;
	Actions actions;
	
	@BeforeMethod
	public void setUp() {
		driver = Driver.getDriver();
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

	}
	
	@AfterMethod
	public void tearDown() {
		Driver.closeDriver();
	}

}
