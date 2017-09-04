package uk.gov.dvla.registration.source;

import java.io.File;
import java.util.List;

/**
 * 
 * A source of files containing registration details. 
 *
 */
public interface FileSource {

	/**
	 * Scan a file source for files
	 * 
	 * @param filter
	 *            to apply
	 * @return list of files
	 */
	List<File> scan();

}
