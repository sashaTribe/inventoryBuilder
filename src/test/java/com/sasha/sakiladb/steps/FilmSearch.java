package com.sasha.sakiladb.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FilmSearch {

    @Given("I want to look for the film {string}")
    public void i_want_to_look_for_the_film(String name){

    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint){

    }

    @Then("I get a status code of {int} ")
    public void i_get_a_status_code_of(int status){

    }
}
