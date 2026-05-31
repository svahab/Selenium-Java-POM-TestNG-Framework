package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public WebDriver driver;

	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='title']")             public WebElement productsTitle;

	@FindBy(xpath = "//div[@class='app_logo']")           public WebElement appLogo;

	@FindBy(xpath = "//div[@class='inventory_container']") public WebElement inventoryContainer;

	public String getProductsTitle()
	{
		return productsTitle.getText();
	}

	public boolean isHomePageDisplayed()
	{
		try {
			return inventoryContainer.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
