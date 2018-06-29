package com.geniisys.automation.underwriting.main.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.underwriting.policyissuance.parcreation.pages.PolicyParCreationPage;

public class UnderwritingMainMenu {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(UnderwritingMainMenu.class.getName());

	private final By policyIssuanceMnuLocator = By.xpath("//a[@id='policyIssuance']");
	private final By parCreationMnuLocator = By.xpath("//a[@id='parCreation']");

	public UnderwritingMainMenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public UnderwritingMainMenu goToPolicyIssuance() {
		try {
			driver.findElement(policyIssuanceMnuLocator).click();
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new UnderwritingMainMenu(driver);
	}

	public PolicyParCreationPage goToParCreationPage() {
		try {
			driver.findClickableElement(parCreationMnuLocator).click();
			LOGGER.info("Go to Par Creation page.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new PolicyParCreationPage(driver);
	}
	
	public void goToGiiss028() {
		try {
			driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[1]/div/div/ul/li[10]/a")).click();
			driver.findClickableElement(By.xpath("//*[@id=\"general\"]")).click();
			driver.findClickableElement(By.xpath("//*[@id=\"taxCharges\"]")).click();
		}catch(TimeoutException e) {
			LOGGER.error(e);
		}
	}
}
