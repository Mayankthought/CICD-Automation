package rahulshettyacademy.tests;
import java.io.IOException;
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
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.TestComponent.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginValidation() throws IOException {
		
		String ProductName= "ADIDAS ORIGINAL";
		
		landingPage.loginApplication("makkrai@gmail.com", "Qwerty234@123");
		
		Assert.assertEquals("Incorrect email or password.",landingPage.errorValidation());
		
	}
	
	@Test
	public void productValidation() throws IOException {
        
		String ProductName= "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingPage.loginApplication("makkrai@gmail.com", "Qwerty@123");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addToCart(ProductName);
		CartPage cartPage =productCatalogue.goToCart();//click add to cart
		
		Boolean match=cartPage.verifyProductDisplayed(ProductName);
		Assert.assertTrue(match);
	}

}
