package com.webOrder;

import org.testng.annotations.DataProvider;

public class WebOrder_TestData {

	@DataProvider(name = "Login")
	public Object[][] getDataforLogin() {
		// Multidimensional Object array
		// 3X3 or 4X3 or 4X5 or 2X4 or 2X7
		return new Object[][] {

				{ "Tester", "test" }, { "Tester", "test" }, { "Tester", "test" }, { "Tester", "test" },
				{ "Tester", "test" }, { "Tester", "test" }, { "Tester", "test" } };

	}

	@DataProvider(name = "WebOrder_LoginAll_TCs")
	public Object[][] getLogin_All_TCs_Scenarios() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5 or 3X5
		return new Object[][] {

				{ "Tester", "test", "Logout" }, { "Tester1", "test", "Invalid Login or Password." },
				{ "Tester", "test1", "Invalid Login or Password." }, { "", "test", "Invalid Login or Password." },
				{ "Tester", "", "Invalid Login or Password." } };

	}

	@DataProvider(name = "WebOrder_All_Orders_TC")
	public Object[][] getOrders_All_TCs_Scenarios() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {
				// Product, quantity, name, street, city, state, zip, cardNo, , expiry, Expected
				// Result
				// Correct order
				{ "1", "5", "Andrew V", "123 Main St", "Washington", "DC", "20010", "123456789", "12/24",
						"New order has been successfully added." },
				{ "0", "0", "Andrew V", "123 Main St", "Washington", "DC", "20010", "123456789", "12/24",
						"Quantity must be greater than zero." },
				{ "1", "5", "", "123 Main St", "Washington", "DC", "20010", "123456789", "12/24",
						"Field 'Customer name' cannot be empty." },
				{ "1", "5", "Andrew V", "123 Main St", "Washington", "DC", "", "123456789", "12/24",
						"Field 'Zip' cannot be empty." },
				{ "1", "5", "Andrew V", "123 Main St", "Washington", "DC", "ff", "123456789", "12/24",
						"Invalid format. Only digits allowed." },
				{ "1", "5", "Andrew V", "123 Main St", "Washington", "DC", "20010", "ff", "12/24",
						"Invalid format. Only digits allowed." },
				{ "1", "5", "Andrew V", "123 Main St", "Washington", "DC", "20010", "123456789", "ff/ff",
						"Invalid format. Required format is mm/yy." },
				{ "1", "5", "Andrew V", "123 Main St", "Washington", "DC", "20010", "123456789", "",
						"Field 'Expire date' cannot be empty." }, };

	}

	// -------------------------------------------- This is to read Excel
	// Data------------

	@DataProvider(name = "LoginExcelData")
	public Object[][] ReadDataFromExcel() throws Exception {
		ReadExcel excel = new ReadExcel();
		// BaseClass excel = new BaseClass();
		String RelativePath = System.getProperty("user.dir");
		// Object[][] testObjArray = excel.getExcelData("C:\\Training_Scripts\\Selenium
		// Training
		// Data\\workspace\\Maven_Selenium_WebDriver_4\\TestDataFile\\WebOrder_Login_TestData.xls","AddUsers");
		Object[][] testObjArray = excel.getExcelData(RelativePath + "\\TestDataFile\\WebOrder_Login_TestData.xlsx",
				"Login");
		// System.out.println(testObjArray);
		return testObjArray;

	}

}
