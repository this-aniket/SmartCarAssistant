package com.smartcarassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.smartcarassistant.Model.Photos;
import com.smartcarassistant.Model.PlaceDetail;
import com.smartcarassistant.Remote.IGoogleAPIService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPlace extends AppCompatActivity {

    ImageView photo;
    RatingBar ratingBar;
    TextView open_hours,place_address,place_name;

    IGoogleAPIService mService;

    PlaceDetail mPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        mService=Common.getGoogleAPIService();
        photo = (ImageView) findViewById(R.id.explorePhoto);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        open_hours = (TextView)findViewById(R.id.openHours);
        place_address = (TextView) findViewById(R.id.placeAddress);
        place_name = (TextView) findViewById(R.id.placeName);

        place_address.setText("");
        place_address.setText("");
        open_hours.setText("");

        if(Common.currentResult.getPhotos() != null && Common.currentResult.getPhotos().length > 0)
        {
            Picasso.with(this)
                .load(getphotoOfPlace(Common.currentResult.getPhotos()[0].getPhoto_reference(),1000))
                //.placeholder(R.drawable.svg_image)
                //.error(R.drawable.svg_error)
                .into(photo);
        }

        if(Common.currentResult.getRating() != null && !TextUtils.isEmpty(Common.currentResult.getRating()))
        {
            ratingBar.setRating(Float.parseFloat(Common.currentResult.getRating()));
        }
        else
        {
            ratingBar.setVisibility(View.GONE);
        }

        if(Common.currentResult.getOpening_hours() != null )
        {
            open_hours.setText("Open Now : "+Common.currentResult.getOpening_hours().getOpen_now());
        }
        else
        {
            open_hours.setVisibility(View.GONE);
        }

        mService.getdetailPlace((getPlaceDeatilUrl(Common.currentResult.getPlace_id())))
                .enqueue(new Callback<PlaceDetail>() {
                    @Override
                    public void onResponse(Call<PlaceDetail> call, Response<PlaceDetail> response) {

                        mPlace=response.body();
                        place_address.setText(mPlace.getResult().getFormatted_address());
                        place_name.setText(mPlace.getResult().getName());
                        
                    }

                    @Override
                    public void onFailure(Call<PlaceDetail> call, Throwable t) {

                    }
                });
    }catch (Exception e){
        FileLog.e(e);

    }
    }

    private String getPlaceDeatilUrl(String place_id) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?placeid="+place_id);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }

    private String getphotoOfPlace(String photo_reference,int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photo_reference);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }
}
