package com.EX2.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	/*
	 * 
	 * WebDriver Properties Logs ExtentReports DB Excel Mail
	 * 
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream configFis;
	public static FileInputStream configOR;

	@BeforeMethod
	@BeforeSuite
	public void setUp() throws FileNotFoundException {
		if (driver == null) {
			String basePath = System.getProperty("user.dir") + "//src//test//resources//com//EX2//properties//";
			FileInputStream fisConfig = new FileInputStream(basePath + "Config.properties");
System.out.println("Test1");

			
			try {
				config.load(fisConfig);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileInputStream fisOR = new FileInputStream(basePath + "OR.properties");
			try {
				OR.load(fisOR);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Test3");

			if (config.getProperty("browser").equals("chrome")) {
				System.out.println("Test2");

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();

			}
			driver.get(config.getProperty("testsiteuRL"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		}

	}

	@AfterMethod
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
			driver.quit();

		}
		
	}

}
