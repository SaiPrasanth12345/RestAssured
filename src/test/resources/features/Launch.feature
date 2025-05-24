@featureTest
Feature: Login Feature
  I want to use this template for my feature file

  @apitesting @GET
  Scenario: GET request for all employees
    Given the URL "https://dummy.restapiexample.com/api/v1/employees" for the request
    When we trigger the GET request to get all employee details
    Then validate the Status code is 200
    Then get all the details of the first employee
    Then get all the list of all employee names
    Then validate for response headers
    #Then validate the response body for 'status' is 'success'
    Then validate for status line

  @userDetails @GET
  Scenario: GET request for all employees
    Given base URL "https://reqres.in/"
    When we trigger the GET request with path "/api/users/2" to get user details
    Then validate the Status code is 200 and status line
    And get the user details of the response body
    And get all the response headers

  @userDetails @GET @POJO
  Scenario: GET request for all employees
    Given base URL "https://reqres.in/"
    When we trigger the GET request with path "/api/users/2" to get user details
    Then validate the Status code is 200 and status line
    And get the response detials in the POJO