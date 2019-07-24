package RestAPI.RestAssuredAPIFramework.tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RestAPI.RestAssuredAPIFramework.files.ReusableMethods;
import RestAPI.RestAssuredAPIFramework.files.payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonFile {

	@Test()
	public void post() throws IOException
	{
	
		RestAssured.baseURI="http://216.10.245.166";
		Response resp=given().header("Content-Type","application/json")
		.body(ReusableMethods.generateStringFromResponse("C:\\jsonFiles.json")).
		when().post("/Library/Addbook.php").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.and().body("Msg", equalTo("successfully added")).extract().response();
		
		JsonPath js= ReusableMethods.RawToJSON(resp);
		String s = js.get("ID");
		System.out.println(s);
		
	}
		
}
