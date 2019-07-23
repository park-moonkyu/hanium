package com.example.mydb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBManager dbManager = new DBManager(getApplicationContext(), "gift.db", null, 1);

        ///////////////////////////////////////////////////////////
        // 입력 받지 않을 경우 삭제 가능

 /*       // DB에 저장 될 속성을 입력받는다
        final EditText etName = (EditText) findViewById(R.id.et_foodname);
        final EditText etPrice = (EditText) findViewById(R.id.et_price);

        // 쿼리 결과 입력
        final TextView tvResult = (TextView) findViewById(R.id.tv_result);

        // Insert
        Button btnInsert = (Button) findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값...);
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                dbManager.insert("insert into GIFR_LIST values(null, '" + name + "', " + price + ");");

                tvResult.setText( dbManager.PrintData() );
            }
        });*/

        // 쿼리 결과 입력
        final TextView tvResult = (TextView) findViewById(R.id.tv_result);
     //   tvResult.setText( dbManager.PrintData() );

        // Insert
        // create를 할때 pk에 unique 조건이 있으므로 같은 행이 삽입되면 오류 발생
        //insert -> insert or ignore로 중복제거
        //임의로 데이터 넣기
        dbManager.insert("insert or ignore into GIFT_LIST values('org_image1', '해피머니', 5000);");
        dbManager.insert("insert or ignore into GIFT_LIST values('org_image2', '해피머니', 10000);");
        dbManager.insert("insert or ignore into GIFT_LIST values('org_image3', '컬쳐랜드', 5000);");
        dbManager.insert("insert or ignore into GIFT_LIST values('org_image4', '컬쳐랜드', 10000);");
        dbManager.insert("insert or ignore into GIFT_LIST values('org_image5', '신세계', 5000);");
        dbManager.insert("insert or ignore into GIFT_LIST values('org_image6', '신세계', 10000);");

/*
        // Update
        Button btnUpdate = (Button) findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // update 테이블명 where 조건 set 값;
                String name = etName.getText().toString();
                String price = etPrice.getText().toString();
                dbManager.update("update FOOD_LIST set price = " + price + " where name = '" + name + "';");

                tvResult.setText( dbManager.PrintData() );
            }
        });
*/


/*        // Delete
        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // delete from 테이블명 where 조건;
                String name = etName.getText().toString();
                dbManager.delete("delete from FOOD_LIST where name = '" + name + "';");

                tvResult.setText( dbManager.PrintData() );
            }
        });
*/

        // Select
        Button btnSelect = (Button) findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                tvResult.setText( dbManager.PrintData() );
            }
        });
    }
}