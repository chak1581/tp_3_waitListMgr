import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class WaitListAppTest {

	WaitListApp app = new WaitListApp();
	
	@Test
	void testGetMostRecentReportSuccess() {
		
		String dirName = ".\\reports\\";
		String response = app.getMostRecentReport(dirName);
		assertEquals("success",response);		
	}
	
	@Test
	void testGetMostRecentReportFailure() {
		
		String dirName = ".\\testReports\\test\\";
		for(File dir_contents: new File(dirName).listFiles()) {
			if(!dir_contents.isDirectory())
				dir_contents.delete();
		}
		String response = app.getMostRecentReport(dirName);
		assertEquals(response,"failure");		
	}
	
	/*@Test
	void test_uploadRegReport() {
		
		String dirName = ".\\reports\\";
		app.uploadRegistrationsReport();
		File file = new File(dirName);
		File[] dir_contents = file.listFiles();
		String filepath = " ";
		filepath = ".\\reports\\" + dir_contents[dir_contents.length-1].getName();
		System.out.println(filepath);
		boolean fileExist = new File(filepath).exists();
		assertEquals(fileExist,true);		
	}
*/
}
