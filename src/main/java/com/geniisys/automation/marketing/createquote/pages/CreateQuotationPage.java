package com.geniisys.automation.marketing.createquote.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.marketing.createquote.blocks.QuotationInformationBlock;
import com.geniisys.automation.marketing.createquote.blocks.QuotationPeriodOfInsuranceBlock;

public class CreateQuotationPage {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(CreateQuotationPage.class.getName());


	private final By saveButton = By.xpath("//input[@id='btnSave']");

	public CreateQuotationPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public QuotationInformationBlock getQuotationInfoBlk() {
		return new QuotationInformationBlock(driver);
	}

	public QuotationPeriodOfInsuranceBlock getPeriodOfInsuranceBlk() {
		return new QuotationPeriodOfInsuranceBlock(driver);
	}

	public void save() {
		try {
			driver.findClickableElement(saveButton).click();
			LOGGER.info("'Save' button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		if (getMessageOverlay().getMessageType().contains("SUCCESS")) {
			LOGGER.info("Message prompt displayed.");
			getMessageOverlay().clickOk();
		}
	}

	private MessageOverlay getMessageOverlay() {
		return new MessageOverlay(driver);
	}

}
