package com.example.dashan.agrofarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionPage extends AppCompatActivity {

    private TextView tvTitle,tvDescription,tvcategorie;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_page);

        tvTitle=(TextView) findViewById(R.id.description_page_title);
        tvDescription=(TextView) findViewById(R.id.description_page_description);
        tvcategorie=(TextView) findViewById(R.id.description_page_categorie);
        imageView=(ImageView) findViewById(R.id.description_page_image);



        Intent intent=getIntent();
        String Title=intent.getExtras().getString("Title");
        String Description=intent.getExtras().getString("Description");
        String Categorie=intent.getExtras().getString("Categorie");
        int image=intent.getExtras().getInt("Thumbnail");

        //Setting Values

        tvTitle.setText(Title);
        tvDescription.setText(Description);
        tvcategorie.setText(Categorie);
        imageView.setImageResource(image);

    }
}
