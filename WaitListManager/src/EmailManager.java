
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailManager {

   String emailSent;
   
public String getEmailSent() {
	return emailSent;
}

public  void setEmailSent(String emailSent) {
	this.emailSent = emailSent;
}

 String loadEmailText() {
		
	   String textFile = "email.txt";
       BufferedReader br = null;
       String line = "";
       String msg = "";
       try {

           br = new BufferedReader(new FileReader(textFile));
           while ((line = br.readLine()) != null) {
        	   msg += line;
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
	return msg;

		
	}
   
   public  void sendEmail(String recipient) {
	   
	   Session session ;
	      String to = recipient;
	      String from = "seis.waitlist@gmail.com";
	     // String host = "localhost";
	      Properties properties = System.getProperties();
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.auth", "true");
	      // Setup mail server
	      properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.port", "587");
	      session=Session.getInstance(properties,new Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	              return new PasswordAuthentication("seis.waitlist@gmail.com","vibs_seis635"); 
	          }
	      });


	      try {
	       
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject("This is the Subject Line!");
	         message.setText(loadEmailText());
	         Transport.send(message);
	         setEmailSent("Email Sent Successfully !");
	      } catch (MessagingException mex) {
	    	  setEmailSent("Error Occurred ! Email Not Sent.");
	         mex.printStackTrace();
	      }
	   
   }
}
