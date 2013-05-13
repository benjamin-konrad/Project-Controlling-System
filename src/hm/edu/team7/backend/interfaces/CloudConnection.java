package hm.edu.team7.backend.interfaces;

import java.io.File;


/**
 * interface for uploading a file to cloud.
 * @author Florian Kirchhofer
 *
 */
public interface CloudConnection {

	boolean saveFileinGoogleCloud(File file, User user);
}
