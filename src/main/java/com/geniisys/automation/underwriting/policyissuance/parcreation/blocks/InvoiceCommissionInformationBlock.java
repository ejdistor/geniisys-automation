package com.geniisys.automation.underwriting.policyissuance.parcreation.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.FindBy;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.ModalDialog;

@FindBy(xpath = "//div[@id='IntermediaryInfoDiv']")
public class InvoiceCommissionInformationBlock {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(InvoiceCommissionInformationBlock.class.getName());

	private By intermediarySearchBtnLocator = By.xpath("//img[@id='oscmIntm']");
	private By sharePercentageFldLocator = By.xpath("//input[@id='txtSharePercentage']");
	private By addIntermediaryBtnLocator = By.xpath("//input[@id='btnSaveIntm']");

	public InvoiceCommissionInformationBlock(BrowserDriver driver) {
		this.driver = driver;
	}

	public void setSharePercentage(Integer sharePercentage) {
		try {
			driver.findClickableElement(sharePercentageFldLocator).click();
			driver.findElement(sharePercentageFldLocator).sendKeys(sharePercentage.toString());
			LOGGER.info("Share Percentage field value set to '" + sharePercentage + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	public void setIntermediary(Integer intmNo) {
		openIntermediaryLov().searchAndSelect(intmNo.toString());
	}

	public void clickAdd() {
		try {
			driver.findClickableElement(addIntermediaryBtnLocator).click();
			LOGGER.info("Add button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}

	private ModalDialog openIntermediaryLov() {
		try {
			driver.findClickableElement(intermediarySearchBtnLocator).click();
			LOGGER.info("Intermediary search button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new ModalDialog(driver);
	}
}