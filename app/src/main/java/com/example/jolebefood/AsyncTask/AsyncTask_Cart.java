package com.example.jolebefood.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Cart_Item;
import com.example.jolebefood.AdapterRecycleView.Purchase_History_Item;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.DTO.OrderDTO;

import java.util.ArrayList;

public class AsyncTask_Cart extends AsyncTask<Void, CartDTO,Void> {

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private Context context;

    private Cart_Item adapter;

    // List này sẽ được Product gửi qua sau khi đọc được từ firebase
    private ArrayList<CartDTO> datalist;

    // List này sẽ được add lần lượt item từ datalist để có thể làm asyncTask chạy lần lượt từng cái lên
    private ArrayList<CartDTO> listTemp = new ArrayList<>();
    private TextView total_pay;


    public AsyncTask_Cart(RecyclerView recyclerView, ProgressBar progressBar, Context context, ArrayList<CartDTO> datalist) {
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    protected void onPreExecute() {
        adapter = new Cart_Item(listTemp, total_pay);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for (CartDTO s : datalist){
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
    protected void onProgressUpdate(CartDTO... values) {
        listTemp.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void unused) {
        progressBar.setVisibility(View.GONE);
    }
}
