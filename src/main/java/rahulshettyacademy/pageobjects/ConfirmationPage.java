package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.abstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver Driver;

	public ConfirmationPage(WebDriver Driver) {
		super(Driver);
		this.Driver=Driver;
		PageFactory.initElements(Driver, this);
	
	}
	
	//Driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	@FindBy(css=".hero-primary")
	WebElement ConfirmationMessage;
	
	public String getConfirmationMessage()
	{
		return ConfirmationMessage.getText();
		
	}

}
