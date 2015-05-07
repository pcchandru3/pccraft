package org.pcn.pccraft.framework;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Driver implements WebDriver {
	
	String sDevice="";
	
	public Driver(String sDeviceBrowser){
		this.sDevice = sDeviceBrowser;
		getDriver(sDeviceBrowser);
	}
	
	//@Test
	public WebDriver getDriver(String sDeviceBrowser){
		
		
		
		//sDevice = "AndroidEmulator";
		//sDevice = "AndroidRealDevice";
		//sDevice = "iOSEmulator";
		//sDevice = "iOSRealDevice";
			
		sDevice = "Firefox";
		
		WebDriver driver = null;
		
		DesiredCapabilities capabilitiesANDROID = new DesiredCapabilities();
		DesiredCapabilities capabilitiesIOS = new DesiredCapabilities();
		DesiredCapabilities capabilities;
		
		switch(sDeviceBrowser){
			
			case "Chrome":
				break;
			case "Firefox":
				//driver = new FirefoxDriver();
				
				capabilities = DesiredCapabilities.firefox();
                driver = new FirefoxDriver(capabilities);
				break;
			case "InternetExplorer":
				break;
			case "Safari":
				//DesiredCapabilities capabilities;
				capabilities = DesiredCapabilities.safari();
                driver = new SafariDriver(capabilities);
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


