package uk.gov.dvla.registration.model;

/**
 * 
 * Store the registration number, make and color
 *
 */
public class RegistrationMakeColor {

	private final String registrationNo;

	private final String make;

	private final String color;

	public RegistrationMakeColor(final String registrationNo, final String make, final String color) {
		this.registrationNo = registrationNo;
		this.make = make;
		this.color = color;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public String getMake() {
		return make;
	}

	public String getColor() {
		return color;
	}

}
