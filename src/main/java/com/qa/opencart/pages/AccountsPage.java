package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	//1. const. of the page class
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2. by locators:
	private By logout = By.linkText("Logout");
	private By myAccount = By.linkText("My Account");
	private By accHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	
	
	//3. page actions:
	public String getAccPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.ACCOUNTS_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.checkElementIsDisplayed(logout);
	}
	
	public boolean isMyAccountLinkExist() {
		return eleUtil.checkElementIsDisplayed(myAccount);
	}
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement> headersList = eleUtil.waitForElementsVisible(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public ResultsPage doSearch(String searchTerm) {
		eleUtil.waitForElementVisible(search, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(search, searchTerm);
		eleUtil.doClick(searchIcon);
		return new ResultsPage(driver);
	}

}
