package rahulshettyacademy.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import rahulshettyacademy.pageobjects.LandingPage;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

public class BaseTest {
	
	public WebDriver Driver;
	public LandingPage landingPage;
	
	public WebDriver initializeBrowser() throws IOException {
		
		
		//properties class
		Properties prop= new Properties();
		FileInputStream file= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resourses\\GlobalData.properties");
		prop.load(file);
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//prop.getProperty("browser");
		
		
		if(browserName.contains("chrome")) 
		{
			ChromeOptions options= new ChromeOptions();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
		 Driver= new ChromeDriver(options);
		 Driver.manage().window().setSize(new Dimension(1440,900));
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//firefox
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			//edge
		}
		
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Driver.manage().window().maximize();
		return Driver;
	}
	
	public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException
	{
		
		//read json to string
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//string to Hashmap
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String, String>> data =mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
	});
		return data;

}

	public String getScreenshot(String testCaseName,WebDriver Driver) throws IOException
	{
		TakesScreenshot	ts=(TakesScreenshot)Driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		
		Driver = initializeBrowser();
		
		 System.out.println("Launching new browser instance: " + Driver.hashCode());
		 
		landingPage= new LandingPage(Driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void quit()
	{
		
		 System.out.println("Closing browser instance: " + Driver.hashCode());
		Driver.quit();
	}

}
