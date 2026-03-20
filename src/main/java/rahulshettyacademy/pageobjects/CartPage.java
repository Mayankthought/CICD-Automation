package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	
	WebDriver Driver;
	public CartPage(WebDriver Driver) 
	{
		super(Driver);
		this.Driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//List<WebElement> cartList=Driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".subtotal .btn.btn-primary")
	WebElement CheckOut;

	public Boolean verifyProductDisplayed(String productName)
	{
		//waitForElementtoAppear(By.cssSelector(".cartSection h3"));//just added
	Boolean match=cartProducts.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productName));
	//just added
	System.out.println("Expected: " + productName);
    cartProducts.forEach(c -> System.out.println("Found in cart: " + c.getText()));
    
	return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		CheckOut.click();
		CheckoutPage checkout= new CheckoutPage(Driver);
		return checkout;
		
	}

}
