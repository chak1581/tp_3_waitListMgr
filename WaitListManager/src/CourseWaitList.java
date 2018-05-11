import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseWaitList {
	
	  String studentDetail;
	  static Map<String, String> waitListStudentDetails = new HashMap<String, String>();
	  Map<String, List<String>> waitListStudentInCourse = new HashMap<String, List<String>>();
	  Map<String, Integer> waitListCount = new HashMap<String, Integer>();
	  ArrayList <String>waitList = new ArrayList<String>();
	  
	  WaitListAppUI waitListAppUi = new WaitListAppUI();
	
	  public void analyzeWaitListReport(String fileName) {
			 
		      startAnalysis(fileName);
			  waitListAppUi.getUserInputToDisplayMultipleWaitlist(this);			  			
			  displayWaitListCountForCourse(waitList);
			  waitListAppUi.displayWaitListForSingleCourse(this);
		  
	  }
	  public void startAnalysis(String fileName) {
			 
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
		  }
		  catch (FileNotFoundException e) {
			  e.printStackTrace();
		  } catch (IOException e) {
			  e.printStackTrace();
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



	 void displayWaitListCountForCourse(ArrayList<String> waitList) {
		for(String word: waitList) {
			  Integer count = waitListCount.get(word);          
			  waitListCount.put(word, (count==null) ? 1 : count+1);  		
		  }
		  waitListAppUi.print("\n *** No. of Waitlist students for each courses ***");
		  waitListAppUi.print("\n"+waitListCount.toString());
	}

	  boolean multipleWaitListExists = false;
	  void multipleWaitlistReport(String choice) {
		  
		  if(choice.equalsIgnoreCase("Y")){
			  for (String key : waitListStudentInCourse.keySet()) {

				  List temp = waitListStudentInCourse.get(key);
				  if(temp.size() > 1)
				  {
					  multipleWaitListExists = true;
					  System.out.println(key+" -> "+temp);
				  }
			  }
		  }
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

		  waitListAppUi.print("\n Waitlist for Course "+Course+" :");
		  for (String key : waitListStudent.keySet()) {
			  if(key.contains(Course)) {
				  studentDetail = waitListStudent.get(key);
				  System.out.println(studentDetail);
			  }
		  }
	  }
}
