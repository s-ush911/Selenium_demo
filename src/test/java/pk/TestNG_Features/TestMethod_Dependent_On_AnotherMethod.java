package pk.TestNG_Features;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestMethod_Dependent_On_AnotherMethod {
	WebDriver driver;
	String UserName;
	@BeforeTest
	public void LaunchBrowser()
	{
		//WebDriverManager.chromedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}
	@Test
	// This is for Sign On Functionality
	public void Sign_On() throws InterruptedException

	{
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$username']")).sendKeys("Tester");
		driver.findElement(By.xpath("//input[@name='ctl00$MainContent$password']")).sendKeys("test");
		driver.findElement(By.cssSelector("input[name='ctl00$MainContent$login_button']")).click();
		driver.findElement(By.linkText("Logout")).isDisplayed();
		//Verify Text Present or not
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
		Assert.assertEquals(ExpURL, ActURL);
		
		
	}
	
	@Test(dependsOnMethods={"Sign_On"})
	public void AddUsers_Page()
	{
		driver.findElement(By.linkText("Order")).click();
		Select product = new Select(driver.findElement(By.name("ctl00$MainContent$fmwOrder$ddlProduct")));
		product.selectByVisibleText("FamilyAlbum");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys("5");
		
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);
		UserName = "Dixit"+randomInt;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(UserName);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("ABC");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Redwood");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys("5");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("123456789");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("12/23");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		String ExpSuccessMsg = driver.findElement(By.xpath("//strong[normalize-space()='New order has been successfully added.']")).getText();
		String ActSuccessMsg = "New order has been successfully added.";
		Assert.assertEquals(ExpSuccessMsg, ActSuccessMsg);
		// GO back to View All Order page and Verify that user got created
		
		driver.findElement(By.linkText("View all orders1")).click();
		String ActUserName = driver.findElement(By.xpath("//td[text()='"+ UserName +"']")).getText();
		//String ExpUserName = "Dixit";
		Assert.assertEquals(UserName, ActUserName);
		
	}
	@Test(dependsOnMethods={"AddUsers_Page"})
	public void VerifyAddedUser() throws InterruptedException
	{
		 driver.findElement(By.xpath("//td[text()='"+ UserName +"']//following-sibling::td/input")).click();
		 driver.findElement(By.xpath("//h2[normalize-space()='Edit Order']")).isDisplayed();
		 driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).clear();
		 driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("CA");
		 Thread.sleep(3000);
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
		 String ActState = driver.findElement(By.xpath("//td[text()='"+ UserName +"']//following-sibling::td[text()='CA']")).getText();
		 String ExpState = "CA";
		 Assert.assertEquals(ExpState, ActState);
	}
	@AfterTest
	public void CloseBrowser()
	{
		driver.quit();
	}

}
