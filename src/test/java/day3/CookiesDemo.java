package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

@Test
public class CookiesDemo {
	
	//@Test(priority = 1)
	void testCookies() {
		given()
		
		.when()
			.get("Https://www.google.com")
		.then()
		.cookie("AEC","AVYB7cqE151fZe8f0OhFg85Psfa7rxtwuVZ2C5Orjj8aClG_Fs088lwuuno")
		.log().cookies()
		;
	}
	//@Test(priority = 2)
	void testCookiesinfo() {
		Response response = given()
		
		.when()
			.get("Https://www.google.com");
		
		//get single cookie info
		String cookie_aec = response.cookie("AEC");
		System.out.println("value of Cookie  ==> "+cookie_aec);
	}
	@Test(priority = 3)
	void testAllCookiesinfo() {
		Response response = given()
		
		.when()
			.get("Https://www.google.com");
		
		//get all  cookies info
		Map<String, String> cookies = response.cookies();
		System.out.println(cookies.keySet());
		
		for(String cookie:cookies.keySet()) {
			
			String cookie_value= response.getCookie(cookie);
			System.out.println( cookie+" ==> "+cookie_value);
			
		}
		
	}

}
