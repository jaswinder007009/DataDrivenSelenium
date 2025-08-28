package com.EX2.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.EX2.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() throws InterruptedException{
		log.debug("Inside Login test");
		driver.findElement(By.xpath(OR.getProperty("btnManagerLogin"))).click();
	//	Thread.sleep(4000);
		
		//
	//System.out.println("1");	
	//driver.findElement(By.xpath(OR.getProperty("addCustbtn"))).click();

	
	Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("addCustbtn"))),"Login not passed");
	
	

	//System.out.println("2");	

		
		log.debug("Login test successfully completed");

		
		
	}

}
