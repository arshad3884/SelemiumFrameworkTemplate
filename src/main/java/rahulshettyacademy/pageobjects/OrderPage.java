package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement ordersHeader;
	
	@FindBy (xpath="//tr[@class='ng-star-inserted']//td[2]")
	List <WebElement> orderItems;
	
	public Boolean verifyOrderDisplay(String productName)
	{		
		ordersHeader.click();
		List<WebElement> productList = orderItems;
		Boolean match = productList.stream().filter(prod->prod.getText().equalsIgnoreCase(productName)).findFirst().isPresent();
		//Check that product added correctly to cart, stream().anyMatch return boolean
		//Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	@FindBy (css=".totalRow button")
	WebElement checkOutCTA;
	public void clickCheckout()
	{
		checkOutCTA.click(); //Checkout click
	}
}
