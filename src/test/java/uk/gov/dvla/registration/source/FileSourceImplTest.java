package uk.gov.dvla.registration.source;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileSourceImplTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private Set<String> files = new HashSet<>();

	@Before
	public void before() throws IOException {
		new Random().ints(300).forEach(i -> {

			try {
				files.add(folder.newFile(String.join("", "file", String.valueOf(i), ".csv")).getAbsolutePath());
				files.add(folder.newFile(String.join("", "file", String.valueOf(i), ".xls")).getAbsolutePath());
				files.add(folder.newFile(String.join("", "file", String.valueOf(i), ".txt")).getAbsolutePath());
			} catch (IOException e) {
				Assert.fail(e.toString());
			}
		});

	}

	@Test
	public void testScan() {
		final FileSource fs = new FileSourceImpl(folder.getRoot().getAbsolutePath(),
				new FileTypeFilter(Arrays.asList("csv", "xls")));

		final List<File> files = fs.scan();
		Set<File> sfiles = files.stream().collect(Collectors.toSet());
		
		Assert.assertEquals(600, sfiles.size());
		
		sfiles.forEach(f->{
			files.contains(f.getAbsolutePath());
		});
		
	}

}
