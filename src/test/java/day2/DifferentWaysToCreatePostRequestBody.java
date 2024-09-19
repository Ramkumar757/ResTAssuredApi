package day2;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


/*we can create the request body in 4 ways==>
 1.HashMap
 2.JSONObject
 3.POJO
 4.External JsonFile */

public class DifferentWaysToCreatePostRequestBody {
	
	//@Test(priority = 1)
	public void testPostUsingHashMap() {
		String[] coursesArr= {"java","sql"};
		HashMap data = new HashMap();
		data.put("name", "deepali");
		data.put("location", "india");
		data.put("phone", "8277906001");
		data.put("courses", coursesArr);
		
		given()
		.contentType("Application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("deepali"))
		.body("location", equalTo("india"))
		.body("phone", equalTo("8277906001"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("sql"))
		.header("Content-Type","application/json")
	;
		
	}
	//@Test(priority = 1)
	public void testPostUsingJsonLibrary() {
		String[] coursesArr= {"java","sql"};
		JSONObject data = new JSONObject();
		data.put("name", "deepali");
		data.put("location", "india");
		data.put("phone", "8277906001");
		data.put("courses", coursesArr);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("deepali"))
		.body("location", equalTo("india"))
		.body("phone", equalTo("8277906001"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("sql"))
		.header("Content-Type","application/json")
	;
		
	}
	//@Test(priority = 1)
	public void testPostUsingPojoClass() {
		String[] coursesArr= {"java","sql"};
		StudentPojo data = new StudentPojo();
		data.setName("deepali");
		data.setLocation("india");
		data.setPhone("8277906001");
		data.setCourses(coursesArr);
		
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("deepali"))
		.body("location", equalTo("india"))
		.body("phone", equalTo("8277906001"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("sql"))
		.header("Content-Type","application/json")
	;
		
	}
	@Test(priority = 1)
	public void testPostUsingExternalJsonFile() throws FileNotFoundException {
		
		File f= new File(".\\src\\test\\resources\\Body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.body("name", equalTo("deepali"))
		.body("location", equalTo("india"))
		.body("phone", equalTo("8277906001"))
		.body("courses[0]", equalTo("java"))
		.body("courses[1]", equalTo("sql"))
		.header("Content-Type","application/json")
	;
		
	}

	@Test(priority = 2)
	public void deleteUser() {
		given()
		.when()
		.delete("http://localhost:3000/students/9323")
		.then()
		.statusCode(200);
	}

}
