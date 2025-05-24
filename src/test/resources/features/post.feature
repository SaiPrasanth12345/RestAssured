@featureTest
Feature: Login Feature
  I want to use this template for my feature file

  @userCreate @POST @POJO
  Scenario: POST request to create user
    Given base URL "https://reqres.in/"
    When set the POST request body from the POJO class
      | name     | job     |
      | morpheus | teacher |
    And we trigger the POST request with path "/api/users" to create user details
    Then validate the Status code is 201
    And validate the ID is generated in the post response body
    And get all the response headers

  @userCreate @POST @Map
  Scenario: POST request to create user
    Given base URL "https://reqres.in/"
    When set the POST request body using the Map
      | name     | job     |
      | morpheus | teacher |
    And we trigger the POST request with path "/api/users" to create user details
    Then validate the Status code is 201
    And validate the ID is generated in the post response body
    And get all the response headers