package com.example.user.weatherapp.Weather;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import com.bumptech.glide.Glide;
import com.example.user.weatherapp.R;
import com.example.user.weatherapp.model.Image;
import com.facebook.CallbackManager;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;


public class Custom_adaptor extends RecyclerView.Adapter<Custom_adaptor.ViewHolder> {

    private List<String> mtitle, mdesc, murl, mimages;
    View v;
    Context mcontext;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    android.support.v4.app.Fragment fragment;

    public Custom_adaptor(Context context, List<String> title, List<String> desc, List<String> urls, List<String> images, android.support.v4.app.Fragment fragment) {
        mtitle = title;
        mdesc = desc;
        murl = urls;
        mimages = images;
        mcontext = context;
        this.fragment = fragment;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txthead;
        TextView txtfoot, ur ;
        ImageView share_fb, share_common,twitter;
        ImageView imageView;
        View layout;

        public ViewHolder(View v) {
            super(v);
            txthead = (TextView) v.findViewById(R.id.firstLine);
            txtfoot = (TextView) v.findViewById(R.id.secondLine);
            share_fb = (ImageView) v.findViewById(R.id.share);
            share_common = (ImageView) v.findViewById(R.id.share_common);
            twitter = (ImageView) v.findViewById(R.id.twitter);
            ur = (TextView) v.findViewById(R.id.url);
            imageView = (ImageView) v.findViewById(R.id.imageee);
            layout = v;
        }
    }

    @NonNull
    @Override
    public Custom_adaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.custom, parent, false);
        ViewHolder vh = new ViewHolder(v);
        callbackManager = CallbackManager.Factory.create();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final Custom_adaptor.ViewHolder holder, final int position) {

        final String mytitle = mtitle.get(position);
        final String mydesc = mdesc.get(position);
        final String urll = murl.get(position);
        final String mimag = mimages.get(position);
        holder.txthead.setText(mytitle);
        holder.txtfoot.setText(mydesc);
        Glide.with(mcontext).load(mimag)
                .into(holder.imageView);
        holder.ur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urll));
                v.getContext().startActivity(browserIntent);


            }
        });

        holder.share_fb.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

             boolean facebookAppFound = false;
               Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
               shareIntent.setType("text/plain");
               shareIntent.putExtra(Intent.EXTRA_TEXT, urll);
               shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(urll));

               PackageManager pm =fragment.getActivity().getPackageManager();
               List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
               for (final ResolveInfo app : activityList) {
                   if ((app.activityInfo.packageName).contains("com.facebook.katana")) {
                       final ActivityInfo activityInfo = app.activityInfo;
                       final ComponentName name = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
                       shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                       shareIntent.setComponent(name);
                       facebookAppFound = true;
                       break;
                   }
               }
               if (!facebookAppFound) {
                   String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urll;
                   shareIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
               }
               fragment.getActivity().startActivity(shareIntent);


              /*  ShareDialog shareDialog = new ShareDialog(fragment);
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse(urll))
                            .build();
                    shareDialog.show(linkContent);


                }*/

            }
        });
        holder.twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder tweetUrl = new StringBuilder("https://twitter.com/intent/tweet?text=");
                if (!TextUtils.isEmpty(urll)) {
                    tweetUrl.append("&url=");
                    tweetUrl.append(urll);
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl.toString()));
                List<ResolveInfo> matches = fragment.getActivity().getPackageManager().queryIntentActivities(intent, 0);
                for (ResolveInfo info : matches) {
                    if (info.activityInfo.packageName.toLowerCase().startsWith("com.twitter")) {
                        intent.setPackage(info.activityInfo.packageName);
                    }
                }
                fragment.getActivity().startActivity(intent);
            }
        });
        holder.share_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, urll);
                v.getContext().startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mtitle.size();
    }

}

