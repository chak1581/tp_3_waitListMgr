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
	
	static WaitListAppUI waitListAppUI = new WaitListAppUI();

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
			waitListAppUI.print("Report -- Course Registration Analysis--File Name: \n"+fileName+"\n");
			waitListAppUI.print("In the format --- Course Name = no. of registered students \n");
			waitListAppUI.print(wordCount.toString());
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
		List<String> previousList = getAllKeys(previousRegistration);
		List<String> currentList = getAllKeys(currentRegistration);
		List<String> collectionPreviousList = previousList;
		List<String> collectionCurrentList = currentList;
		collectionPreviousList.retainAll(currentList);
		collectionCurrentList.retainAll(previousList);
		if(collectionPreviousList!=null & collectionCurrentList!=null) // 
		{
			for (int i = 0; i < collectionPreviousList.size(); i++) 
			{
				if (!(previousRegistration.get(collectionPreviousList.get(i))).equals(currentRegistration.get(collectionPreviousList.get(i)))) 
				{
					openSeat = true;
					waitListAppUI.print("\n"+collectionPreviousList.get(i)+" Registrations: Before- "+previousRegistration.get(collectionPreviousList.get(i))+" After- "+currentRegistration.get(collectionPreviousList.get(i)));
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
