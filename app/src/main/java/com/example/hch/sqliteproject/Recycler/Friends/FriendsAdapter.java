package com.example.hch.sqliteproject.Recycler.Friends;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.hch.sqliteproject.DO.DAO;
import com.example.hch.sqliteproject.DO.Friends;
import com.example.hch.sqliteproject.MainActivity;
import com.example.hch.sqliteproject.MotifyItemActivity;
import com.example.hch.sqliteproject.R;

import java.util.zip.Inflater;

/**
 * Created by hch on 2018-01-31.
 */

public class FriendsAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private RecyclerView recyclerView;

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    public FriendsAdapter(Context context,  RecyclerView recyclerView) {
        this.mContext = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friends, parent, false);
        FriendsHolder holder = new FriendsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        FriendsHolder hold = (FriendsHolder) holder;
        final Friends item = DAO.friendsList.get(position);

        hold.Item_Num.setText(position+"");

        hold.Item_Name.setText(item.getF_name());

        hold.Item_Phone.setText(item.getF_phone());
        Linkify.addLinks(hold.Item_Phone, Linkify.PHONE_NUMBERS);

        hold.Item_Comments.setText(item.getF_comment());

        hold.Item_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO.handler.delete(item.getF_id()+"");
                DAO.loadData_freindsList();
                recyclerView.getAdapter().notifyDataSetChanged();
                Toast.makeText(mContext, "삭제 완료했습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        hold.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)mContext).startCommentActivity(position);
            }
        });

        setAnimation(hold.itemView, position);

    }

    @Override
    public int getItemCount() {
        return DAO.friendsList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // 새로 보여지는 뷰라면 애니메이션을 해줍니다
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


}
