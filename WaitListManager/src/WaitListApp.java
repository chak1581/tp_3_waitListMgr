import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;


public class WaitListApp {

	static String fileName;
	static CourseRegistration registration= new CourseRegistration();
	public static void main(String[] args) {
		
		getMostRecentReport("C:\\Users\\Dell\\git\\tp_3_waitListMgr\\WaitListManager\\reports\\");
		System.out.println("***New Report Upload***");
		uploadRegistrationsReport();
		boolean openSeat = registration.determineOpenSeat(registration.getPreviousRegistration(),registration.getCurrentRegistration());
		if(openSeat==true) {
			sendEmailToWaitListCandidate();
		}
		else {
			System.out.println("No Changes in Registrations !");
		}
		
	}


	private static void sendEmailToWaitListCandidate() {
		
		System.out.println("Would you like to send emails to the waitlist candidates at this time? [Y/N]");
		Scanner input = new Scanner(System.in); 
		 String choice = input.next();
		 if(choice.equalsIgnoreCase("Y")) {
			 
			 EmailManager emailManager = new EmailManager();
			 System.out.println("Please review the body of the email!");
			 System.out.println("********************************************");
			 System.out.println(emailManager.loadEmailText());
			 System.out.println("Note: If you need to change the text of the email, "
			 		+ "please go to email.txt file and make your changes.");
			 System.out.println("Enter the email address of the recepient:");
			 Scanner inputAddress = new Scanner(System.in); 
			 String emailAddress = inputAddress.next();
			 emailManager.sendEmail(emailAddress);
		 }
		 
		 else {
			 System.out.println("Thank you !");
		 }
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
		    registration.setPreviousRegistration(CourseRegistration.analyzeCourseRegistrationReport(choice.toString()));
		    
	}

	private static void uploadRegistrationsReport() {
		//String filePath = "C:\\Users\\Dell\\Downloads\\abc.txt";
	     System.out.println("Please provide the path of the file to be uploaded:");
	     String dirName = "C:\\Users\\Dell\\\\git\\tp_3_waitListMgr\\WaitListManager\\reports\\";
		 Scanner path = new Scanner(System.in);
		 String filePath = path.next();
		 System.out.println(filePath);
		 File file = new File(filePath);
		 fileName = file.getName();
		 fileName = fileName.replaceAll(".csv", "."+System.currentTimeMillis()+".csv");
		 System.out.println(fileName);
		 if(file.renameTo(new File(dirName+fileName))) {
			 System.out.println("File Move Successful");
			 registration.setCurrentRegistration(CourseRegistration.analyzeCourseRegistrationReport(dirName+fileName));
		 }
		 else
			 System.out.println("Error");
		
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
