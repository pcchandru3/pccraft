package org.pcn.mockito;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;




public class TestMockito {
	
	@Mock
	Math mathObj;
	
	@BeforeMethod
	public void create() {
		
		mathObj = Mockito.mock(Math.class);
		Mockito.when(mathObj.add(1,2)).thenReturn(3);
		
		
		
		
		
		
		
	}

	

	
	@Test
	public void MockitoTest() {
		System.out.println(mathObj.add(1, 3));
	}

	
	
	

}
