import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CourseWaitList {
	
	  String studentDetail;
	  static Map<String, String> waitListStudentDetails = new HashMap<String, String>();
	  static Map<String, List<String>> waitListStudentInCourse = new HashMap<String, List<String>>();
	  WaitListAppUI waitListAppUi = new WaitListAppUI();
	
	
	public void analyzeWaitListReport(String fileName) {
		ArrayList <String>waitList = new ArrayList<String>();
		String csvFile = fileName;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
		
		    br = new BufferedReader(new FileReader(csvFile));
		    while ((line = br.readLine()) != null) {
		        String[] waitListArr = line.split(cvsSplitBy);
		        waitList.add(waitListArr[0]);
		        waitListStudentDetails.put((waitListArr[0]+"_"+waitListArr[2]),("Name : "+waitListArr[4]+" "+waitListArr[5]+", Email : "+waitListArr[6]));
		        identifyStudentInMultipleCoursesOrSections(waitListArr);	        
		    }
		    multipleWaitlistReport();
		    Map<String, Integer> wordCount = new HashMap<String, Integer>();			
			for(String word: waitList) {
			  Integer count = wordCount.get(word);          
			  wordCount.put(word, (count==null) ? 1 : count+1);  		
		}
			System.out.println("\n *** No. of Waitlist students for each courses ***");
			System.out.println("\n"+wordCount.toString());
			displayWaitListForSingleCourse();
		}
		catch (FileNotFoundException e) {
		    e.printStackTrace();
		    //return null;
		} catch (IOException e) {
		    e.printStackTrace();
		   // return null;
		} finally {
		    if (br != null) {
		        try {
		            br.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}	
	}

	private void multipleWaitlistReport() {
		System.out.println("Would you like to run analysis to check if any student is registered in multiple courses?[Y/N]");
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		if(choice.equalsIgnoreCase("Y")){
		for (String key : waitListStudentInCourse.keySet()) {
			
			List temp = waitListStudentInCourse.get(key);
			if(temp.size() > 1)
			{
				System.out.println(key+" -> "+temp);
			}
		}
		}
	}

	public void displayWaitListForSingleCourse() {
		
		 System.out.println("\n \nPlease enter the course ID to view the waitlisted students:");
	        Scanner input = new Scanner(System.in);
			String choice = input.next();	
				displayWaitListForCourse(choice,waitListStudentDetails);
			
	}

	public void identifyStudentInMultipleCoursesOrSections(String[] waitListArr) {
		String name = waitListArr[4]+" "+waitListArr[5];
		String value = "Course : "+waitListArr[0]+" & Section : "+waitListArr[1];
		List<String> valueList = new ArrayList();
		valueList.add(value);
		if(waitListStudentInCourse.keySet().contains(name)) {
			List valuesList = waitListStudentInCourse.get(name);
			valuesList.add(value);
			waitListStudentInCourse.put(name, valuesList);
		}else {
			waitListStudentInCourse.put(name, valueList);
		}
	}
	
	public void displayWaitListForCourse(String Course, Map<String,String> waitListStudent){
		
		//ArrayList <String> waitListForCourse = new ArrayList <String>();
		System.out.println("\n Waitlist for Course "+Course+" :");
		for (String key : waitListStudent.keySet()) {
		    if(key.contains(Course)) {
		    	studentDetail = waitListStudent.get(key);
		    	System.out.println(studentDetail);
		    }
		}
	}
}
