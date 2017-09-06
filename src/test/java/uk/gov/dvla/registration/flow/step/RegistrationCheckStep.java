package uk.gov.dvla.registration.flow.step;

import java.util.List;
import java.util.logging.Level;

import org.junit.Assert;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import uk.gov.dvla.registration.flow.RegistrationCheck;
import uk.gov.dvla.registration.model.ExpectedActual;
import uk.gov.dvla.registration.model.RegistrationMakeColor;

public class RegistrationCheckStep {

	@Given("^the following registration details:$")
	public void the_following_registration_details(DataTable registrations) throws Exception {

		final List<List<String>> data = registrations.raw();

		PhantomJSDriver driver = new PhantomJSDriver();
		driver.setLogLevel(Level.FINEST);
		
		data.stream().forEach(rec -> {
			// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			try {
				List<ExpectedActual> eas = new RegistrationCheck().execute(driver,
						new RegistrationMakeColor(rec.get(0), rec.get(1), rec.get(2)));
				Assert.assertEquals(rec.get(0), eas.get(0).getActual());
				Assert.assertEquals(rec.get(1), eas.get(1).getActual());
				Assert.assertEquals(rec.get(2), eas.get(2).getActual());
			} catch (Exception e) {
				Assert.fail(e.toString());
			} finally {
				driver.close();
			}
		});

	}
}
