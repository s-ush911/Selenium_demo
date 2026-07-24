package com.DemoWebShop;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DemoShop_Add_To_Cart_DataProvider {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
	} 
	
	@Test(priority = 1)
	public void removeProductFromCart() throws InterruptedException {
//		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
		//Thread.sleep(5000);
		 List<WebElement> emptyMessage =
		            driver.findElements(By.xpath("//div[contains(text(),'Your Shopping Cart is empty')]"));
		if(emptyMessage.size() > 0) {
			driver.findElement(By.xpath("//img[@alt ='Tricentis Demo Web Shop']")).click();
		}else {
			List<WebElement> checkboxes = driver.findElements(By.name("removefromcart"));
			for(WebElement checkbox :checkboxes) {
				checkbox.click();
			}
			driver.findElement(By.name("updatecart")).click();
			//Thread.sleep(3000);
			driver.findElement(By.xpath("//img[@alt ='Tricentis Demo Web Shop']")).click();
			}
	}
	
	@Test(priority = 2, dataProvider = "demoWebShop_Add_Multi_Product", dataProviderClass = DemoWebShop_TestData.class)
	public void addAllDesktopProducts(String productName, String optionLabel) throws InterruptedException {
		driver.findElement(By.xpath("//img[@alt ='Tricentis Demo Web Shop']")).click();
		driver.findElement(By.xpath("//*[@class = 'listbox']//a[normalize-space() = 'Computers']")).click();
		driver.findElement(By.xpath("//*[@class = 'listbox']//a[normalize-space() = 'Desktops']")).click();
		addProductToCart(productName, optionLabel);
		
	}
		
	public void addProductToCart(String productName, String optionLabel) throws InterruptedException {
		double additionalProductPrice;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//a[normalize-space() ='"+productName+"']")).click();
		driver.findElement(By.xpath("//label[normalize-space()='" + optionLabel + "']")).click();
		driver.findElement(By.xpath("//div[@class='add-to-cart-panel']//input[@value='Add to cart']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
		//Thread.sleep(10000);
		driver.navigate().refresh();

	}
	

	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
	
}
	
