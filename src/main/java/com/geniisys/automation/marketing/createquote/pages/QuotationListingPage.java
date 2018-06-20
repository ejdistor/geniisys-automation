package com.geniisys.automation.marketing.createquote.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.geniisys.automation.common.BrowserDriver;

public class QuotationListingPage {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(QuotationListingPage.class.getName());


	private final By addButton = By.xpath("//span[@id='mtgAddBtn1']");

	public QuotationListingPage(BrowserDriver driver) {
		this.driver = driver;
	}

	public CreateQuotationPage addNewRecord() {
		try {
			driver.findClickableElement(addButton).click();
			LOGGER.info("'Add' button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		return new CreateQuotationPage(driver);
	}

}
