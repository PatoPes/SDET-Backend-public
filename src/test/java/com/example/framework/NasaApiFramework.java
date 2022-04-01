package com.example.framework;

import com.example.utils.TestProperties;
import com.example.utils.Utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class NasaApiFramework {
    private static String BASE_URL = TestProperties.getInstance().getString("mars.photos.base.url");
    private static String API_KEY = TestProperties.getInstance().getString("mars.photos.api.key");

    private RequestSpecification queryingNasaApi(String camera){
        return RestAssured.given().baseUri(BASE_URL)
                .queryParam("api_key", API_KEY)
                .queryParam("camera", camera);
    }

    public Response queryingBySol(int sol, String camera){
        return queryingNasaApi(camera)
                .queryParam("sol", sol)
                .get();
    }

    public Response queryingByEarthDate(int sol, String camera){
        String date = Utils.convertSolToDate(sol);
        return queryingNasaApi(camera)
                .queryParam("earth_date", date)
                .get();
    }

    public List getImagesFromResponse(Response res, int elements){
        List list = res.jsonPath().getList("photos");
        if(elements < list.size())
            return list.subList(0, elements);
        return null;
    }
}
