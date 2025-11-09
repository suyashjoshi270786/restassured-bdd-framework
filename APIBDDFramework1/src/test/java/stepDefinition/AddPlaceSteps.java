package stepDefinition;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.api.automation.utils.PayloadBuilder;   // ✅ new package
import com.api.automation.utils.SpecFactory;      // ✅ new package
import com.api.automation.pojo.AddPlaceRequest;
import com.api.automation.pojo.AddPlaceResponse;
import resourcesEnum.APIResources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaceSteps {

    private RequestSpecification reqSpec;
    private Response response;
    private static String placeId; // shared across steps within scenario

    @Given("Add Place payload with {string} {string} {string} {string} {double} {double}")
    public void add_place_payload(String name, String language, String address,
                                  String website, double lat, double lng) {

        AddPlaceRequest body = PayloadBuilder.addPlace(name, language, address, website, lat, lng);
        reqSpec = given().spec(SpecFactory.base()).body(body);
    }

    @When("user calls AddPlace API")
    public void user_calls_addplace_api() {
        response = reqSpec.when().post(APIResources.AddPlace.path());
    }

    @Then("API call succeeded with status {int}")
    public void api_call_succeeded_with_status(Integer code) {
        response.then().statusCode(code);
    }

    @And("{string} in AddPlace response is {string}")
    public void key_in_addplace_response_is(String key, String expected) {
        response.then().body(key, equalTo(expected));
    }

    @And("store place_id")
    public void store_place_id() {
        AddPlaceResponse resp = response.then().extract().as(AddPlaceResponse.class);
        placeId = resp.getPlace_id();
    }

    @When("user calls GetPlace API")
    public void user_calls_getplace_api() {
        response =
                given().spec(SpecFactory.base())
                        .queryParam("place_id", placeId)
                        .when().get(APIResources.GetPlace.path());
    }

    @Then("place name is {string}")
    public void place_name_is(String expectedName) {
        response.then().spec(SpecFactory.okJson()).body("name", equalTo(expectedName));
        // Optionally deserialize:
        // GetPlaceResponse get = response.as(GetPlaceResponse.class);
        // assert get.getName().equals(expectedName);
    }

    @When("user calls DeletePlace API")
    public void user_calls_deleteplace_api() {
        String deletePayload = String.format("{\"place_id\":\"%s\"}", placeId);
        response = given().spec(SpecFactory.base()).body(deletePayload)
                .when().post(APIResources.DeletePlace.path());
    }
}
