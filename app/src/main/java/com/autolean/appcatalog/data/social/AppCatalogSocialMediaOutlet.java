package com.autolean.appcatalog.data.social;

import android.app.Activity;

import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by AKiniyalocts on 4/3/15.
 */

@ParseClassName("AppCatalogSocialMediaOutlet")
public class AppCatalogSocialMediaOutlet extends ParseObject {


  public AppCatalogSocialMediaOutlet(){}

  /**
   * Creating an empty socialMediaOutlet that is only a header
   * @param isHeader
   */
  public AppCatalogSocialMediaOutlet(boolean isHeader){
    this.setText("Social");
  }


  /**
   *
   * @return title text for social media outlet
   */
  public String getText(){
    return getString("text");
  }

  public void setText(String value){
    put("text", value);
  }

  /**
   *
   * @return subtitle for social media outlet
   */
  public String getSubtitle(){
    return getString("subtitle");
  }

  public void setSubtitle(String value){
    put("subtitle", value);
  }


  public void setImage2x(ParseFile file){
    put("image2x", file);
  }

  /**
   *
   * @return image for social media outlet
   */
  public ParseFile getImage2x(){
    return getParseFile("image2x");
  }

  /**
   *
   * @return url for image
   */
  public String getImage2xUrl(){
    if(getImage2x() != null)
      return getImage2x().getUrl();
    else
      return "blank";
  }

  /**
   *
   * @return Link to social profile
   */
  public String getLink(){
    return getString("link");
  }

  /**
   *
   * @param value value for social link
   */
  public void setLink(String value){
    put("link", value);
  }

  /**
   *
   * @return url link (not a URI)
   */
  public String getBackupLink(){
    return getString("link_backup");
  }

  public void setBackupLink(String value){
    put("link_backup", value);
  }

  @Override public String toString() {
    return "Backup Link: " + getBackupLink() + "\n" + "Link: " + getLink();
  }


  public static void load(Activity activity){
    final OnSocialMediaLoadedListener mListener = (OnSocialMediaLoadedListener) activity;

    ParseQuery<AppCatalogSocialMediaOutlet> query = ParseQuery.getQuery(AppCatalogSocialMediaOutlet.class);
    query.fromLocalDatastore();
    query.whereEqualTo("enabled", true);
    query.findInBackground(new FindCallback<AppCatalogSocialMediaOutlet>() {
      public void done(List<AppCatalogSocialMediaOutlet> socialMediaOutlets, ParseException e) {
        if (e == null) {
          socialMediaOutlets.add(0, new AppCatalogSocialMediaOutlet(true));// append the social header
          mListener.onSocialMediaLoaded(socialMediaOutlets);
        }
      }
    });

    query = null;

    query = ParseQuery.getQuery(AppCatalogSocialMediaOutlet.class);
    query.whereEqualTo("enabled", true);
    query.findInBackground(new FindCallback<AppCatalogSocialMediaOutlet>() {
      public void done(List<AppCatalogSocialMediaOutlet> socialMediaOutlets, ParseException e) {
        if (e == null) {
          socialMediaOutlets.add(0, new AppCatalogSocialMediaOutlet(true));// append the social header
          mListener.onSocialMediaLoaded(socialMediaOutlets);

          pinAllInBackground(socialMediaOutlets);
        }
      }
    });
  }

}
