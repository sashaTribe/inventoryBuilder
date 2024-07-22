package com.sasha.sakiladb.steps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static com.jayway.jsonpath.internal.function.ParamType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoreSteps {


    private static String url = "http://localhost:8080";
    @Autowired
    private StoreResponse storeResponse;
    @Autowired
    public StoreController storeController;
    private Store mockStore;
    @Autowired
    public StoreRepository storeRepository;
    private HttpResponse response;
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
    public void i_send_a_get_request_to_endpoint(String endpoint) throws IOException, JSONException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String main_url = url + endpoint + storeId;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(main_url))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        String stringResponse = response.body().toString();
        JsonNode rootNode = mapper.readTree(stringResponse);
        id = rootNode.get("id").shortValue();
        filmStock = rootNode.get("filmStock").asInt();
        address = rootNode.get("address").asText();
        city = rootNode.get("city").asText();
        country = rootNode.get("country").asText();



        /*
        response = restTemplate.getForEntity(main_url, String.class, Map.of("id", 1));

        boolean id = response.getBody().contains("id");
        System.out.println(response.getBody() + id);

         */
        //JSONObject jsonObject = new JSONObject(response.getBody());


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


/*
    public void set_values() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StoreResponse storeResponse = mapper.readValue(response.getBody(), StoreResponse.class);
        id = storeResponse.getId();
        address = storeResponse.getAddress();
        city = storeResponse.getCity();
        country = storeResponse.getCountry();
        filmStock = storeResponse.getFilmStock();

    }

 */



    @Then("the response status should be {int}")
    public void the_response_status_should_be (int statusCode) throws IOException {
        //set_values();

        assertEquals(statusCode, response.statusCode());
        //System.out.println(response.getBody());

    }



    @Then("the response body should contain id with {int}")
    public void the_response_body_should_contain_id_with(int storeId) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //StoreResponse storeResponse = mapper.readValue(response.getBody(), StoreResponse.class);
        //String storeResponse = response.statusCode();
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
