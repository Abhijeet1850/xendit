package com.test.xendit.features_ui;

import com.test.xendit.TestBase.TestBase;
import com.test.xendit.helper.configReader.PropertyReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.util.stream.Stream;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"}, tags = "@test", publish = true, monochrome = true)
public class ITRunCukes {

}