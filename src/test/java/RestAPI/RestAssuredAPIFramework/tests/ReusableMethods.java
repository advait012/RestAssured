package RestAPI.RestAssuredAPIFramework.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;


public class ReusableMethods {

	public static XmlPath RawToXML(Response resp)
	{
		String respon=resp.asString();
		XmlPath x = new XmlPath(respon);
		return x;
	}
	
	public static JsonPath RawToJSON(Response resp)
	{
		String respon=resp.asString();
		JsonPath j = new JsonPath(respon);
		return j;
	}
	
	public static String generateStringFromResponse(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
		
	}
	
	public static String getSessionKey()
	{
		RestAssured.baseURI="http://localhost:8088";
		Response resp=given().header("Content-Type","application/json").body("{ \"username\": \"advaitsharma012\", \"password\": \"AL@advait12\" }").when().post("/rest/auth/1/session").
				then().assertThat().statusCode(200).extract().response();
		
		JsonPath js = RawToJSON(resp);
		String s =js.get("session.value");
		System.out.println(s);
		return s;
		
	}

}
