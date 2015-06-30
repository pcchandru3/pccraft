package org.pcn.pccraft;



import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pcn.pccraft.framework.Driver;
import org.pcn.pccraft.framework.DeviceBrowsers;
import org.testng.annotations.Test;


//import android.support

public class AndroidWebViewTest {
	
	@Test
	public  void test() throws InterruptedException{
		
		AndroidDriver d; // = new Driver(DeviceBrowsers.AndroidDevice_WebView_Chrome);
		
		
		d = Driver.getAndroidDriver(DeviceBrowsers.AndroidDevice_WebView_Chrome);
		
		Thread.sleep(3000);
		
		d.get("http://nike.com");
		Thread.sleep(3000);
		
		
		d.findElement(By.cssSelector(".cart")).click();
		
		while (!d.findElement(By.cssSelector(".search-field-container>input")).isDisplayed()){
			if(d.findElement(By.cssSelector(".search-field-container>input")).isDisplayed()){
				System.out.println("Pass");
				break;
			}
		}
		
		Thread.sleep(3000);
		
		//d.findElement(By.cssSelector(".search")).click();
		d.findElement(By.cssSelector(".search-field-container>input")).sendKeys("test");
		
		
		
		d.findElement(By.cssSelector(".cart")).getCssValue("font-size");
		
		
		
	}

}
