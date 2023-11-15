package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryOption;
	By countrySuggestionList = By.cssSelector(".ta-results");
	@FindBy(xpath = "//section//button[contains(@class,'ta-item')]")
	WebElement selectCountry;
	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	public void selectCountry(String countryName) {
		// Select country
		Actions a = new Actions(driver);
		a.sendKeys(countryOption, countryName).build().perform();
		waitForElementToAppear(countrySuggestionList);
		selectCountry.click();
	}

	public void placeOrder() {
		placeOrder.click(); // Place Order
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMsg;

	public Boolean checkOrderConfirmation() {
		// Confirm that order is placed or not
		String responseMessage = confirmationMsg.getText();
		Boolean response = responseMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		return response;
	}
}
