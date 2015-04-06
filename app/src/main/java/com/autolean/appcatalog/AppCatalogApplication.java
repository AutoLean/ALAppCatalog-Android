package com.autolean.appcatalog;

import android.app.Application;
import android.content.Context;
import com.autolean.appcatalog.data.apps.AppCatalogApp;
import com.autolean.appcatalog.data.social.AppCatalogSocialMediaOutlet;
import com.parse.Parse;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;
import com.uservoice.uservoicesdk.Config;
import com.uservoice.uservoicesdk.UserVoice;

/**
 * Created by AKiniyalocts on 4/3/15.
 */
public class AppCatalogApplication extends Application {


  public static Context context;

  public static Context getContext(){
    return context;
  }


  public static Picasso picasso;

  public static Picasso getPicasso(){
    if(picasso != null)
      return picasso;

    else {
      return Picasso.with(getContext());
    }
  }


  @Override public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
    initParse();
    initUserVoice();
  }

  public static boolean isTablet(){
    return getContext().getResources().getBoolean(R.bool.isTablet);
  }

  public static boolean isLandscape(){
    return getContext().getResources().getBoolean(R.bool.isLandscape);
  }

  public static boolean isLogging(){
    return getContext().getResources().getBoolean(R.bool.logging);
  }

  //TODO: Make string resources
  private void initParse(){
    Parse.enableLocalDatastore(this);
    ParseObject.registerSubclass(AppCatalogApp.class);
    ParseObject.registerSubclass(AppCatalogSocialMediaOutlet.class);
    Parse.initialize(this, "eQYNqF66Ix7pBClatFHWU2BkQfjmtBHsuSCiqIwj", "MBc7OEZLhSMCseZ0I07bUhZuoWCPzQxJaewS34KP");
  }

  private void initUserVoice(){
    Config config = new Config("autolean.uservoice.com");
    UserVoice.init(config, this);
  }



}

