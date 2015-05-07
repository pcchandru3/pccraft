package org.pcn.pccraft;



import org.openqa.selenium.WebDriver;
import org.pcn.pccraft.framework.Driver;
import org.testng.annotations.Test;

public class SampleTest {
	
	@Test
	public void test(){
		//Driver d = new Driver();
		//d
		
		
		WebDriver d = new Driver("Firefox");
		
		d.get("http://nike.com");
		
		
		d = new Driver("Safari");
		
		d.get("http://nike.com");
		
	}

}
