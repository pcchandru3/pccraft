package org.pcn.pccraft.framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.pcn.pccraft.framework.DeviceBrowsers;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;



public class Driver implements WebDriver {
	
	String sDevice="";
	
	/*
	 	this.sDevice = deviceBrowser.toString();
		getDriver(deviceBrowser);
	}
	*/
	
	
	public Driver(DeviceBrowsers deviceBrowser) {
		this.sDevice = deviceBrowser.toString();
		getDriver(deviceBrowser);
	}

	//@Test
	public static WebDriver getDriver(DeviceBrowsers deviceBrowser){
		
		
		
		//sDevice = "AndroidEmulator";
		//sDevice = "AndroidRealDevice";
		//sDevice = "iOSEmulator";
		//sDevice = "iOSRealDevice";
			
		//sDevice = "Firefox";
		
		WebDriver driver = null;
		
		DesiredCapabilities capabilitiesANDROID = new DesiredCapabilities();
		DesiredCapabilities capabilitiesIOS = new DesiredCapabilities();
		DesiredCapabilities capabilities;
		
		
		//DeviceBrowsers deviceBrowser=null;
		
		
		switch(deviceBrowser){
			
			case Chrome:
				break;
			case Firefox:
				//driver = new FirefoxDriver();
				
				capabilities = DesiredCapabilities.firefox();
                driver = new FirefoxDriver(capabilities);
				break;
			case InternetExplorer:
				break;
			case Safari:
				//DesiredCapabilities capabilities;
				capabilities = DesiredCapabilities.safari();
                driver = new SafariDriver(capabilities);
				break;
			
			case AndroidEmulator:
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
			case AndroidDevice_WebView_Chrome: //DeviceBrowsers.  GlobalObjects.DeviceBrowsers. "AndroidDevice-WebView-Chrome":
				capabilitiesANDROID.setCapability("platformName", "Android");
				capabilitiesANDROID.setCapability("platformVersion", "4.4");
				capabilitiesANDROID.setCapability("browserName", "Chrome");
				capabilitiesANDROID.setCapability("deviceName", "c03cb826");
				try {
					driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilitiesANDROID);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				break;
                
			case AndroidDevice_WebView_NativeBrowser:
				capabilitiesANDROID.setCapability("platformName", "Android");
				capabilitiesANDROID.setCapability("platformVersion", "4.4");
				capabilitiesANDROID.setCapability("browserName", "Browser");
				capabilitiesANDROID.setCapability("deviceName", "c03cb826");
				try {
					driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilitiesANDROID);
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				break;    
                
            case AndroidDevice_App_APK:
            	// /Users/cpanag/01WorkingFolder/My-GitHub/pccraft/src/test/resources/WhatsApp.apk
                //File app = new File("src//test//resources//WhatsApp.apk");
            	File app = new File("src//test//resources//AndroidCalculator.apk");
                capabilitiesANDROID.setCapability(CapabilityType.BROWSER_NAME, "");
                capabilitiesANDROID.setCapability("deviceName","Nexus 5");
                capabilitiesANDROID.setCapability("platformVersion", "4.4.4");
                capabilitiesANDROID.setCapability("platformName","Android");
                capabilitiesANDROID.setCapability("app", app.getAbsolutePath());
                capabilitiesANDROID.setCapability("appPackage", "com.whatsapp");
                capabilitiesANDROID.setCapability("appActivity", "com.whatsapp.Main");
                try {
					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesANDROID);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                break;
                
                
            case Android_NativeApp:
            	capabilitiesANDROID.setCapability("deviceName","Nexus 5");
                capabilitiesANDROID.setCapability("platformVersion", "4.4.4");
                capabilitiesANDROID.setCapability("platformName","Android");
                //capabilitiesANDROID.setCapability("app", app.getAbsolutePath());
                capabilitiesANDROID.setCapability("appPackage", "com.whatsapp");
                capabilitiesANDROID.setCapability("appActivity", "com.whatsapp.Main");
                try {
					driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitiesANDROID);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                break;
                
			case iOSEmulator:
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
			case iOSRealDevice:
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
		
		return driver;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WebElement findElement(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void get(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getWindowHandles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Options manage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Navigation navigate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return null;
	}
	

}


