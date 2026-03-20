package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver Driver;
	
	public LandingPage(WebDriver Driver)
	{
		super(Driver);
		this.Driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//WebElement userEmail=Driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;

	//Driver.findElement(By.id("userPassword"))
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	//Driver.findElement(By.id("login"))
	@FindBy(id="login")
	WebElement login;
	
	//[class*='-flyInOut']
	@FindBy(css="[class*='-flyInOut']")
	WebElement errorMessage;
	
	public String errorValidation()
	{
		waitForElementtoAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(Driver);
		return productCatalogue;
	}
	
	public void goTo() {
		
		Driver.get("https://rahulshettyacademy.com/client");
	}
}
