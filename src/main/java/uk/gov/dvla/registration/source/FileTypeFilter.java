package uk.gov.dvla.registration.source;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * File type filter to retrieve certain supported mime type files
 *
 */
public final class FileTypeFilter implements FileFilter {

	/**
	 * A set of file extensions csv, xls
	 */
	private final Set<String> fileType = new HashSet<>();

	public FileTypeFilter(final List<String> fileType) {
		fileType.replaceAll(String::toLowerCase);
		this.fileType.addAll(fileType);
	}

	/**
	 * Check if the file extension matches the configured file types
	 */
	@Override
	public boolean accept(final File file) {
		final String fileName = file.getAbsolutePath();
		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			return fileType.contains(fileName.substring(i + 1).toLowerCase());
		}
		return false;
	}

	public Set<String> getFileType() {
		return fileType;
	}

}