package com.cbt.tests.order;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.cbt.pages.AllOrdersPage;
import com.cbt.pages.LoginPage;
import com.cbt.pages.ProductsPage;
import com.cbt.tests.TestBase;
import com.cbt.utilities.ConfigurationReader;

public class OrderTests extends TestBase {

	String userId = ConfigurationReader.getProperty("username");
	String password = ConfigurationReader.getProperty("password");

	
	@Test(description = "Verify labels and tab links are displayed", priority = 1)
	public void labelsVerication() {
		
		LoginPage loginPage = new LoginPage();

		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");
		loginPage.login(userId, password);
		
		AllOrdersPage allOrdersPage = new AllOrdersPage();
		assertTrue(allOrdersPage.webOrders.isDisplayed(), "Web Orders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrders.isDisplayed(), "List Of All Orders label is not displayed");
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, " + userId + "!");
		assertTrue(allOrdersPage.viewAllOrders.isDisplayed(), "viewAllOrders is not displayed");
		assertTrue(allOrdersPage.orderTab.isDisplayed(), "orderTab is not displayed");
	}

	@Test(description = "Verify default Products and prices")
	public void availableProductsTest() {
		assertEquals(driver.getTitle(), "Web Orders Login", "LoginPage is not displayed. Application is down.");
		LoginPage loginPage = new LoginPage();
		loginPage.login(userId, password);
		AllOrdersPage allOrdersPage = new AllOrdersPage();
		allOrdersPage.viewAllProducts.click();
		ProductsPage productsPage = new ProductsPage();
		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList<>();

		// productsPage.productNames.forEach(
		// elem -> actProducts.add(elem.getText())
		// );
		for (WebElement prod : productsPage.productNames) {
			actProducts.add(prod.getText());
		}
		assertEquals(actProducts, expProducts);

		for (WebElement row : productsPage.productsRows) {

			System.out.println(row.getText());
			String[] prodData = row.getText().split(" ");
			switch (prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "8%");
				break;
			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;
			case "ScreenSaver":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;
			}

		}

	}

}