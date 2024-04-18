package com.example.jolebefood.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Product_Item;
import com.example.jolebefood.DTO.ProductDTO;

import org.apache.http.conn.ConnectTimeoutException;

import java.util.ArrayList;

public class AsyncTask_Product extends AsyncTask<Void,ProductDTO,Void> {

    private String UID;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private Context context;

    private Product_Item adapter;

    // List này sẽ được Product gửi qua sau khi đọc được từ firebase
    private ArrayList<ProductDTO> datalist;

    // List này sẽ được add lần lượt item từ datalist để có thể làm asyncTask chạy lần lượt từng cái lên
    private ArrayList<ProductDTO> listTemp = new ArrayList<>();

    public AsyncTask_Product(String UID, RecyclerView recyclerView, ProgressBar progressBar, Context context, ArrayList<ProductDTO> datalist) {
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.context = context;
        this.datalist = datalist;
        this.UID = UID;
    }

    @Override
    protected void onPreExecute() {
        adapter = new Product_Item(listTemp,UID);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for (ProductDTO s : datalist){
            publishProgress(s);
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return null;
    }



    @Override
    protected void onProgressUpdate(ProductDTO... values) {
        listTemp.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void unused) {
        progressBar.setVisibility(View.GONE);
    }
}
