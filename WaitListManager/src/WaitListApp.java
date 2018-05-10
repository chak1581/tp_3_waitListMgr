import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;


public class WaitListApp {

	static String fileName;
	static CourseRegistration registration= new CourseRegistration();
	static WaitListAppUI waitListAppUI = new WaitListAppUI();
	
	public static void main(String[] args) {
	
		waitListAppUI.loadApp();
		String response = getMostRecentReport(".\\reports\\");
		waitListAppUI.userGuide();
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
			uploadWaitlistReport();
			sendEmailToWaitListCandidate();			
		  }
		else {
			waitListAppUI.print("No Changes in the Registrations !!");
		}
		}
		else {
			waitListAppUI.print("Thank you for uploading registrations report."
					+ "\n There are no files at this time to find open seats.");
		}
		return openSeat;
	}


	private static void sendEmailToWaitListCandidate() {
		 EmailManager emailManager = new EmailManager();
	
		String choice = "";		
		 choice = waitListAppUI.getUserInputToSendEmail();
		 if(choice.equalsIgnoreCase("Y")) {
			 
			
			 waitListAppUI.print("Please review the body of the email!"
			 		+ "\n**************************************************");
			
			 waitListAppUI.print(emailManager.loadEmailText());
			 waitListAppUI.print("\n Note: If you need to change the text of the email, "
			 		+ "please go to 'email.txt' file and make your changes.");
			 String emailAddress = waitListAppUI.getEmailAddress();			 
			 emailManager.sendEmail(emailAddress);
			 waitListAppUI.displayEmailSuccessMsg(emailManager.getEmailSent());
			  while(choice.equalsIgnoreCase("Y")) {
				  sendEmailToWaitListCandidate();
				  choice="";
			  }
		 }
		
		 else {
			 waitListAppUI.print("*********Thank you for using WaitListManager!*********");
		 }
	
	}


	public static String getMostRecentReport(String dirName) {
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
		    
		    System.out.println("The most recent file in the Reports Folder is ------->>>>>>   "+choice+"\n");
		    registration.setPreviousRegistration(CourseRegistration.analyzeCourseRegistrationReport(choice.toString()));
		    response = "success";
		    }
		    
		    else {
		    	response = "failure";
		    }
			return response;
		    
	}

	public static void uploadRegistrationsReport() {
		
		String filePath = waitListAppUI.getUserInput();
	     String dirName = ".\\reports\\";
		 File file = new File(filePath);
		 fileName = file.getName();
		 fileName = fileName.replaceAll(".csv", "."+System.currentTimeMillis()+".csv");
		 if(file.renameTo(new File(dirName+fileName))) {
			 System.out.println("\n File Moved Successfully.");
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
	
	public static void uploadWaitlistReport() {
		//String filePath = "C:\\Users\\Dell\\Downloads\\abc.txt";
		waitListAppUI.print("\n **** Start WaitList Report Analysis ***");
		waitListAppUI.print("\n Would you like to upload the waitlist file? [Y/N]");
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		if(choice.equalsIgnoreCase("Y")){
		CourseWaitList courseWaitList = new CourseWaitList();
		String filePath = waitListAppUI.getUserInput();
	     String dirName = ".\\waitlistReports\\waitlist_scrubbed.csv";
		 File file = new File(filePath);
		 fileName = file.getName();
		 String fromLocation = file.getPath();
		 Path to = Paths.get(dirName);
		 Path from = Paths.get(fromLocation);
		 System.out.println(to);
		 System.out.println(from);
		 try {
			Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
			 courseWaitList.analyzeWaitListReport(dirName);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
}
