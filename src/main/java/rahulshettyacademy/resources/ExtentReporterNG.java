package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports giveReportObject()
	{
		//ExtentReports, ExtentSparkReporter   these two classes are used
				//ExtentSparkReporter is expecting a path where our reports would be created
				String path = System.getProperty("user.dir")+"//reports//index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results"); //To set report name
				reporter.config().setDocumentTitle("Test Results"); // Set Document Title
				
				//ExtentReports responsible to drive all reporting execution 
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter); //Attaching a reporter config class object with our main class extent
				extent.setSystemInfo("Tester", "Rahul Shetty"); //Set tester Name	
				return extent;
	}

}
