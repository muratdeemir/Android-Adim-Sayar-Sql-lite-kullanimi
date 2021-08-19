package com.example.denemem.swipeandtabs.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.denemem.swipeandtabs.Model.Food;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteAssetHelper {

    private static final String DB_NAME="Foods.db";
    private static final int DB_VER=1;


    public DataBase(Context context) {
        super(context, DB_NAME, null, null, DB_VER);
    }

    //Function get all friends
    public List<Food> getFriends(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Make sure all is colum name in your Table
        String[] sqlSelect={"id","Name","Calory","Gram"};
        String tableName="Foods";//Make sure this is your table name


        qb.setTables(tableName);
        Cursor cursor= qb.query(db, sqlSelect, null, null, null, null, null);
        List<Food> result= new ArrayList<>();
        if (cursor.moveToFirst())   {
            do {
                Food foods= new Food();
                foods.setId(cursor.getInt(cursor.getColumnIndex("id")));
                foods.setName(cursor.getString(cursor.getColumnIndex("Name")));
                foods.setCalory(cursor.getString(cursor.getColumnIndex("Calory")));
                foods.setGram(cursor.getString(cursor.getColumnIndex("Gram")));

                result.add(foods);
            }   while (cursor.moveToNext());
        }
        return result;
    }

    //Function get all friend's name
    public List<String> getNames()  {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        //Make sure all is colum name in your Table
        String[] sqlSelect={"Name"};
        String tableName="Foods";//Make sure this is your table name

        qb.setTables(tableName);
        Cursor cursor= qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result= new ArrayList<>();
        if (cursor.moveToFirst())   {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Name")));

            }   while (cursor.moveToNext());
        }
        return result;
    }

}
