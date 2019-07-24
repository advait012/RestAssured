package RestAPI.RestAssuredAPIFramework.tests;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import RestAPI.RestAssuredAPIFramework.files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class JIRATest {
	
	@Test
	public void Jira()
	{
		//Create issue
		RestAssured.baseURI="http://localhost:8088";
		Response resp=given().header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).header("Content-Type","application/json").
		body("{"+
	"\"fields\":{"+
			"\"project\":{"+
			"\"key\":\"RES\""+
			"},"+
			"\"summary\":\"RaiseJIraIssue\","+
			"\"description\":\"description\","+
			"\"issuetype\":{"+
				"\"name\":\"Bug\""+
			"}"+
	"}"+
"}").when().post("/rest/api/2/issue").then().assertThat().statusCode(201).extract().response();
		
		JsonPath js = ReusableMethods.RawToJSON(resp);
		String s = js.get("id");
		System.out.println(s);
	}

}
