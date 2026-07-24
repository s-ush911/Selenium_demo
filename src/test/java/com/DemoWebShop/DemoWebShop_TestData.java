package com.DemoWebShop;

import org.testng.annotations.DataProvider;

public class DemoWebShop_TestData {

	@DataProvider(name = "demoWebShop_Register")
	public Object[][] getLogin_All_TCs_Scenarios() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5 or 3X5
		return new Object[][] {

				// { "John", "David", "john@gmail.com", "John123", "John123", "Your registration
				// completed" },
				{ "Monica", "Xavier", "moni@gmail.com", "Moni123", "Moni123", "Your registration completed" },
				{ "", "Xavier", "moni@gmail.com", "Moni123", "Moni123", "First name is required." },
				{ "Monica", "", "moni@gmail.com", "Moni123", "Moni123", "Last name is required." },
				{ "Monica", "Xavier", "", "Moni123", "Moni123", "Email is required. " },
				{ "Monica", "Xavier", "moni@gmail", "Moni123", "Moni123", "Wrong email" },
				{ "Monica", "Xavier", "sush@gmail.com", "Moni123", "Moni123", "The specified email already exists" },
				{ "Monica", "Xavier", "moni@gmail.com", "", "Moni123", "Password is required." },
				{ "Monica", "Xavier", "moni@gmail.com", "Moni123", "", "Password is required." },
				{ "Monica", "Xavier", "moni@gmail.com", "Moni", "Moni",
						"The password should have at least 6 characters." },
				{ "Monica", "Xavier", "moni@gmail.com", "Moni123", "Moni",
						"The password and confirmation password do not match." }

		};
	}

	@DataProvider(name = "demoWebShop_Add_Multi_Product")
	public Object[][] add_to_Cart_MultiPrd_TCs_Scenarios() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5 or 3X5
		return new Object[][] {

				{ "Build your own cheap computer", "Medium [+15.00]" }, 
				{ "Simple Computer", "Slow" },
				{ "Build your own computer", "320 GB" }, 
				{ "Build your own expensive computer", "Slow" }

		};
	}

}
