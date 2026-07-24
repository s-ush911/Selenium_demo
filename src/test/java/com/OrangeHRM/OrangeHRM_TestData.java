package com.OrangeHRM;

import org.testng.annotations.DataProvider;

import com.webOrder.ReadExcel;

public class OrangeHRM_TestData {
  
	
	@DataProvider(name = "OarngeHRM_LoginAll_TCs")
	public Object[][] getDataforLogin() {
		// Multidimensional Object array
		// 3X3 or 4X3 or 4X5 or 2X4 or 2X7
		return new Object[][] {

			{ "Admin", "admin123", "Dashboard" }, 
			{ "Admin", "admin", "Invalid credentials" },
			{ "Admin1", "admin123", "Invalid credentials" }, 
			{ "", "admin123", "Required" },
			{ "Admin", "", "Required" } };

	}
	
	@DataProvider(name = "LoginExcelData")
	public Object[][] ReadDataFromExcel() throws Exception {
		ReadExcel excel = new ReadExcel();
		String RelativePath = System.getProperty("user.dir");
		Object[][] testObjArray = excel.getExcelData(RelativePath + "\\TestDataFile\\OrangeHRM_Login_TestData.xlsx",
				"Login");
		// System.out.println(testObjArray);
		return testObjArray;

	}
}
