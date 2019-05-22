package com.smartcarassistant.Remote;

import com.smartcarassistant.Model.PlaceDetail;
import com.smartcarassistant.Model.myPlaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Aniket on 16-Mar-18.
 */

public interface IGoogleAPIService {
    @GET
    Call<myPlaces> getNearbyPlaces(@Url String url);
    @GET
    Call<PlaceDetail> getdetailPlace(@Url String url);
}