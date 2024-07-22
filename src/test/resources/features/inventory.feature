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
    And the inventory id should be 4592
    And the film name is "ALADDIN CALENDAR"
    And the address is "28 MySQL Boulevard"

  Scenario: Delete item from inventory
    Given I want to delete an item with id number 4592
    When I send a DELETE request to "/inventory/4592"
    Then the delete status code should be 200



  Scenario: Search a film within an inventory
    Given I want to look for the film "Agent Truman"
    When I send a GET request to "/inventory?name=agent+truman"
    Then I get a status code of 200



