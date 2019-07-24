package RestAPI.RestAssuredAPIFramework.tests;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import RestAPI.RestAssuredAPIFramework.files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class Xmlpost {

	@Test
	public void post() throws IOException
	{
	
		RestAssured.baseURI="http://216.10.245.166";
		String post=generateStringFromResponse("path");
		Response resp=given().
		queryParam("key", "qaclick123").body(post).
		when().post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML)
		.and().extract().response();
		XmlPath x=	ReusableMethods.RawToXML(resp);
		x.get("PlaceAddResponse.place_id");
	
		
	}
	
	public static String generateStringFromResponse(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}

}
