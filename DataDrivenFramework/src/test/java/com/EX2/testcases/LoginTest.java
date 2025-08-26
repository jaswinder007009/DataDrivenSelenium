package com.EX2.testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.EX2.base.TestBase;

public class LoginTest extends TestBase{
	
	@Test
	public void loginAsBankManager() throws InterruptedException{
		driver.findElement(By.xpath(OR.getProperty("btnManagerLogin"))).click();
		Thread.sleep(3000);
		
		
		
	}

}
