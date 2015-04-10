package com.autolean.appcatalog;

import android.util.Log;

/**
 * Created by AKiniyalocts on 4/6/15.
 */
public class aLog {

  private static boolean isLogging = true;

  public static void w(String TAG, String msg){
    if(TAG != null && msg != null && isLogging){
      Log.w(TAG, msg);
    }
  }
}
