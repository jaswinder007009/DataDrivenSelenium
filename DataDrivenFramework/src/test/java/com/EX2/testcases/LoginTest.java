package com.EX2.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.EX2.base.TestBase;

public class LoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() throws InterruptedException{
		log.debug("Inside Login test");
		driver.findElement(By.xpath(OR.getProperty("btnManagerLogin"))).click();
		Thread.sleep(3000);
		log.debug("Login test successfully completed");

		
		
	}

}
