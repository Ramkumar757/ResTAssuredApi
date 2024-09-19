package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {

	int id;

	@Test(priority = 1)
	public void getUser() {
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log()
				.all();
	}

	@Test(priority = 2)
	public void createUser() {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("name", "ram");
		hm.put("job", "Learner");
		id = given().contentType("application/json").body(hm).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

		System.out.println(id);
		// .then().statusCode(201).log().all();
	}

	@Test(priority = 3, dependsOnMethods = { "createUser" })
	public void updateUser() {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("name", "ram");
		hm.put("job", "javaTester");
		given().contentType("application/json").body(hm).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
	}

	@Test(priority = 4)
	public void DeleteUser() {
		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}
}
