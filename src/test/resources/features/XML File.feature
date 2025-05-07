@featureTest
Feature: XML Feaures
  I want to use this template for my feature file

  @xml @generate
  Scenario: Generate XML using normal methods
    Given Generate an XML File
    And Create a Dcoument
    When Add the XML details in the document
    Then Print the XML in the console
    And upload the XML deails to a file