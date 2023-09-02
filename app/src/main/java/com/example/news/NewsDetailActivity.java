package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
     String title,Desc,content,url,ima;
     private TextView titletv,subdesctv,contenttv;
     private ImageView newsiv;
     private Button readnewsbutton;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=getIntent().getStringExtra("title");
        Desc=getIntent().getStringExtra("Desc");
        content=getIntent().getStringExtra("content");
        url=getIntent().getStringExtra("url");
        ima=getIntent().getStringExtra("image");
        titletv=findViewById(R.id.idTVtitle);
         subdesctv=findViewById(R.id.idTVsubdesc);
         contenttv=findViewById(R.id.idTvcontent);
         newsiv=findViewById(R.id.idIVnews);
         readnewsbutton=findViewById(R.id.idbtnread);
         titletv.setText(title);
         subdesctv.setText(Desc);
         contenttv.setText(content);
         Picasso.get().load(ima).error(R.drawable.error_placeholder).into(newsiv);

         if (url != null) {
             Log.d("NewsDetailActivity", "URL: " + url);
         } else {
             Log.d("NewsDetailActivity", "URL is null");
         }

        readnewsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (url != null) {
                    Log.d("NewsDetailActivity", "Opening URL: " + url);
                    Intent ii = new Intent(Intent.ACTION_VIEW);
                    ii.setData(Uri.parse(url));
                    startActivity(ii);
                } else {
                    Log.d("NewsDetailActivity", "URL is null, cannot open.");
                }
            }
        });

    }
}