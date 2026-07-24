package com.webOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class WebOrder_Login_CreateOrder_VerifyOrder {
	//Global Variable 
	WebDriver driver;
	
	@AfterTest // This will run at last
	public void post_condition() {
		driver.findElement(By.linkText("Logout")).click();
		//driver.close(); // Close will close only current browser ( which is opened by Selenium)
		driver.quit(); // This will close all browser ( which is opened by Selenium)
	}
	
	@Test(priority=1)
	public void login_to_app() {
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		/*//Verify Text Present or not
		String ActListElementName = driver.findElement(By.xpath("//h2[normalize-space()='List of All Orders']")).getText();
		String ExpListElementName = "List of All Orders";
		Assert.assertEquals(ExpListElementName, ActListElementName);
		// Verify Title of the Page
		String ActTitle = driver.getTitle();
		String ExpTitle = "Web Orders";
		Assert.assertEquals(ExpTitle, ActTitle);
		//Verify URL of the Page
		String ActURL = driver.getCurrentUrl();
		String ExpURL = "http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/default.aspx";
		Assert.assertEquals(ExpURL, ActURL);*/
		
	}
	@Test(priority=2)
	public void create_Order()
	{
		//driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Process.aspx");
		driver.findElement(By.linkText("Order")).click();
		// Select class in Selenium will help you to identify Dropdown field
		Select product = new Select(driver.findElement(By.name("ctl00$MainContent$fmwOrder$ddlProduct")));
		product.selectByValue("FamilyAlbum");
		//product.selectByVisibleText("MyMoney");
		//product.selectByIndex(0);
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys("5");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("Dixit");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("ABC");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Redwood");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys("5");
		
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("123456789");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("12/26");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		String ActSuccessMsg =driver.findElement(By.tagName("strong")).getText();
		//driver.findElement(By.xpath("//strong[normalize-space()='New order has been successfully added.']")).isDisplayed();
		//String ActSuccessMsg = driver.findElement(By.xpath("//strong[normalize-space()='New order has been successfully added.']")).getText();
		String ExpSuccessMsg = "New order has been successfully added.";
		Assert.assertEquals(ExpSuccessMsg, ActSuccessMsg);
		// GO back to View All Order page and Verify that user got created
		
		driver.findElement(By.linkText("View all orders")).click();
		String ActUserName = driver.findElement(By.xpath("//td[normalize-space()='Dixit']")).getText();
		String ExpUserName = "Dixit";
		Assert.assertEquals(ExpUserName, ActUserName);
	}
	
	@BeforeTest // This will run first
	public void pre_condition() {
		//WebDriverManager.chromedriver().setup();
		// Local Variable
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}

}
