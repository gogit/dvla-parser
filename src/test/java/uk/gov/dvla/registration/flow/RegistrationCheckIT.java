package uk.gov.dvla.registration.flow;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import uk.gov.dvla.registration.model.ExpectedActual;
import uk.gov.dvla.registration.model.RegistrationMakeColor;
import uk.gov.dvla.registration.source.FileSource;
import uk.gov.dvla.registration.source.FileSourceImpl;
import uk.gov.dvla.registration.source.FileTypeFilter;

/**
 * 
 * The integration test reads files from the test/resources/files directory and
 * checks the DVLA website for registrations listed in the files.
 *
 * The test should ignore the file12.txt file
 */
public class RegistrationCheckIT {

	@Test
	public void testOnDvlaSite() throws Exception {


		getFileSource().scan().stream().forEach(f -> {
			try {
				WebDriver driver = new PhantomJSDriver();

				try (Stream<String> lines = Files.lines(Paths.get(f.getAbsolutePath()))) {
					lines.forEach(s -> {

						String rec[] = s.split(",");
						try {
							List<ExpectedActual> eas = new RegistrationCheck().execute(driver,
									new RegistrationMakeColor(rec[0], rec[1], rec[2]));
							Assert.assertEquals(rec[0], eas.get(0).getActual());
							Assert.assertEquals(rec[1], eas.get(1).getActual());
							Assert.assertEquals(rec[2], eas.get(2).getActual());
						} catch (Exception e) {
							Assert.fail(e.toString());
						} finally {
							driver.close();
						}
					});
				}
			} catch (IOException e) {
				Assert.fail(e.toString());
			}

		});
	}

	private FileSource getFileSource() throws URISyntaxException {
		URL dir = this.getClass().getClassLoader().getResource("files");

		return new FileSourceImpl(new File(dir.toURI()).getAbsolutePath(),
				new FileTypeFilter(Arrays.asList("csv", "xls")));

	}

}
