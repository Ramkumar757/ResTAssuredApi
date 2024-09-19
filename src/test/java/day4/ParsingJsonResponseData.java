package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJsonResponseData {
	@Test
	public void testResponseData() {

		// Approach 1

		/*
		 * given()
		 * 
		 * .when() .get("http://localhost:3000/bookStore")
		 * 
		 * .then() .body("book[3].title",equalTo("The Lord of the Rings")) .log().all()
		 * ;
		 * 
		 * 
		 * Response res = given()
		 * 
		 * .when().get("http://localhost:3000/bookStore");
		 * 
		 * Assert.assertEquals(res.header("Content-Type"), "application/json");
		 * Assert.assertEquals(res.getStatusCode(), 200); String bookName =
		 * res.jsonPath().get("book[3].title").toString(); Assert.assertEquals(bookName,
		 * "The Lord of the Rings");
		 */

		Response res = given().contentType(ContentType.JSON).when().get("http://localhost:3000/bookStore");

		JSONObject js = new JSONObject(res.asString());
		/*
		 * for(int i=0 ; i<=js.getJSONArray("book").length()-1;i++) { String book_Title
		 * = js.getJSONArray("book").getJSONObject(i).get("title").toString();
		 * System.out.println(book_Title);
		 * 
		 * }
		 */

		boolean status = false;
		for (int i = 0; i <= js.getJSONArray("book").length() - 1; i++) {
			String book_Title = js.getJSONArray("book").getJSONObject(i).get("title").toString();
			if (book_Title.equals("The Lord of the Rings")) {
				status = true;
				break;
			}

		}
		Assert.assertEquals(status, true);
		double totalPrice = 0;
		for (int i = 0; i <= js.getJSONArray("book").length() - 1; i++) {
			String book_price = js.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalPrice = totalPrice + Double.parseDouble(book_price);
		}
		Assert.assertEquals(totalPrice, 526.5);

	}
}
