package rahulshettyacademy.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {
	@Test (groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	
	public void loginErrorValidation() throws IOException
	{
		initializeDriver();
		
		// Login
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("alpha@yopmail.com", "Alpha247");
		String msg = driver.findElement(By.cssSelector(".ng-trigger-flyInOut")).getText();
		Assert.assertEquals(msg, "Incorrect email or password.");	
		driver.close();
	}
	@Test
	public void productErrorValidation() throws IOException
	{
		String productName = "ADIDAS ORIGINAL";
		initializeDriver();
		
		// Login
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("alpha@yopmail.com", "Alpha247*");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		driver.close();
	}
}
