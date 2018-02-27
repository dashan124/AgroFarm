package com.example.dashan.agrofarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private ViewPager mslideViewPager;
    private LinearLayout mDotlayout;
    private SlideAdapter mslideadapter;
    private Button mnext;
    private Button mskip;
    private TextView[] Dots;
    private int mcurrentpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mslideViewPager=(ViewPager) findViewById(R.id.view_pager);
        mDotlayout=(LinearLayout) findViewById(R.id.dot_view);

        mnext=(Button)findViewById(R.id.next_button);
        mskip=(Button) findViewById(R.id.skip_button);

        mslideadapter=new SlideAdapter(this);
        mslideViewPager.setAdapter(mslideadapter);
        addDotsIndicator(0);
        mslideViewPager.addOnPageChangeListener(viewListener);
        mnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mslideViewPager.setCurrentItem(mcurrentpage+1);
                int current=mcurrentpage+1;
                if(current<Dots.length){
                    mslideViewPager.setCurrentItem(current);
                }
                else{
                    Intent i=new Intent(getApplicationContext(),LoginMobile.class);
                    startActivity(i);
                }

            }
        });
        mskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),LoginMobile.class);
                startActivity(i);
            }
        });
    }
    public void addDotsIndicator(int pos){
        Dots=new TextView[3];
        mDotlayout.removeAllViews();
        for(int i=0;i<Dots.length;i++){
            Dots[i]=new TextView(this);
            Dots[i].setText(Html.fromHtml("&#8226"));
            Dots[i].setTextSize(36);
            Dots[i].setGravity(Gravity.CENTER_HORIZONTAL);
            Dots[i].setTextColor(getResources().getColor(R.color.Black));
            mDotlayout.addView(Dots[i]);
        }
        if(Dots.length>0){
            Dots[pos].setTextColor(getResources().getColor(R.color.AliceBlue));
        }
    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);

            mcurrentpage=position;
         if(position==0){
             mnext.setEnabled(true);
             mnext.setVisibility(View.VISIBLE);
             mskip.setText("Skip");
             mskip.setEnabled(true);
         }
         else if(position==Dots.length-1){
             mnext.setEnabled(true);
             mnext.setText("Finish");
             mskip.setEnabled(true);
             mskip.setText("Skip");
             mnext.setVisibility(View.INVISIBLE);
         }
         else{
             mnext.setEnabled(true);
             mnext.setVisibility(View.VISIBLE);
             mnext.setText("Next");
             mskip.setText("Skip");
         }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
