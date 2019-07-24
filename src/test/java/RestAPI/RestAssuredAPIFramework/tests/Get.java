package RestAPI.RestAssuredAPIFramework.tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Get {
	
	Logger log =(Logger) LogManager.getLogger(Get.class.getName());

	
	@Test
	public void test() {
		
		//BaseURL
		RestAssured.baseURI="https://maps.googleapis.com";

		given().param("location", "-33.8670522,151.1957362").param("radius", 1500).param("key", "AIzaSyBiFc3Dk4YLzivaYSbeYyaqcwaG_RjoXZM").
when().get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().
		header("Server","scaffolding on HTTPServer2");
	}


}
