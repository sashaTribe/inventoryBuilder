Feature: store

  Scenario: Getting Store information
    Given the store id is 1
    When I send a GET request to "/stores/"
    Then the response status should be 200
    Then the response body should contain id with 1
    And the response body should contain filmStock with 2269
    And the reponse body should contain address with "47 MySakila Drive"
    And the response body should contain city with "Lethbridge"
    And the response body should contain country with "Canada"


    Scenario: Getting list of stores
      Given I am on page "/stores"
      When I send a GET request to "/stores"
      Then the response status should be 200