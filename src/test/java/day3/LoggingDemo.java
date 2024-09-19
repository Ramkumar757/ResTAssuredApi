package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	@Test
	void testLogsDemo() {
		given()
		
		.when()
			.get("https://reqres.in/api/users/1")
		.then()
		//.log().body();
		//.log().headers();
		//.log().cookies();
		.log().all();
		
	}

}
