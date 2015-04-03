package com.autolean.appcatalog.data;

import com.parse.ParseQuery;

/**
 * Created by AKiniyalocts on 4/3/15.
 */
public class QueryManager {

  public static class Builder{

    Class clazz;
    ParseQuery query;


    public Builder setClass(Class clazz){
      this.clazz = clazz;
      return this;
    }


  }
}
