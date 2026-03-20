package rahulshettyacademy.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rahulshettyacademy.pageobjects.LandingPage;

public class WebLogin {

	public static void main(String[] args) {
		
		String ProductName= "ADIDAS ORIGINAL";
		
		WebDriver Driver= new ChromeDriver();
		Driver.get("https://rahulshettyacademy.com/client");
		
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Driver.manage().window().maximize();
		
		Driver.findElement(By.id("userEmail")).sendKeys("makkrai@gmail.com");
		Driver.findElement(By.id("userPassword")).sendKeys("Qwerty@123");
		Driver.findElement(By.id("login")).click();
		WebDriverWait wait= new WebDriverWait(Driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		List<WebElement>products=Driver.findElements(By.cssSelector(".col-lg-4"));//List of all products present
		
		WebElement prod=products.stream()
		.filter(product->product.findElement(By.cssSelector("b")).getText().equals(ProductName))
		.findFirst().orElse(null);// product name
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();//click add to cart
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(Driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
		Driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartList=Driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match=cartList.stream().anyMatch(cart->cart.getText().equals(ProductName));
		Assert.assertTrue(match);
		
		Driver.findElement(By.cssSelector(".subtotal .btn.btn-primary")).click();
		
		
		Actions a= new Actions(Driver);
		a.sendKeys(Driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		Driver.findElement(By.cssSelector(".ta-results button:nth-child(3)")).click();
		
		Driver.findElement(By.cssSelector(".action__submit")).click();
		String Complete=Driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(Complete, "THANKYOU FOR THE ORDER.");
		Driver.quit();
		


	}

}
