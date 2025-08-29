package com.EX2.testcases;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.EX2.base.TestBase;
import com.EX2.utilities.ExcelReader;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "getData")
	public void addCustomer(String firstName, String lastName, String postCode) throws InterruptedException {

		driver.findElement(By.xpath(OR.getProperty("addCustbtn"))).click();

		driver.findElement(By.xpath(OR.getProperty("firstName"))).sendKeys(firstName);

		Thread.sleep(1000);

		driver.findElement(By.xpath(OR.getProperty("lastName"))).sendKeys(lastName);
		Thread.sleep(1000);

		driver.findElement(By.xpath(OR.getProperty("postCode"))).sendKeys(postCode);
		Thread.sleep(1000);

		driver.findElement(By.xpath(OR.getProperty("addCustomer"))).click();
		Thread.sleep(1000);

		// Switch to alert and accept
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert text: " + alert.getText());
		alert.accept();

	}

	@DataProvider(name = "getData")
	public Object[][] getData() throws Exception {
		String excelPath = System.getProperty("user.dir") + "/src/test/resources/com/EX2/excel/testdata.xlsx";
		return ExcelReader.readSheet(excelPath, "AddCustomerTest"); // sheet name here
	}
}
