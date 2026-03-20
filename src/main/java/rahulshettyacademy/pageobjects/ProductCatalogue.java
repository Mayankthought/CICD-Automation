package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver Driver;
	
	public ProductCatalogue(WebDriver Driver)
	{
		super(Driver);
		this.Driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//List<WebElement>products=Driver.findElements(By.cssSelector(".col-lg-4"));//List of all products present
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	//wait.until(ExpectedConditions.invisibilityOf(Driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
	@FindBy(css=".ngx-spinner-overlay")
	WebElement spinner;
	
	
	
	  By ProductsBy=By.cssSelector(".col-lg-4");
	  By addcart= By.cssSelector(".card-body button:last-of-type");
	 By toastMessage= By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()
	{
		waitForElementtoAppear(ProductsBy);
		return products;
	}
	
	public WebElement getProductName( String productName)
	{
		
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText()
				.equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName)
	{
		WebElement prod= getProductName(productName);
		prod.findElement(addcart).click();
		waitForElementtoAppear(toastMessage);
		waitForElementtoDisappear(spinner);
		
	}

		
	
}