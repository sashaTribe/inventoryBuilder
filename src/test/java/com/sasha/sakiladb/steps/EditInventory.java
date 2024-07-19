package com.sasha.sakiladb.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditInventory {

    private int inventoryId;
    private int filmId;


    @Given("the inventory id is {int}")
    public void the_inventory_id_is(int id){
        inventoryId = id;
    }

    @Given("I want to change the filmId to {int}")
    public void i_want_to_change_the_filmId_to(int id) {
        filmId = id;

    }

    @When("I send a PATCH request to {string}")
    public void i_send_a_patch_request_to(String endpoint){

    }

    @Then("the film name should be {string}")
    public void the_film_name_should_be(String filmName){

    }



}
