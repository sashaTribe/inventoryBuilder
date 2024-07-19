package com.sasha.sakiladb.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasha.sakiladb.input.InventoryFormInput;
import com.sasha.sakiladb.input.InventoryInput;
import com.sasha.sakiladb.output.InventoryResponse;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Objects;

public class InventorySteps {

    private int filmId;
    private String url;
    private Short storeId;
    private RestTemplate restTemplate;
    private InventoryFormInput inventory;
    private int inventoryId;
    private String filmName;
    private String address;
    ResponseEntity<String> response;


    @Given("the film id is {int} and the storeId is {int}")
    public void the_film_name_is_and_the_store_id_is(int int1, int int2) {
        filmId = int1;
        short tempVal = (short) int2;
        storeId = Short.valueOf(tempVal);
    }

    @When("I send a POST request to {string}")
    public void when_i_send_a_post_request_to(String endpoint) {
        restTemplate = new RestTemplate();
        InventoryFormInput inventoryInput = new InventoryFormInput();
        inventoryInput.setStoreId(storeId);
        inventoryInput.setFilmId(filmId);
        String main_url = "http://localhost:8080" + endpoint;
        //String main_url = url + endpoint;
        HttpEntity<InventoryFormInput> request = new HttpEntity<>(inventoryInput);
        response = restTemplate.postForEntity(main_url, request, String.class);
        //System.out.println(response.getBody());
    }


    @Before
    public void set_values() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InventoryResponse inventoryResponse = mapper.readValue(response.getBody(), InventoryResponse.class);
        inventoryId =inventoryResponse.getId();
        filmName = inventoryResponse.getFilmName();
        address = inventoryResponse.getAddress();

    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(int status) throws IOException {
        //set_values();
        Assertions.assertEquals(status, response.getStatusCode().value());
    }

    @Then("the inventory id should be {int}")
    public void the_inventory_id_should_be(int id){
        Assertions.assertEquals(id, inventoryId);
    }

    @Then("the film name is {string}")
    public void the_film_name_is(String name) {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(name, filmName);
        //
    }

    @Then("the address is {string}")
    public void the_store_id_is(String name) {
        Assertions.assertEquals(name, address);
    }






}
