package com.sasha.sakiladb.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sasha.sakiladb.input.InventoryFormInput;
import com.sasha.sakiladb.input.InventoryInput;
import com.sasha.sakiladb.output.InventoryResponse;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class InventorySteps {

    private int filmId;
    private String url;
    private Short storeId;
    private RestTemplate restTemplate;
    private InventoryFormInput inventory;
    private Long inventoryId;
    private String filmName;
    private String address;
    private ResponseEntity<String> response;
    private TestRestTemplate template;


    @Given("the film id is {int} and the storeId is {int}")
    public void the_film_name_is_and_the_store_id_is(int int1, int int2) {
        filmId = int1;
        short tempVal = (short) int2;
        storeId = Short.valueOf(tempVal);
    }

    @When("I send a POST request to {string}")
    public void when_i_send_a_post_request_to(String endpoint) throws IOException, InterruptedException {
        String main_url = "http://localhost:8080" + endpoint;
        template = new TestRestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        InventoryFormInput inventoryInput = new InventoryFormInput();
        inventoryInput.setStoreId(storeId);
        inventoryInput.setFilmId(filmId);


        response = template.postForEntity(main_url, inventoryInput, String.class);
        System.out.println(response.getBody());
        String stringResponse = response.getBody().toString();
        JsonNode rootNode = mapper.readTree(stringResponse);
        inventoryId = rootNode.get("id").longValue();
        filmName = rootNode.get("filmName").asText();
        address = rootNode.get("address").asText();

        //HttpResponse response = client.send(request, BodyHandler.asFile(Paths.get("/path")));
        //Path body = response.body();



        //String main_url = url + endpoint;
       // HttpEntity<InventoryFormInput> request = new HttpEntity<>(inventoryInput);

        //System.out.println(response.getBody());
    }


    /*

    @Before
    public void set_values() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InventoryResponse inventoryResponse = mapper.readValue(response.getBody(), InventoryResponse.class);
        inventoryId =inventoryResponse.getId();
        filmName = inventoryResponse.getFilmName();
        address = inventoryResponse.getAddress();

    }

     */

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

/*
    HttpClient client = HttpClient
            .newBuilder()
            .build();

    HttpRequest request = HttpRequest
            .newBuilder(new URI("http://www.foo.com/"))
            .POST(BodyProcessor.fromString("Hello world"))
            .build();

    HttpResponse<Path> response =
            client.send(request, BodyHandler.asFile(Paths.get("/path")));

    Path body = response.body();

 */




}
