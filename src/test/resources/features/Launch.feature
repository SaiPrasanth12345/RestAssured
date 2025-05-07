@featureTest
Feature: Login Feature
  I want to use this template for my feature file

  @apitesting @GET
  Scenario: GET request for all employees
    Given the URL "https://dummy.restapiexample.com/api/v1/employees" for the request
    When we trigger the GET request to get all employee details
    Then validate the Status code is 200
    Then validate the response body for 'status' is 'success'
    Then validate for response body
    Then validate for status line
