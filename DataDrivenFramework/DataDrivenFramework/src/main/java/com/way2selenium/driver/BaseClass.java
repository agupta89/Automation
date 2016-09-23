package com.way2selenium.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public static XlsReader data;
	public Properties prop;
	@BeforeClass
	@Parameters({"browserName","environment"})
	public void setup(String browserName,String environment) throws InterruptedException, IOException{
		
		//Excel path configuration
		data= new XlsReader(System.getProperty("user.dir")+"/src/main/resources/TestData.xlsx"); 
		
		 
		//selecting browser based on parameter from TestNG.xml
		if(browserName.equalsIgnoreCase("firefox")){
			
			driver = new FirefoxDriver();
		
		}
		else if(browserName.equalsIgnoreCase("chrome")){

			//work with chrome
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/DriverFiles/chromedriver.exe");
			driver = new ChromeDriver();	
		}
		
		else if(browserName.equalsIgnoreCase("opera")){
			//opera
			System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"/DriverFiles/operadriver.exe");
			driver = new OperaDriver();
		}
		
		else if(browserName.equalsIgnoreCase("ie")){

			//work with Internet explorer
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/DriverFiles/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
		}
		
		else if(browserName.equalsIgnoreCase("safari")){

			//work with Internet explorer
			System.setProperty("webdriver.safari.noinstall", "true"); //To stop uninstall each time
			driver = new SafariDriver();
			
		}
		
		else if(browserName.equalsIgnoreCase("phantom")){

			File src = new File ("C:\\AutomationGroup10\\Drivers\\phantomjs.exe");
			System.setProperty("phantomjs.binary.path", src.getAbsolutePath());
		    driver = new PhantomJSDriver();
			
		}
		
		else if(browserName.equalsIgnoreCase("Htmlunit")){

			
		    driver = new HtmlUnitDriver();
		    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
		   /* Logger logger = Logger.getLogger("");
		    logger.setLevel(Level.OFF); */
			
		}
		
		
		
		driver.manage().window().maximize();
		

		prop= new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/Environment.properties");

		prop.load(file);
		String qaurl=prop.getProperty("ENV_QA");
		String stageurl=prop.getProperty("ENV_SATGING");
		String productionurl=prop.getProperty("ENV_PRODUCTION");
		
		if(environment.equalsIgnoreCase("qa")){
			
			driver.get(qaurl);
			
		}
		else if(environment.equalsIgnoreCase("staging")){
			driver.get(stageurl);

			
		}
		else if(environment.equalsIgnoreCase("production")){
			driver.get(productionurl);

			
		}
		Thread.sleep(5000);

	}
	
	
	
	@AfterClass
	public void end(){
		
		driver.close();
			
	}
	
	
	
	
	
	
	
	
	

}
