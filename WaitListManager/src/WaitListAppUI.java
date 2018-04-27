import java.util.Scanner;

public class WaitListAppUI {
	
	
WaitListApp waitListApp = new WaitListApp();
CourseRegistration registration= new CourseRegistration();

public void loadApp() {
	
	System.out.println(" Welcome to Waitlist Manager");
}

public void initiateNewReportUpload() {
	
	System.out.println("***New Report Upload***");
	boolean openSeat = waitListApp.startFileUpload();
	if (openSeat ==false && registration.getPreviousRegistration()!=null ) {
	System.out.println("No Changes in the Registrations !!");
	}
	else {
		
		System.out.println("Run the Application again to compare the reports.");
	}
	
}

public void getSuccessMessage() {
	
	System.out.println("Process Successful !!");
}

public void getFailureMessage() {
	
	System.out.println("Process Failed !! Retry again !");
}

public void displayEmptyFolderMessage() {

	System.out.println("No Files in the Reports Folder");
	initiateNewReportUpload();
	
}

public String getUserInput() {
	
	System.out.println("Please provide the path of the file to be uploaded:");
	 Scanner path = new Scanner(System.in);
	 String filePath = path.next();
	 System.out.println(filePath);
	return filePath;
	
}
	
	
}
