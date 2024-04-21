package com.example.jolebefood.AdapterRecycleView;

import android.annotation.SuppressLint;
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
import com.example.jolebefood.DAO.CartDAO.OnGetListCartListener;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.StorageReference;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class Cart_Item extends RecyclerView.Adapter<Cart_Item.MyViewHolder> {
    View view;
    ArrayList<CartDTO> datalist;
    CallFirebaseStrorage callFirebaseStrorage;
    private String userId;
    TextView total_pay;
    Currency currency = Currency.getInstance(new Locale("vi", "VN"));
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public Cart_Item(ArrayList<CartDTO> datalist, TextView total_pay) {
        this.datalist = datalist;
        this.total_pay = total_pay;
        callFirebaseStrorage = new CallFirebaseStrorage();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();
        currencyFormat.setCurrency(currency);
    }

    @NonNull
    @Override
    public Cart_Item.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new Cart_Item.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        CartDTO cartItem = datalist.get(position);

        holder.ProductName.setText(cartItem.getTenMonAn());
        holder.Quantity.setText(String.valueOf(cartItem.getSL()));
        holder.ProductPrice.setText(currencyFormat.format(cartItem.getTongTien()));
        holder.SetIMG(callFirebaseStrorage, cartItem.getImage());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCartItem(cartItem, position);
                notifyItemRemoved(position);
                calculateTotalAmount();
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = cartItem.getSL();
                if (currentQuantity > 1) {
                    currentQuantity--;
                    cartItem.setSL(currentQuantity);
                    int totalPrice = calculateTotalPrice1(cartItem);
                    cartItem.setTongTien(totalPrice);
                    holder.Quantity.setText(String.valueOf(currentQuantity));
                    holder.ProductPrice.setText(currencyFormat.format(totalPrice));
                    updateCartItem(v.getContext(), cartItem);
                    notifyDataSetChanged();
                    calculateTotalAmount();
                }
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentQuantity = cartItem.getSL();
                currentQuantity++;
                cartItem.setSL(currentQuantity);
                int totalPrice = calculateTotalPrice(cartItem);
                cartItem.setTongTien(totalPrice);
                holder.Quantity.setText(String.valueOf(currentQuantity));
                holder.ProductPrice.setText(currencyFormat.format(totalPrice));
                updateCartItem(v.getContext(), cartItem);
                notifyDataSetChanged();
                calculateTotalAmount();
            }
        });
    }

    private int calculateTotalPrice(CartDTO cartItem) {
        int pricePerItem = cartItem.getTongTien();
        int quantity = cartItem.getSL();
        if (quantity == 2) {
            return pricePerItem * quantity;
        } else {
            return pricePerItem / (quantity - 1) * quantity;
        }
    }

    private int calculateTotalPrice1(CartDTO cartItem) {
        int pricePerItem = cartItem.getTongTien();
        int quantity = cartItem.getSL();
        return pricePerItem / (quantity + 1) * quantity;
    }

    private void deleteCartItem(CartDTO cartItem, int position) {
        CartDAO cartDAO = new CartDAO();
        cartDAO.deleteData(cartItem, userId);
        if (position < datalist.size()) {
            datalist.remove(position);
            notifyItemRemoved(position);
        }
    }

    private void updateCartItem(Context context, CartDTO cartItem) {
        CartDAO cartDAO = new CartDAO();
        cartDAO.SetData(context, cartItem, userId);
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

        public void SetIMG(CallFirebaseStrorage callFirebaseStrorage, String imgURL) {
            StorageReference mountainRef = callFirebaseStrorage.getStorageRef().child(imgURL);

            final long ONE_MEGABYTE = 1024 * 1024;
            mountainRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    imageProduct.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e("Kien Test ProductItem", "" + imgURL + " " + exception.toString());
                }
            });
        }
    }

    private void calculateTotalAmount() {
        int totalAmount = 0;
        for (CartDTO item : datalist) {
            totalAmount += item.getTongTien();
        }
        total_pay.setText(currencyFormat.format(totalAmount));
    }
}

