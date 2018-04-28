import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
class EmailManagerTest {

	EmailManager email = new EmailManager();
	
	@Test
	public void test_loadEmailText()
	{
		
		String msg = "Dear Student,There is an open seat in the course[seisXXX]. "
				+ "Please reply to this email as soon as possible to confirm your registration for the same."
				+ "Thanks,GPS Staff";
		assertEquals(msg,email.loadEmailText());
	}
	
	@Test
	public void test_sendEmail()
	{
		
		String recipient = "ips.sinha2013@gmail.com";		
		email.sendEmail(recipient);
		String emailSent = email.getEmailSent();
		assertEquals("Email Sent Successfully !",emailSent);
		
	}
	
	


}
