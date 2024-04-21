package com.example.jolebefood.AdapterRecycleView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jolebefood.DAO.CallFirebaseStrorage;
import com.example.jolebefood.DAO.CartDAO.CartDAO;
import com.example.jolebefood.DTO.CartDTO;
import com.example.jolebefood.DTO.ProductDTO;
import com.example.jolebefood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Product_Item extends RecyclerView.Adapter<Product_Item.MyViewHolder> {

    Context context;
    ArrayList<ProductDTO> dataList;

    CallFirebaseStrorage callFirebaseStrorage;

    String userID;

    NumberFormat currencyFormat;

    public Product_Item(ArrayList<ProductDTO> dataList, String userID) {

        this.dataList = dataList;

        callFirebaseStrorage = new CallFirebaseStrorage();

        this.userID = userID;

        // Tạo một đối tượng NumberFormat với định dạng tiền tệ và quốc gia
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.bindData(dataList.get(position));
        holder.name_product.setText(dataList.get(position).getTenMonAn());
        holder.price_product.setText(currencyFormat.format(dataList.get(position).getGia()));
        holder.quantity_sold.setText("Đã bán " + dataList.get(position).getSoluongdaban());
        holder.descripe.setText(dataList.get(position).getMoTa());
        holder.SetIMG(callFirebaseStrorage,dataList.get(position).getIMG());

        holder.add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy Drawable từ ImageView
                Drawable drawable = holder.product_picture.getDrawable();
                // Kiểm tra xem Drawable có tồn tại không
                if (drawable instanceof BitmapDrawable) {

                    Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                    ShowDialog(v.getContext(),dataList.get(position),bitmap);
                } else {
                    Toast.makeText(v.getContext(), "Hãy chờ hệ thống load dữ liệu",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_product, price_product, descripe, quantity_sold;
        ImageView product_picture;
        ImageButton add_product;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_product = itemView.findViewById(R.id.name_product);
            price_product = itemView.findViewById(R.id.price_product);
            product_picture = itemView.findViewById(R.id.img_product);
            add_product = itemView.findViewById(R.id.add_productbtn);
            descripe = itemView.findViewById(R.id.describe_txt);
            quantity_sold = itemView.findViewById(R.id.sale_count);
        }

       public void bindData(ProductDTO dto1) {


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
                    product_picture.setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@androidx.annotation.NonNull Exception exception) {
                    Log.e("Kien Test ProductItem", "" + imgURL + " "+ exception.toString());
                }
            });
        }
    }


    private void ShowDialog(Context context, ProductDTO item, Bitmap fileBitmap) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottom_sheet_layout);

        TextView txtName = dialog.findViewById(R.id.txtNameProduct);
        TextView txtGia = dialog.findViewById(R.id.txtPriceProduct);
        EditText editText = dialog.findViewById(R.id.edtSL);
        TextView btnPlus = dialog.findViewById(R.id.btnPlusProduct);
        TextView btnMinus = dialog.findViewById(R.id.btnMinusProduct);
        Button btnAddCart = dialog.findViewById(R.id.btnAddCart);
        ImageView img = dialog.findViewById(R.id.imgProduct);

        txtName.setText(item.getTenMonAn());
        txtGia.setText(currencyFormat.format(item.getGia()));
        img.setImageBitmap(fileBitmap);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int intValue;
                try {
                    intValue = Integer.parseInt(editText.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Nhập số", Toast.LENGTH_SHORT).show();
                    return;
                }

                int sl_new = intValue + 1;

                editText.setText(Integer.toString(sl_new));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int intValue;
                try {
                    intValue = Integer.parseInt(editText.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Nhập số", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (intValue == 1){
                    return;
                }
                int sl_new = intValue - 1;

                editText.setText(Integer.toString(sl_new));
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Phương thức này được gọi trước khi văn bản trong EditText thay đổi.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int intValue;
                try {
                    intValue = Integer.parseInt(editText.getText().toString());
                } catch (NumberFormatException e) {
                    return;
                }

                if (intValue == 0){
                    Toast.makeText(context, "Số lượng phải lớn 0", Toast.LENGTH_SHORT).show();
                    editText.setText("1");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Phương thức này được gọi sau khi văn bản trong EditText thay đổi.
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int intValue;
                try {
                    intValue = Integer.parseInt(editText.getText().toString());
                } catch (NumberFormatException e) {
                    return;
                }

                CartDTO cart = new CartDTO(item.getMaMonAn(),item.getTenMonAn(),intValue,"/"+item.getMaMonAn()+".jpg",item.getGia());
                new CartDAO().SetData(context,cart,userID);
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);


    }


}
