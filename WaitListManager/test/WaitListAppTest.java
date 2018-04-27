import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WaitListAppTest {

	WaitListApp waitListApp = new WaitListApp();
	
	@Test
	void testGetMostRecentReport() {
		
		String dirName = ".\\testReports\\";
		String response = waitListApp.getMostRecentReport(dirName);
		assertEquals("success",response);		
	}
	


}
