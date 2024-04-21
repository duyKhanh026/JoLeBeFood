package com.example.jolebefood.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Order_Details_Item;
import com.example.jolebefood.DTO.OrderDetailsDTO;

import java.util.ArrayList;

public class AsyncTask_OrderDetails  extends AsyncTask<Void, OrderDetailsDTO,Void> {


    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private Context context;

    private Order_Details_Item adapter;

    // List này sẽ được Product gửi qua sau khi đọc được từ firebase
    private ArrayList<OrderDetailsDTO> datalist;

    // List này sẽ được add lần lượt item từ datalist để có thể làm asyncTask chạy lần lượt từng cái lên
    private ArrayList<OrderDetailsDTO> listTemp = new ArrayList<>();

    public AsyncTask_OrderDetails(RecyclerView recyclerView, ProgressBar progressBar, Context context, ArrayList<OrderDetailsDTO> datalist) {
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    protected void onPreExecute() {
        adapter = new Order_Details_Item(listTemp);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for (OrderDetailsDTO s : datalist){
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
    protected void onProgressUpdate(OrderDetailsDTO... values) {
        listTemp.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void unused) {
        progressBar.setVisibility(View.GONE);
    }
}
