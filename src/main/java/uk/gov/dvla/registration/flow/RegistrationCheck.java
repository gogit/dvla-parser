package uk.gov.dvla.registration.flow;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import uk.gov.dvla.registration.model.ExpectedActual;
import uk.gov.dvla.registration.model.RegistrationMakeColor;
import uk.gov.dvla.registration.page.ConfirmVehicleHomePage;
import uk.gov.dvla.registration.page.ConfirmVehiclePage;

/**
 * The "registration check" webflow is pre-configured with a set of xpath
 * expressions and supplied with input needed to execute the process flow.
 * 
 * The series of steps executed are
 * 
 * <ol>
 * <li>Input the registration number on the webdriver</li>
 * <li>Click on the button to submit the form</li>
 * <li>Parse the result page and populate the actual output</li>
 * </ol>
 * 
 * @see WebFlow
 */
public class RegistrationCheck implements WebFlow<RegistrationMakeColor, ExpectedActual> {

	@Override
	public List<ExpectedActual> execute(WebDriver driver, RegistrationMakeColor input)
			throws InterruptedException, IllegalStateException {

		final List<ExpectedActual> ea = new ArrayList<>();

		ConfirmVehicleHomePage homePage = new ConfirmVehicleHomePage(driver);

		homePage.setRegistrationNumber(input.getRegistrationNo());
		homePage.clickRegistrationCheckButton();

		ConfirmVehiclePage confirmVehiclePage = new ConfirmVehiclePage(driver);

		if (!confirmVehiclePage.isPageOpened()) {
			throw new IllegalStateException("Could not open confirmVehiclePage");
		}

		ea.add(new ExpectedActual().setExpected(input.getRegistrationNo())
				.setActual(confirmVehiclePage.getRegistrationNumber()));

		ea.add(new ExpectedActual().setExpected(input.getMake()).setActual(confirmVehiclePage.getMake()));

		ea.add(new ExpectedActual().setExpected(input.getColor()).setActual(confirmVehiclePage.getColor()));

		return ea;
	}
}
