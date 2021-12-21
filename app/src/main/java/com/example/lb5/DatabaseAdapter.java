package com.example.lb5;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseAdapter {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context) {
        databaseHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public List<CorpsInfo> getCorps(){
        List<CorpsInfo> corps = new ArrayList<>();
        Cursor cursor = getECorps();
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID1));
            @SuppressLint("Range") String corps_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CorpsName));
            corps.add(new CorpsInfo(id, corps_name));
        }
        return corps;
    }
    private Cursor getECorps(){
        return database.query(DatabaseHelper.TABLE1,
                new String[] {DatabaseHelper.COLUMN_ID1, DatabaseHelper.COLUMN_CorpsName},
                null, null, null, null, null);
    }


    public List<FoundersInfo> getFounders(){
        List<FoundersInfo> founders = new ArrayList<>();
        Cursor cursor = getEFounders();
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID2));
            @SuppressLint("Range") String founders_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FoundersName));
            founders.add(new FoundersInfo(id, founders_name));
        }
        cursor.close();
        return founders;
    }
    private Cursor getEFounders(){
        return database.query(DatabaseHelper.TABLE2,
                new String[] {DatabaseHelper.COLUMN_ID2, DatabaseHelper.COLUMN_FoundersName},
                null, null, null, null, null);
    }

    public List<ProductsInfo> getProducts(){
        List<ProductsInfo> products = new ArrayList<>();
        Cursor cursor = getEProducts();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID3));
            @SuppressLint("Range") String products_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductsName));
            @SuppressLint("Range") String products_category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductCategory));
            @SuppressLint("Range") String products_price = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductPrice));
            products.add(new ProductsInfo(id, products_name, products_category, products_price));
        }
        return products;
    }
    private Cursor getEProducts(){
        return database.query(DatabaseHelper.TABLE3,
                new String[] {DatabaseHelper.COLUMN_ID3, DatabaseHelper.COLUMN_ProductsName, DatabaseHelper.COLUMN_ProductCategory,
                        DatabaseHelper.COLUMN_ProductPrice},
                null, null, null, null, null);
    }

    public List<UserInfo> getUser(){
        List<UserInfo> users = new ArrayList<>();
        Cursor cursor = getEUser();
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID4));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_UsersEmail));
            @SuppressLint("Range") String login = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_UsersLogin));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_UsersPass));
            users.add(new UserInfo(id, email, login, password));
        }
        return users;
    }
    private Cursor getEUser(){
        return database.query(DatabaseHelper.TABLE4,
                new String[] {DatabaseHelper.COLUMN_ID4, DatabaseHelper.COLUMN_UsersLogin,
                        DatabaseHelper.COLUMN_UsersPass, DatabaseHelper.COLUMN_UsersEmail},
                null, null, null, null, null);
    }

    public CorpsInfo getCorp(int id){
        CorpsInfo corp = null;
        String query = String.format("Select * from %s where %s = ?", DatabaseHelper.TABLE1, DatabaseHelper.COLUMN_ID1);
        Cursor cursor = database.rawQuery(query, new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            @SuppressLint("Range") String corp_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CorpsName));
            corp = new CorpsInfo(id, corp_name);
        }
        cursor.close();
        return corp;
    }
    public FoundersInfo getFounder(int id){
        FoundersInfo founder = null;
        String query = String.format("Select * from %s where %s = ?", DatabaseHelper.TABLE2, DatabaseHelper.COLUMN_ID2);
        Cursor cursor = database.rawQuery(query, new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FoundersName));
            founder = new FoundersInfo(id, name);
        }
        cursor.close();
        return founder;
    }
    public ProductsInfo getProduct(int id){
        ProductsInfo product = null;
        String query = String.format("Select * from %s where %s = ?", DatabaseHelper.TABLE3, DatabaseHelper.COLUMN_ID3);
        Cursor cursor = database.rawQuery(query, new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            @SuppressLint("Range") String product_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductsName));
            @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductCategory));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductPrice));
            product = new ProductsInfo(id, product_name, category, price);
        }
        cursor.close();
        return product;
    }
   /* public All_info getAllInfo(int id){
        All_info info = null;
        String query = String.format("Select * from %s where %s = ?", DatabaseHelper.TABLE4, DatabaseHelper.COLUMN_ID4);
        Cursor cursor = database.rawQuery(query, new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()){
            @SuppressLint("Range") String corps_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CorpsName1));
            @SuppressLint("Range") String founders_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FName));
            @SuppressLint("Range") String products_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PName));
            @SuppressLint("Range") String products_category = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductCategory1));
            @SuppressLint("Range") String products_price = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ProductPrice1));
            info = new All_info(id, corps_name, founders_name, products_name, products_category, products_price);
        }
        cursor.close();
        return info;
    }
*/

    public long addCorps(CorpsInfo corp){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_CorpsName, corp.getCorpName());
        return database.insert(DatabaseHelper.TABLE1, null, contentValues);
    }

    public long addFounders(FoundersInfo founder){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_FoundersName, founder.getFounderName());
        return database.insert(DatabaseHelper.TABLE2, null, contentValues);
    }

    public long addProducts(ProductsInfo product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_ProductsName, product.getProductName());
        contentValues.put(DatabaseHelper.COLUMN_ProductCategory, product.getCategory());
        contentValues.put(DatabaseHelper.COLUMN_ProductPrice, product.getPrice());
        return database.insert(DatabaseHelper.TABLE3, null, contentValues);
    }
    public long addUsers(@NonNull UserInfo user){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_UsersLogin, user.getLogin());
        contentValues.put(DatabaseHelper.COLUMN_UsersPass, user.getPass());
        contentValues.put(DatabaseHelper.COLUMN_UsersEmail, user.getEmail());
        return database.insert(DatabaseHelper.TABLE4, null, contentValues);
    }


    public int editCorps(CorpsInfo corps){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_CorpsName, corps.getCorpName());
        return database.update(DatabaseHelper.TABLE1, contentValues, DatabaseHelper.COLUMN_ID1 + "=" + corps.getId(), null);
    }
    public int editFounders(FoundersInfo founders){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_FoundersName, founders.getFounderName());
        return database.update(DatabaseHelper.TABLE2, contentValues,DatabaseHelper.COLUMN_ID2 + "=" + founders.getId(), null);
    }
    public int editProducts(ProductsInfo product){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_ProductsName, product.getProductName());
        contentValues.put(DatabaseHelper.COLUMN_ProductCategory, product.getCategory());
        contentValues.put(DatabaseHelper.COLUMN_ProductPrice, product.getPrice());
        return database.update(DatabaseHelper.TABLE3, contentValues, DatabaseHelper.COLUMN_ID3 + "=" + product.getId(), null);
    }
    /*
    public int editAllInfo(All_info info){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_CorpsName1, info.getAllCorps());
        contentValues.put(DatabaseHelper.COLUMN_FName, info.getAllFounders());
        contentValues.put(DatabaseHelper.COLUMN_PName, info.getAllProducts());
        contentValues.put(DatabaseHelper.COLUMN_ProductCategory1, info.getAllPcategory());
        contentValues.put(DatabaseHelper.COLUMN_ProductPrice1, info.getAllPprice());
        return database.update(DatabaseHelper.TABLE3, contentValues, DatabaseHelper.COLUMN_ID4 + "=" + info.getId(), null);
    } */

    public int delCorps(int id){
        return database.delete(DatabaseHelper.TABLE1, "id = ?", new String[]{String.valueOf(id)});
    }
    public int delFounders(int id){
        return database.delete(DatabaseHelper.TABLE2, "id = ?", new String[]{String.valueOf(id)});
    }
    public int delProducts(int id){
        return database.delete(DatabaseHelper.TABLE3, "id = ?", new String[]{String.valueOf(id)});
    }
    /*
    public int delAllInfo(int id){
        return database.delete(DatabaseHelper.TABLE4, "id = ?", new String[]{String.valueOf(id)});
    }*/



   public Cursor sortbyname(){
        ContentValues contentValues = new ContentValues();
        return database.rawQuery(
                "Select * from products order by products.ProductsName", null);

    }

    public Cursor groupbycategory(String cat){
        ContentValues contentValues = new ContentValues();
        return database.rawQuery(
                "Select * from products where products.ProductsCategory like '%" + cat + "%'", null);

    }

    public Cursor getFullCost(){
        ContentValues contentValues = new ContentValues();
        return database.rawQuery(
                "Select SUM(products.ProductsPrice) from products", null);

    }

    public Cursor getAvgCost(){
        ContentValues contentValues = new ContentValues();
            return database.rawQuery(
                    "Select AVG(products.ProductsPrice) from products", null);
    }



    public DatabaseAdapter openDBAdapter(){
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void closeDBAdapter(){
        databaseHelper.close();
    }
}
