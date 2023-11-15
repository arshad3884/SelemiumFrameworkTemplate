package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	//String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider = "getDataSet2", groups="Purchase")
	public void SubmitOrder(HashMap <String, String> input) throws IOException {
		// TODO Auto-generated method stub
		
		initializeDriver();
		// Login
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication(input.get("email"), input.get("password"));
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(input.get("productName"));
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		cartPage.clickCheckout();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectCountry("Pakistan");
		checkoutPage.placeOrder();
		Boolean response = checkoutPage.checkOrderConfirmation();
		Assert.assertTrue(response);
		driver.close();
	}
	@Test (dependsOnMethods= {"SubmitOrder"}, dataProvider = "getDataSet1")
	public void orderHistoryTest(String email, String password, String productName) throws IOException
	{
		//this methods Verify the order is placed correctly
		initializeDriver();
		// Login
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication(email, password);
		OrderPage orderPage = new OrderPage(driver);
		Boolean match = orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
		driver.close();
	}
	
	//Extent Reports utility used to create excellent HTML reports, we also can attach screenshot in reports
	
	//1st type TestNG DataProvider
	@DataProvider
	public Object[][] getDataSet1()
	{
		return new Object[][] {
			{"alpha@yopmail.com","Alpha247*", "ZARA COAT 3"},  //1st data set
			{"anshika@gmail.com","Iamking@000", "ADIDAS ORIGINAL"}}; //2nd data set
	}
	//HashMap DataProvider
	@DataProvider
	public Object[][] getDataSet2() throws IOException
	{
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("email","alpha@yopmail.com"); map.put("password", "Alpha247*");
		 * map.put("productName", "ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email","anshika@gmail.com"); map1.put("password","Iamking@000");
		 * map1.put("productName","ADIDAS ORIGINAL");
		 */
		//This method return the list of hashMap, it is reading data from json file
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
