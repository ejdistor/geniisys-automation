package com.geniisys.automation.marketing.createquote.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.marketing.createquote.pages.QuoteLineListingPage;

public class QuotationProcessingSubmenu {

	private BrowserDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(QuotationProcessingSubmenu.class.getName());

	private final By LINE_LISTING_SUBMENU = By.xpath("//*[@id='lineListing']");

	public QuotationProcessingSubmenu(BrowserDriver driver) {
		this.driver = driver;
	}

	public QuoteLineListingPage goToQuoatationListing() {
		try {
			driver.findClickableElement(LINE_LISTING_SUBMENU).click();
			LOGGER.info("'Quotation Listing' submenu clicked.");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return new QuoteLineListingPage(driver);
	}
}
