package uk.gov.dvla.registration.source.filter;

import java.io.File;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import uk.gov.dvla.registration.source.FileTypeFilter;

public class FileTypeFilterTest {

	@Test
	public void testLowerCaseFileTypeFilter() {
		FileTypeFilter ftf = new FileTypeFilter(Arrays.asList("XLS", "CSV"));
		Assert.assertTrue(ftf.getFileType().contains("xls"));
		Assert.assertTrue(ftf.getFileType().contains("csv"));
		Assert.assertEquals(2, ftf.getFileType().size());
	}

	@Test
	public void testAcceptValidFileType() {
		FileTypeFilter ftf = new FileTypeFilter(Arrays.asList("XLS", "CSV"));
		Assert.assertTrue(ftf.accept(new File("/file1.csv")));
		Assert.assertTrue(ftf.accept(new File("/file1.xls")));
		Assert.assertTrue(ftf.accept(new File("/file1.CSV")));
		Assert.assertTrue(ftf.accept(new File("/file1.Csv")));
		Assert.assertTrue(ftf.accept(new File("/file1.XLS")));
		Assert.assertTrue(ftf.accept(new File("/file1.XLs")));		
	}
	
	@Test
	public void testIgnoreInvalidFileType() {
		FileTypeFilter ftf = new FileTypeFilter(Arrays.asList("XLS", "CSV"));
		Assert.assertFalse(ftf.accept(new File("/file1csv")));
		Assert.assertFalse(ftf.accept(new File("/file1xls")));
		Assert.assertFalse(ftf.accept(new File("/file1CSV")));
		Assert.assertFalse(ftf.accept(new File("/file1Csv")));
		Assert.assertFalse(ftf.accept(new File("/file1XLS")));
		Assert.assertFalse(ftf.accept(new File("/file1XLs")));		
	}

}
