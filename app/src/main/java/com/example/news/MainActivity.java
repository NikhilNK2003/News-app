package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVadapter.CategoryClickinterface {
//dd1f05c4866240b3a3823a2b77d8999c

    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingpb;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private NewsRVAdapter newsRVAdapter;
    private CategoryRVadapter categoryRVadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV=findViewById(R.id.RVnews);
        categoryRV=findViewById(R.id.RVcat);
        loadingpb=findViewById(R.id.ldbar);
        articlesArrayList=new ArrayList<>();
        categoryRVModalArrayList=new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVadapter=new CategoryRVadapter(categoryRVModalArrayList,this,this::oncategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVadapter);
        getCategories();
        getNews("All");
        newsRVAdapter.notifyDataSetChanged();

    }
private void getCategories(){
        categoryRVModalArrayList.add(new CategoryRVModal("All","https://images.unsplash.com/photo-1523995462485-3d171b5c8fa9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fGFsbCUyMG5ld3N8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"));
    categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
    categoryRVModalArrayList.add(new CategoryRVModal("Science","https://images.unsplash.com/photo-1507413245164-6160d8298b31?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c2NpZW5jZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
    categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://images.unsplash.com/photo-1541252260730-0412e8e2108e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTF8fHNwb3J0c3xlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
    categoryRVModalArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1457369804613-52c61a468e7d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8Z2VuZXJhbHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
    categoryRVModalArrayList.add(new CategoryRVModal("Business","https://images.unsplash.com/photo-1661956602153-23384936a1d3?ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxzZWFyY2h8MXx8YnVzaW5lc3N8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=500&q=60"));
    categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://images.unsplash.com/photo-1598899134739-24c46f58b8c0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&w=500&q=60"));
    categoryRVModalArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1511688878353-3a2f5be94cd7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NXx8aGVhbHRofGVufDB8fDB8fHww&auto=format&fit=crop&w=500&q=60"));
    categoryRVadapter.notifyDataSetChanged();
}
private void getNews(String category)
{
    loadingpb.setVisibility(View.VISIBLE);
    articlesArrayList.clear();
    String categoryurl="https://newsapi.org/v2/top-headlines?country=in&category="+ category+"&apikey=dd1f05c4866240b3a3823a2b77d8999c";
    String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=dd1f05c4866240b3a3823a2b77d8999c";
    String Baseurl="https://newsapi.org/";
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(Baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
    Call<NewsModal> call;
    if(category.equals("All"))
    {
        call=retrofitAPI.getAllNews(url);
    }else {
        call=retrofitAPI.getNewsByCategory(categoryurl);
    }

    call.enqueue(new Callback<NewsModal>() {
        @Override
        public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
           NewsModal newsModal=response.body();
            loadingpb.setVisibility(View.GONE);
            ArrayList<Articles> articles =newsModal.getArticles();
            for(int i=0;i<articles.size();i++)
            {
                articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToimage(),articles.get(i).getUrl(),articles.get(i).getContent()));
            }
            newsRVAdapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<NewsModal> call, Throwable t) {
            Toast.makeText(MainActivity.this, "Failed to get news", Toast.LENGTH_SHORT).show();
        }
    });
}
    @Override
    public void oncategoryClick(int position) {
        String category=categoryRVModalArrayList.get(position).getCategory();
        getNews(category);

    }
}