package com.api.automation.utils;

import com.api.automation.pojo.AddPlaceRequest;
import com.api.automation.pojo.Location;
import java.util.Arrays;

public class PayloadBuilder {

    public static AddPlaceRequest addPlace(String name, String language, String address,
                                           String website, double lat, double lng) {

        AddPlaceRequest req = new AddPlaceRequest();
        req.setAccuracy(50);
        req.setName(name);
        req.setPhone_number("(+91) 983 893 3937");
        req.setAddress(address);
        req.setWebsite(website);
        req.setLanguage(language);
        req.setTypes(Arrays.asList("shoe park","shop"));

        Location loc = new Location();
        req.setLocation(loc);

        return req;
    }
}
