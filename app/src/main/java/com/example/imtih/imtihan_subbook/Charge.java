package com.example.imtih.imtihan_subbook;

/**
 * Created by imtih on 2018-02-03.
 */

public class Charge {

    private float charge;

    Charge(){
        charge = 0.00f;
    }

    Charge(float value){
        charge = value;
    }

    public float getCharge(){
        return charge;
    }

    public void setCharge(float value){
        charge = value;
    }
}
