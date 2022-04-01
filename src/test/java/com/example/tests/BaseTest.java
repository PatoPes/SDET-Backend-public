package com.example.tests;

import com.example.framework.NasaApiFramework;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected NasaApiFramework nasaApiFramework;
    protected String[] cameras;

    @BeforeClass
    public void setup() {
        nasaApiFramework = new NasaApiFramework();
        cameras = new String[]{"fhaz", "rhaz", "mast", "chemcam", "mahli", "mardi", "navcam", "pancam", "minites"};
    }

    public void requestSuccess(Response res, String msg){
        Assert.assertEquals(res.statusCode(), HttpStatus.SC_OK, msg);
    }
}
