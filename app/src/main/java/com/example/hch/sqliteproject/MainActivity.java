package com.example.hch.sqliteproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hch.sqliteproject.DO.DAO;
import com.example.hch.sqliteproject.Recycler.Friends.FriendsAdapter;

public class MainActivity extends AppCompatActivity {
    private final String TAG="MainActivity";
    public final int Main_ADD_SIGNAL = 1001;

    private Button Add;
    private TextView Notify;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setUI();
        event();
    }

    private void init(){
        //variable
        DAO.init_freindsList(getApplicationContext());
        DAO.loadData_freindsList();

        //layout
        Add = (Button) findViewById(R.id.friends_add);
        Notify = (TextView) findViewById(R.id.friends_noList_notify_text);
        recyclerView = (RecyclerView) findViewById(R.id.friends_recyclerList);
    }

    private void event(){
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MotifyItemActivity.class);
                intent.putExtra("func", 0);
                startActivityForResult(intent, Main_ADD_SIGNAL);
            }
        });
    }

    private void setUI(){
        if(DAO.friendsList.size() <= 0){
            recyclerView.setVisibility(View.GONE);
        }else{
            Notify.setVisibility(View.GONE);

            setRecyclerView();
        }
    }

    private void setRecyclerView(){
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new FriendsAdapter(MainActivity.this, recyclerView));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startCommentActivity(int position){
        Intent intent = new Intent(getApplicationContext(), MotifyItemActivity.class);
        intent.putExtra("func", 1);
        intent.putExtra("pos", position);
        startActivityForResult(intent, Main_ADD_SIGNAL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult : " + requestCode);

        if(requestCode == Main_ADD_SIGNAL){
            if(resultCode == RESULT_OK){
                String order = data.getStringExtra("order");

                if(order.equals("0")){
                    DAO.loadData_freindsList();

                    if(DAO.friendsList.size() <= 1){
                        setUI();
                    }else{
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                    Toast.makeText(getApplicationContext(), "등록을 완료하였습니다.", Toast.LENGTH_SHORT).show();


                    setUI();
                }else{
                    DAO.loadData_freindsList();
                    Toast.makeText(getApplicationContext(), "수정을 완료하였습니다.", Toast.LENGTH_SHORT).show();
                    recyclerView.getAdapter().notifyDataSetChanged();
                }


            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "등록을 취소하였습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
