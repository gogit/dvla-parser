package uk.gov.dvla.registration.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmVehicleHomePage {

	private static final String PAGE_URL = "https://vehicleenquiry.service.gov.uk/ConfirmVehicle";

	@FindBy(xpath = ".//*[@id='Vrm']")
	private WebElement registrationNumberInput;

	@FindBy(xpath = "//button[@class='button']")
	private WebElement registrationCheckButton;

	public ConfirmVehicleHomePage(WebDriver driver) {
		driver.get(PAGE_URL);
		PageFactory.initElements(driver, this);
	}

	public void setRegistrationNumber(String registrationNumber) {
		registrationNumberInput.clear();
		registrationNumberInput.sendKeys(registrationNumber);
	}

	public void clickRegistrationCheckButton() {
		registrationCheckButton.click();
	}
}
