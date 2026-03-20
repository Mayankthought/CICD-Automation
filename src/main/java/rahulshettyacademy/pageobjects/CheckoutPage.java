package rahulshettyacademy.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver Driver;

	public CheckoutPage(WebDriver Driver) {
		super(Driver);
		this.Driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//Driver.findElement(By.xpath("//input[@placeholder='Select Country']
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement typeCountry;
	
	//Driver.findElement(By.cssSelector(".ta-results button:nth-child(3)"))
	@FindBy(css=".ta-results button:nth-child(3)")
	WebElement SelectCountry;
	
	@FindBy(css=".action__submit")
	WebElement submitOrder;
	
	
	By Results=By.cssSelector(".ta-results");
	
	public String selectCountry(String countryName)
	{
		
		Actions a= new Actions(Driver);
		a.sendKeys(typeCountry, "india").build().perform();
		waitForElementtoAppear(Results);
		SelectCountry.click();
		return countryName;
		
		}
	
	public ConfirmationPage gotoConfirmation()
	{
		submitOrder.click();
		ConfirmationPage confirmationPage= new ConfirmationPage(Driver);
		return confirmationPage;
	}

}
