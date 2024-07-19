package com.sasha.sakiladb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@SpringBootTest
@CucumberOptions(features="./resources/features", glue="com.sasha.sakiladb.steps.StoreSteps")
class SakiladbApplicationTests {

	@Test
	void contextLoads() {
	}

}
