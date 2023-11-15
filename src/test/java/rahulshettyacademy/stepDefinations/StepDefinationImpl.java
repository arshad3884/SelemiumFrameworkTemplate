package rahulshettyacademy.stepDefinations;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinationImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue; 
	CheckoutPage checkoutPage;
	@Given ("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
	landingPage.loginApplication(username, password);
	}
	@When ("^I add the product (.+) to cart$")
	public void  add_product_to_cart(String productName)
	{
	productCatalogue = new ProductCatalogue(driver);
	productCatalogue.addProductToCart(productName); 
	}
	@And ("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName)
	{
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.clickCheckout();
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.selectCountry("Pakistan");
		checkoutPage.placeOrder();
	}
	@Then ("I verify the message {string} is displayed on confirmation page")
	public void verify_message_displayed_on_Conf_Page(String string)
	{
		Boolean response = checkoutPage.checkOrderConfirmation();
		Assert.assertTrue(response);
		driver.close();
	}
	@Then ("{string} message is displayed")
	public void error_msg_is_displayed(String string)
	{
		String msg = driver.findElement(By.cssSelector(".ng-trigger-flyInOut")).getText();
		Assert.assertEquals(msg, string);
		driver.close();
	}
}
