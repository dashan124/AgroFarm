package com.example.dashan.agrofarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by dashan on 1/4/18.
 */

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Context mContext;

    public CustomInfoWindowAdapter( Context context) {
        this.mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window,null);
        this.mContext = context;
    }

    private void RenderWindowText(Marker marker,View view){
        String title=marker.getTitle();
        TextView tvTitle=(TextView)view.findViewById(R.id.textview_info_window_title);
        if(!title.equals("")){
            tvTitle.setText(title);
        }
        String snippt=marker.getSnippet();
        TextView tvSnippt=(TextView)view.findViewById(R.id.snippt);
        if(!snippt.equals("")){
            tvSnippt.setText(snippt);
        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        RenderWindowText(marker,mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        RenderWindowText(marker,mWindow);
        return mWindow;
    }
}
