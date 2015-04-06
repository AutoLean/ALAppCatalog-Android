package com.autolean.appcatalog;

import android.util.Log;

/**
 * Created by AKiniyalocts on 4/6/15.
 */
public class aLog {

  public static void w(String TAG, String msg){
    if(TAG != null && msg != null && AppCatalogApplication.isLogging()){
      Log.w(TAG, msg);
    }
  }
}
