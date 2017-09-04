package uk.gov.dvla.registration.flow;

import java.util.List;

import org.openqa.selenium.WebDriver;

/**
 * 
 * A web flow is a functional process flow that could span multiple web pages
 * using a supplied input and returns specific output
 *
 * @see RegistrationCheck for details
 * @param <I>
 *            input to the flow
 * @param <O>
 *            output from the flow
 */
public interface WebFlow<I, O> {

	/**
	 * Execute a flow using the supplied input
	 * 
	 * @param driver
	 *            the selenium webdriver
	 * @param input
	 *            to the flow
	 * @return output
	 * @throws InterruptedException
	 */
	List<O> execute(WebDriver driver, I input) throws InterruptedException;

}
