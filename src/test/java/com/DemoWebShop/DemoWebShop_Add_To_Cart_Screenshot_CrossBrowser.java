package com.DemoWebShop;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class DemoWebShop_Add_To_Cart_Screenshot_CrossBrowser {

	WebDriver driver;
	String prdName;
	

	@Test
	public void add_item_to_cart() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.navigate().refresh();

		driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Computers']")).click();
		// driver.findElement(By.xpath("//ul[@class='top-menu']//a[normalize-space()='Computers']//following-sibling::ul/li/a[normalize-space()='Desktops']")).click();
		driver.findElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"))
				.click();
		prdName = driver.findElement(By.xpath("//a[normalize-space()='Simple Computer']")).getText();

		driver.findElement(By.xpath(
				"//a[normalize-space()='Simple Computer']/parent::h2//following-sibling::div//input[@type='button']"))
				.click();

		driver.findElement(By.id("product_attribute_75_5_31_96")).click();
		String prdPrice = driver.findElement(By.xpath("//span[@class='price-value-75']")).getText();
		System.out.println(prdPrice);

		driver.findElement(By.xpath("//input[@id='add-to-cart-button-75']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();

		String actualPrdName = driver.findElement(By.xpath("//h1[normalize-space()='Shopping cart']/parent::div/following-sibling::div//a[text()='"+prdName+"']")).getText();

		String actualPrdPrice = driver.findElement(By.xpath("//a[normalize-space()='"+prdName+"']/parent::td/following-sibling::td//span[@class='product-subtotal']")).getText();
		Assert.assertEquals(prdName, actualPrdName);

		Assert.assertEquals(prdPrice, actualPrdPrice);

	}

	@Test(priority = 2, enabled = true)
	public void Update_From_Cart() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.findElement(By.xpath("//td/a[normalize-space()='" + prdName + "']//following-sibling::div/a")).click();
		String expSoftName = driver.findElement(By.xpath("//label[normalize-space()='Image Viewer']")).getText();
		driver.findElement(By.xpath("//label[normalize-space()='Image Viewer']")).click();
		driver.findElement(By.xpath("//h1[normalize-space()='" + prdName +"']/../..//input[@value='Update']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
		
		String actualSoftName = driver.findElement(By.xpath("//a[normalize-space()='" + prdName + "']/parent::td//div[@class='attributes']")).getText();
		
		//String actualSoftName = driver.findElement(By.xpath("//a[text()='"+prdName+"']/..//div[@class='attributes']")).getText();

		Assert.assertTrue(actualSoftName.trim().contains(expSoftName.trim()));

	}

	@Test(priority = 3)

	public void Remove_From_Cart() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[normalize-space()='" + prdName
				+ "']/parent::td/preceding-sibling::td//input[@name='removefromcart']")).click();
		driver.findElement(By.name("updatecart")).click();
		Boolean deltedPrdName = driver.getPageSource().contains(prdName);
		Assert.assertFalse(deltedPrdName);

	}


	@BeforeTest
	@Parameters({ "browser", "url" })
	public void pre_condition(String browser, String url) throws Exception {
		driver = BaseClass.crossBrowserTesting(browser);
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	@AfterMethod
	public void CaptureScreenShot(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			BaseClass.getScreenshotfailure(driver, result.getName());
			
		}
		else if (ITestResult.SUCCESS == result.getStatus()) 
		{
			BaseClass.getScreenshotSuccess(driver, result.getName());
		}
			
	}

	@AfterTest
	public void post_condition() {
		// driver.findElement(By.linkText("Logout")).click();
		driver.close();
	}
}
