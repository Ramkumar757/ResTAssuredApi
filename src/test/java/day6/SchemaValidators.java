package day6;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SchemaValidators {
	@Test
	void jsonSchemaValidator() {
		//https://reqres.in/api/users?page=2
		

		
		given()
		.when()
		.get("https://reqres.in/api/users?page=2")
		.then()
		.log().body()
		.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"));
		 
	}

	@Test
	void xmlSchemaValidator() {
		given()
		.header("accept", "application/xml")
		.when()
		.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
		.then()
		
		.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));
	}

}
