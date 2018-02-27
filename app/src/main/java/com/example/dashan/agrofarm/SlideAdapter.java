package com.example.dashan.agrofarm;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by dashan on 27/2/18.
 */

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SlideAdapter(Context context){
        this.context=context;
    }
    public int[] slide_images={
      R.drawable.soil,
            R.drawable.crops_image,
            R.drawable.cash
    };
    public String[] slide_headings={
            "BUY COMPOSITS",
            "SELL HARVESTS",
            "EASY PAYMENTS"
    };
    public String[] slide_descriptions={
            "Now buy composiites and seeds directly from stores nearer you",
            "sell harvests at most convinient price",
            "paynents are directly in your bank account"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout)object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=(ImageView) view.findViewById(R.id.image_view);
        TextView slideheadingtext=(TextView) view.findViewById(R.id.text_heading);
        TextView slidetextdescription=(TextView) view.findViewById(R.id.text_descri);
       slideImageView.setImageResource(slide_images[position]);
       slideheadingtext.setText(slide_headings[position]);
       slidetextdescription.setText(slide_descriptions[position]);
       container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}
