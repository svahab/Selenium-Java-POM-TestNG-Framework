package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;


	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath ="//input[@id='user-name']") public WebElement usernameTest;

	@FindBy(xpath ="//input[@id='password']") public WebElement passwordTest;

	@FindBy(xpath ="//input[@id='login-button']") public WebElement loginBtn;

	public void enterUsername(String username)
	{
		usernameTest.clear();
		usernameTest.sendKeys(username);

	}

	public void enterPassword(String password)
	{
		passwordTest.clear();
		passwordTest.sendKeys(password);

	}





}
