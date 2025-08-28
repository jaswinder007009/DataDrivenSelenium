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

	@DataProvider
	public Object[][] getData() throws IOException {

		String excelPath = (System.getProperty("user.dir") + "//src//test//resources//com//EX2//excel//testdata.xlsx");
		FileInputStream inputStream = new FileInputStream(excelPath);

		XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
		// Workbook -> Sheet ->Rows ->Cells

		XSSFSheet sheet = workBook.getSheet("AddCustomerTest");

		// FOR LOOP
		int rowCount = sheet.getLastRowNum();
		System.out.println("Row: " + rowCount);
		int cols = sheet.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount][cols];

		for (int r = 1; r <= rowCount; r++) {

			XSSFRow row = sheet.getRow(r);

			for (int c = 0; c < cols; c++) {

				XSSFCell cell = row.getCell(c);

				switch (cell.getCellType()) {

				case STRING: {
					data[r - 1][c] = cell.getStringCellValue();
					System.out.print(cell.getStringCellValue());
					break;
				}
				case NUMERIC: {
					data[r - 1][c] = cell.getNumericCellValue();
					System.out.print(cell.getNumericCellValue());
					break;

				}
				case BOOLEAN: {
					data[r - 1][c] = cell.getBooleanCellValue();
					System.out.print(cell.getBooleanCellValue());

					break;

				}
				default:
					break;
				}

			}

		}
		if (workBook != null)
			workBook.close(); // closes OPCPackage
		if (inputStream != null)
			inputStream.close();

		return data;

	}

}