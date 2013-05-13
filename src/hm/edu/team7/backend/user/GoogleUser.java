package hm.edu.team7.backend.user;

import hm.edu.team7.backend.interfaces.GoogleUserInterface;

/**
 * Userimplementation of googleuser.
 * @author Florian Kirchhofer
 *
 */
public class GoogleUser implements GoogleUserInterface{

	@Override
	public String getUserAccount() {
		// TODO Auto-generated method stub
		return "user";
	}

	@Override
	public String getUserpassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGoogleAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGoogleAccountpassword() {
		// TODO Auto-generated method stub
		return null;
	}

}
