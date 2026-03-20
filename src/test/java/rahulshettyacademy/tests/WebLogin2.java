package rahulshettyacademy.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class WebLogin2 extends BaseTest {
	
	 String productName;

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {
		
		//productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();
		
		productCatalogue.addToCart(input.get("product"));
		CartPage cartPage =productCatalogue.goToCart();//click add to cart
		
		Boolean match=cartPage.verifyProductDisplayed(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkout= cartPage.goToCheckout();
		checkout.selectCountry("india");
		ConfirmationPage confirmationPage=checkout.gotoConfirmation();
		String confirm=confirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirm, "THANKYOU FOR THE ORDER.");
		

	}
	
	//verify product is on order history page
	
	@Test(dependsOnMethods={"submitOrder"})
	public void verifyProduct() throws InterruptedException
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("makkrai@gmail.com", "Qwerty@123");
		
		//Thread.sleep(3000);
		
		OrderPage order =productCatalogue.goToOrders();
		
		Assert.assertTrue(order.verifyProductOrder(productName));
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>>data=getJasonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\purchaseOrder.jason");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

	

	//HashMap<String,String> map= new HashMap<String,String>();
	//map.put("email", "pady1@gmail.com");
	//map.put("password", "Qwerty@123");
	//map.put("product", "ZARA COAT 3");
	
	//HashMap<String,String> map1= new HashMap<String,String>();
	//map1.put("email", "makkrai@gmail.com");
	//map1.put("password", "Qwerty@123");
	//map1.put("product", "ADIDAS ORIGINAL");
	
}