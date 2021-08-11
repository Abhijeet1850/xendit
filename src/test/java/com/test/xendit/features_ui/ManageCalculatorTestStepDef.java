package com.test.xendit.features_ui;

import com.test.xendit.PageActions.*;
import com.test.xendit.TestBase.TestBase;
import com.test.xendit.helper.configReader.PropertyReader;
import com.test.xendit.helper.logger.LoggerHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;

import java.util.List;
import java.util.Map;

public class ManageCalculatorTestStepDef extends TestBase {

    private Logger log = LoggerHelper.getLogger(ManageCalculatorTestStepDef.class);
    CalculatorMainPage calcPage = new CalculatorMainPage(driver);
    PropertyReader reader = new PropertyReader();

    @Given("I visit the online calculator url")
    public void i_visit_the_online_calculator_url() {
        calcPage.fetchCalculatorDetails();
    }

    @When("I enter following values and press CE button")
    public void i_enter_following_values_and_press_ce_button(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> map : data) {
            calcPage.enterNumberOrOperator(map.get("value1"));
            calcPage.enterNumberOrOperator(map.get("operator"));
            if (map.get("value2") != null) {
                calcPage.enterNumberOrOperator(map.get("value2"));
                calcPage.enterNumberOrOperator("=");
            }
            calcPage.enterNumberOrOperator("CE");
        }
    }


    @Then("I should be able to see correct result")
    public void i_should_be_able_to_see_correct_result(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps();
        for (Map<String, String> map : data) {
            Assertions.assertThat(calcPage.getActualResult()).isEqualTo(map.get("expected"));
        }
    }
}
