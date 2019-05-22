package com.smartcarassistant;

import com.smartcarassistant.Model.Results;
import com.smartcarassistant.Model.myPlaces;
import com.smartcarassistant.Remote.IGoogleAPIService;
import com.smartcarassistant.Remote.RetrofitClient;

/**
 * Created by Aniket on 16-Mar-18.
 */

public class Common {

    public static Results currentResult;
    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";
    public static IGoogleAPIService getGoogleAPIService(){
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
