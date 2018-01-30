package com.example.hch.sqliteproject.Recycler.Friends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hch.sqliteproject.R;

/**
 * Created by hch on 2018-01-31.
 */

public class FriendsHolder extends RecyclerView.ViewHolder {
    public TextView Item_Num;
    public TextView Item_Name;
    public TextView Item_Phone;
    public TextView Item_Comments;
    public ImageButton Item_Delete;

    public FriendsHolder(View itemView) {
        super(itemView);

        Item_Num = (TextView) itemView.findViewById(R.id.item_friends_num);
        Item_Name = (TextView) itemView.findViewById(R.id.item_friends_who);
        Item_Phone = (TextView) itemView.findViewById(R.id.item_friends_phone);
        Item_Comments = (TextView)  itemView.findViewById(R.id.item_friends_comments);
        Item_Delete = (ImageButton)  itemView.findViewById(R.id.item_friends_deleteIBTN);
    }
}
