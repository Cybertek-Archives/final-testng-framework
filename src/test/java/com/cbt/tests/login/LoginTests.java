package com.cbt.tests.login;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.cbt.pages.AllOrdersPage;
import com.cbt.pages.LoginPage;
import com.cbt.tests.TestBase;
import com.cbt.utilities.ConfigurationReader;

public class LoginTests extends TestBase {

	@Test()
	public void positiveloginTest() {
		// name of the test
		extentLogger = report.createTest("Positive login test");
		// info ()  --> to print a message
		extentLogger.info("entering user credentials");
		
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		extentLogger.info("click login");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		extentLogger.info("Verifying log out link");
		assertTrue(new AllOrdersPage().logoutLink.isDisplayed());
		// pass --> message the tells us what passed
		extentLogger.pass("Verified log out link displayed");
	}

	@Test(priority = 2, groups= {"smoke"})
	public void positiveLoginUsingPOM() {
		extentLogger = report.createTest("Positive login test 2");

		LoginPage loginPage = new LoginPage();
		loginPage.userName.sendKeys(ConfigurationReader.getProperty("username"));
		loginPage.password.sendKeys(ConfigurationReader.getProperty("password"));
		loginPage.loginButton.click();
		fail("failed to login");
		extentLogger.pass("Verified log out link displayed");
		
	}

	@Test(groups= {"smoke"}, priority = 1)
	public void invalidUsernameTest() {
		extentLogger = report.createTest("Invalid username test");
		LoginPage loginPage = new LoginPage();
		loginPage.userName.sendKeys("invalid");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		String errMsg = loginPage.invalidUserNameErrMsg.getText();

		assertEquals(errMsg, "Invalid Login or Password.");
		
		extentLogger.pass("Verified error message: \"Invalid Login or Password.\"");

	}
	
	@Test(priority = 2, groups= {"smoke"})
	public void anotherpositiveLoginUsingPOM() {
		extentLogger = report.createTest("Another positive login test 2");

		LoginPage loginPage = new LoginPage();
		loginPage.userName.sendKeys(ConfigurationReader.getProperty("username"));
		loginPage.password.sendKeys(ConfigurationReader.getProperty("password"));
		loginPage.loginButton.click();
		
		throw new SkipException("This test is work in progress");
		
	}
	

}
