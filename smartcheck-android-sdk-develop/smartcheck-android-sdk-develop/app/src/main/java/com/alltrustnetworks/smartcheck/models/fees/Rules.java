package com.alltrustnetworks.smartcheck.models.fees;


import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Rules {
    public @Json(name = "existing_customer")ExistingRule existingCustomer;
    public @Json(name = "existing_maker") ExistingRule existingMaker;
    public @Json(name = "new_customer") NewRule newCustomer;
    public @Json(name = "new_maker") NewRule newMaker;

    public Rules() {
    }

    public Rules(ExistingRule existingCustomer, ExistingRule existingMaker, NewRule newCustomer, NewRule newMaker) {
        this.existingCustomer = existingCustomer;
        this.existingMaker = existingMaker;
        this.newCustomer = newCustomer;
        this.newMaker = newMaker;
    }

    public ExistingRule getExistingCustomer() {
        return existingCustomer;
    }

    public void setExistingCustomer(ExistingRule existingCustomer) {
        this.existingCustomer = existingCustomer;
    }

    public ExistingRule getExistingMaker() {
        return existingMaker;
    }

    public void setExistingMaker(ExistingRule existingMaker) {
        this.existingMaker = existingMaker;
    }

    public NewRule getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(NewRule newCustomer) {
        this.newCustomer = newCustomer;
    }

    public NewRule getNewMaker() {
        return newMaker;
    }

    public void setNewMaker(NewRule newMaker) {
        this.newMaker = newMaker;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "existingCustomer=" + existingCustomer +
                ", existingMaker=" + existingMaker +
                ", newCustomer=" + newCustomer +
                ", newMaker=" + newMaker +
                '}';
    }
}
