package com.exmaple.csn_291project.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.exmaple.csn_291project.list_parameters.list_parameters;
import com.exmaple.csn_291project.model.lists;

import java.util.ArrayList;
import java.util.List;

public class MyListDBHandler extends SQLiteOpenHelper {

    public MyListDBHandler(Context context){
        super(context, list_parameters.DB_NAME,null,list_parameters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String create = "CREATE TABLE " + list_parameters.DB_TABLE_NAME + "(" + list_parameters.KEY_ID + " INTEGER PRIMARY KEY, " + list_parameters.KEY_NAME + " TEXT)";
        Log.d("CSN_291","QUERY BEING RUN IS " + create);
        db.execSQL(create);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }

    public void addList(lists List){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(list_parameters.KEY_NAME,List.getName());
        db.insert(list_parameters.DB_TABLE_NAME,null,values);
        Log.d("CSN_291","Successfully Inserted");
        db.close();
    }

    public List<lists> getAllLists(){
        List<lists> Array_of_ListNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the query to read from the database
        String select = "SELECT * FROM " + list_parameters.DB_TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                lists List = new lists();
                List.setId(Integer.parseInt(cursor.getString(0)));
                List.setName(cursor.getString(1));
                Array_of_ListNames.add(List);
            }while(cursor.moveToNext());
        }
        return Array_of_ListNames;
    }

    public int updateList(lists List){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(list_parameters.KEY_NAME, List.getName());

        //Lets update now
        return db.update(list_parameters.DB_TABLE_NAME, values, list_parameters.KEY_ID + "=?",
                new String[]{String.valueOf(List.getId())});

    }

    public void deleteListById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(list_parameters.DB_TABLE_NAME, list_parameters.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteList(lists List){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(list_parameters.DB_TABLE_NAME, list_parameters.KEY_ID +"=?", new String[]{String.valueOf(List.getId())});
        db.close();
    }

    public int getCount(){
        String query = "SELECT  * FROM " + list_parameters.DB_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();

    }

}
