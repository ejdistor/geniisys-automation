package com.geniisys.automation.underwriting.fileMaintenance.general.giiss028;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.geniisys.automation.common.BrowserDriver;
import com.geniisys.automation.common.DatePicker;
import com.geniisys.automation.common.MessageOverlay;
import com.geniisys.automation.common.ModalDialog;

public class TaxChargePage {

	private BrowserDriver driver;
	private DatePicker datePicker;

	private static final Logger LOGGER = LogManager.getLogger(TaxChargePage.class.getName());
	
	private By issCdLovLocator = By.xpath("//img[@id='searchIssLOV']");
	private By lineCdLovLocator = By.xpath("//img[@id='searchLineLOV']");
	private By taxCdLovLocator = By.xpath("//img[@id='searchTaxCdLOV']");
	private By taxPerilLovLocator = By.xpath("//img[@id='searchTaxPerilLOV']");
	
	
	private By executeQueryBtnLocator = By.xpath("//span[@id='btnToolbarExecuteQuery']");
	private By saveTaxChargeBtnLocator = By.xpath("//input[@id='btnSave']");
	private By deleteTaxChargeBtnLocator = By.xpath("//input[@id='btnDelete']");
	private By okDeleteBtnLovator = By.xpath("//input[@id='btn1']");
	private By openTaxPerilBtnLovator = By.xpath("//input[@id='btnTaxPeril']");
	private By closeTaxPerilBtnLovator = By.xpath("//*[@id='btnCancelTaxPeril']");
	private By saveTaxPerilBtnLocator = By.xpath("//*[@id='btnSaveTaxPeril']");
	private By deleteTaxPerilBtnLocator = By.xpath("//*[@id='btnDeleteTaxPeril']");
	
	
	private By functionNameFldLocator = By.xpath("//input[@id='txtFunctionName']");
	private By sequenceFldLocator = By.xpath("//input[@id='txtSequence']");
	private By rateFldLocator = By.xpath("//input[@id='txtRate']");
	private By hidMaxSequenceFldLocator = By.xpath("//input[@id='hidMaxSequence']");
	
	private By startDateImgLocator = By.xpath("//img[@id='imgFromDate']");
	private By endDateImgLocator = By.xpath("//img[@id='imgToDate']");
	
	public TaxChargePage(BrowserDriver driver) {
		this.driver = driver;
	}
	
	private ModalDialog openIssueSourceLOV() {
		try {
			driver.findClickableElement(issCdLovLocator).click();
		}catch(TimeoutException e) {
			LOGGER.error(e);
		}
		return new ModalDialog(driver);
	}
	
	public void setIssueSource(String issCd) {
		openIssueSourceLOV().searchAndSelectAutoSelectOne(issCd);
	}
	
	private ModalDialog openLineLOV() {
		try {
			driver.findClickableElement(lineCdLovLocator).click();
		}catch(TimeoutException e) {
			LOGGER.error(e);
		}
		return new ModalDialog(driver);
	}
	
	public void setLine(String lineCd) {
		openLineLOV().searchAndSelectAutoSelectOne(lineCd);
	}
	
	public void executeQuery() {
		driver.findClickableElement(executeQueryBtnLocator).click();
	}
	
	private ModalDialog openTaxLOV() {
		try {
			driver.findClickableElement(taxCdLovLocator).click();
		}catch(TimeoutException e) {
			LOGGER.error(e);
		}
		return new ModalDialog(driver);
	}
	
	public void setTaxCode(String taxCd) {
		openTaxLOV().searchAndSelectAutoSelectOne(taxCd);
	}
	
	public void setFunctionName(String functionName) {
		try {
			driver.findClickableElement(functionNameFldLocator).click();
			driver.findElement(functionNameFldLocator).sendKeys(functionName);
			LOGGER.info("Function Name field value set to '" + functionName + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setRate(BigDecimal rate) {
		try {
			driver.findClickableElement(rateFldLocator).click();
			driver.findElement(rateFldLocator).sendKeys(rate.toString());
			LOGGER.info("Rate field value set to '" + rate + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setStartDate(String date) {
		try {
			datePicker = new DatePicker(driver);
			datePicker.setDate(startDateImgLocator, date);
			LOGGER.info("Start Date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void setEndDate(String date) {
		try {
			datePicker = new DatePicker(driver);
			datePicker.setDate(endDateImgLocator, date);
			LOGGER.info("End Date set to '" + date + "'.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
	}
	
	public void validateSequence() {
		String hidMaxSequence = driver.findHiddenElement(hidMaxSequenceFldLocator).getAttribute("value");
		String sequence = driver.findElement(sequenceFldLocator).getAttribute("value");
		
		Assert.assertEquals(hidMaxSequence, sequence);
	}
	
	public void checkTaxChargeRequiredFields() {
		List<WebElement> l = driver.findElements(By.className("required"));
		for(WebElement list : l) {
			if(!"".equals(list.getAttribute("id"))){
				Assert.assertTrue(!"".equals(list.getAttribute("value")));
			}
		}
	}
	
	public void saveTaxCharge() {
		try {
			driver.findClickableElement(saveTaxChargeBtnLocator).click();
			LOGGER.info("Save button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		getMessageOvl().clickOk();
	}
	
	public void deleteTaxCharge(String taxCd) {
		this.selectTabulatorRow(taxCd);
		driver.findClickableElement(deleteTaxChargeBtnLocator).click();
		driver.findClickableElement(okDeleteBtnLovator).click();
		getMessageOvl().clickOk();
	}
	
	public void selectTabulatorRow(String keyword) {
		driver.findElement(By.xpath("//div[starts-with(@class,'tabulator-cell') "
				+ "and contains(text(),"
				+ " \"" + keyword + "\")]")).click();
	}
	
	
	
	public void tagTaxType(String id) {
		driver.findClickableElement(By.xpath("//*[@id='"+id+"']")).click();
	}
	
	public void openTaxPerilOverlay() {
		driver.findClickableElement(openTaxPerilBtnLovator).click();
	}
	
	public void closeTaxPerilOverlay() {
		driver.findClickableElement(closeTaxPerilBtnLovator).click();
	}
	
	public void setTaxPeril(String perilCd) {
		openTaxPerilLOV().searchAndSelectAutoSelectOne(perilCd);
	}
	
	private ModalDialog openTaxPerilLOV() {
		try {
			driver.findClickableElement(taxPerilLovLocator).click();
		}catch(TimeoutException e) {
			LOGGER.error(e);
		}
		return new ModalDialog(driver);
	}
	
	public void saveTaxPeril() {
		try {
			driver.findClickableElement(saveTaxPerilBtnLocator).click();
			LOGGER.info("Save Tax Peril button clicked.");
		} catch (TimeoutException e) {
			LOGGER.error(e);
		}
		getMessageOvl().clickOk();
	}
	
	public void deleteTaxPeril(String taxPerilCd) {
		this.selectTabulatorRow(taxPerilCd);
		driver.findClickableElement(deleteTaxPerilBtnLocator).click();
		driver.findClickableElement(okDeleteBtnLovator).click();
		getMessageOvl().clickOk();
	}
	
	private MessageOverlay getMessageOvl() {
		return new MessageOverlay(driver);
	}
	
}
