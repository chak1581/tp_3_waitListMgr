import static org.junit.jupiter.api.Assertions.*;

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

}
