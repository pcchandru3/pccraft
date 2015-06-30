package org.pcn.pccraft;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class SeleniumInterview {
	
	
	@Test
	public void testSeleniumInterview(){
		
		WebDriver d = new FirefoxDriver();
		
		// FONT SIZE, COLOR, TYPE AND BACKGROUND COLOR
		d.findElement(By.xpath("")).getCssValue("font-size");
		d.findElement(By.xpath("")).getCssValue("font-colour");
		d.findElement(By.xpath("")).getCssValue("font-type");
		d.findElement(By.xpath("")).getCssValue("background-colour");
		
		
		
		
		// IMPLICIT AND EXPLICIT WAIT
		
		
		
		// DRAG AND DROP
		
		
		// OVERCOME SAME ORIGIN ISSUE : PROXY SETTINGS
		
		
		// ALERTS - SWITCHTO
		
		
		// MOUSE ACTIONS - 
		
	}
	
	
	// CHECK IF AN IMAGE IS BROKEN IMAGE
	public static void verifyimgActive(WebElement img) {
		int invalidimg=0;
		
		try {
		HttpResponse response = new DefaultHttpClient().execute(new HttpGet(img.getAttribute("src")));
		if (response.getStatusLine().getStatusCode() != 200)
		invalidimg++;
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		}
	
	
	
	
	

}
