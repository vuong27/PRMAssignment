package com.trainh.assignmentprm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.trainh.assignmentprm.adapter.CartAdapter;

public class CartTv extends AppCompatActivity {

    private CartAdapter adapter;
    private TextView total;

    private RecyclerView recyclerviewgiohang;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_tv);


        Button btnthanhtoan = findViewById(R.id.btnthanhtoan);
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartTv.this, thanh_toan_page.class);
                startActivity(intent);
            }
        });

    }
}