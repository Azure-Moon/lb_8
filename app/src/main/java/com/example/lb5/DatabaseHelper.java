package com.example.lb5;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Objects;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "corpsinfo.db";
    private static final int SCHEMA = 1;
    static final String TABLE1 = "company";
    static final String TABLE2 = "founders";
    static final String TABLE3 = "products";
    static final String TABLE4 = "users";

    public static final String COLUMN_ID1 = "id";
    public static final String COLUMN_CorpsName = "CompanysName";

    public static final String COLUMN_ID2 = "id";
    public static final String COLUMN_FoundersName = "FoundersName";

    public static final String COLUMN_ID3 = "id";
    public static final String COLUMN_ProductsName = "ProductsName";
    public static final String COLUMN_ProductCategory = "ProductsCategory";
    public static final String COLUMN_ProductPrice = "ProductsPrice";

    public static final String COLUMN_ID4 = "id";
    public static final String COLUMN_UsersEmail = "EMail";
    public static final String COLUMN_UsersLogin = "Login";
    public static final String COLUMN_UsersPass = "Password";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE1 + " (" + COLUMN_ID1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CorpsName + " TEXT);");


        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE2 + " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FoundersName + " TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE3 + " (" + COLUMN_ID3 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ProductsName + " TEXT," + COLUMN_ProductCategory + " TEXT," + COLUMN_ProductPrice
                + " TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE4 + " (" + COLUMN_ID4 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_UsersLogin + " TEXT," + COLUMN_UsersPass + " TEXT," + COLUMN_UsersEmail
                + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3);
    }


}
