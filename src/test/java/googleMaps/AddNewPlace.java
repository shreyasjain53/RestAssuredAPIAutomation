package googleMaps;

import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctions.Base;
import groovy.json.JsonParser;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class AddNewPlace extends Base{
	
	Properties data = loadProperties(AddNewPlace.class.getPackageName());
	
	
	@Test
	public void addNewPlace() throws ParseException {
		//given
		//when
		//then
		RestAssured.baseURI = data.getProperty("baseURI");
		
		String jsonBody = """
				{
					  "location": {
						    "lat": -38.383494,
						    "lng": 33.427362
						  },
						  "accuracy": 50,
						  "name": "Frontline house",
						  "phone_number": "(+91) 983 893 3937",
						  "address": "29, side layout, cohen 09",
						  "types": [
						    "shoe park","shop"
						  ],
						  "website": "http://google.com",
						  "language": "French-IN"
						}
				""";
		
		String response  = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(jsonBody).when().post(data.getProperty("resourcesURI")).asPrettyString();
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response);
		
		System.out.println("JSON response  = \n"+ response);
		
		
		String placeID = (String) jsonObject.get("place_id");
		
		System.out.println("Place ID ========== "+ placeID);
	}

}
