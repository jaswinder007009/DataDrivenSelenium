package com.EX2.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
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
	public static Logger log = Logger.getLogger("devpinoyLogger");

	@BeforeMethod
	@BeforeSuite
	public void setUp() throws FileNotFoundException {
		if (driver == null) {
			String basePath = System.getProperty("user.dir") + "//src//test//resources//com//EX2//properties//";
			FileInputStream fisConfig = new FileInputStream(basePath + "Config.properties");
		System.out.println(System.getProperty("user.dir"));
			try {
				config.load(fisConfig);
				log.debug("Config file loaded !!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileInputStream fisOR = new FileInputStream(basePath + "OR.properties");
			try {
				OR.load(fisOR);
				log.debug("OR file loaded !!!!");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Test3");

			if (config.getProperty("browser").equals("chrome")) {
				System.out.println("Test2");

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.debug("Chrome launched !!!!");
			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.debug("Firefox launched !!!!");

			} else if (config.getProperty("browser").equals("ie")) {

				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.debug("Internet Explorer launched !!!!");


			}
			driver.get(config.getProperty("testsiteuRL"));
			log.debug("Navigated to :" + config.getProperty("testsiteuRL"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
		}

	}

	@AfterMethod
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();


		}
		
		log.debug("Test execution completed");

	}

}
