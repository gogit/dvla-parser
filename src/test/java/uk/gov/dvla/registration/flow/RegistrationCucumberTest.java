package uk.gov.dvla.registration.flow;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {
		"classpath:features/registrationCheck.feature" }, glue = "uk.gov.dvla.registration.flow.step", plugin = {
				"pretty", "html:target/cucumber" })
public class RegistrationCucumberTest {
	
}
