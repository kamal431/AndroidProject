package com.example.contactapp;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<UserInfo> {

    private Context mContext;

    private List<UserInfo> userList = new ArrayList<UserInfo>();

    public MyCustomAdapter(@NonNull Context context, List<UserInfo> list) {
        super(context, 0 , list);
        mContext = context;
        userList = list;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public int getPosition(@Nullable UserInfo item) {
        return super.getPosition(item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View layoutView = null;

        if(convertView == null){
            layoutView = LayoutInflater.from(mContext).inflate(R.layout.userinfolayout, parent, false);
        }

        ImageView myImage = layoutView.findViewById(R.id.imageID);
        TextView nameInfo = layoutView.findViewById(R.id.nameID);
        TextView descInfo = layoutView.findViewById(R.id.descID);

        UserInfo  userObj = userList.get(position);

        nameInfo.setText(userObj.name);
        descInfo.setText(userObj.desc);
        myImage.setImageResource(userObj.imageID);

        return layoutView;
    }
}