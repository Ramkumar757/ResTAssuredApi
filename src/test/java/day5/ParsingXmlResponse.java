package day5;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.mozilla.javascript.xml.XMLObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXmlResponse {

	@Test(priority = 1)
	void testXmlResponse() {
		given()
		.header("accept", "application/xml")
		.when()
			.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
			.then()
			.log().all();
			//.body("pets.Pet.status[1].text()",equalTo("available"));
					
		/*
		
		
		Response res = given()
		.header("accept", "application/xml")
		.when()
			.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.getHeader("Content-Type"), "application/xml");
		
		Assert.assertEquals(res.xmlPath().get("pets.Pet.status[1].text()").toString(),"available");
		*/
	}
	//@Test(priority = 2)
	void testXmlResponseBody() {
		Response res = given()
				.header("accept", "application/xml")
				.when()
					.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
		
		XmlPath xp = new XmlPath(res.asString());
		
		List<String> list = xp.getList("pets.Pet");
		//Assert.assertEquals(list.size(),300);
		//Puňťa
		List<String> names = xp.getList("pets.Pet.name");
		System.out.println(names.toString());
		boolean status = false;
		for(String name: names) {
			if(name.equals("capybarinya")) {
				status = true;
			}
		}
		Assert.assertTrue(status);
	}
}
