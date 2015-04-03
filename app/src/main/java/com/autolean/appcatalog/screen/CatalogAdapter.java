package com.autolean.appcatalog.screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.autolean.appcatalog.AppCatalogApplication;
import com.autolean.appcatalog.R;
import com.autolean.appcatalog.data.apps.AppCatalogApp;
import com.autolean.appcatalog.data.social.AppCatalogSocialMediaOutlet;
import java.util.List;

/**
 * Created by AKiniyalocts on 4/3/15.
 */
public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private LayoutInflater mInflater;
  private Context mContext;

  private List<AppCatalogApp> mApps;
  private List<AppCatalogSocialMediaOutlet> mSocials;

  public CatalogAdapter(Context mContext,
      List<AppCatalogApp> mApps) {
    this.mContext = mContext;
    this.mApps = mApps;
    this.mInflater = LayoutInflater.from(mContext);
  }

  public void setSocials(List<AppCatalogSocialMediaOutlet> socials){
    this.mSocials = socials;
  }

  /**
   * Return the appropriate view type. Whether it is a header/not
   * @param position
   * @return layout id
   */
  @Override public int getItemViewType(int position) {

    if(position == 0 || position == mApps.size())
      return R.layout.list_header;
    else
      return R.layout.catalog_list_item;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if(viewType == R.layout.list_header){
      View viewHolder = mInflater.inflate(R.layout.list_header, parent, false);
      return new HeaderViewHolder(viewHolder);
    }
    else {
      View viewHolder = mInflater.inflate(R.layout.catalog_list_item, parent, false);
      return new CatalogViewHolder(viewHolder);
    }
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    // Application Section
    if(position < mApps.size()) {

      // Header for Application
      if(position == 0){
        HeaderViewHolder headerViewHolder = ((HeaderViewHolder) holder);
        headerViewHolder.mHeader.setText(mApps.get(position).getName());
      }

      // Applications
      else {
        CatalogViewHolder catalogViewHolder = ((CatalogViewHolder) holder);
        AppCatalogApp app = mApps.get(position);

        AppCatalogApplication.getPicasso()
            .load(app.getIconUrl())
            .fit()
            .into(catalogViewHolder.mImage);

        catalogViewHolder.mName.setText(app.getName());
        catalogViewHolder.mDesc.setText(app.getDescription());
      }
    }

    // Social Section
    else{

      // Social Header
      if(position == mApps.size()){
        HeaderViewHolder headerViewHolder = ((HeaderViewHolder) holder);
        headerViewHolder.mHeader.setText(mSocials.get(position - mApps.size()).getText());
      }

      // Social items
      else {
        CatalogViewHolder catalogViewHolder = ((CatalogViewHolder) holder);

        AppCatalogSocialMediaOutlet social = mSocials.get(position - mApps.size());

        AppCatalogApplication.getPicasso()
            .load(social.getImage2xUrl())
            .fit()
            .into(catalogViewHolder.mImage);
        catalogViewHolder.mName.setText(social.getText());
        catalogViewHolder.mDesc.setText(social.getSubtitle());
      }

    }

  }



  @Override public int getItemCount() {
    return mApps.size() + mSocials.size();
  }

  public static class CatalogViewHolder extends RecyclerView.ViewHolder{
    @InjectView(R.id.app_icon) ImageView mImage;
    @InjectView(R.id.app_name) TextView mName;
    @InjectView(R.id.app_desc) TextView mDesc;

    public CatalogViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }

  public static class HeaderViewHolder extends RecyclerView.ViewHolder{
    @InjectView(R.id.list_header_item) TextView mHeader;

    public HeaderViewHolder(View itemView){
      super(itemView);
      ButterKnife.inject(this, itemView);
    }


  }
}
