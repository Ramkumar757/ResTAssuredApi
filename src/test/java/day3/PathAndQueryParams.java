package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParams {

//	https://reqres.in/api/users?page=2
//  https://reqres.in/api/users?page=2& id=5
// query params no need to put into request, they goes without anything	
// Path params  need to put into request with in curlly bracess		
	
	@Test
	void testQueryAndPathParams() {
		given()
			.pathParam("mypath", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		.when()
		.get("https://reqres.in/api/{mypath}")
		.then()
		.statusCode(200)
		.log().all();
		
	}
}
