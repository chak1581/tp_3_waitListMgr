import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CourseRegistration {
	
	
	Map previousRegistration;
	Map currentRegistration;

	public Map getPreviousRegistration() {
		return previousRegistration;
	}

	public void setPreviousRegistration(Map previousRegistration) {
		this.previousRegistration = previousRegistration;
	}

	public Map getCurrentRegistration() {
		return currentRegistration;
	}

	public void setCurrentRegistration(Map currentRegistration) {
		this.currentRegistration = currentRegistration;
	}

	static Map analyzeCourseRegistrationReport(String fileName) {
	
	
	ArrayList <String>registrationList = new ArrayList<String>();
	String csvFile = fileName;
	//System.out.println(csvFile);
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	
	try {
	
	    br = new BufferedReader(new FileReader(csvFile));
	    while ((line = br.readLine()) != null) {
	        String[] registrations = line.split(cvsSplitBy);
	        registrationList.add(registrations[20]);
	        
	    }
	    
	    //System.out.println(registrationList);
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
	
		for(String word: registrationList) {
		  Integer count = wordCount.get(word);          
		  wordCount.put(word, (count==null) ? 1 : count+1);  		  
		}
	System.out.println("Report -- Course Registration Analysis--File Name: \n"+fileName+"\n");
	System.out.println("In the format --- Course Name = no. of registered students \n");
	System.out.println(wordCount.toString());
	return wordCount;
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	    return null;
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
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

	 boolean determineOpenSeat(Map previousRegistration, Map currentRegistration) {	
		boolean openSeat =  false;
		List<String> beforeList = getAllKeys(previousRegistration);
	    List<String> afterList = getAllKeys(currentRegistration);
	    List<String> commonList1 = beforeList;
	    List<String> commonList2 = afterList;
	    commonList1.retainAll(afterList);
	    commonList2.retainAll(beforeList);
	    if(commonList1!=null & commonList2!=null) // 
	    {
	        for (int i = 0; i < commonList1.size(); i++) 
	        {
	            if (!(previousRegistration.get(commonList1.get(i))).equals(currentRegistration.get(commonList1.get(i)))) 
	            {
	            	openSeat = true;
	                System.out.println("\n"+commonList1.get(i)+" Registrations: Before- "+previousRegistration.get(commonList1.get(i))+" After- "+currentRegistration.get(commonList1.get(i)));
	            }
	          
	        }
	    }
		return openSeat;
		
	}

	static List<String> getAllKeys(Map<String, String> map1)
	 {
	     List<String> key = new ArrayList<String>();
	     if (map1 != null) 
	     {
	         Iterator<String> mapIterator = map1.keySet().iterator();
	         while (mapIterator.hasNext()) 
	         {
	             key.add(mapIterator.next());
	         }
	     }
	     return key;
	 }

}
