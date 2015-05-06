package org.pcn.pccraft.framework;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Driver {
	
	@Test
	public void testSimulator(){
		
		//String sDevice = "AndroidEmulator";
		//String sDevice = "AndroidRealDevice";
		String sDevice = "iOSEmulator";
		//String sDevice = "iOSRealDevice";
		
		WebDriver driver = null;
		
		DesiredCapabilities capabilitiesANDROID = new DesiredCapabilities();
		DesiredCapabilities capabilitiesIOS = new DesiredCapabilities();
		
		switch(sDevice){
			
			case "Chrome":
				break;
			case "Firefox":
				break;
			case "InternetExplorer":
				break;
			case "Safari":
				break;
			
			case "AndroidEmulator":
				capabilitiesANDROID.setCapability("platformName", "Android");
				capabilitiesANDROID.setCapability("platformVersion", "4.4");
				capabilitiesANDROID.setCapability("deviceName", "emulator-5554");
				capabilitiesANDROID.setCapability("browserName", "Browser");
				//capabilitiesANDROID.setCapability("browserName", "Chrome");
				
				try {
					driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilitiesANDROID);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "AndroidRealDevice":
				capabilitiesANDROID.setCapability("platformName", "Android");
				capabilitiesANDROID.setCapability("platformVersion", "4.4");
				//capabilitiesANDROID.setCapability("browserName", "Browser");
				capabilitiesANDROID.setCapability("browserName", "Chrome");
				capabilitiesANDROID.setCapability("deviceName", "c03cb826");
				
				try {
					driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilitiesANDROID);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "iOSEmulator":
				capabilitiesIOS.setBrowserName(BrowserType.SAFARI);
				capabilitiesIOS.setCapability("platformName", "iOS");
				capabilitiesIOS.setCapability("platformVersion", "8.2");
				//capabilitiesIOS.setCapability("safariAllowPopups", false);
				//capabilitiesIOS.setCapability("safariIgnoreFraudWarning", "true");
				capabilitiesIOS.setCapability("deviceName", "iPhone 5");
				//capabilitiesIOS.setCapability("browserName", "Safari");
				
				try {
					driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilitiesIOS);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				break;
			case "iOSRealDevice":
				capabilitiesIOS.setCapability("platformName", "iOS");
				capabilitiesIOS.setCapability("platformVersion", "8.2");
				capabilitiesIOS.setCapability("safariAllowPopups", false);
				capabilitiesIOS.setCapability("safariIgnoreFraudWarning", "true");
				capabilitiesIOS.setCapability("deviceName", "iPhone 6");
				capabilitiesIOS.setCapability("browserName", "Safari");
				
				try {
					driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilitiesIOS);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				break;
		}
		
		driver.get("http://nike.com");
	}
	

}


