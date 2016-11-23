package com.mobile.cpt.cpt_mobileapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.Constant;
import com.mobile.cpt.cpt_mobileapp.R;
import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import java.util.List;

import static com.mobile.cpt.cpt_mobileapp.Constant.*;

public class PresentAdapter extends ArrayAdapter<FaultModel> {
    private Context context;
    private int resource;
    private List<FaultModel> objects;
    private static class FaultModelHolder {
        TextView tw_id;
        TextView tw_datetime;
        TextView tw_description;
        TextView tw_issuer;
        TextView tw_obj_no;
        TextView tw_topic;
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
            holder.tw_id = (TextView) convertView.findViewById(TW_ID);
            holder.tw_description = (TextView) convertView.findViewById(TW_DESCRIPTION);
            holder.tw_issuer = (TextView) convertView.findViewById(TW_ISSUER);
            holder.tw_obj_no = (TextView) convertView.findViewById(TW_OBJ_NO);
            holder.tw_datetime = (TextView) convertView.findViewById(TW_DATETIME);
            holder.tw_topic = (TextView) convertView.findViewById(TW_TOPIC);

            convertView.setTag(holder);
        } else {
            holder = (FaultModelHolder) convertView.getTag();
        }

        FaultModel object = objects.get(position);
        holder.tw_id.setText(Integer.toString(object.getId()));
        holder.tw_description.setText(object.getDescription());
        holder.tw_datetime.setText(object.getDate_time());
        holder.tw_issuer.setText(Integer.toString(object.getIssuer()));
        holder.tw_obj_no.setText(Integer.toString(object.getObject_number()));
        holder.tw_topic.setText(object.getTopic());
        return convertView;
    }
}
