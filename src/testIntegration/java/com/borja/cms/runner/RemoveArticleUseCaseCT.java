package com.borja.cms.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"com.borja.cms.steps.article"},
        features = {"src/testIntegration/resources/features"})
public class RemoveArticleUseCaseCT {
}
