package RestAPI.RestAssuredAPIFramework.tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import RestAPI.RestAssuredAPIFramework.files.payload;
import RestAPI.RestAssuredAPIFramework.files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Delete {
	
	Properties prop = new Properties();
	Logger log = (Logger) LogManager.getLogger(Delete.class.getName());
	
	@BeforeTest
	public void start() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\env.properties");
	prop.load(fis);
	}
	
	@Test
	public void post()
	{
	String b=payload.placeBody();
		log.info("Host Information"+prop.getProperty("HOST"));
		RestAssured.baseURI=prop.getProperty("HOST");
		Response rs=given().
		queryParam("key", prop.getProperty("KEY")).body(b).
		when().post(resources.getPostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
		.and().body("status", equalTo("OK")).extract().response();
	
	String resp=rs.asString();
	System.out.println(resp);
	JsonPath js = new JsonPath(resp);
	
	String a = js.get("place_id");
	System.out.println(a);
	
	//Delete the place id 
	
	given().queryParam("key", "qaclick123").body("{\r\n" + 
			"� � \"place_id\":\""+a+"\"\r\n" + 
			"}\r\n" + 
			"").
	when().post("/maps/api/place/delete/json").then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
	.and().body("status", equalTo("OK"));
	
	
	
	}
	
	
	
	

}



