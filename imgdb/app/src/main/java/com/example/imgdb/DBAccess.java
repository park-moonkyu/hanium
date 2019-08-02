package com.example.imgdb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBAccess extends SQLiteOpenHelper{
    private static String DB_NAME = "money.db";
    private SQLiteDatabase myDatabase;
    private final Context myContext;
    private static String DB_PATH;
    //@SuppressLint("SdCardPath")
    public DBAccess(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DB_NAME, factory, version);
        this.myContext = context;
        DB_PATH = "/data/data/" + myContext.getApplicationContext().getPackageName() + "/databases/";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public void openDatabase() throws IOException {
        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createDatabase() throws IOException {
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        }catch(SQLiteException e){
        }
        if(checkDB != null){
            checkDB.close();
        }
        boolean dbExist = checkDB != null ? true : false ;
        if(dbExist){
        }else{
            this.getReadableDatabase();
            try{
                InputStream myInput = myContext.getAssets().open(DB_NAME);
                String outFileName = DB_PATH + DB_NAME;
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte [] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer))>0) {
                    myOutput.write(buffer,0,length);
                }
                myOutput.flush();
                myOutput.close();
                myInput.close();
            }catch(IOException e){
                throw new Error("Error");
            }
        }
    }

    public String PrintData() {
      //  SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = myDatabase.rawQuery("select name from images", null);
        while(cursor.moveToNext()) {
            str += "Name : " + cursor.getString(0) ;
        }
        return str;
    }


    public Bitmap getBlob(){
        //byte [] img;
        Bitmap bitmap = null;
        Cursor cursor = myDatabase.rawQuery("select image from images", null);
        while(cursor.moveToNext()) {
            byte[] img = cursor.getBlob(0);
            //ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            //bitmap = BitmapFactory.decodeStream(imageStream);
            bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        }
        return bitmap;
    }

}
