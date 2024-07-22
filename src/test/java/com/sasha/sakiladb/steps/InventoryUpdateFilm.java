package com.sasha.sakiladb.steps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InventoryUpdateFilm {
    private String filmName;
    private int storeId;
    private String mainUrl = "http://localhost:8080/inventory";
    private HttpResponse response;
    private RestTemplate restTemplate;
    private String message;


    @Given("I want to check how many DVDs of the film {string} in store {int}")
    public void i_want_to_check_how_many_DVDs_of_the_film_in_store(String name, int int2){
        filmName = name;
        storeId = int2;

    }

    @When("I send another GET request to {string}")
    public void i_send_another_get_request_to(String endpoint) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String url = mainUrl + endpoint;
        //System.out.println(url);
        //restTemplate = new RestTemplate();
        //response = restTemplate.getForEntity(url, String.class);

        RestTemplate restTemplate = new RestTemplate();
        HttpClient client = HttpClient.newHttpClient();
        System.out.println(URI.create(url));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());
        String stringResponse = response.body().toString();
        System.out.println(stringResponse);
        JsonNode rootNode = mapper.readTree(stringResponse);



    }

    @Then("I should get a response of {string}")
    public void i_should_get_a_response_of(String answer){
        //String message = response.getBody();
        Assertions.assertEquals(answer, message);
    }
}
