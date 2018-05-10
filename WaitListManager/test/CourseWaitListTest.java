import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class CourseWaitListTest {
	
	CourseWaitList CourseWaitList = new CourseWaitList();

    Map<String, String> waitListStudentDetails = new HashMap<String, String>();
    
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
}
