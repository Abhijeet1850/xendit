package com.test.xendit.features_ui;

import com.test.xendit.TestBase.TestBase;
import com.test.xendit.helper.configReader.PropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;


public class ManageCommons {

	static TestBase testbase;
	PropertyReader reader = new PropertyReader();
	public final String DRIVER_PATH = "//src//main//java//com//test//xendit//driver";

	@Before("@start")
	public void setUp() {
		testbase = new TestBase();
		testbase.setupDriver(reader.getBroswerType(), DRIVER_PATH);
	}

	@After("@end")
	public void tearDown() {
		testbase.shutDownDriver();
	}

}
