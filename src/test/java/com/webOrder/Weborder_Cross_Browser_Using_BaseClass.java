package com.webOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Weborder_Cross_Browser_Using_BaseClass {
	WebDriver driver;

	@Test()
	public void login_to_app() throws InterruptedException {
		Thread.sleep(8000);	
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).clear();
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).clear();
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		driver.findElement(By.linkText("Logout")).click();

	}

	@BeforeTest
	@Parameters({ "browser", "url" })
	public void pre_condition(String browser, String url) throws Exception {
		driver = BaseClass.crossBrowserTesting(browser);
		driver.manage().window().maximize();
		driver.get(url);
	}

	@AfterTest
	public void post_condition() {

		driver.quit();
	}
}

