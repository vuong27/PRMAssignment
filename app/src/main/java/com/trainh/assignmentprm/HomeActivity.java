package com.trainh.assignmentprm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.trainh.assignmentprm.adapter.ProductAdapter;
import com.trainh.assignmentprm.database.Database;
import com.trainh.assignmentprm.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ProductAdapter.SelectedProduct{

    Database database;
    TextView tvUsername;
    List<Product> productList;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = new Database(getApplicationContext());

        SharedPreferences settings = getApplicationContext().getSharedPreferences("username", 0);
        String username = settings.getString("username", "username");
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvUsername.setText(username);
        productList = getProductComputer();
        recyclerView = (RecyclerView) findViewById(R.id.rvComputer);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        productAdapter = new ProductAdapter(productList,  this);

        recyclerView.setAdapter(productAdapter);


    }

    private List<Product> getProductComputer() {
        List<Product> products = new ArrayList<Product>();
        Cursor dataProduct = database.GetData("SELECT * FROM product WHERE type = 'Máy tính'");
        while (dataProduct.moveToNext()) {
            Product product = new Product(dataProduct.getInt(0), dataProduct.getString(1), dataProduct.getString(2), dataProduct.getDouble(3), dataProduct.getString(4), dataProduct.getString(5));
            Log.d("product", dataProduct.getString(2));
            products.add(product);
        }
        return products;
    }

    @Override
    public void selectedProduct(Product product) {
//        startActivity(new Intent(this, HomeActivity.this, ));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}