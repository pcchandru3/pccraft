package org.pcn.pccraft;



import org.openqa.selenium.WebDriver;
import org.pcn.pccraft.framework.Driver;
import org.pcn.pccraft.framework.DeviceBrowsers;
import org.testng.annotations.Test;

public class SampleTest {
	
	@Test
	public void test() throws InterruptedException{
		//Driver d = new Driver();
		//d
		
		//DeviceBrowsers. deviceBrowser = "";
		
		WebDriver d;// = new Driver(DeviceBrowsers.AndroidDevice_WebView_Chrome);
		
		
		d =  Driver.getDriver(DeviceBrowsers.AndroidDevice_App_APK);
		
		d.get("http://nike.com");
		
		//d.wait(3000);
		
		//d = new Driver(DeviceBrowsers.Safari);
		
		//d.get("http://nike.com");
		
		
		
	}

}
