import java.util.Scanner;

public class WaitListAppUI
{

	WaitListApp waitListApp = new WaitListApp();
	CourseRegistration registration = new CourseRegistration();

	public void loadApp()
	{

		// System.out.println(" Welcome to Waitlist Manager");
		System.out.println(
				"          shy        +hh/        ohy                 `dmh     `.`       shs         :mmo                 `.`       shhh+            ohhh/                                                                                                                                                                                                                                                                                                \r\n"
						+ "          /MM/      `NMMm`      -MM+                  +s/     dmo       mMd         .oo-                 dm+       mMmNM+          oMdNMs                                                                                                                                                                                                                                                                                                \r\n"
						+ "           dMm`     oMmNMo      yMm`    `.-::-.`      ..`  `..NMy...`   mMd          ..`     `.-:-.`  `.-NMy...`   mMy/MN:        /MN-NMs     `..-::-.      `.` `.-:-.`       `.-::-.`       `.-:-.....     `.-::-.`      ..  .-:.                                                                                                                                                                                                       \r\n"
						+ "           :MM+    `NM:oMN.    -MM/    odmmddmNms`    dMy  :mmMMNmmm+   mMd         `NM/   `ommdhdmh` :mmMMNmmm+   mMy sMm.      -NM: NMs    :hdmdddNNh-    +Md/hmmmNNh:    .hdmdddmNd:    `smmdhdNMMmm.  `/hmmhdmmh/     NM/ymmNm                                                                                                                                                                                                       \r\n"
						+ "            yMm`   oMd `mMs    hMh     /:`    :NMo    dMh   ``NMy```    mMd         `MM+   +MN.   `:   ``NMs```    mMy `dMd`    .mMo  NMs    -/.    .dMd    oMMm+.  .hMN`   ./-`   `sMN.   yMd.   -NM+    yMd-    :NMo   `MMNh:``.                                                                                                                                                                                                       \r\n"
						+ "            .NMo  `NM:  +MN.  :MM-       ``````mMy    dMh     NMs       mMd         `MM+   :NMs-``      `NMs       mMy  .NMs   `dMy   NMs       `````sMN`   oMN.     -MM:      `````/MM-   mMo     dMy   /MM:``````sMN`  `MMy                                                                                                                                                                                                            \r\n"
						+ "             sMN` oMh    mMy  hMs      -sddmmmmMMy    dMh     NMs       mMd         `MM+    -sdNNds:    `NMs       mMy   /MM+  yMd`   NMs    `ohdmmmmNMN`   oMN`     .MM/   `/ydmdmmmMM:   /MNo:-:yMm-   oMMmmmmmmmmmh`  `MMo                                                                                                                                                                                                            \r\n"
						+ "             `NMs`NM:    /MM-:MN.     :MMo`    mMy    dMh     NMs       mMd         `MM+       `-oNMo   `NMs       mMy    oMN-+MN.    NMs   `mMh.    oMN`   oMN`     .MM/   yMm:`   -MM:  `hMoshdhs/`    +MM-            `MMo                                                                                                                                                                                                            \r\n"
						+ "              +MNyMh      dMddMo      oMN.    :NMy    dMh     mMh       mMd         `MM+   -`    `dMh    mMh       mMy     hMNMM:     NMs   -MM+    .hMN`   oMN`     .MM/  `NMh    `sMM:  .NMo/:::-.`    `mMd.      ``   `MMo                                                                                                                                                                                                            \r\n"
						+ "               dMMM:      -MMMm`      `hMNysymdhMy    dMy     /NMdyho   dMNmmmmmmm: `MM+   hNdyyymMh.    +NMdyh+   mMy     .mMM+      NMs    oNMhsydmyMN`   +MN`     .MM:   /mMdyshmyNM-   +MNhhdddNMd:   `sNNdyyyhdN/   `NMo                                                                                                                                                                                                            \r\n"
						+ "               `::-        -::`         .:++:. .:.    -:.      `:++:`   .:::::::::`  ::`    .:/+/:`       `:++:`   -:.      .:-       -:.     `:/+/-  :-    `:-       ::`     -/+/:` -:`  +Mm.      sMN`     -/++/:-`     -:`                                                                                                                                                                                                            \r\n"
						+ "                                                                                                                                                                                           yMm:`   `:dMh                                                                                                                                                                                                                                  \r\n"
						+ "                                                                                                                                                                                           `+hmNNNNNmy:                                                                                                                                      ");
	}

	public void initiateNewReportUpload()
	{

		System.out.println("*****************New Report Upload*****************");
		waitListApp.startFileUpload();

	}

	public void userGuide()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("\r\n=============================================================");
		System.out.println("Would you like to read the user manual?[Y/N]");
		String choice = input.next();
		if (choice.equalsIgnoreCase("Y"))
			displayUserGuide();
	}

	private void displayUserGuide()
	{
		System.out.println("\r\n=========================User Manual=========================" + "\r\n" + "\r\n"
				+ "• Provide the filepath for the desired registration report when prompted \r\n" + "\r\n"
				+ "• Console will return the courses that have new open spots.\r\n" + "\r\n"
				+ "• Provide the filepath for the desired waitlist report when prompted (only when there is open spots).\r\n"
				+ "\r\n" + "• Customize email message to students.\r\n" + "\r\n"
				+ "• Send emails by typing each student's email address. \r\n" + "\r\n");
	}

	public void getSuccessMessage()
	{

		System.out.println("Process Successful !!");
	}

	public void getFailureMessage()
	{

		System.out.println("Process Failed !! Retry again !");
	}

	public void displayEmptyFolderMessage()
	{

		System.out.println("No Files in the Reports Folder");
		initiateNewReportUpload();

	}

	public String getUserInput()
	{

		System.out.println("Please provide the path of the file to be uploaded:");
		Scanner path = new Scanner(System.in);
		String filePath = path.next();
		System.out.println(filePath);
		// System.out.println(filePath);
		return filePath;

	}

	public String getUserInputToSendEmail()
	{
		System.out.println("**************************************************");
		System.out.println("Would you like to send emails to the waitlist candidates at this time?[Y/N]");
		Scanner input = new Scanner(System.in);
		String choice = input.next();
		return choice;

	}

	public String getEmailAddress()
	{
		System.out.println("\nEnter the email address of the recipient[username@gmail.com]:");
		Scanner inputAddress = new Scanner(System.in);
		String emailAddress = inputAddress.next();
		return emailAddress;
	}

	public void displayEmailSuccessMsg(String emailSent)
	{
		System.out.println(emailSent);

	}

	public void print(String msg)
	{

		System.out.println(msg);
	}

	void getUserInputToDisplayMultipleWaitlist(CourseWaitList courseWaitList) {
		  print("Would you like to run analysis to check if any student is registered in multiple courses?[Y/N]");
		  Scanner input = new Scanner(System.in);
		  String choice = input.next();
		  courseWaitList.multipleWaitlistReport(choice);
		
	}

	public void displayWaitListForSingleCourse(CourseWaitList courseWaitList) {
	
		  print("\n \nPlease enter the course ID to view the waitlisted students:");
		  Scanner input = new Scanner(System.in);
		  String choice = input.next();	
		  courseWaitList.displayWaitListForCourse(choice,CourseWaitList.waitListStudentDetails);
	
	  }

}
