package com.sasha.sakiladb.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DeleteItem {

    private int inventoryId;
    private String mainUrl = "http://localhost:8080";
    private RestTemplate restTemplate;
    private ResponseEntity<Void> response;

    @Given("I want to delete an item with id number {int}")
    public void i_want_to_delete_an_item_with_id_number(int id){
        inventoryId = id;
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint){
        String url = mainUrl + endpoint;
        restTemplate = new RestTemplate();
        response = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the delete status code should be {int}")
    public void the_delete_status_code_should_be(int code) {
        Assertions.assertEquals(code, response.getStatusCode().value());
    }
}
