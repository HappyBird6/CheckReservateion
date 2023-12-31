import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {
	private String fromEmail;
	private String fromPassword;
	
	public MyAuthenticator(String fromEmail,String fromPassword) {
		this.fromEmail = fromEmail;
		this.fromPassword = fromPassword;
	}
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(fromEmail, fromPassword);
	}
}
