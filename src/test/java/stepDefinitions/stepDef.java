package stepDefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//import com.browserstack.pageobjects.HomePage;

import googleApiPojo.GetAddPlace;
import googleApiPojo.Location;

public class stepDef {
	
	RequestSpecification req;
	Response res;
	WebDriver driver;
	
//	 @Before
//	    public void setUp() throws MalformedURLException {
//	        MutableCapabilities capabilities = new MutableCapabilities();
//	        HashMap<String, String> bstackOptions = new HashMap<>();
//	        bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
//	        capabilities.setCapability("bstack:options", bstackOptions);
//	        driver = new RemoteWebDriver(
//	                new URL("https://hub.browserstack.com/wd/hub"), capabilities);
//	        driver.get("https://www.youtube.com");
////	        homePage = new HomePage(driver);
//	        
//	        
//	    }
	
	
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
	
	ExtentTest test1 = extent.createTest("MyFirstTest");
	
//	test.pass("Pass");

	// fluent    "testReport.html"
//	ExtentTest test = extent.createTest("MyFirstTest").pass("Pass");

	
//	extent.flush();

	
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_place_payload_with(String string, String string2, String string3) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		GetAddPlace py = new GetAddPlace();
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		py.setLocation(l);
		py.setAccuracy(50);
		py.setName(string);
		py.setPhone_number("(+91) 983 893 3937");
		py.setAddress(string3);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		py.setTypes(types);
		py.setWebsite("http://google.com");
		py.setLanguage(string2);
		
//		given().log().all().header("Content-Type","application/json").queryParam("key","qaclick123").body(py)
//		.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(403);
		
		 req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick").setContentType(ContentType.JSON).setBody(py).build();

	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String string, String string2) {
		 res = given().spec(req).when().post("/maps/api/place/add/json").then().extract().response();
		 assertEquals(res.statusCode(),200);
		 
		 extent.attachReporter(spark);
		 test1.log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
//		 test1.pass(string2, null)
		 
		 extent.flush();
	}
	
	
	
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
//	    throw new io.cucumber.java.PendingException();
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() {
	    // Write code here that turns the phrase above into concrete actions
	    
	}



}
