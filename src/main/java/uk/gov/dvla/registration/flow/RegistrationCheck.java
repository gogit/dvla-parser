package uk.gov.dvla.registration.flow;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import uk.gov.dvla.registration.model.ExpectedActual;
import uk.gov.dvla.registration.model.RegistrationMakeColor;

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

	private final String registrationInputXPath;

	private final String formSubmitXPath;

	private final String registrationNoXPath;

	private final String makeXPath;

	private final String colorXPath;

	public RegistrationCheck(final String registrationInputXPath, final String formSubmitXPath,
			final String registrationNoXPath, final String makeXPath, final String colorXPath) {
		this.registrationInputXPath = registrationInputXPath;
		this.formSubmitXPath = formSubmitXPath;
		this.registrationNoXPath = registrationNoXPath;
		this.makeXPath = makeXPath;
		this.colorXPath = colorXPath;
	}

	@Override
	public List<ExpectedActual> execute(WebDriver driver, RegistrationMakeColor input) throws InterruptedException {

		final List<ExpectedActual> ea = new ArrayList<>();

		driver.findElement(By.xpath(registrationInputXPath)).sendKeys(input.getRegistrationNo());

		driver.findElement(By.xpath(formSubmitXPath)).click();

		ea.add(new ExpectedActual().setExpected(input.getRegistrationNo())
				.setActual(driver.findElement(By.xpath(registrationNoXPath)).getText()));

		ea.add(new ExpectedActual().setExpected(input.getMake())
				.setActual(driver.findElement(By.xpath(makeXPath)).getText()));

		ea.add(new ExpectedActual().setExpected(input.getRegistrationNo())
				.setActual(driver.findElement(By.xpath(colorXPath)).getText()));

		return ea;
	}
}
