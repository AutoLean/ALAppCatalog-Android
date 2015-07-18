package com.autolean.appcatalog;

import android.app.Application;
import android.content.Context;

import com.autolean.appcatalog.data.apps.AppCatalogApp;
import com.autolean.appcatalog.data.social.AppCatalogSocialMediaOutlet;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.PushService;

/**
 * Created by anthony on 7/17/15.
 *
 * Extend your Application class from this class whenever Parse, or the AppCatalog
 * is needed in your application.
 *
 */
public abstract class CatalogApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initParse();
    }

    public abstract Context getContext();
    public abstract String getApplicationId();
    public abstract String getClientId();
    public abstract Class getDefaultCallback();

    public void initParse(){
        Parse.enableLocalDatastore(getContext());
        ParseCrashReporting.enable(getContext());

        ParseObject.registerSubclass(AppCatalogApp.class);
        ParseObject.registerSubclass(AppCatalogSocialMediaOutlet.class);

        Parse.initialize(getContext(), getApplicationId(), getClientId());
        PushService.setDefaultPushCallback(getContext(), getDefaultCallback());
    }
}
