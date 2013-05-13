package hm.edu.team7.backend.interfaces;
/**
 * Interface that adds a googleaccount to the user.
 * @author Florian Kirchhofer
 *
 */
public interface GoogleUserInterface extends User{
	
	String getGoogleAccount();
	String getGoogleAccountpassword();

}
