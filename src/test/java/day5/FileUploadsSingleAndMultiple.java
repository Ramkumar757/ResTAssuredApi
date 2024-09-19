package day5;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadsSingleAndMultiple {
	
	@Test
	void singleFileUpload() {
		
		File file= new File("C:\\API Testing\\jsonfiles\\POSTMAN.txt");
		given()
		.multiPart(file)
		.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadFile")
		
		.then()
		.statusCode(200)
		.log().all()
		;
	}

	@Test
	void multipleFilesUpload() {
		
		File file= new File("C:\\API Testing\\jsonfiles\\POSTMAN.txt");
		File file2= new File("C:\\API Testing\\jsonfiles\\jsonVSxml.png");
		given()
		.multiPart("file",file)
		.multiPart("file",file2)
		.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadMultipleFiles")
		
		.then()
		.statusCode(200)
		.log().body();
	}
}
