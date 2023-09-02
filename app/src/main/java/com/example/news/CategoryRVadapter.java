package com.example.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CategoryRVadapter extends RecyclerView.Adapter<CategoryRVadapter.ViewHolder> {

  final  private ArrayList<CategoryRVModal> categoryRVModals;
     final private Context context;
   final private CategoryClickinterface categoryClickinterface;

    public CategoryRVadapter(ArrayList<CategoryRVModal> categoryRVModals, Context context, CategoryClickinterface categoryClickinterface) {
        this.categoryRVModals = categoryRVModals;
        this.context = context;
        this.categoryClickinterface = categoryClickinterface;
    }

    @NonNull
    @Override
    public CategoryRVadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_rv_items,parent,false);
        return new CategoryRVadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVadapter.ViewHolder holder, int position) {
       CategoryRVModal categoryRVModal=categoryRVModals.get(position);
       holder.categorytv.setText(categoryRVModal.getCategory());
        Picasso.get().load(categoryRVModal.getCategoryImageurl()).into(holder.categoryIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickinterface.oncategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryRVModals.size();
    }
         public interface CategoryClickinterface{
           void oncategoryClick(int position);
         }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView categorytv;
        private ImageView categoryIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categorytv=itemView.findViewById(R.id.idtv);
            categoryIv=itemView.findViewById(R.id.idvcat);

        }
    }
}
