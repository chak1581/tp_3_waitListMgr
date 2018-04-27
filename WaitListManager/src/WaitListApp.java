import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;


public class WaitListApp {

	static String fileName;
	static CourseRegistration registration= new CourseRegistration();
	static WaitListAppUI waitListAppUI = new WaitListAppUI();
	
	public static void main(String[] args) {
		
		waitListAppUI.loadApp();
		String response = getMostRecentReport(".\\reports\\");
		if(response == "success") {
			waitListAppUI.initiateNewReportUpload();
		}
		else {
			waitListAppUI.displayEmptyFolderMessage();
		}
	}


	public static boolean startFileUpload() {
		boolean openSeat = false;
		uploadRegistrationsReport();
		if(registration.getCurrentRegistration()!=null && registration.getPreviousRegistration()!=null) {
		openSeat = registration.determineOpenSeat(registration.getPreviousRegistration(),registration.getCurrentRegistration());
		if(openSeat==true) {
			sendEmailToWaitListCandidate();			
		  }
		}
		return openSeat;
	}


	private static void sendEmailToWaitListCandidate() {
		
		String choice = waitListAppUI.getUserInputToSendEmail();
		 if(choice.equalsIgnoreCase("Y")) {
			 
			 EmailManager emailManager = new EmailManager();
			 System.out.println("Please review the body of the email!");
			 System.out.println("********************************************");
			 System.out.println(emailManager.loadEmailText());
			 System.out.println("Note: If you need to change the text of the email, "
			 		+ "please go to email.txt file and make your changes.");
			 String emailAddress = waitListAppUI.getEmailAddress();			 
			 emailManager.sendEmail(emailAddress);
			 waitListAppUI.displayEmailSuccessMsg(emailManager.getEmailSent());
		 }
		 
		 else {
			 System.out.println("Thank you !");
		 }
	}


	private static String getMostRecentReport(String dirName) {
	String response = "failure";
		 File fl = new File(dirName);
		
		    File[] files = fl.listFiles(new FileFilter() {          
		        public boolean accept(File file) {
		            return file.isFile();
		        }
		    });
		    long lastMod = Long.MIN_VALUE;
		    File choice = null;
		    if (files.length>0) {
		    for (File file : files) {
		        if (file.lastModified() > lastMod) {
		            choice = file;
		            lastMod = file.lastModified();
		        }
		    }
		    
		    System.out.println(choice);
		    registration.setPreviousRegistration(CourseRegistration.analyzeCourseRegistrationReport(choice.toString()));
		    response = "success";
		    }
		    
		    else {
		    	response = "failure";
		    }
			return response;
		    
	}

	public static void uploadRegistrationsReport() {
		//String filePath = "C:\\Users\\Dell\\Downloads\\abc.txt";
		String filePath = waitListAppUI.getUserInput();
	     String dirName = ".\\reports\\";
		 File file = new File(filePath);
		 fileName = file.getName();
		 fileName = fileName.replaceAll(".csv", "."+System.currentTimeMillis()+".csv");
		 System.out.println(fileName);
		 if(file.renameTo(new File(dirName+fileName))) {
			 System.out.println("File Move Successful");
			 if(registration.getPreviousRegistration()!=null) {
			 registration.setCurrentRegistration(CourseRegistration.analyzeCourseRegistrationReport(dirName+fileName));
			 }
			 else {
				 return;
			
			 }
		 }
		 else
			 waitListAppUI.getFailureMessage();
		
	}
	
/*private static void loadAllWaitListReport() {
		
	    System.out.println("Please provide the path of the waitlist report to be uploaded:");
	    Scanner path = new Scanner(System.in);
		String filePath = path.next();
		System.out.println(filePath);
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
*/



}
