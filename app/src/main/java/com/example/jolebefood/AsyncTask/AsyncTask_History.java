package com.example.jolebefood.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Product_Item;
import com.example.jolebefood.AdapterRecycleView.Purchase_History_Item;
import com.example.jolebefood.DTO.OrderDTO;
import com.example.jolebefood.DTO.ProductDTO;

import java.util.ArrayList;

public class AsyncTask_History extends AsyncTask<Void,OrderDTO,Void> {

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private Context context;

    private Purchase_History_Item adapter;

    // List này sẽ được Product gửi qua sau khi đọc được từ firebase
    private ArrayList<OrderDTO> datalist;

    // List này sẽ được add lần lượt item từ datalist để có thể làm asyncTask chạy lần lượt từng cái lên
    private ArrayList<OrderDTO> listTemp = new ArrayList<>();


    public AsyncTask_History(RecyclerView recyclerView, ProgressBar progressBar, Context context, ArrayList<OrderDTO> datalist) {
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    protected void onPreExecute() {
        adapter = new Purchase_History_Item(listTemp);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for (OrderDTO s : datalist){
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
    protected void onProgressUpdate(OrderDTO... values) {
        listTemp.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void unused) {
        progressBar.setVisibility(View.GONE);
    }
}
