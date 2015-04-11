package com.autolean.appcatalogtest;

import android.app.Application;
import com.autolean.appcatalog.data.apps.AppCatalogApp;
import com.autolean.appcatalog.data.social.AppCatalogSocialMediaOutlet;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by AKiniyalocts on 4/11/15.
 */
public class TestActivityApplication extends Application {

  @Override public void onCreate() {
    super.onCreate();

    initParse();
  }

  private void initParse(){
    Parse.enableLocalDatastore(this);
    ParseObject.registerSubclass(AppCatalogApp.class);
    ParseObject.registerSubclass(AppCatalogSocialMediaOutlet.class);

    /*
      Change to your appropriate key for Parse
     */
    Parse.initialize(this, "YXWlT6b00FDzu0Vt23247ttEVjmdjhnLK8NowLrY",
        "rMjgV5pT6Qd3kvLzk5TZWH2nyAwX8lTTR5BDjiKV");

  }
}
