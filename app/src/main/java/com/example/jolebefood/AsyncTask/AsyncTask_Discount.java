package com.example.jolebefood.AsyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.AdapterRecycleView.Category_Item;
import com.example.jolebefood.AdapterRecycleView.Discount_Item;
import com.example.jolebefood.DTO.CategoryDTO;
import com.example.jolebefood.DTO.DiscountDTO;

import java.util.ArrayList;

public class AsyncTask_Discount extends AsyncTask<Void, DiscountDTO,Void> {


    private String UID = "", PhuongThuc = "";

    private double latitude = 0, longitude = 0;

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private Context context;

    private Discount_Item adapter;

    // List này sẽ được Product gửi qua sau khi đọc được từ firebase
    private ArrayList<DiscountDTO> datalist;

    // List này sẽ được add lần lượt item từ datalist để có thể làm asyncTask chạy lần lượt từng cái lên
    private ArrayList<DiscountDTO> listTemp = new ArrayList<>();


    public AsyncTask_Discount(RecyclerView recyclerView, ProgressBar progressBar, Context context, ArrayList<DiscountDTO> datalist) {
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.context = context;
        this.datalist = datalist;
    }

    public AsyncTask_Discount(String UID, String phuongThuc, double latitude, double longitude, RecyclerView recyclerView, ProgressBar progressBar, Context context, ArrayList<DiscountDTO> datalist) {
        this.UID = UID;
        PhuongThuc = phuongThuc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    protected void onPreExecute() {

        if (UID.equals("")){
            adapter = new Discount_Item(listTemp);
        }
        else {
            adapter = new Discount_Item(context,listTemp,UID,PhuongThuc,latitude,longitude);
        }
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids) {

        for (DiscountDTO s : datalist){
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
    protected void onProgressUpdate(DiscountDTO... values) {

        listTemp.add(values[0]);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onPostExecute(Void unused) {
        progressBar.setVisibility(View.GONE);
    }
}
