import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CourseWaitListTest {
	
	CourseWaitList CourseWaitList = new CourseWaitList();

    Map<String, String> waitListStudentDetails = new HashMap<String, String>();
    Map<String, Integer> waitListCount = new HashMap<String, Integer>();
    ArrayList <String>waitList = new ArrayList<String>();
    
	@Test
	void testDisplayWaitListForCourse() {
		waitListStudentDetails.put(("SEIS601"),("Name : TestStudent1, Email : test@email.com"));
		waitListStudentDetails.put(("SEIS602"),("Name : TestStudent2, Email : test@email.com"));
		waitListStudentDetails.put(("SEIS602"),("Name : TestStudent3, Email : test@email.com"));
		CourseWaitList.displayWaitListForCourse("SEIS601", waitListStudentDetails);
		assertEquals("Name : TestStudent1, Email : test@email.com",CourseWaitList.studentDetail);
	}

	@Test
	void testIdentifyStudentInMultipleCoursesOrSections() {
		
		String[] waitListArr = {"SEIS602","1","3","428","Last378","First276","STUDENT426@GMAIL.COM"};
		CourseWaitList.identifyStudentInMultipleCoursesOrSections(waitListArr);
		assertEquals("[Course : "+waitListArr[0]+" & Section : "+waitListArr[1]+"]",CourseWaitList.waitListStudentInCourse.get(waitListArr[4]+" "+waitListArr[5]).toString());
	}
	@Test
	void testIdentifyStudentInMultipleCoursesOrSectionsExistingData() {
		
		String[] waitListArr = {"SEIS602","1","3","428","Last378","First276","STUDENT426@GMAIL.COM"};
		waitListArr[1] = "\"SEIS602\",\"1\",\"3\",\"428\",\"Last378\",\"First276\",\"STUDENT426@GMAIL.COM\"";
		CourseWaitList.identifyStudentInMultipleCoursesOrSections(waitListArr);
		assertEquals("[Course : "+waitListArr[0]+" & Section : "+waitListArr[1]+"]",CourseWaitList.waitListStudentInCourse.get(waitListArr[4]+" "+waitListArr[5]).toString());
	}
	
	@Test
	void testMultipleWaitlistReport() {
		List<String> valueList = new ArrayList();
		String value1 = "Name : TestStudent1 Course : SEIS6013 & Section : 1";
		String value2 = "Name : TestStudent1 Course : SEIS6013 & Section : 1";
		
		valueList.add(value1);
		valueList.add(value2);
		CourseWaitList.waitListStudentInCourse.put("Name : TestStudent1", valueList);
		CourseWaitList.multipleWaitlistReport("Y");
		assertEquals(true,CourseWaitList.multipleWaitListExists);
	}
	
	@Test
	void testDisplayWaitListCountForCourse() {
		CourseWaitList.waitList.add("SEIS602");
		CourseWaitList.waitList.add("SEIS602");
		CourseWaitList.waitList.add("SEIS602");
		
		CourseWaitList.displayWaitListCountForCourse(CourseWaitList.waitList);
		int count = CourseWaitList.waitListCount.get("SEIS602");
		assertEquals(3,count);
		
	}
	
	@Test
	void testStartAnalysis() {
		String fileName = ".\\testReports\\registered_scrubbed.csv";
		CourseWaitList.startAnalysis(fileName);
		assertEquals(5,CourseWaitList.waitListStudentDetails.size());
		assertEquals(7,CourseWaitList.waitList.size());
		
	}
	}
