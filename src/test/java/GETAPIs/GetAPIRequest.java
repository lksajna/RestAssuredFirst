package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//Non BDD Approach
public class GetAPIRequest {
	RequestSpecification request;
@BeforeTest
public void setup() {
	RestAssured.baseURI = "https://gorest.co.in/";
	request = RestAssured.given();
	request.header("Authorization","Bearer 835ea27f8cda6fa2cfd8f15c5869d89810ecdaeec8a565e17c1c87004a77d2dc");
	
}
@Test
public void getAllUserAPITest(){

	Response response=request.get("public/v2/users/");
	
	int statusCode=response.statusCode();
	System.out.println("Status Code ---> "+statusCode);
	
	//Verification point
	Assert.assertEquals(statusCode, 200);
	
	//StatusMessage
	String statusLine = response.statusLine();
	System.out.println("Status Code ---> "+statusLine);
	
	//fetch Body
	response.prettyPrint();
	
	//fetch headers
	String contentType=response.header("Content-Type");
	System.out.println(contentType);
	
	
	//fetch all headers
		List<Header> headerList=response.headers().asList();
		System.out.println(headerList.size());
		
	//Print all headers
		for(Header h : headerList) {
			System.out.println(h.getName()+" : "+h.getValue());
		}
		
	
	
	
}
@Test
public void getAllUserAPITestWithQueryParameters(){
	
	request.queryParams("name","naveen");
	Response response=request.get("public/v2/users/");
	
	int statusCode=response.statusCode();
	System.out.println("Status Code ---> "+statusCode);
	
	//Verficication point
	Assert.assertEquals(statusCode, 200);
	
	//StatusMessage
	String statusLine = response.statusLine();
	System.out.println("Status Code ---> "+statusLine);
	
	//fetch Body
	response.prettyPrint();
}
	
	@Test
	public void getAllUserAPITestWithQueryParametersUsingHashMap(){
		Map<String,String> queryParamsMap = new HashMap<String,String>();
		queryParamsMap.put("name", "naveen");
		queryParamsMap.put("gender", "female");
		request.queryParams(queryParamsMap);	
		Response response=request.get("public/v2/users/");
		
		int statusCode=response.statusCode();
		System.out.println("Status Code ---> "+statusCode);
		
		//Verficication point
		Assert.assertEquals(statusCode, 200);
		
		//StatusMessage
		String statusLine = response.statusLine();
		System.out.println("Status Code ---> "+statusLine);
		
		//fetch Body
		response.prettyPrint();

}
}
