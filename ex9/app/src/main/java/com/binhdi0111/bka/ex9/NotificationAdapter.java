package com.binhdi0111.bka.ex9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Notification> list;

    public NotificationAdapter(Context context, int layout, List<Notification> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{
        TextView txtTitle,txtMessage;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder = new ViewHolder();
            //anh xa
            holder.txtTitle = view.findViewById(R.id.textViewTitle);
            holder.txtMessage = view.findViewById(R.id.textViewMessage);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Notification notification = list.get(i);
        holder.txtTitle.setText(notification.getTitle());
        holder.txtMessage.setText(notification.getMessage());
        return view;
    }
}
