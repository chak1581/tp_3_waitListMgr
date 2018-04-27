import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CourseRegistrationTest {

	
CourseRegistration registration = new CourseRegistration();
	
	@Test
	public void testGetAllKeys() {
		
		Map<String,String> testMap = new HashMap<String,String>();
		testMap.put("SEIS630", "5");
		testMap.put("SEIS631", "8");
		testMap.put("SEIS633", "10");
		
		List<String> key = new ArrayList<String>();
		key = registration.getAllKeys(testMap);
		assertEquals("SEIS633",key.get(0));
		assertEquals("SEIS631",key.get(1));
		assertEquals("SEIS630",key.get(2));
	}
	
	@Test
	public void testGetAllKeys_2() {
		
		Map<String,String> testMap = new HashMap<String,String>();
		
		List<String> key = new ArrayList<String>();
		key = registration.getAllKeys(testMap);
		assertEquals(true,key.isEmpty());
		
	}
	
	@Test
	public void testDetermineOpenSeat() {
		
		
		Map<String,String> testPrevMap = new HashMap<String,String>();
		testPrevMap.put("SEIS630", "5");
		testPrevMap.put("SEIS631", "8");
		testPrevMap.put("SEIS633", "10");
		
		Map<String,String> testCurrentMap = new HashMap<String,String>();
		testCurrentMap.put("SEIS630", "5");
		testCurrentMap.put("SEIS631", "8");
		testCurrentMap.put("SEIS633", "10");
		
		boolean openSeat = registration.determineOpenSeat(testPrevMap, testCurrentMap);
		
		assertEquals(false,openSeat);
		
	}
	
	@Test
	public void testDetermineOpenSeat_2() {
		
		
		Map<String,String> testPrevMap = new HashMap<String,String>();
		testPrevMap.put("SEIS630", "5");
		testPrevMap.put("SEIS631", "8");
		testPrevMap.put("SEIS633", "10");
		
		Map<String,String> testCurrentMap = new HashMap<String,String>();
		testCurrentMap.put("SEIS630", "5");
		testCurrentMap.put("SEIS631", "9");
		testCurrentMap.put("SEIS633", "11");
		
		boolean openSeat = registration.determineOpenSeat(testPrevMap, testCurrentMap);
		
		assertEquals(true,openSeat);
		
	}
	
	@Test
	public void testDetermineOpenSeat_3() {
		
		
		Map<String,String> testPrevMap = new HashMap<String,String>();
		testPrevMap.put("SEIS630", "5");
		testPrevMap.put("SEIS631", "9");
		testPrevMap.put("SEIS633", "10");
		
		Map<String,String> testCurrentMap = new HashMap<String,String>();
		testCurrentMap.put("SEIS630", "5");
		testCurrentMap.put("SEIS631", "8");
		testCurrentMap.put("SEIS633", "9");
		
		boolean openSeat = registration.determineOpenSeat(testPrevMap, testCurrentMap);
		
		assertEquals(true,openSeat);
		
	}

}
