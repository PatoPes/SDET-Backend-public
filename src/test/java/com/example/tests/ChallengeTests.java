package com.example.tests;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChallengeTests extends BaseTest{
    private static int martianSol = 1000;
    private static int amountOfImages = 10;
    private static int imgsMultiplier = 10;

    @Test
    @Description("Retrieve and compare the first 10 Mars photos made by \"Curiosity\" " +
            "on 1000 sol and on Earth date equal to 1000 Martian sol.")
    public void retrieveAndCompareImages(){
        Response solarImagesResponse = nasaApiFramework.queryingBySol(martianSol, null);
        requestSuccess(solarImagesResponse, "Failure when requesting images by Solar Date");
        Response earthImagesResponse = nasaApiFramework.queryingByEarthDate(martianSol, null);
        requestSuccess(earthImagesResponse,"Failure when requesting images by Earth Date");
        List solarImages = nasaApiFramework.getImagesFromResponse(solarImagesResponse, amountOfImages);
        List earthImages = nasaApiFramework.getImagesFromResponse(earthImagesResponse, amountOfImages);
        Assert.assertTrue(solarImages.equals(earthImages));
    }

    @Test
    @Description("Validate that the amounts of pictures that each \"Curiosity\" camera took on 1000 Mars sol" +
            " is not greater than 10 times the amount taken by other cameras on the same date.")
    public void validateAmountOfPictures(){
        HashMap<String, Integer> imgs = getImagesPerCamera();
        SoftAssert softAssertion = new SoftAssert();
        for(int i=0; i< cameras.length; i++){
            for(Map.Entry<String, Integer> entry : imgs.entrySet()){
                if(cameras[i] != entry.getKey()){
                    softAssertion.assertTrue(imgs.get(cameras[i])/10 <= entry.getValue(),
                            "Failure! Camera: " + cameras[i] + " has: "  +  imgs.get(cameras[i]) +
                                    " images ,10 times more images than camera: " + entry.getKey() + "which has: " + entry.getValue());
                }
            }
        }
        softAssertion.assertAll();
    }


    private HashMap<String, Integer> getImagesPerCamera(){
        HashMap<String, Integer> imagesPerCamera = new HashMap<>();
        Response response;
        for(int i=0; i<cameras.length; i++){
            response = nasaApiFramework.queryingBySol(martianSol, cameras[i]);
            requestSuccess(response, "Failure when requesting images for camera: " + cameras[i]);
            imagesPerCamera.put(cameras[i], response.jsonPath().getList("photos").size());
        }
        return imagesPerCamera;
    }

}
