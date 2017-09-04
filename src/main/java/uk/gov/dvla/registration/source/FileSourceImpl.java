package uk.gov.dvla.registration.source;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * Scan the configured directory on the file system using the supplied
 * FileFilter implementation
 * 
 * 
 *
 */
public class FileSourceImpl implements FileSource {

	private Logger LOG = Logger.getLogger(FileSourceImpl.class);

	private final File dir;

	private final FileFilter filter;

	/**
	 * Initialise a file source implementation
	 * 
	 * @param directory
	 *            containing the files
	 * @param filter
	 *            to apply
	 */
	public FileSourceImpl(final String directory, final FileFilter filter) {
		dir = new File(directory);
		if (!dir.exists() || !dir.canRead()) {
			final String msg = String.format("Error initialising FileSystemSource using directory %s", directory);
			LOG.error(msg);
			throw new IllegalArgumentException(msg);
		}
		this.filter = filter;
	}

	/**
	 * Scan a directory on the file system using a filter
	 */
	@Override
	public List<File> scan() {
		LOG.info(String.format("Scanning the directory %s for files", dir.getAbsolutePath()));
		return Arrays.asList(dir.listFiles(filter));
	}

}
