package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "IPHONE 13 PRO";
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//Login
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("alpha@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Alpha247*");
		driver.findElement(By.id("login")).click();
		
		//Grabbing all products
		List<WebElement> allProducts = driver.findElements(By.cssSelector(".mb-3"));
		//stream().filter return the webElement 
		WebElement prod = allProducts.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		//Add to cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//Checking the push "Added to card" notification using explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//Wait until push popup "Add to card" appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//Wait until loader disappears
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div.ngx-spinner-overlay"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click(); // click cart
		//grabbing all cart items
		List <WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart']//h3"));
		//Check that product added correctly to cart, stream().anyMatch return boolean
		Boolean match = cartItems.stream().anyMatch(cartItem->cartItem.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click(); //Checkout click
		//Select country
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"Pakistan").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//section//button[contains(@class,'ta-item')]")).click(); 
		driver.findElement(By.cssSelector(".action__submit")).click(); //Place Order
		//Confirm that order is placed or not 
		String responseMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(responseMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
