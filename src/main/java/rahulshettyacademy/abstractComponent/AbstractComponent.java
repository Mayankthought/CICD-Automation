package rahulshettyacademy.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver Driver;
	public AbstractComponent(WebDriver Driver) {
		this.Driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement clickCart;
	
	
	//Driver.findElement(By.className("[routerlink*='/dashboard/myorders']")).click();
	@FindBy(css="[routerlink*='/dashboard/myorders']")
	WebElement clickOrders;
	
	
	public OrderPage goToOrders()
	{
		clickOrders.click();
		OrderPage order= new OrderPage(Driver);
		return order;
	}
	
	
	public CartPage goToCart()
	{
		
		clickCart.click();
		CartPage cart= new CartPage(Driver);
		return cart;
	}

	public void waitForElementtoAppear(By FindBy)
	{
	
	WebDriverWait wait= new WebDriverWait(Driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForElementtoAppear(WebElement FindBy)
	{
	
	WebDriverWait wait= new WebDriverWait(Driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	//wait.until(ExpectedConditions.invisibilityOf(Driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
	
	public void waitForElementtoDisappear(WebElement ele)
	{
		WebDriverWait wait= new WebDriverWait(Driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
