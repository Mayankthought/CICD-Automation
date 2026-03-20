package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver Driver;
	public OrderPage(WebDriver Driver) {
		super(Driver);
		this.Driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	 @FindBy(css="div.ngx-spinner-overlay")
	 WebElement spinner;
	 @FindBy(css = "tr td:nth-child(3)")
	 WebElement listAppear;
	 
	//List<WebElement> cartList=Driver.findElements(By.xpath("//tr/td[text()='ADIDAS ORIGINAL']"));
	    @FindBy(css = "tr td:nth-child(3)")
		List<WebElement> orderList;
		
		public boolean verifyProductOrder(String productName)
		{
			//waitForElementtoDisappear(spinner); // Wait for spinner to disappear
			//waitForElementtoAppear(listAppear); // Wait for table rows
			boolean match=orderList.stream().anyMatch(orderedProduct->orderedProduct.getText().equalsIgnoreCase(productName));
			System.out.println("Expected: " + productName);
			orderList.forEach(c -> System.out.println("Found in orders: " + c.getText()));
			return match;
		}

}
