package com.trainh.assignmentprm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.trainh.assignmentprm.adapter.ProductAdapter;
import com.trainh.assignmentprm.database.Database;
import com.trainh.assignmentprm.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class thanh_toan_page extends AppCompatActivity implements ProductAdapter.SelectedProduct {

    Database database;
    TextView tvUsername;
    List<Product> productComputer;
    List<Product> productKeyboard;
    RecyclerView rvComputer;
    RecyclerView rvKeyboard;
    ProductAdapter productAdapterComputer;
    ProductAdapter productAdapterKeyboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_page);
        Button btndathang = findViewById(R.id.btndathang);
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dathang();
            }
        });
        database = new Database(getApplicationContext());

        SharedPreferences settings = getApplicationContext().getSharedPreferences("username", 0);
        String username = settings.getString("username", "username");
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUsername.setText(username);
        productComputer = getProductComputer();
        productKeyboard = getProductKeyboard();
        rvComputer = (RecyclerView) findViewById(R.id.rvComputer);
        rvKeyboard = (RecyclerView) findViewById(R.id.rvKeyboard);

        LinearLayoutManager linearLayoutManagerComputer = new LinearLayoutManager(this);
        linearLayoutManagerComputer.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager linearLayoutManagerKeyboard = new LinearLayoutManager(this);
        linearLayoutManagerKeyboard.setOrientation(LinearLayoutManager.HORIZONTAL);


        rvComputer.setLayoutManager(linearLayoutManagerComputer);
        rvKeyboard.setLayoutManager(linearLayoutManagerKeyboard);

        productAdapterComputer = new ProductAdapter(productComputer,  this);

        productAdapterKeyboard = new ProductAdapter(productKeyboard,  this);

        rvComputer.setAdapter(productAdapterComputer);
        rvKeyboard.setAdapter(productAdapterKeyboard);
    }
    private void dathang() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dat_hang_dialog);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        Button btncontinues = dialog.findViewById(R.id.tieptuc);
        btncontinues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
    private List<Product> getProductComputer() {
        List<Product> products = new ArrayList<Product>();
        Cursor dataProduct = database.GetData("SELECT * FROM product WHERE type = 'Máy tính'");
        while (dataProduct.moveToNext()) {
            Product product = new Product(dataProduct.getInt(0), dataProduct.getInt(1), dataProduct.getString(2), dataProduct.getDouble(3), dataProduct.getString(4), dataProduct.getString(5));
            Log.d("product", dataProduct.getString(2));
            products.add(product);
        }
        return products;
    }

    private List<Product> getProductKeyboard() {
        List<Product> products = new ArrayList<Product>();
        Cursor dataProduct = database.GetData("SELECT * FROM product WHERE type = 'Bàn phím'");
        while (dataProduct.moveToNext()) {
            Product product = new Product(dataProduct.getInt(0), dataProduct.getInt(1), dataProduct.getString(2), dataProduct.getDouble(3), dataProduct.getString(4), dataProduct.getString(5));
            Log.d("product", dataProduct.getString(2));
            products.add(product);
        }
        return products;
    }

    @Override
    public void selectedProduct(Product product) {
        Log.d("selected product", product.getName());
    }
}