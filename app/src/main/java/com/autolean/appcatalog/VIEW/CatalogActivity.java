package com.autolean.appcatalog.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.autolean.appcatalog.R;
import com.autolean.appcatalog.data.apps.AppCatalogApp;
import com.autolean.appcatalog.data.apps.OnAppCatalogLoadedListener;
import com.autolean.appcatalog.data.social.AppCatalogSocialMediaOutlet;
import com.autolean.appcatalog.data.social.OnSocialMediaLoadedListener;
import com.uservoice.uservoicesdk.Config;
import com.uservoice.uservoicesdk.UserVoice;
import java.util.List;

public class CatalogActivity extends ActionBarActivity implements
    OnAppCatalogLoadedListener,
    OnSocialMediaLoadedListener
{


  private RecyclerView mRecycler;

  private CatalogAdapter adapter;
  private GridLayoutManager gridLayoutManager;

  private List<AppCatalogApp> apps;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_catalog);
    getSupportActionBar().setTitle("AutoLean");
    initUserVoice();


    initRecycler();

    AppCatalogApp.load(this);

  }


  private void initUserVoice(){
    Config config = new Config("autolean.uservoice.com");
    UserVoice.init(config, this);
  }


  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getMenuInflater().inflate(R.menu.menu_catalog, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {

    if(item.getItemId() == R.id.action_feedback)
      UserVoice.launchUserVoice(this);

    if(item.getItemId() == android.R.id.home)
      onBackPressed();

    return super.onOptionsItemSelected(item);
  }

  // Set the recycler layout manager. Grid if its a tablet, List if it isn't.
  private void initRecycler(){
    mRecycler = (RecyclerView)findViewById(R.id.catalog_recycler);

    gridLayoutManager = new GridLayoutManager(this, 3);

    if(isTablet()) {
        mRecycler.setLayoutManager(gridLayoutManager);
    }
    else {
      mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }
  }

  // App list loaded from data manager
  @Override public void onAppCatalogLoaded(List<AppCatalogApp> apps) {
    if(apps != null) {
      this.apps = apps;

      AppCatalogSocialMediaOutlet.load(this);
    }
  }

  // Social list loaded from data manager
  @Override public void onSocialMediaLoaded(List<AppCatalogSocialMediaOutlet> socialMediaOutlets) {

    adapter = new CatalogAdapter(this, apps);
    mRecycler.setAdapter(adapter);

    // Span the headers across the screen if it is a header
    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override public int getSpanSize(int position) {
        if (adapter.getItemViewType(position) == R.layout.list_header)
          return 3;
        else
          return 1;
      }
    });


    adapter.setSocials(socialMediaOutlets);
    adapter.notifyDataSetChanged();

  }

  public  boolean isTablet(){
    return getResources().getBoolean(R.bool.isTablet);
  }

}
