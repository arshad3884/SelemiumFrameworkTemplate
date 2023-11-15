package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//div[@class='cart']//h3")
	List <WebElement> cartItems;
	public Boolean verifyProductDisplay(String productName)
	{
		//Check that product added correctly to cart, stream().anyMatch return boolean
		Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	@FindBy (css=".totalRow button")
	WebElement checkOutCTA;
	public void clickCheckout()
	{
		checkOutCTA.click(); //Checkout click
	}
}
