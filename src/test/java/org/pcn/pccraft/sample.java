package org.pcn.pccraft;

import io.appium.java_client.android.AndroidDriver;


//import org.junit.Test;
import org.openqa.selenium.By;
import org.pcn.pccraft.framework.DeviceBrowsers;
import org.pcn.pccraft.framework.Driver;
import org.testng.annotations.Test;

public class sample {

	public AndroidDriver d; 
	
	
	@Test
	public void testingnnn(){
		
		try {
			d = Driver.getAndroidDriver(DeviceBrowsers.AndroidDevice_WebView_Chrome);
			
			d.get("http://nike.com");
			
			while (d.findElement(By.cssSelector(".search-field-container>input")).isDisplayed()){
				if(d.findElement(By.cssSelector(".search-field-container>input")).isDisplayed())
					break;
			}
			
			//d.findElement(By.cssSelector(".search")).click();
			d.findElement(By.cssSelector(".search-field-container>input")).sendKeys("test");
			System.out.println("pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
