package com.cbt.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class LoginPage {

	public LoginPage() {
		PageFactory.initElements(Driver.getDriver(), this);	
	}

	@FindBy(id = "ctl00_MainContent_username")
	public WebElement userName;

	@FindBy(id = "ctl00_MainContent_password")
	public WebElement password;

	@FindBy(id = "ctl00_MainContent_login_button")
	public WebElement loginButton;

	@FindBy(id = "ctl00_MainContent_status")
	public WebElement invalidUserNameErrMsg;

	public void login(String uid, String pwd) {
		userName.sendKeys(uid);
		password.sendKeys(pwd);
		loginButton.click();
	}

}