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

	private static final String registrationInputXPath = "//*[@id='Vrm']";
	private static final String formSubmitXPath = "//button[@class='button']";
	private static final String registrationXPath = "//span[@class='reg-mark']";
	private static final String makeXPath = "//div[@id='pr3']//ul[1]/li[2]/span[2]/strong";
	private static final String colorXPath = "//div[@id='pr3']//ul[1]/li[3]/span[2]/strong";

	@Test
	public void testOnDvlaSite() throws Exception {

		getFileSource().scan().stream().forEach(f -> {
			try {
				try (Stream<String> lines = Files.lines(Paths.get(f.getAbsolutePath()))) {
					lines.forEach(s -> {

						WebDriver driver = new PhantomJSDriver();
						driver.get("https://vehicleenquiry.service.gov.uk/ConfirmVehicle");

						String rec[] = s.split(",");
						try {
							List<ExpectedActual> eas = new RegistrationCheck(registrationInputXPath, formSubmitXPath,
									registrationXPath, makeXPath, colorXPath).execute(driver,
											new RegistrationMakeColor(rec[0], rec[1], rec[2]));
							Assert.assertEquals(rec[0], eas.get(0).getActual());
							Assert.assertEquals(rec[1], eas.get(1).getActual());
							Assert.assertEquals(rec[2], eas.get(2).getActual());
						} catch (Exception e) {
							Assert.fail(e.toString());
						}
						finally{
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
