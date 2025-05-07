package StepDefinition;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class TestSteps {
	String getURL;
	Response response;

	@Given("the URL {string} for the request")
	public void the_url_for_the_request(String url) {
		getURL = url;
	}

	@When("we trigger the GET request to get all employee details")
	public void we_trigger_the_get_request_to_get_all_employee_details() {
		response = RestAssured.given().get(getURL);
		System.out.println(response.body().asPrettyString());
	}

	@Then("validate the Status code is {int}")
	public void validate_the_status_code_is(Integer statusCode) {
		Assert.assertTrue("Response Status is 200", response.getStatusCode() == statusCode);

	}

	@Then("validate for response headers")
	public void validate_for_response_headers() {
		Headers resHeader = response.getHeaders();
		for (Header h : resHeader) {
			System.out.print("Header Name:" + h.getName());
			System.out.println("Header Value:" + h.getValue());
		}	
	}

	@Then("validate for response body")
	public void validate_for_response_body() {

	}

	@Then("validate for status line")
	public void validate_for_status_line() {

	}

}
