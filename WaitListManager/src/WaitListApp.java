import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class WaitListApp {

	public static void main(String[] args) {
		
		loadAllRegistrationsReport();
		loadAllWaitListReport();
		
	}

	private static void loadAllRegistrationsReport() {
		
		String csvFile = "StudentRegistrations.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] registrations = line.split(cvsSplitBy);

                System.out.println("Student ID: "+registrations[0]+" Student Name: "+registrations[1]+" Course: "+
                registrations[2]+"Section: "+registrations[3]);

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
