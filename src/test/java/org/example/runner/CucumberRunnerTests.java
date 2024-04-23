package org.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "@CTB",
        features = "src/test/resources/features",
        glue = "org.example.definitions",
//        plugin = {
//                "html:target/cucumber-reports/TestRunnerCategoryCMS.html",
//                "json:target/cucumber-reports/TestRunnerCategoryCMS.json"
//        }
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}


)

public class CucumberRunnerTests  {

}