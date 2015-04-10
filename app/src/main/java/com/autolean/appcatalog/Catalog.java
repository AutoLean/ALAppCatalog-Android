package com.autolean.appcatalog;

import android.content.Context;
import android.content.Intent;
import com.autolean.appcatalog.view.CatalogActivity;

/**
 * Created by AKiniyalocts on 4/10/15.
 */
public class Catalog {

  public static void start(Context context){
    context.startActivity(new Intent(context, CatalogActivity.class));
  }

}
