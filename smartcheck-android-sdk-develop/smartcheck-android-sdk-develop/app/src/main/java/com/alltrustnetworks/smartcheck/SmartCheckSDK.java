package com.alltrustnetworks.smartcheck;

import com.alltrustnetworks.smartcheck.BuildConfig;
import com.alltrustnetworks.smartcheck.MisnapApi;
import com.alltrustnetworks.smartcheck.SmartCheckApi;

public class SmartCheckSDK {

    /**
     * set mode is debug or release to get api
     */
    public static void getInstance(){
        new SmartCheckApi(BuildConfig.API_URL, BuildConfig.API_TOKEN, BuildConfig.BUSINESS_ID, BuildConfig.LOCATION_ID,BuildConfig.API_URL_CONSUMER);
        new MisnapApi(BuildConfig.misnapUrl, BuildConfig.misnapUsername, BuildConfig.misnapPassword);

    }
}
