package day3;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HeadersDemo {
	
	@Test(priority = 1)
	void testHeader() {
		given().when()
		.get("Https://www.google.com")
		.then()
		.statusCode(200)
		.log().headers()
		.header("Content-Type", "text/html; charset=ISO-8859-1");
	}
	@Test(priority = 2)
	void testGetHeaders() {
		Response response = given().when()
		.get("Https://www.google.com");
		System.out.println("-------------------------");
		System.out.println(response.header("Content-Type"));
		
	}
	
	@Test(priority = 3)
	void getAllHeaders() {
		Response response = given().when()
		.get("Https://www.google.com");
		Headers headers = response.headers();
		for(Header header:headers) {
			
			System.out.println( header.getName()+" ==> "+header.getValue());
		}
		
		
	}

}
