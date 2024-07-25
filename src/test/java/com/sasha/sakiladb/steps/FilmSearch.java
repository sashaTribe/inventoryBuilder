package com.sasha.sakiladb.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FilmSearch {

    private String filmName;
    private final String URL = "http://localhost:8080";
    private HttpResponse response;

    @Given("I want to look for the film {string}")
    public void i_want_to_look_for_the_film(String name){
        filmName = name;
    }

    @When("I send a film GET request to {string}")
    public void i_send_a_film_get_request_to(String endpoint) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String main_url = URL + endpoint;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(main_url))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());

    }

    @Then("I get a success status code of {int}")
    public void i_get_a_success_status_code_of(int status){
        Assertions.assertEquals(status, response.statusCode());

    }
}
