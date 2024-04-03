package com.example.jolebefood.AdapterRecycleView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.example.jolebefood.DAO.CallFirebaseStrorage;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Cart_Item extends RecyclerView.Adapter<Cart_Item.MyViewHolder> {
    View view;
    private ArrayList<CartDTO> datalist;
    CallFirebaseStrorage callFirebaseStrorage;
    private String userId;

    public Cart_Item(ArrayList<CartDTO> datalist) {
        this.datalist = datalist;
        callFirebaseStrorage = new CallFirebaseStrorage();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userId = user.getUid();
        } else {
            // Xử lý trường hợp không có người dùng đăng nhập
        }
    }

    @NonNull
    @Override
    public Cart_Item.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new Cart_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CartDTO cartItem = datalist.get(position);

        holder.ProductName.setText(cartItem.getTenMonAn());
        holder.Quantity.setText(String.valueOf(cartItem.getSL()));
        holder.ProductPrice.setText(String.valueOf(cartItem.getTongTien()));
        holder.SetIMG(callFirebaseStrorage,cartItem.getImage());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCartItem(cartItem, position);
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = cartItem.getSL();
                if (currentQuantity > 1) {
                    currentQuantity--;
                    cartItem.setSL(currentQuantity);
                    holder.Quantity.setText(String.valueOf(currentQuantity));
                    holder.ProductPrice.setText(String.valueOf(calculateTotalPrice(cartItem)));
                }
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = cartItem.getSL();
                currentQuantity++;
                cartItem.setSL(currentQuantity);
                holder.Quantity.setText(String.valueOf(currentQuantity));
                holder.ProductPrice.setText(String.valueOf(calculateTotalPrice(cartItem)));
            }
        });
    }

    private int calculateTotalPrice(CartDTO cartItem) {
        int pricePerItem = cartItem.getTongTien();
        int quantity = cartItem.getSL();
        return pricePerItem * quantity;
    }

    private void deleteCartItem(CartDTO cartItem, int position) {
        CartDAO cartDAO = new CartDAO();
        cartDAO.deleteData(cartItem, userId);
        datalist.remove(position);
        notifyItemRemoved(position);
    }







    @Override
    public int getItemCount() {
        return datalist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageProduct;
        private TextView ProductName, delete, btnMinus, btnPlus, Quantity, ProductPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            ProductName = itemView.findViewById(R.id.ProductName);
            delete = itemView.findViewById(R.id.delete);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            Quantity = itemView.findViewById(R.id.Quantity);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
        }

        public void SetIMG(CallFirebaseStrorage callFirebaseStrorage, String imgURL){
            StorageReference mountainRef = callFirebaseStrorage.getStorageRef().child(imgURL);

            final long ONE_MEGABYTE = 1024 * 1024;
            mountainRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    // Convert bytes to Bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    // Set the Bitmap to the ImageView
                    imageProduct.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("Kien Test ProductItem", "" + imgURL + " "+ exception.toString());
                }
            });
        }
    }
}

