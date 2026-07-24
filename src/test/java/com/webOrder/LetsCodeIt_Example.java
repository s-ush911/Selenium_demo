package com.webOrder;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LetsCodeIt_Example {

	WebDriver driver;
	@Test(enabled=true)
	public void Hide_Element_Example() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();
		//Thread.sleep(5000);
		Boolean txtbox = driver.findElement(By.id("displayed-text")).isDisplayed();
		//Boolean txtbox = driver.getPageSource().contains("display: block;");
		Assert.assertFalse(txtbox);
	}
	
	@Test
	public void Enabled_Field_Example() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='disabled-button']")).click();
		//Thread.sleep(5000);
		Boolean txtbox = driver.findElement(By.xpath("//input[@id='enabled-example-input']")).isEnabled();
		//Boolean txtbox = driver.getPageSource().contains("display: block;");
		Assert.assertFalse(txtbox);
	}
	@BeforeTest
	public void LaunchBrowser() {
		// Define browser driver reference
		//WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		// WebDriverManager.chromedriver().setup();
		// WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.letskodeit.com/practice");
	}
	

	// Case 1--------------RADIO BUTTON---------------------------

	@Test(enabled = true)
	public void RadioButton() {
		driver.findElement(By.xpath("//input[@id='bmwradio']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='bmwradio']")).click();
		driver.findElement(By.xpath("//input[@id='benzradio']")).click();
		driver.findElement(By.xpath("//input[@id='hondaradio']")).click();
		

	}

	@Test(enabled = true)
	// Case 2----------------CHECKBOX SELECTION---------------------------
	public void CheckBox() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='hondacheck']")).isDisplayed();
		WebElement selectCar = driver.findElement(By.xpath("//input[@id='hondacheck']"));
		selectCar.click();
		Assert.assertEquals(selectCar.isSelected(), true);
		driver.findElement(By.id("bmwcheck")).click();
		Thread.sleep(3000);
	}

	@Test(enabled = true)
	// Case 3------------------Switch Window ---------------------------
	public void switchWindow() throws InterruptedException {

		driver.findElement(By.id("openwindow")).click();
		Thread.sleep(8000);
		// fetch handles of all windows, there will be two, [0]- default, [1] - new
		// window
		Object[] windowHandles = driver.getWindowHandles().toArray();
		driver.switchTo().window((String) windowHandles[1]);
		// assert on title of new window
		String title = driver.getTitle();
		assertEquals("All Courses", title);
		driver.close();
		driver.switchTo().window((String) windowHandles[0]);
// check title and then close.
	}

	@Test(enabled = true)
	// Case 4---------------------------------Switch Tab ---------------------------
	public void switchTab() throws InterruptedException {

		driver.get("https://www.letskodeit.com/practice");
		driver.findElement(By.id("opentab")).click();
		driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
		assertTrue(driver.findElement(By.xpath("//h1[@class='dynamic-heading margin-bottom-20']")).isDisplayed());
		Thread.sleep(3000);
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "https://www.letskodeit.com/courses";
		Assert.assertEquals(ExpectedURL, ActualURL);
		String title = driver.getTitle();
		assertEquals("All Courses", title);
		driver.close();
		driver.getWindowHandles().forEach(tab -> driver.switchTo().window(tab));
		
		//comment line no 79 and check the title.
	}

	@Test(enabled = true)
	// Case 5------------------------Drop down---------------------------
	public void selectDropDown() throws InterruptedException {
		driver.findElement(By.xpath("//select[@id='carselect']")).isDisplayed();
		driver.findElement(By.xpath("//select[@id='carselect']")).sendKeys("benz");
		Thread.sleep(8000);

	}

	@Test(enabled = true)
	// Case 6------------------------Multiple Selection---------------------------
	public void MultiSelect() throws InterruptedException {
		driver.findElement(By.xpath("//select[@id='multiple-select-example']")).isDisplayed();
		driver.findElement(By.xpath("//select[@id='multiple-select-example']//option[@value='apple']")).click();
		driver.findElement(By.xpath("//select[@id='multiple-select-example']//option[@value='peach']")).click();
		Thread.sleep(2000);
	}

	@Test(enabled = true)
	// Case 7----------------------AutoSuggestion---------------------------
	public void AutoSuggest() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='autosuggest']")).isDisplayed();
		String autoSuggestText = driver.findElement(By.xpath("//input[@id='autosuggest']")).getAttribute("placeholder");
		String Expectedmsg = "Start Typing...";
		Assert.assertEquals(Expectedmsg, autoSuggestText);
		driver.findElement(By.xpath("//input[@id='autosuggest']")).clear();
		driver.findElement(By.xpath("//input[@id='autosuggest']")).sendKeys("selenium");
		Thread.sleep(4000);
		// Handling auto-suggestion dropdown or list

		WebElement dropdown = driver.findElement(By.xpath("//ul[@id='ui-id-1']"));
		List<WebElement> options = dropdown.findElements(By.tagName("li"));
		for (WebElement option : options) {
			if (option.getText().equals("Selenium WebDriver Python")) {
				option.click(); // click the desired option
				break;
			}
		}

		Thread.sleep(2000);
		
	}

	@Test(enabled = true)
	// Case 8------------Enabled/Disabled Text Box---------------------------
	public void disableEnableTextBox() {
		driver.findElement(By.xpath("//input[@id='enabled-example-input']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='disabled-button']")).click();
		String value = driver.findElement(By.xpath("//input[@id='enabled-example-input']")).getAttribute("disabled");
		System.out.println("When its disabled " + value);
		Assert.assertEquals("true", value);
		driver.findElement(By.xpath("//input[@id='enabled-button']")).click();
		String value1 = driver.findElement(By.xpath("//input[@id='enabled-example-input']")).getAttribute("disabled");
		System.out.println("When its enabled " + value1);
		Assert.assertEquals(null, value1);

	}

	@Test(enabled = true)
	// Case 9 ----------------Element Displayed Hide/Show---------------------------
	public void ElementHide() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='hide-textbox']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='hide-textbox']")).click();
		boolean HideVal = driver.findElement(By.xpath("//input[@placeholder='Hide/Show Example']")).isDisplayed();
		Assert.assertEquals(false, HideVal);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='show-textbox']")).click();
		boolean HideVal1 = driver.findElement(By.xpath("//input[@placeholder='Hide/Show Example']")).isDisplayed();
		Assert.assertEquals(true, HideVal1);
		Thread.sleep(3000);
	}

	@Test(enabled = true)
	// Case 10.1--------------Alert Click OK---------------------------
	public void alertClickOK() throws InterruptedException {

		driver.findElement(By.xpath("//input[@id='alertbtn']")).click();
		String ExpAlertMsg = "Hello , share this practice page and share your knowledge";
		String ActAlertMsg = driver.switchTo().alert().getText();
		Assert.assertEquals(ExpAlertMsg, ActAlertMsg);
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		// This will take you out from frame to outside
		//driver.switchTo().defaultContent();

	}

	@Test(enabled = true)
	// Case 10.2------------------Confirm Click Cancel---------------------------
	public void confirmClickOK() throws InterruptedException {
		Thread.sleep(5000);

		driver.findElement(By.xpath("//input[@id='confirmbtn']")).click();
		String ExpAlertMsg = "Hello , Are you sure you want to confirm?";
		String ActAlertMsg = driver.switchTo().alert().getText();
		Assert.assertEquals(ExpAlertMsg, ActAlertMsg);
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(3000);
		// This will take you out from frame to outside
		//driver.switchTo().defaultContent();

	}

	@Test(enabled = true)
	// Case 11------------------MouseHover---------------------------
	public void mouseHover() throws InterruptedException {
		driver.findElement(By.xpath("//button[@id='mousehover']")).isDisplayed();
		WebElement ele = driver.findElement(By.xpath("//button[@id='mousehover']"));

		// Creating object of an Actions class
		Actions action = new Actions(driver);

		// Performing the mouse hover action on the target element.
		action.moveToElement(ele).perform();
		Thread.sleep(4000);
		// Locating the element from Sub Menu
		WebElement subMenu = driver.findElement(By.xpath("//a[text()='Reload']"));
		subMenu.click();
		// To mouse hover on sub menu
		//action.moveToElement(subMenu);
		Thread.sleep(2000);
		// build()- used to compile all the actions into a single step
		//action.click().build().perform();
	}

	@Test(enabled = true)

	// Case 12---------------------------------WebTable---------------------------
	public void WebTable() throws InterruptedException {

		// Thread.sleep(3000);
	}
	@Test(enabled = true)
	//Case 13---------------------------------Select with in iFrame---------------------------
	public void selectInSideIframe() throws InterruptedException {
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.switchTo().frame(driver.findElement(By.xpath("//iFrame[@id='courses-iframe']")));
		
		driver.findElement(By.xpath("//h4[contains(text(),'Cypress.io Test Automation')]")).isDisplayed();
		driver.findElement(By.xpath("//h4[contains(text(),'Cypress.io Test Automation')]")).click();
		/*WebElement Element=driver.findElement(By.xpath("//h4[contains(text(),'Cypress.io Test Automation')]"));
		// Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", Element);
		driver.findElement(By.xpath("//a[@href='/courses/cypress-automation-framework']")).click();
		driver.findElement(By.xpath("//button[text()='Enroll in Course']")).isDisplayed();
		WebElement Element1=driver.findElement(By.xpath("//button[text()='Enroll in Course']"));
		js.executeScript("arguments[0].scrollIntoView();", Element1);*/
		Thread.sleep(3000);
		//Switching back to the main window
		driver.switchTo().defaultContent();
	}

	@AfterTest
	public void CloseBrowser()
	{
		driver.close();
	}
}
