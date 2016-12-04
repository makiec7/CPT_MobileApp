package com.mobile.cpt.cpt_mobileapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import java.util.List;

import static com.mobile.cpt.cpt_mobileapp.Constant.TV_DATETIME;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_DESCRIPTION;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_ID;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_ISSUER;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_OBJ_NO;
import static com.mobile.cpt.cpt_mobileapp.Constant.TV_TOPIC;

public class PresentAdapter extends ArrayAdapter<FaultModel> {
    private Context context;
    private int resource;
    private List<FaultModel> objects;
    private static class FaultModelHolder {
        TextView tv_id;
        TextView tv_datetime;
        TextView tv_description;
        TextView tv_issuer;
        TextView tv_obj_no;
        TextView tv_topic;
    }

    public PresentAdapter(Context context, int resource, List<FaultModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        FaultModelHolder holder;

        if (convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
            holder = new FaultModelHolder();
            holder.tv_id = (TextView) convertView.findViewById(TV_ID);
            holder.tv_description = (TextView) convertView.findViewById(TV_DESCRIPTION);
            holder.tv_issuer = (TextView) convertView.findViewById(TV_ISSUER);
            holder.tv_obj_no = (TextView) convertView.findViewById(TV_OBJ_NO);
            holder.tv_datetime = (TextView) convertView.findViewById(TV_DATETIME);
            holder.tv_topic = (TextView) convertView.findViewById(TV_TOPIC);

            convertView.setTag(holder);
        } else {
            holder = (FaultModelHolder) convertView.getTag();
        }

        FaultModel object = objects.get(position);
        holder.tv_id.setText(Integer.toString(object.getId()));
        holder.tv_description.setText(object.getDescription());
        holder.tv_datetime.setText(object.getDate_time());
        holder.tv_issuer.setText(Integer.toString(object.getIssuer()));
        holder.tv_obj_no.setText(Integer.toString(object.getObject_number()));
        holder.tv_topic.setText(object.getTopic());
        return convertView;
    }
}
