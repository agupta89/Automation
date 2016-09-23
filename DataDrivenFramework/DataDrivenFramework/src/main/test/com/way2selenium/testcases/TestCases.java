package com.way2selenium.testcases;

import java.io.IOException;







import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.way2selenium.ReusableMethods.ReusableMethods;
import com.way2selenium.driver.BaseClass;


public class TestCases extends BaseClass {
	
	ReusableMethods reuse = new ReusableMethods();
	/**
	 * TC-001 - Verify Contact Form Functionality
	 * @throws IOException
	 */
	@Test(priority=1)
	public void contactForm() throws IOException{
		try
		{
			reuse.clickOnContact(driver);
			reuse.getDataForConatctForm("ContactForm");
			reuse.fillConatctForm(driver);
			reuse.clickOnSubmit(driver);
		
		}
		catch(Throwable t)
		{
		System.out.println(t.getLocalizedMessage());
		Error e1 = new Error(t.getMessage()); 
		e1.setStackTrace(t.getStackTrace()); 
		throw e1;
		}
	}
	
	@AfterTest
	public void teardown(){
		
		driver.quit();
	}
	
}
