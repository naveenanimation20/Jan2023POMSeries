package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
				
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	
	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[2]/input");
	
	private By userRegSuccMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String registerUser(String firstName, String lastName, 
			String email, String telephone, String password, String subscribe) {
		
		eleUtil.waitForElementVisible(this.firstName, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);

		doSubscribe(subscribe);
			
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		String userRegSuccessMesg = 
				eleUtil.waitForElementVisible(userRegSuccMessg, AppConstants.MEDIUM_DEFAULT_WAIT).getText();
		System.out.println(userRegSuccessMesg);
		
		
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);

		
		
		return userRegSuccessMesg;
	}
	
	private void doSubscribe(String subscribe) {
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}
		else {
			eleUtil.doClick(subscribeNo);
		}
	}

	
	
	

}
