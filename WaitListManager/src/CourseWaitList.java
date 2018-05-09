import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseWaitList {
	
	  static Map<String, String> waitListStudent = new HashMap<String, String>();
	  static Map<String, List<String>> waitListStudent1 = new HashMap<String, List<String>>();
	  WaitListAppUI waitListAppUi = new WaitListAppUI();
	
	public static void main(String[] args) {
		analyzeWaitListReport("waitlist_scrubbed.csv");
		displayWaitListForCourse("SEIS602",waitListStudent);
	}

	public static void analyzeWaitListReport(String fileName) {
		ArrayList <String>waitList = new ArrayList<String>();
		String csvFile = fileName;
		System.out.println(csvFile);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
		
		    br = new BufferedReader(new FileReader(csvFile));
		    while ((line = br.readLine()) != null) {
		        String[] waitListArr = line.split(cvsSplitBy);
		        waitList.add(waitListArr[0]);
		        waitListStudent.put((waitListArr[0]+"_"+waitListArr[2]),("Name : "+waitListArr[4]+" "+waitListArr[5]+", Email : "+waitListArr[6]));
		        identifyStudentInMultipleCoursesOrSections(waitListArr);
		        
		    }
		    System.out.println("waitListStudent1 \n"+waitListStudent1);
	        for (String key : waitListStudent1.keySet()) {
	        	
	        	List temp = waitListStudent1.get(key);
	        	if(temp.size() > 1)
	        	{
	        		System.out.println(key+" -> "+temp);
	        	}
	        }
		    
		  //  System.out.println(waitList);
		    Map<String, Integer> wordCount = new HashMap<String, Integer>();
			
			for(String word: waitList) {
			  Integer count = wordCount.get(word);          
			  wordCount.put(word, (count==null) ? 1 : count+1);  
			
		
		}
			System.out.println("Report -- Course Registration Analysis--File Name: "+fileName);
			System.out.println(wordCount.toString());
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

	private static void identifyStudentInMultipleCoursesOrSections(String[] waitListArr) {
		String name = waitListArr[4]+" "+waitListArr[5];
		String value = "Course : "+waitListArr[0]+" & Section : "+waitListArr[1];
		List<String> valueList = new ArrayList();
		valueList.add(value);
		if(waitListStudent1.keySet().contains(name)) {
			List valuesList = waitListStudent1.get(name);
			valuesList.add(value);
			waitListStudent1.put(name, valuesList);
		}else {
			waitListStudent1.put(name, valueList);
		}
	}

	public static void displayWaitListForCourse(String Course, Map<String,String> waitListStudent){
		ArrayList <String> waitListForCourse = new ArrayList <String>();
		System.out.println(waitListStudent);
		System.out.println("Waitlist for Course "+Course+" :");
		for (String key : waitListStudent.keySet()) {
		    if(key.contains(Course)) {
		    	System.out.println(waitListStudent.get(key));
		    }
		}
	}
}
