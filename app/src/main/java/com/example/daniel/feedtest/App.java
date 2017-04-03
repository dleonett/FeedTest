package com.example.daniel.feedtest;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Daniel Leonett (d@hustling.me) on 30/3/2017.
 * Copyright (c) 2017 Hustling. All rights reserved.
 */
public class App extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Poppins-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
