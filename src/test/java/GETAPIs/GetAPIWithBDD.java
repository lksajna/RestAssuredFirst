package GETAPIs;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class GetAPIWithBDD {

		@Test
		public void getProductsTest(){
			given().log().all()
				.when().log().all()
					.get("https://fakestoreapi.com/products/")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.header("Connection", "keep-alive")
												.and()
													.body("$.size()",equalTo(20))
														.and()
															.body("id",is(notNullValue()))
																.and()
																	.body("title",hasItem("Mens Cotton Jacket"));
			
		}
		@Test
		public void getUserAPITest(){
			RestAssured.baseURI="https://gorest.co.in";
			given().log().all()
				.when().log().all()
					.get("/public/v2/posts")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.body("$.size()", equalTo(10));	
		}
		@Test
		public void getProductDataAPIWithoutQueryParamTest() {
		RestAssured.baseURI="https://fakestoreapi.com";
		given().log().all()
			.queryParams("limit",5)
			//.queryParams("name","naveen")
				.when().log().all()
					.get("/products")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.contentType(ContentType.JSON);	
		}
		@Test
		//Assignment
		public void getAllUserAPITestWithQueryParameters() {
			RestAssured.baseURI = "https://gorest.co.in"; 
			given().log().all()
				.queryParams("name","naveen")
				.queryParams("status","active")
					.when().log().all()
						.get("public/v2/users")
							.then().log().all()
								.assertThat()
									.statusCode(200)
										.and()
											.contentType(ContentType.JSON);
		}
		
		@Test
		public void getProductDataAPI_With_ExtractBody(){
		RestAssured.baseURI="https://fakestoreapi.com";
		Response response = given().log().all()
			.queryParams("limit",5)
				.when().log().all()
					.get("/products");
		JsonPath js=response.jsonPath();	
		int firstProductId = js.getInt("[0].id");
		System.out.println("First Product ID ----> "+firstProductId);
		
		String firstProductTitle = js.getString("[0].title");
		System.out.println("First Product Title ----> "+firstProductTitle);
		
		float firstProductPrice = js.getFloat("[0].price");
		System.out.println("First Product Price ----> "+firstProductPrice);
		
		int firstProductCount = js.getInt("[0].rating.count");
		System.out.println("First Product Count ----> "+firstProductCount);
		
		}
		
		@Test
		public void getProductDataAPI_With_ExtractBody_with_Array(){
		RestAssured.baseURI="https://fakestoreapi.com";
		Response response = given().log().all()
			.queryParams("limit",10)
				.when().log().all()
					.get("/products");
		JsonPath js=response.jsonPath();	
		List<Integer> idList = js.getList("id");
		List<String> TitleList = js.getList("title");
		//List<Float> rateList = js.getList("rating.rate");
		//List<Object> rateList = js.getList("rating.rate");
		List<Float> rateList = js.getList("rating.rate",Float.class);
		List<Integer> countList = js.getList("rating.count");
		//System.out.println(countList);
			for(int i=0;i<idList.size();i++) {
				int id = idList.get(i);
				String title = TitleList.get(i);
				Object rate = rateList.get(i);
				int count = countList.get(i);
			
				System.out.println("ID---> "+id+"   "+"Title---->"+title+"    Rate---->"+rate+"     Count--->"+count);
			}
		}
		
	}

