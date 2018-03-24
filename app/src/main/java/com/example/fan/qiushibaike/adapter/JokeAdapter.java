package com.example.fan.qiushibaike.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fan.qiushibaike.R;
import com.example.fan.qiushibaike.bean.Joke;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Fan on 2018/3/23.
 */

public class JokeAdapter extends BaseAdapter {
    private List<Joke> jokeList;
    private View view;
    private Context mContext;
    private ViewHolder viewHolder;


    public JokeAdapter(Context mContext,List<Joke> jokeList){
        this.jokeList = jokeList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return jokeList.size();
    }

    @Override
    public Object getItem(int position) {
        return jokeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.joke_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.userName = (TextView)view.findViewById(R.id.user_name);
            viewHolder.userName.setTextColor(Color.GRAY);
            viewHolder.content = (TextView)view.findViewById(R.id.content);
            viewHolder.content.setTextColor(Color.BLACK);
            viewHolder.userImage = (CircleImageView)view.findViewById(R.id.user_image);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.userName.setText(jokeList.get(position).getUserName());
        viewHolder.content.setText(jokeList.get(position).getContent());
        Glide.with(mContext).load(jokeList.get(position).getUserImageUrl()).into(viewHolder.userImage);
        return view;
    }

    class ViewHolder{
        CircleImageView userImage;
        TextView userName;
        TextView content;
    }
}
