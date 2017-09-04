package uk.gov.dvla.registration.model;

/**
 * 
 * Store the expected and actual values
 *
 */
public class ExpectedActual {

	/**
	 * Expected string
	 */
	private String expected;

	/**
	 * Actual string
	 */
	private String actual;

	public String getExpected() {
		return expected;
	}

	public ExpectedActual setExpected(String expected) {
		this.expected = expected;
		return this;
	}

	public String getActual() {
		return actual;
	}

	public ExpectedActual setActual(String actual) {
		this.actual = actual;
		return this;
	}

}
