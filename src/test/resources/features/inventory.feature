Feature: Inventory

  Scenario: Edit an inventory item
    Given the inventory id is 3
    And I want to change the filmId to 9
    When I send a PATCH request to "/inventory/3"
    Then the film name should be "ALABAMA DEVIL"


  Scenario: Add to inventory
    Given the film id is 10 and the storeId is 2
    When I send a POST request to "/inventory"
    Then the status code should be 201
    And the inventory id should be 0
    And the film name is "interstellar"
    And the address is "123 Abbey Street"

    Scenario: Delete item from inventory
      Given I want to delete an item with id number 385
      When I send a DELETE request to "/inventory/385"
      Then the delete status code should be 200

  Scenario: Getting status on how many films are in a valid store
    Given I want to check how many films of id 26 in store 1
    When I send another GET request to "/inventory?filmId=26&storeId=1"
    Then I should get a response of "There are 5 copies left in store 1"


  Scenario: Getting status on how many films are in an invalid store
    Given I want to check how many films of id 26 in store 4
    When I send another GET request to "/inventory/availability"
    Then I should get a response of "There are 5 copies left in store 1"

