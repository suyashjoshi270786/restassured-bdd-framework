Feature: Place APIs with POJOs

  Scenario: Create, verify, and delete a place
    Given Add Place payload with "Frontline house" "French-IN" "29, side layout, cohen 09" "http://google.com" 29.123 31.555
    When user calls AddPlace API
    Then API call succeeded with status 200
    And "scope" in AddPlace response is "APP"
    And store place_id
    When user calls GetPlace API
    Then place name is "Frontline house"
    When user calls DeletePlace API
    Then API call succeeded with status 200
