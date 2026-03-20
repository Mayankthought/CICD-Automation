package rahulshettyacademy.resourses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{
		
		String path=System.getProperty("user.dir")+"\\reprots\\index.html";
		ExtentSparkReporter reproter= new ExtentSparkReporter(path);
		reproter.config().setReportName("WebResults");
		reproter.config().setDocumentTitle("TestResults");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(reproter);
		extent.setSystemInfo("Tester", "Mayank Pal");
		return extent;
	}

}
