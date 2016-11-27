package com.mobile.cpt.cpt_mobileapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
            convertView.setTag(holder);
        } else {
            holder = (FaultModelHolder) convertView.getTag();
        }
        FaultModel object = objects.get(position);
        holder.tv_datetime.setText(object.getDate_time());
        holder.tv_topic.setText(object.getTopic());
        return convertView;
    }
}
