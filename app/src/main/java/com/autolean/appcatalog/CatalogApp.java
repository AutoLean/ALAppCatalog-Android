package com.autolean.appcatalog;

import android.app.Application;
import android.content.Context;

import com.autolean.appcatalog.data.apps.AppCatalogApp;
import com.autolean.appcatalog.data.social.AppCatalogSocialMediaOutlet;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.PushService;
import com.uservoice.uservoicesdk.Config;
import com.uservoice.uservoicesdk.UserVoice;

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
        initUserVoice();
    }

    public abstract Context getContext();
    public abstract String getApplicationId();
    public abstract String getClientId();
    public abstract Class getDefaultCallback();
    public abstract String getUserVoiceWebsite();
    public abstract int getUserVoiceForumId();

    public void initParse(){
        Parse.enableLocalDatastore(getContext());
        ParseCrashReporting.enable(getContext());

        ParseObject.registerSubclass(AppCatalogApp.class);
        ParseObject.registerSubclass(AppCatalogSocialMediaOutlet.class);

        Parse.initialize(getContext(), getApplicationId(), getClientId());
        PushService.setDefaultPushCallback(getContext(), getDefaultCallback());
    }

    private void initUserVoice(){
        if(getUserVoiceWebsite() != null) {
            Config config = new Config(getUserVoiceWebsite());

            if(getUserVoiceForumId() != 0){
                config.setForumId(getUserVoiceForumId());
            }

            UserVoice.init(config, this);
        }
    }
}
