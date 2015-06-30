package org.pcn.pccraft;



import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.pcn.pccraft.framework.Driver;
import org.pcn.pccraft.framework.DeviceBrowsers;
import org.testng.annotations.Test;


//import android.support

public class IOSTest {
	
	//@Test
	public void test() throws InterruptedException{
		
		//File app = new File("/Users/cpanag/01WorkingFolder/My-GitHub/pccraft/src/test/resources/iOS/UICatalog.app");
		
		IOSDriver d = Driver.getIOSDriver(DeviceBrowsers.iOSRealDevice_Safari);
		
		d.get("http://nike.com");
		
		
	}

}
