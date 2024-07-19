package com.sasha.sakiladb.steps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasha.sakiladb.controllers.StoreController;
import com.sasha.sakiladb.entities.Store;
import com.sasha.sakiladb.input.InventoryInput;
import com.sasha.sakiladb.output.InventoryResponse;
import com.sasha.sakiladb.output.StoreResponse;
import com.sasha.sakiladb.repository.StoreRepository;
import io.cucumber.java.en.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.jayway.jsonpath.internal.function.ParamType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoreSteps {


    private static String url = "http://localhost:8080/stores/";
    @Autowired
    private StoreResponse storeResponse;
    @Autowired
    public StoreController storeController;
    private Store mockStore;
    @Autowired
    public StoreRepository storeRepository;
    private ResponseEntity<String> response;
    private Short id;
    private long filmStock;
    private String address;
    private String city;
    private String country;
    private RestTemplate restTemplate;
    private String filmName;
    private Short storeId;

    private JSONObject jsonObject;


    @Given("the store id is {int}")
    public void the_API_endpoint_is(int id) {
        short tempVal = (short) id;
        storeId = Short.valueOf(tempVal);
        this.mockStore = new Store();

        //row new io.cucumber.java.PendingException();
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to_endpoint(String endpoint) throws IOException, JSONException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080" + endpoint + storeId;
        response = restTemplate.getForEntity(url, String.class);
        System.out.println(response.getBody());
        JSONObject jsonObject = new JSONObject(response.getBody());


        //ObjectMapper mapper = new ObjectMapper();
        //StoreResponse storeResponse = mapper.readValue(response.getBody(), StoreResponse.class);

        /*
        System.out.println(storeId);
        storeController = new StoreController();

        storeResponse = storeController.getStoreById(storeId);
        System.out.println("Store response: " + storeResponse.getAddress());
        //row new io.cucumber.java.PendingException();

         */

    }



    public void set_values() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StoreResponse storeResponse = mapper.readValue(response.getBody(), StoreResponse.class);
        id = storeResponse.getId();
        address = storeResponse.getAddress();
        city = storeResponse.getCity();
        country = storeResponse.getCountry();
        filmStock = storeResponse.getFilmStock();

    }



    @Then("the response status should be {int}")
    public void the_response_status_should_be (int statusCode) throws IOException {
        //set_values();

        assertEquals(statusCode, response.getStatusCode().value());
        System.out.println(response.getBody());

    }



    @Then("the response body should contain id with {int}")
    public void the_response_body_should_contain_id_with(int storeId) {
        short tempVal = (short) storeId;
        Short shortStoreId = Short.valueOf(tempVal);
        assertEquals(shortStoreId, id);

    }

    @Then("the response body should contain filmStock with {int}")
    public void the_response_body_should_contain_filmstock_with(int stockVal) {
        assertEquals(stockVal, filmStock);
    }

    @Then("the reponse body should contain address with {string}")
    public void the_response_body_should_contain_address_with(String storeAddr){
        assertEquals(storeAddr, address);

    }

    @Then("the response body should contain city with {string}")
    public void the_response_body_should_contain_city_with(String cityName){
        assertEquals(cityName,city);
    }

    @Then("the response body should contain country with {string}")
    public void the_response_body_should_contain_country_with(String countryName){
        assertEquals(countryName, country);

    }



}
