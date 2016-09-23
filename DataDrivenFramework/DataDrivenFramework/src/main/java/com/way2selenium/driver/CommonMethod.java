package com.way2selenium.driver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonMethod {
	
	public static WebElement findElement(final WebDriver driver,String objectLocater) throws IOException{
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		String objecttypeandvalues = OR.getProperty(objectLocater);
		System.out.println(objecttypeandvalues);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0]; 
		System.out.println("obj type: " + objecttype);
		String objectvalue = splits[1];
		System.out.println("obj val: " + objectvalue);
			
		switch(objecttype){
		  
						case "id":
							return driver.findElement(By.id(objectvalue));
							
						case "xpath":
			  
							return driver.findElement(By.xpath(objectvalue));
			  			                
			            case "name":
			  
			            	return driver.findElement(By.name(objectvalue));
			  			               		  
			            case "class":
			  
			            	return driver.findElement(By.className(objectvalue));
			  
			            case "tagname":
			  
			        	    return driver.findElement(By.tagName(objectvalue));
			     
			            case "css":
			  			  
				        	return driver.findElement(By.cssSelector(objectvalue));
			            default:
			            	
			            	return null;
		}
		
		}
	//user defined click Method
	public static void click(WebDriver driver,String objectLocater) throws IOException{
		findElement(driver, objectLocater).click();
	}
	//user defined sendkeys Method
	public static void sendKeys(WebDriver driver,String objectLocater,String value) throws IOException{
		findElement(driver, objectLocater).sendKeys(value);
	}
	//user defined gettext Method
	public static String getText(WebDriver driver, String objectLocater) throws IOException {
	
		return findElement(driver, objectLocater).getText();
		
	}
	//user defined clear Method
	public static void clear(WebDriver driver,String objectLocater) throws IOException{
		findElement(driver, objectLocater).clear();
	}
		
}