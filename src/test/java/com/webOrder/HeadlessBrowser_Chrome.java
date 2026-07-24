package com.webOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class HeadlessBrowser_Chrome {
	WebDriver driver;

	@Test
	public void Chrome_Headless() {

		//WebDriverManager.chromedriver().setup();
		//ChromeOptions options = new ChromeOptions();
		EdgeOptions options = new EdgeOptions();
		//options.addArguments("--headless=new");
		//options.setAcceptInsecureCerts(true);
		options.addArguments("incognito");
		//driver = new ChromeDriver(options);
		driver = new EdgeDriver(options);
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");

		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		driver.findElement(By.linkText("Logout")).click();
		driver.findElement(By.name("ctl00$MainContent$login_button")).isDisplayed();
		driver.quit();
	}
}

