package org.pcn.pccraft;



import io.appium.java_client.android.AndroidDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.pcn.pccraft.framework.Driver;
import org.pcn.pccraft.framework.DeviceBrowsers;
import org.testng.annotations.Test;


//import android.support

public class AndroidTest {
	
	//@Test
	public void test() throws InterruptedException{
		//Driver d = new Driver();
		//d
		
		//DeviceBrowsers. deviceBrowser = "";
		
		
		//String sDeviceBrowser = DeviceBrowsers.Android_NativeApp.toString();
		
		
		AndroidDriver d;// = new Driver(DeviceBrowsers.AndroidDevice_WebView_Chrome);
		
		
		d =   Driver.getAndroidDriver(DeviceBrowsers.Android_NativeApp);
		
		
		
		
		//d.scrollToExact("Jayram");
		
		
		System.out.println(d.getContext());
		
		
		d.findElementByAndroidUIAutomator("UiSelector().text(\"Keypad\")").click();
		
		
		WebElement x = d.findElement(By.name("Jayram"));
		x.click();
		
	
	
		
		x = d.findElement(By.name("Keypad"));
		x.click();
		
		//x.sendKeys("scroll(0, 50);");
		
		
		//UiScrollable settings = new UiScrollable(new UiSelector().className("android.widget.ListView")); 
		
		
		String y = "1";
		
		x = d.findElement(By.name(y));
		x.click();

		d.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				
		x = d.findElement(By.name("5"));
		x.click();
				
		x = d.findElement(By.id("com.android.contacts:id/dialButton"));
		x.click();
		
		
		
		//((JavascriptExecutor) d).executeScript("scroll(0, 50);");
		
		//x = d.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.android.contacts:id/dialButton']"));
		//x.click();
		
		
		
		
		
		//d.get("http://nike.com");
		
		//d.wait(3000);
		
		//d = new Driver(DeviceBrowsers.Safari);
		
		//d.get("http://nike.com");
		
		
		
	}

}
