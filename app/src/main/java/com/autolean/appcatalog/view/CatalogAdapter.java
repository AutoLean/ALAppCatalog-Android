package com.autolean.appcatalog.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.autolean.appcatalog.R;
import com.autolean.appcatalog.aLog;
import com.autolean.appcatalog.data.apps.AppCatalogApp;
import com.autolean.appcatalog.data.social.AppCatalogSocialMediaOutlet;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by AKiniyalocts on 4/3/15.
 */
public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  public static final String TAG = CatalogAdapter.class.getSimpleName();

  private LayoutInflater mInflater;
  private Context mContext;
  private List<AppCatalogApp> mApps;
  private List<AppCatalogSocialMediaOutlet> mSocials;
  private int green, blue;

  public CatalogAdapter(Context mContext,
      List<AppCatalogApp> mApps) {
    this.mContext = mContext;
    this.mApps = mApps;
    this.mInflater = LayoutInflater.from(mContext);

    green = mContext.getResources().getColor(R.color.android_green);
    blue = mContext.getResources().getColor(R.color.ios_blue);
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
        Picasso.with(mContext)
            .load(app.getIconUrl())
            .fit()
            .into(catalogViewHolder.mImage);

        catalogViewHolder.mName.setText(app.getName());
        catalogViewHolder.mDesc.setText(app.getDescription());
        catalogViewHolder.mOS.setText(app.getOperatingSystem());

        // Set the operating system tag
        if(app.getOperatingSystem() != null && app.getOperatingSystem().toLowerCase().equals("android"))
          catalogViewHolder.mOS.setBackgroundColor(green);
        else
          catalogViewHolder.mOS.setBackgroundColor(blue);

        catalogViewHolder.mOS.setVisibility(View.VISIBLE);
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
        aLog.w(TAG, social.toString());
        Picasso.with(mContext)
            .load(social.getImage2xUrl())
            .fit()
            .into(catalogViewHolder.mImage);
        catalogViewHolder.mName.setText(social.getText());
        catalogViewHolder.mDesc.setText(social.getSubtitle());

        // hide tag for social items
        catalogViewHolder.mOS.setVisibility(View.INVISIBLE);
      }

    }

  }



  @Override public int getItemCount() {
    return mApps.size() + mSocials.size();
  }

  public class CatalogViewHolder extends RecyclerView.ViewHolder implements
      View.OnClickListener{
    private final ImageView mImage;
    private final TextView mName;
    private final TextView mDesc;
    private final TextView mOS;

    public CatalogViewHolder(View itemView) {
      super(itemView);

      mImage = (ImageView)itemView.findViewById(R.id.app_icon);
      mName = (TextView)itemView.findViewById(R.id.app_name);
      mDesc = (TextView)itemView.findViewById(R.id.app_desc);
      mOS = (TextView)itemView.findViewById(R.id.app_os);

      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View v) {
      String url = null;

      if(getItemViewType() != R.layout.list_header){

        if(getPosition() >= mApps.size()){

          url = mSocials.get(getPosition() - mApps.size()).getBackupLink();
        }

        else {
          url = mApps.get(getPosition()).getLink();
        }
      }

      if(url != null) {
        Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        web.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(web);
      }
    }
  }

  public static class HeaderViewHolder extends RecyclerView.ViewHolder{
    private final TextView mHeader;

    public HeaderViewHolder(View itemView){
      super(itemView);
      mHeader = (TextView)itemView.findViewById(R.id.list_header_item);
    }


  }
}
