import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class WaitListApp {

	static String fileName;
	static Map previous;
	static Map current;
	static Map difference;
	public static void main(String[] args) {
			
		getMostRecentReport("C:\\Users\\Dell\\git\\tp_3_waitListMgr\\WaitListManager\\reports\\");
		System.out.println("***New Report Upload***");
		uploadRegistrationsReport();
		determineWaitList();
		//loadAllWaitListReport();
		
	}


	private static void determineWaitList() {
		difference = new HashMap<>();
	    difference.putAll(previous);
	    difference.putAll(current);
	    difference.entrySet().removeAll(previous.entrySet());
	    System.out.println("Difference Map : "+difference.toString());
		
	}


	private static void getMostRecentReport(String dirName) {
	
		 File fl = new File(dirName);
		    File[] files = fl.listFiles(new FileFilter() {          
		        public boolean accept(File file) {
		            return file.isFile();
		        }
		    });
		    long lastMod = Long.MIN_VALUE;
		    File choice = null;
		    for (File file : files) {
		        if (file.lastModified() > lastMod) {
		            choice = file;
		            lastMod = file.lastModified();
		        }
		    }
		    
		    System.out.println(choice);
		    previous=analyzeReport(choice.toString());
		    
	}

	private static void uploadRegistrationsReport() {
		//String filePath = "C:\\Users\\Dell\\Downloads\\abc.txt";
	     System.out.println("Please provide the path of the file to be uploaded:");
	     String dirName = "C:\\\\Users\\\\Dell\\\\git\\\\tp_3_waitListMgr\\\\WaitListManager\\\\reports\\\\";
		 Scanner path = new Scanner(System.in);
		 String filePath = path.next();
		 System.out.println(filePath);
		 File file = new File(filePath);
		 fileName = file.getName();
		 fileName = fileName.replaceAll(".csv", "."+System.currentTimeMillis()+".csv");
		 System.out.println(fileName);
		 if(file.renameTo(new File(dirName+fileName))) {
			 System.out.println("File Move Successful");
			 current=analyzeReport(dirName+fileName);
		 }
		 else
			 System.out.println("Error");
		
	}

	private static Map analyzeReport(String fileName) {
		
		
		ArrayList <String>registrationList = new ArrayList<String>();
		String csvFile = fileName;
		System.out.println(csvFile);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] registrations = line.split(cvsSplitBy);
                registrationList.add(registrations[20]);
                
            }
            
            System.out.println(registrationList);
    		Map<String, Integer> wordCount = new HashMap<String, Integer>();

    		for(String word: registrationList) {
    		  Integer count = wordCount.get(word);          
    		  wordCount.put(word, (count==null) ? 1 : count+1);  		  
    		}
    	System.out.println("Report -- Course Registration Analysis--File Name: "+fileName);
    	System.out.println(wordCount.toString());
    	//wordCount.clear();
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
	
private static void loadAllWaitListReport() {
		
		String csvFile = "WaitListStudents.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] waitList = line.split(cvsSplitBy);

                System.out.println("Student ID: "+waitList[0]+" Student Name: "+waitList[1]+" Course: "+
                		waitList[2]+"Section: "+waitList[3]);

            }

        } catch (FileNotFoundException e) {
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




}
