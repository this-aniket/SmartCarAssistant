package com.smartcarassistant;

/**
 * Created by lenovo on 26-02-2018.
 */

public class Monthly_Expenses {
    String Petrol,Washing,Tyre,Parking,Total;

    public Monthly_Expenses(String petrol, String washing, String tyre, String parking, String total) {
        Petrol = petrol;
        Washing = washing;
        Tyre = tyre;
        Parking = parking;
        Total = total;
    }

    public String getPetrol() {
        return Petrol;
    }

    public String getWashing() {
        return Washing;
    }

    public String getTyre() {
        return Tyre;
    }

    public String getParking() {
        return Parking;
    }

    public String getTotal() {
        return Total;
    }
}
