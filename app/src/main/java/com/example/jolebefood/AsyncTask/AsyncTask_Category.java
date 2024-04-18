package com.example.jolebefood.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Category_Item;
import com.example.jolebefood.AdapterRecycleView.Product_Item;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.ArrayList;

public class AsyncTask_Category extends AsyncTask<Void, CategoryDTO,Void> {


    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private Context context;

    private Category_Item adapter;

    // List này sẽ được Product gửi qua sau khi đọc được từ firebase
    private ArrayList<CategoryDTO> datalist;

    // List này sẽ được add lần lượt item từ datalist để có thể làm asyncTask chạy lần lượt từng cái lên
    private ArrayList<CategoryDTO> listTemp = new ArrayList<>();


    public AsyncTask_Category(RecyclerView recyclerView, ProgressBar progressBar, Context context, ArrayList<CategoryDTO> datalist) {
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    protected void onPreExecute() {
        adapter = new Category_Item(context,listTemp);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {


        for (CategoryDTO s : datalist){
            publishProgress(s);

            try {
                Thread.sleep(500); // Ngủ 100ms trước khi kiểm tra lại
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }



    @Override
    protected void onProgressUpdate(CategoryDTO... values) {

        listTemp.add(values[0]);

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostExecute(Void unused) {
        progressBar.setVisibility(View.GONE);
    }
}
