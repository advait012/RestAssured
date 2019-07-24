package RestAPI.RestAssuredAPIFramework.tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import RestAPI.RestAssuredAPIFramework.files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TraverseJsonArrays {
	
	@Test
	public void test() {
		
		//BaseURL
		RestAssured.baseURI="https://maps.googleapis.com";

		Response resp=given().param("location", "-33.8670522,151.1957362").param("radius", 1500).param("key", "AIzaSyBiFc3Dk4YLzivaYSbeYyaqcwaG_RjoXZM").
when().get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("results[0].name", equalTo("Sydney")).and().
		header("Server","scaffolding on HTTPServer2").extract().response();
		
		JsonPath j=ReusableMethods.RawToJSON(resp);
		int count=j.get("results.size()");
		System.out.println(count);
		for(int i =0;i<count;i++) 
		{
			String a=j.get("results["+i+"].name");
			System.out.println(a);
		}
		
		
	}



}
