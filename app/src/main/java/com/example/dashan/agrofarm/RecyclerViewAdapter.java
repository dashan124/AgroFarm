package com.example.dashan.agrofarm;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dashan on 1/4/18.
 */

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context mContext;
    private List<CardBook> mData;

    public RecyclerViewAdapter(Context mContext,List<CardBook> mData) {
        this.mContext = mContext;
        this.mData=mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        view=layoutInflater.inflate(R.layout.cardview_for_home,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());
        holder.book_image.setImageResource(mData.get(position).getThumbnail());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,WebActivity.class);
                intent.putExtra("BookTitle",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Categorie",mData.get(position).getCategory());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_book_title;
        ImageView book_image;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);
            tv_book_title=(TextView) itemView.findViewById(R.id.title_textview);
            book_image=(ImageView) itemView.findViewById(R.id.image_thumbnail);

            cardView=(CardView) itemView.findViewById(R.id.card_view_android);

        }
    }
}
