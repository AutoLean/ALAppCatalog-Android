package com.autolean.appcatalogtest;

import android.content.Context;

import com.autolean.appcatalog.CatalogApp;

/**
 * Created by AKiniyalocts on 4/11/15.
 */
public class TestActivityApplication extends CatalogApp {

  private static String PARSE_APPLICATION_ID = "";
  private static String PARSE_CLIENT_KEY = "";

  @Override
  public Context getContext() {
    return getApplicationContext();
  }

  @Override
  public String getApplicationId() {
    return PARSE_APPLICATION_ID;
  }

  @Override
  public String getClientId() {
    return PARSE_CLIENT_KEY;
  }

  @Override
  public Class getDefaultCallback() {
    return TestActivity.class;
  }

  @Override
  public String getUserVoiceWebsite() {
    return null;
  }

  @Override
  public int getUserVoiceForumId() {
    return 0;
  }
}
