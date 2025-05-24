package StepDefinition;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.mapper.factory.DefaultJackson2ObjectMapperFactory;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSteps {
	String getURL;
	Response response;
	RequestSpecification reqSpec;

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
		System.out.println("Header Details : ");
		for (Header h : resHeader) {
			System.out.print("Header Name:" + h.getName());
			System.out.println(" Header Value:" + h.getValue());
		}	
	}

	@Then("get all the details of the first employee")
	public void get_all_the_details_of_the_first_employee() {
		int id = response.body().jsonPath().getInt("data[0].id");
		String employeeName = response.getBody().jsonPath().getString("data[0].employee_name");
		int salary = response.jsonPath().getInt("data[0].employee_salary");
		int empAge = response.jsonPath().getInt("data[0].employee_age");

		System.out.println("First Employee details are: ");
		System.out.println("ID : "+id);
		System.out.println("Employee Name: "+employeeName);
		System.out.println("Salary: "+salary);
		System.out.println("Employee Age: "+empAge);
	}

	@Then("get all the list of all employee names")
	public void get_all_the_list_of_all_employee_names() {
		List<String> employeeNameList  = response.jsonPath().getList("data.employee_name");

		System.out.println("Employee Names Count : "+employeeNameList.size());
		System.out.println("First 5 Employee Names : ");
		for (int i=0; i<5; i++) {
			System.out.println(employeeNameList.get(i));
		}
	}


	@Then("validate for status line")
	public void validate_for_status_line() {

	}

	@Then("validate the response body for {string} is {string}")
	public void validate_the_response_body_for_is(String string, String string2) {

	}

	/*------------------------------------------------------------------------------*/

	@Given("base URL {string}")
	public void base_url(String url) {
		reqSpec =  RestAssured.given();
		reqSpec.baseUri(url);
	}
	@When("we trigger the GET request with path {string} to get user details")
	public void we_trigger_the_get_request_with_path_to_get_user_details(String pathURL) {
		response = reqSpec.get(pathURL);

		System.out.println("Response Pretty Print :");
		System.out.println(response.body().prettyPrint());
	}
	@Then("validate the Status code is {int} and status line")
	public void validate_the_status_code_is_and_status_line(Integer int1) {
		Assert.assertTrue("Status code is 200 ", response.getStatusCode() == 200);
	}
	@Then("get the user details of the response body")
	public void get_the_user_details_of_the_response_body() {
		int datasize = response.getBody().jsonPath().get("data.size()");
		System.out.println("Elements inside data: "+datasize);

		int id = response.getBody().jsonPath().getInt("data.id");
		String email = response.getBody().jsonPath().get("data.email");
		String firstName = response.getBody().jsonPath().getString("data.first_name");
		String lastName = response.getBody().jsonPath().get("data.last_name");
		String supportURL = response.getBody().jsonPath().getString("support.url");
		String text = response.getBody().jsonPath().getString("support.text");

		System.out.println("User details are: ");
		System.out.println("ID : "+id);
		System.out.println("Employee Name: "+firstName.concat(" ").concat(lastName));
		System.out.println("Email: "+email);
		System.out.println("Text: "+text);

	}

	@Then("get the response detials in the POJO")
	public void get_the_response_detials_in_the_pojo() throws JsonProcessingException {
		String responsetxt = response.body().prettyPrint();

		ObjectMapper objectMapper = new ObjectMapper();
		ResponseUser resUserDet = objectMapper.readValue(responsetxt, ResponseUser.class);

		System.out.println("User details are: ");
		System.out.println("ID : "+resUserDet.getData().getId());
		System.out.println("Employee Name: "+resUserDet.getData().getFirst_name().concat(" ").concat(resUserDet.getData().getLast_name()));
		System.out.println("Email: "+resUserDet.getData().getEmail());
		System.out.println("Text: "+resUserDet.getSupport().getText());
	}


	@Then("get all the response headers")
	public void get_all_the_response_headers() {
		Headers resHeader = response.getHeaders();
		System.out.println("Response headers : ");

		// Single header values
		System.out.print("Single header - 'Content-Type' value is :" + response.getHeader("Content-Type"));

		// Multiple headers values
		for (Header h : resHeader) {
			System.out.print("Header Name:" + h.getName());
			System.out.println(" Header Value:" + h.getValue());
		}
	}

	@When("set the POST request body from the POJO class")
	public void set_the_post_request_body_from_the_POJO_class(List<Map<String, String>> bodyMap) throws JsonProcessingException {
		String name = bodyMap.get(0).get("name");
		String job = bodyMap.get(0).get("job");

		CreateUser user = new CreateUser(name, job);

		//convert pojo to string
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonreq= objectMapper.writeValueAsString(user);
		reqSpec.body(jsonreq);
	}

	@When("set the POST request body using the Map")
	public void set_the_post_request_body_using_the_map(List<Map<String, String>> bodyMap) throws JsonProcessingException {
		//to get the json body using the map
		Map<String, String> reqBody = bodyMap.get(0);

		ObjectMapper objectMapper = new ObjectMapper();
		String mapbodyreq= objectMapper.writeValueAsString(reqBody);
		reqSpec.body(mapbodyreq);
	}

	@When("we trigger the POST request with path {string} to create user details")
	public void we_trigger_the_post_request_with_path_to_create_user_details(String path) {
		//post request
		response = reqSpec.header("x-api-key", "reqres-free-v1")
					.when().post(path).then().extract().response();

		System.out.println("POST request response is: "+ response.body().prettyPrint());
	}

	@When("validate the ID is generated in the post response body")
	public void validate_the_ID_is_generated_in_the_post_response_body() {
		// validate response contains ID
		int id = response.body().jsonPath().getInt("id");

		System.out.println("User ID got created is: "+ id);
		System.out.println("Time of creation: "+ response.jsonPath().get("createdAt"));
	}

}
