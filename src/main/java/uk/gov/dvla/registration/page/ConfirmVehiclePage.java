package uk.gov.dvla.registration.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmVehiclePage {

	@FindBy(xpath = ".//*[@id='pr3']/div/h1")
	private WebElement heading;

	@FindBy(xpath = ".//*[@id='pr3']/div/ul/li[1]/span[2]")
	private WebElement registrationNumber;

	@FindBy(xpath = ".//*[@id='pr3']/div/ul/li[2]/span[2]/strong")
	private WebElement make;

	@FindBy(xpath = ".//*[@id='pr3']/div/ul/li[3]/span[2]/strong")
	private WebElement color;

	public ConfirmVehiclePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getHeading() {
		return heading.getText();
	}

	public String getRegistrationNumber() {
		return registrationNumber.getText();
	}

	public String getMake() {
		return make.getText();
	}

	public String getColor() {
		return color.getText();
	}

	public boolean isPageOpened() {
		return getHeading().equals("Is this the vehicle you are looking for?");
	}
}
