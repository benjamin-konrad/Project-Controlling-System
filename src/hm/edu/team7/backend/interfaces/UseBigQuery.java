package hm.edu.team7.backend.interfaces;
/**
 * Interface for querying googles big query.
 * @author Florian Kirchhofer
 *
 */
public interface UseBigQuery {
	
	String[] queryBigQuery(String query, GoogleUserInterface user, String file);

}
