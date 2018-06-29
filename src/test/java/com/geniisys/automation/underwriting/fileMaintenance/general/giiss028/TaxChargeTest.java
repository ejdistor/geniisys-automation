package com.geniisys.automation.underwriting.fileMaintenance.general.giiss028;

import java.math.BigDecimal;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.geniisys.automation.BaseTest;
import com.geniisys.automation.main.pages.HomePage;
import com.geniisys.automation.underwriting.main.pages.UnderwritingPage;

public class TaxChargeTest extends BaseTest {

	private final String issCd = "HO";
	private final String lineCd = "AV";
	private final String taxCd = "36";
	private final String functionName = "test function name";
	private final BigDecimal rate = new BigDecimal("15");
	private final String startDate = "05-01-2018";
	private final String endDate = "05-01-2019";
	private TaxChargePage tc;
	
	@BeforeClass
	public void initializeTaxCharge() {
		tc = new TaxChargePage(driver);
	}

	@Test(priority=1)
	public void goToGiiss028() {
		HomePage homePage = new HomePage(driver);
		UnderwritingPage uwPage = homePage.goToUnderwritingPage();
		uwPage.getMenu().goToGiiss028();
	}
	
	@Test(priority=2, dependsOnMethods="goToGiiss028")
	public void queryParams() {
		tc.setIssueSource(issCd);
		tc.setLine(lineCd);
		tc.executeQuery();
	}
	
	@Test(priority=3, dependsOnMethods="queryParams")
	public void createTaxChargeTest() {
		this.createTaxCharge();
		this.saveTaxChargeTest();
		this.deleteTaxChargeRecord(taxCd);
	}
	
	public void createTaxCharge() {
		tc.validateSequence();
		tc.setTaxCode(taxCd);
		tc.setFunctionName(functionName);
		tc.setRate(rate);
		tc.setStartDate(startDate);
		tc.setEndDate(endDate);
	}
	
	public void saveTaxChargeTest() {
		tc.checkTaxChargeRequiredFields();
		tc.saveTaxCharge();
	}
	
	public void deleteTaxChargeRecord(String t) {
		tc.deleteTaxCharge(t);
	}
	
}
