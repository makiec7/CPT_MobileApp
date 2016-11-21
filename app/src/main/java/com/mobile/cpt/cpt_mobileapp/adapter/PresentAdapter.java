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

public class PresentAdapter extends ArrayAdapter<FaultModel> {
    Context context;
    int resource;
    List<FaultModel> objects;

    public PresentAdapter(Context context, int resource, List<FaultModel> objects) {
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
            holder.tw_id = (TextView) convertView.findViewById(R.id.tw_id);
            holder.tw_description = (TextView) convertView.findViewById(R.id.tw_description);
            holder.tw_floor_no = (TextView) convertView.findViewById(R.id.tw_floor_no);
            holder.tw_issuer = (TextView) convertView.findViewById(R.id.tw_issuer);
            holder.tw_obj_name = (TextView) convertView.findViewById(R.id.tw_obj_name);
            holder.tw_obj_no = (TextView) convertView.findViewById(R.id.tw_obj_no);
            holder.tw_priority = (TextView) convertView.findViewById(R.id.tw_priority);
            holder.tw_room_no = (TextView) convertView.findViewById(R.id.tw_room_no);
            holder.tw_status = (TextView) convertView.findViewById(R.id.tw_status);
            holder.tw_topic = (TextView) convertView.findViewById(R.id.tw_topic);

            convertView.setTag(holder);
        } else {
            holder = (FaultModelHolder) convertView.getTag();
        }

        FaultModel object = objects.get(position);

        holder.tw_id.setText(Integer.toString(object.getId()));
        holder.tw_description.setText(object.getDescription());
        holder.tw_floor_no.setText(Integer.toString(object.getFloor_no()));
        holder.tw_issuer.setText(Integer.toString(object.getIssuer()));
        holder.tw_obj_name.setText(object.getObj_name());
        holder.tw_obj_no.setText(Integer.toString(object.getObj_no()));
        holder.tw_priority.setText(Integer.toString(object.getPriority()));
        holder.tw_room_no.setText(Integer.toString(object.getRoom_no()));
        holder.tw_status.setText(Integer.toString(object.getStatus()));
        holder.tw_topic.setText(object.getTopic());

        return convertView;
    }

    static class FaultModelHolder {
        TextView tw_id;
        TextView tw_description;
        TextView tw_floor_no;
        TextView tw_issuer;
        TextView tw_obj_name;
        TextView tw_obj_no;
        TextView tw_priority;
        TextView tw_room_no;
        TextView tw_status;
        TextView tw_topic;
    }
}