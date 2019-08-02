package com.example.imgdb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBAccess dbManager = new DBAccess(getApplicationContext(), "money.db", null, 1);
        try {
            dbManager.createDatabase();
            dbManager.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 쿼리 결과 입력
        final TextView tvResult = (TextView) findViewById(R.id.tv_result);
        //tvResult.setText( dbManager.PrintData() );

        final ImageView blobImg = (ImageView) findViewById(R.id.b_img);
        blobImg.setImageBitmap(dbManager.getBlob());


        // Select
//        Button btnSelect = (Button) findViewById(R.id.btn_select);
//        btnSelect.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                tvResult.setText( dbManager.PrintData() );
//            }
//        });
    }

}
