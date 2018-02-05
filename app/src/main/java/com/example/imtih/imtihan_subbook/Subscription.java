
/*
 * Copyright (c) University of Alberta -All Rights Reserved.
 */

package com.example.imtih.imtihan_subbook;

import java.util.Date;

/**
 *
 * The class Subscription holds the information for a subscription
 * @version 1.0
 * @author imtihan
 *
 * Each subscription is stored in a Subscription class object.
 * In keeping with OO principles, each Subscription object contains
 * the values required for a subscription.
 */

public class Subscription {

    private String name;
    private String date;
    private float charge;
    private String comment;

    Subscription(){

    }

    Subscription(String name, String date, float charge, String comment){
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getCharge() {
        return String.valueOf(charge);
    }

    public String getComment() {
        return comment;
    }

    /**
     * Overrides the toString method. This is required to use GSON to store the object
     * and retrieve the elements after
     * @return
     */

    @Override
    public String toString() {
        return "Name: " + name + "\nDate: " + date + "\nMonthly Cost(CAD): " +String.valueOf(charge) + "\n" + comment;
    }
}
