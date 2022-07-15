
package com.trainh.assignmentprm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.trainh.assignmentprm.entities.Account;
import com.trainh.assignmentprm.entities.Cart;
import com.trainh.assignmentprm.entities.Product;

public class Database extends SQLiteOpenHelper {

    private static final String dbName = "storeBanDoDB";
    private static final int dbVersion = 1;

    private static final String accountTable = "account";
    private static final String productTable = "product";
    private static final String cartTable = "cart";

    private static final String idColumn = "id";

    private static final String usernameColumn = "username";
    private static final String passwordColumn = "password";

    private static final String imageColumn = "image";
    private static final String nameColumn = "name";
    private static final String priceColumn = "price";
    private static final String typeColumn = "type";
    private static final String descriptionColumn = "descriptionColumn";
    private static final String totalColumn = "total";


//    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, dbName, null, dbVersion);
//    }

    public Database(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + accountTable + "(" +
                idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                usernameColumn + " TEXT ," +
                passwordColumn + " TEXT " +
                ")");

        db.execSQL("CREATE TABLE " + productTable + "(" +
                idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                imageColumn + " TEXT ," +
                nameColumn + " TEXT ," +
                priceColumn + " INTEGER ," +
                typeColumn + " TEXT ," +
                descriptionColumn + " TEXT " +
                ")");
        db.execSQL("CREATE TABLE " + cartTable + "(" +
                idColumn + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                nameColumn + " TEXT ," +
                imageColumn + " TEXT ," +
                priceColumn + " INTEGER ," +
                totalColumn + " INTEGER " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean createAccount(Account account) {
        try {
            SQLiteDatabase sqlite = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(usernameColumn, account.getUsername());
            contentValues.put(passwordColumn, account.getPassword());
            return sqlite.insert(accountTable, null, contentValues) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createProduct(Product product) {
        try {
            SQLiteDatabase sqlite = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(imageColumn, product.getImage());
            contentValues.put(nameColumn, product.getName());
            contentValues.put(priceColumn, product.getPrice());
            contentValues.put(typeColumn, product.getType());
            contentValues.put(descriptionColumn, product.getDescription());
            return sqlite.insert(productTable, null, contentValues) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean createCart(Cart cart) {
        try {
            SQLiteDatabase sqlite = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(imageColumn, cart.getImage());
            contentValues.put(nameColumn, cart.getName());
            contentValues.put(priceColumn, cart.getPrice());
            contentValues.put(totalColumn, cart.getTotal());
            return sqlite.insert(cartTable, null, contentValues) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

}
