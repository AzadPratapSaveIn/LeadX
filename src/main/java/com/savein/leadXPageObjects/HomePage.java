package com.savein.leadXPageObjects;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import DemoHome.JsonUtils;
import DemoHome.model.PageElements;


import utilities.HtmlWebElements;

public class HomePage{
	private WebDriver driver1;
	private HtmlWebElements htmlWebElements;
	public HomePage(WebDriver driver) {	
		this.driver1 = driver;
		 htmlWebElements = new HtmlWebElements(driver1);
	}

	JsonUtils jsonutils= new JsonUtils();

	public String listPractice() {
		String str3 = null;
		PageElements str = jsonutils.getPageElements("btnListYourPractice");
		String str1= str.getLocatorType();
		String str2=str.getLocatorValue();
		try {
			htmlWebElements.getElement(str2, str1).click();
			str3=htmlWebElements.getElement("//div[text()='Join India’s first marketplace ']", "XPATH").getText();
			
		} catch (Exception e) {
			System.out.println("This is the issue" +e);
		}	
		return str3;
	}
	
	
	public void myAccount() throws InterruptedException {
		PageElements str = jsonutils.getPageElements("lnkTxtDentist");
		String str1= str.getLocatorType();
		String str2=str.getLocatorValue();
		htmlWebElements.getElement(str2, str1).click();
		Thread.sleep(7000);
		String strDental=htmlWebElements.getText("(//div[text()='Dental'])[2]", "XPATH").replace("\n", "");
		Assert.assertEquals(strDental, "Dental(1 partner)");
		Thread.sleep(7000);
	}
	

}

