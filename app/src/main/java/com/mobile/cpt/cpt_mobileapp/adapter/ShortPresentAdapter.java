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

    Context context;
    int resource;
    List<FaultModel> objects;

    public ShortPresentAdapter(Context context, int resource, List<FaultModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FaultModelHolder holder = null;

        if (convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
            holder = new FaultModelHolder();
            holder.tw_room_no = (TextView) convertView.findViewById(R.id.tw_room_no);
            holder.tw_topic = (TextView) convertView.findViewById(R.id.tw_topic);

            convertView.setTag(holder);
        } else {
            holder = (FaultModelHolder) convertView.getTag();
        }

        FaultModel object = objects.get(position);
        holder.tw_room_no.setText(Integer.toString(object.getRoom_no()));
        holder.tw_topic.setText(object.getTopic());

        return convertView;
    }

    static class FaultModelHolder {
        TextView tw_room_no;
        TextView tw_topic;
    }
}
