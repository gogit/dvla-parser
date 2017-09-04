package uk.gov.dvla.registration.flow.step;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import uk.gov.dvla.registration.flow.RegistrationCheck;
import uk.gov.dvla.registration.model.ExpectedActual;
import uk.gov.dvla.registration.model.RegistrationMakeColor;

public class RegistrationCheckStep {

	private static final String registrationInputXPath = "//*[@id='Vrm']";
	private static final String formSubmitXPath = "//button[@class='button']";
	private static final String registrationXPath = "//span[@class='reg-mark']";
	private static final String makeXPath = "//div[@id='pr3']//ul[1]/li[2]/span[2]/strong";
	private static final String colorXPath = "//div[@id='pr3']//ul[1]/li[3]/span[2]/strong";

	@Given("^the following registration details:$")
	public void the_following_registration_details(DataTable registrations) throws Exception {

		final List<List<String>> data = registrations.raw();

		data.parallelStream().forEach(rec -> {
			WebDriver driver = new PhantomJSDriver();
			driver.get("https://vehicleenquiry.service.gov.uk/ConfirmVehicle");

			try {
				List<ExpectedActual> eas = new RegistrationCheck(registrationInputXPath, formSubmitXPath,
						registrationXPath, makeXPath, colorXPath).execute(driver,
								new RegistrationMakeColor(rec.get(0), rec.get(1), rec.get(2)));
				Assert.assertEquals(rec.get(0), eas.get(0).getActual());
				Assert.assertEquals(rec.get(1), eas.get(1).getActual());
				Assert.assertEquals(rec.get(2), eas.get(2).getActual());
			} catch (Exception e) {
				Assert.fail(e.toString());
			}
			finally{
				driver.close();
			}
		});

	}
}
