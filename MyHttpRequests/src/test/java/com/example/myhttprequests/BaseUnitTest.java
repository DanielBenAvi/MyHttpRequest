package com.example.myhttprequests;

import com.example.myhttprequests.Interfaces.HttpCallback;
import com.example.myhttprequests.model.MyResponse;
import com.example.myhttprequests.rest.MyHttpGet;
import com.example.myhttprequests.rest.MyHttpPost;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class BaseUnitTest {
    private static MyHttpGet myHttpGet;
    private static MyHttpPost myHttpPost;
    private static String baseUrl = "http://example.com";
    private static ArrayList<TestObject> testObjects;


    @BeforeClass
    public static void setUp() {
        System.out.println("Setting up tests");
        myHttpGet = Mockito.mock(MyHttpGet.class);
        myHttpPost = Mockito.mock(MyHttpPost.class);
    }
    
    @Before
    public void beforeEachTest() {
        System.out.println("Running test");
        testObjects = new ArrayList<>();
    }

    /**
     * Test 1
     * This test is used to test the post request method
     *
     */
    @Test
    public void test1() {
        final String[] data = {""};

        Mockito.doNothing().when(myHttpPost).postRequest(baseUrl, "data", new ArrayList<>(), new HttpCallback() {
            @Override
            public void onError(String error) {
                data[0] = error;
            }

            @Override
            public void onSuccess(MyResponse response) {
                data[0] = response.getResponseBody();
            }
        });




    }




}