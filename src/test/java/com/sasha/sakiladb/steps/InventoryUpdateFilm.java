package com.sasha.sakiladb.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class InventoryUpdateFilm {
    private int filmId;
    private int storeId;
    private String mainUrl = "http://localhost:8080";
    private ResponseEntity<String> response;
    private RestTemplate restTemplate;


    @Given("I want to check how many films of id {int} in store {int}")
    public void i_want_to_check_how_many_films_of_id_in_store(int int1, int int2){
        filmId = int1;
        storeId = int2;

    }

    @When("I send another GET request to {string}")
    public void i_send_another_get_request_to(String endpoint){
        String url = mainUrl + endpoint;
        restTemplate = new RestTemplate();
        response = restTemplate.getForEntity(url, String.class);

    }

    @Then("I should get a response of {string}")
    public void i_should_get_a_response_of(String answer){
        String message = response.getBody();
        Assertions.assertEquals(answer, message);
    }
}
