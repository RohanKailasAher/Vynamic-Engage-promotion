package AuthorizationToken;

import POJO.ChildGroup;
import POJO.Location;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class AccessToken {

    public static void main(String[] args) {
        // Set the base URI
        RestAssured.baseURI = "https://engage-lulugcc-uat5-sin.ve.vaas-uat.rtl.ap.dieboldnixdorf.com";

// Define the request body for obtaining the access token
        String requestBody = "grant_type=client_credentials&scope=DN_FULL_API";

// Send the POST request to obtain the access token
        String creatingAccessToken = given()
                .log().all()
                .header("Authorization", "Basic TE9ZQUxUSUNTOjgzRTgwQUYzLTFGNDEtNDQ1QS1CNkIwLTZBOEMwMUQyMEU2OA==")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(requestBody)
                .when()
                .post("/identity/connect/token").asString();
        //System.out.println(creatingAccessToken);

// Extract the access token from the response
        JsonPath jsonPath = new JsonPath(creatingAccessToken);
        String accessToken = jsonPath.getString("access_token");
        System.out.println("accessToken :" + accessToken);
// Use the access token to access the resource
        String campaign = given()
                .header("Authorization", "Bearer " + accessToken)
                .when().log().all()
                .get("/marketing/api/v1/campaigns/201").asString();

        // System.out.println(campaign);
        JsonPath jsonPath1 = new JsonPath(campaign);
        String campaignID = jsonPath1.getString("CampaignId");
        System.out.println("campaignID : " + campaignID);


        String Offer = given()
                .header("Authorization", "Bearer " + accessToken)
                .when().log().all()
                .get("marketing/api/v2/offers/1").asString();
        JsonPath jsonPath2 = new JsonPath(Offer);
        String OfferID = jsonPath2.getString("OfferId");
        System.out.println("OfferID : " + OfferID);

//        String  ListOfLocation = given()
//                 .header("Authorization", "Bearer " + accessToken)
//                .when().log().all()
//                .get("location/api/v1/LocationGroups").asString();
//        System.out.println(ListOfLocation);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .when()
                .log()
                .all()
                .get("location/api/v1/LocationGroups");

        // Extract the list of ChildGroup objects from the response using JsonPath
        List<ChildGroup> childGroups = response.jsonPath().getList("$", ChildGroup.class);


        // Iterate over the list of ChildGroup objects using a traditional for loop
        for (int i = 0; i < childGroups.size(); i++) {
            ChildGroup childGroup = childGroups.get(i);
            // Fetch the Location information for each ChildGroup
            Location location = childGroup.getLocation();

            // Perform actions with the Location object
            if (location != null) {
                System.out.println("Location ID: " + location.getId());
                System.out.println("Location Address: " + location.getAddress());
                System.out.println("Location City: " + location.getCity());


//        String Coupon  = given()
//                .header("Authorization", "Bearer " + accessToken)
//                .when().log().all()
//                .get("ccvm/api/v1/coupons/templates/130").asString();
//        System.out.println(Coupon);

            }
        }
    }
}