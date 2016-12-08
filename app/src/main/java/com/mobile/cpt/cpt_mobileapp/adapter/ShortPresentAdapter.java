package com.mobile.cpt.cpt_mobileapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import java.util.List;

public class ShortPresentAdapter extends ArrayAdapter<FaultModel> {

    private Context context;
    private int resource;
    private List<FaultModel> objects;
    private static class FaultModelHolder {
        TextView tv_datetime;
        TextView tv_topic;
        ImageView iv_status;
    }

    public ShortPresentAdapter(Context context, int resource, List<FaultModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FaultModelHolder holder;
        if (convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
            holder = new FaultModelHolder();
            holder.tv_datetime = (TextView) convertView.findViewById(R.id.tv_datetime);
            holder.tv_topic = (TextView) convertView.findViewById(R.id.tv_topic);
            holder.iv_status = (ImageView) convertView.findViewById(R.id.iv_status);
            convertView.setTag(holder);
        } else {
            holder = (FaultModelHolder) convertView.getTag();
        }
        FaultModel object = objects.get(position);
        holder.tv_datetime.setText(object.getDate_time());
        holder.tv_topic.setText(object.getTopic());
        if (object.getStatus() == 2){
            holder.iv_status.setImageResource(R.drawable.tick_logo);
        } else {
            holder.iv_status.setImageResource(R.drawable.cross_logo);
        }
        convertView.setBackgroundColor(position % 2 == 0 ? Color.WHITE : Color.rgb(230, 230, 230));
        return convertView;
    }
}
