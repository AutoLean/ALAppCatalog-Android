package com.autolean.appcatalog.data.apps;

import android.app.Activity;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

/**
 * Return object for AppCatalogApp
 *
 *
 * Created by AKiniyalocts on 4/3/15.
 */

@ParseClassName("AppCatalogApp")
public class AppCatalogApp extends ParseObject {


  public AppCatalogApp(){}

  /**
   * Creating an empty App with only a header paramter
   * @param isHeader
   */
  public AppCatalogApp(boolean isHeader){
    this.setName("Apps");
  }

  /**
   *
   * @return Name of Application
   */
  public String getName() {
    return getString("name");
  }

  /**
   *
   * @param value value for name of application
   */
  public void setName(String value) {
    put("name", value);
  }

  /**
   *
   * @return Description of Application
   */
  public String getDescription(){
    return getString("description");
  }

  /**
   *
   * @param value value for app description
   */
  public void setDescription(String value){
    put("description", value);
  }

  /**
   *
   * @return Link to App Store
   */
  public String getLink(){
    return getString("link");
  }

  /**
   *
   * @param value value for link
   */
  public void setLink(String value){
    put("link", value);
  }

  /**
   *
   * @param file file name for icon image
   */
  public void setIcon2x(ParseFile file){
    put("icon2x", file);
  }

  /**
   *
   * @return file icon image
   */
  public ParseFile getIcon2x(){
    return getParseFile("icon2x");
  }

  /**
   *
   * @return url for icon image
   */
  public String getIconUrl(){
    return getIcon2x().getUrl();
  }

  /**
   *
   * @return String type of OS for the app
   */
  public int getOperatingSystem(){
    return getInt("operatingSystem");
  }

  public void setOperatingSystem(int value){
    put("operatingSystem", value);
  }

  @Override public String toString() {
    return "\n" + getName() + ": "
        + "\n" + getOperatingSystem()
        + "\n"
        ;
  }

  /**
   * Loads from local data store, and then load from net
   * @param activity
   */
  public static void load(Activity activity){
    final OnAppCatalogLoadedListener mListener = (OnAppCatalogLoadedListener) activity;

    ParseQuery<AppCatalogApp> query = ParseQuery.getQuery(AppCatalogApp.class);
    query.fromLocalDatastore();
    query.whereEqualTo("enabled", true);
    query.findInBackground(new FindCallback<AppCatalogApp>() {
      public void done(List<AppCatalogApp> apps, ParseException e) {
        if (e == null) {
          apps.add(0, new AppCatalogApp(true));//append the header onto the app list
          mListener.onAppCatalogLoaded(apps);
        }
      }
    });

    query = null;

    query = ParseQuery.getQuery(AppCatalogApp.class);
    query.whereEqualTo("enabled", true);
    query.findInBackground(new FindCallback<AppCatalogApp>() {
      public void done(List<AppCatalogApp> apps, ParseException e) {
        if (e == null) {
          apps.add(0, new AppCatalogApp(true));//append the header onto the app list
          mListener.onAppCatalogLoaded(apps);

          pinAllInBackground(apps);
        }
      }
    });

  }



}
