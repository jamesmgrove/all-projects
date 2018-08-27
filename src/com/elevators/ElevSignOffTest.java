package com.elevators;

import com.util.Constants;
import com.util.TestUtil;
import com.util.Xls_Reader;
import java.util.Hashtable;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.base.TestBase;
import com.pages.DobDashboardPage;
import com.pages.ElevatorsPage;
import com.relevantcodes.extentreports.LogStatus;

public class ElevSignOffTest extends TestBase {
	String testname = this.getClass().getSimpleName();
	Xls_Reader xlsx = new Xls_Reader(Constants.testCasesesElevator);

	@BeforeSuite
	public void BeforeSuite() {
		initConfigurations();
	}

	@BeforeMethod
	public void init() {
		initDriver();
		getEnvironmentDetails();
	}

	@AfterMethod
	public void quit() {
		quitDriver();
	}

	@AfterSuite
	public void killDrivers() {
		killDriver();
	}

	@DataProvider
	public Object[][] getTestData() {
		return TestUtil.getData(testname, xlsx);
	}
	
	ElevatorsPage elv = new ElevatorsPage();
	DobDashboardPage dash = new DobDashboardPage();

	@Test(priority = 0, dataProvider = "getTestData")
	public void Portal(Hashtable<String, String> data) {
		if (!TestUtil.isExecutable(testname, xlsx) || data.get("Runmode").equals("N"))
			throw new SkipException("Skipping the test");
		System.out.println("BEGIN " + convertedTimestamp() + " **************** " + data.get("description"));
		test = rep.startTest(data.get("description"));
		test.log(LogStatus.INFO, data.get("description"));
		test = rep.startTest("Test Case Data");
		test.log(LogStatus.INFO, data.toString());

		
		

		
		dash.jobFilingElev(data.get("work_type"));
		elv.searchAddDevice(data.get("address"));
		elv.deviceInfoAlteration(data.get("device_info"));
		elv.machineRoom(data.get("machine_room"));
		elv.deviceGeneralinfo(data.get("device_general_info"));
		elv.carsCounterweight(data.get("cars_counterweight"));
		elv.hoistwayOpeneing(data.get("hoistway_opening"));
		elv.pitAndBuffers(data.get("pit_and_buffers"));
		elv.insuranceFeeInfo(data.get("insurance_fee"));
		elv.documents(data.get("documents"));
		elv.signaturesSignOff(data.get("signatures"));
		elv.previewToFile(data.get("preview_to_file"));
		successMessage(data.get("description"));
	}

/*	// CPE VIEW-ACCEPT DOCS
	@Test(priority = 7, dataProvider = "getTestData", dependsOnMethods = {"Portal"})
	public void CPEAcceptDocs(Hashtable<String, String> data) {
		CrmTaskFormPage crm_task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		crm_task_form.viewAcceptDocsElv(data.get("cpe"));
	}

	// CPE ASSIGN TO PE
	@Test(priority = 8, dataProvider = "getTestData", dependsOnMethods = {"CPEAcceptDocs"})
	public void ChiefPlanExaminer(Hashtable<String, String> data) {
		CrmTaskFormPage crm_task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		crm_task_form.cpeAssignToSelfElv(data.get("cpe"));
	}

	// PE Permit Issued
	@Test(priority = 9, dataProvider = "getTestData", dependsOnMethods = {"ChiefPlanExaminer"})
	public void PlanExaminer(Hashtable<String, String> data) {
		CrmTaskFormPage crm_task_form = PageFactory.initElements(driver, CrmTaskFormPage.class);
		crm_task_form.cpeActionElv(data.get("cpe_action"));
	}

	// Sign Off
	@Test(priority = 10, dataProvider = "getTestData", dependsOnMethods = {"PlanExaminer"})
	public void SignOff(Hashtable<String, String> data) {
		DobDashboardPage dash = PageFactory.initElements(driver, DobDashboardPage.class);
		dash.requestSinOff(data.get("action"));
		ElevatorsSignOffPage off = PageFactory.initElements(driver, ElevatorsSignOffPage.class);
		off.inspectingAgencyinfo(data.get("sign_off_info"));
		off.testInfo(data.get("sign_off_info"));
		off.signature(data.get("sign_off_info"));
		off.submit(data.get("sign_off_info"));
		successMessage(data.get("description"));
	}*/
	
}