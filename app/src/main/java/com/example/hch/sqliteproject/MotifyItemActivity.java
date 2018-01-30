package com.example.hch.sqliteproject;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hch.sqliteproject.DO.DAO;
import com.example.hch.sqliteproject.DO.Friends;

public class MotifyItemActivity extends AppCompatActivity {
    private final String TAG = "MotifyItemActivity";
    private int order;
    private String f_id;

    private Button add;
    private Button cancel;

    private EditText name;
    private EditText phone;
    private EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motify_item);

        init();

        order = getIntent().getIntExtra("func", 0);
        if(order== 0){
            setTitle("추가하기");
        }else if(order==1){
            setTitle("수정하기");

            Friends friends = DAO.friendsList.get(getIntent().getIntExtra("pos", 0));
            name.setText(friends.getF_name());
            phone.setText(friends.getF_phone());
            comment.setText(friends.getF_comment());
            f_id = friends.getF_id()+"";
        }else{
            Toast.makeText(getApplicationContext(), "잘못된 명령입니다.\n다시 시도해주세요.", Toast.LENGTH_LONG).show();
            finish();
        }


        event();
    }

    private void init(){
        add = (Button) findViewById(R.id.modify_ok_BTN);
        cancel =  (Button) findViewById(R.id.modify_cancel_BTN);
        name = (EditText) findViewById(R.id.modify_name);
        phone = (EditText) findViewById(R.id.modify_phone);
        comment = (EditText) findViewById(R.id.modify_comment);
    }

    private void event(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name = name.getText().toString();
                String s_phone = phone.getText().toString();
                String s_comment = comment.getText().toString();
                if(s_name.equals("") || s_phone.equals("")){
                    Toast.makeText(getApplicationContext(), "이름과 번호는 필수입니다.", Toast.LENGTH_SHORT).show();
                }else{
                    if(order == 0){
                        DAO.handler.insert(s_name, s_phone, s_comment);
                        Intent intent = new Intent();
                        intent.putExtra("order", order+"");
                        setResult(RESULT_OK, intent);
                    }else{
                        //order == 1 onCreate 에서 예외상황 제외함.
                        DAO.handler.updeate(f_id, s_name, s_phone, s_comment);
                        Intent intent = new Intent();
                        intent.putExtra("order", order+"");
                        setResult(RESULT_OK, intent);
                    }
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
