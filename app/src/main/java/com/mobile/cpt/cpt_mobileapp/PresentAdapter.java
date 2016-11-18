package com.mobile.cpt.cpt_mobileapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.cpt.cpt_mobileapp.model.FaultModel;

import java.util.List;

/**
 * Created by Makiec on 18.11.2016.
 */

public class PresentAdapter extends ArrayAdapter<FaultModel> {
    public PresentAdapter(Context context, int resource, List<FaultModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FaultModel faultModel = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fault_present,
                    parent, false);
        }

        TextView tw_id = (TextView) convertView.findViewById(R.id.tw_id);
        TextView tw_description = (TextView) convertView.findViewById(R.id.tw_description);
        TextView tw_floor_no = (TextView) convertView.findViewById(R.id.tw_floor_no);
        TextView tw_issuer = (TextView) convertView.findViewById(R.id.tw_issuer);
        TextView tw_obj_name = (TextView) convertView.findViewById(R.id.tw_obj_name);
        TextView tw_obj_no = (TextView) convertView.findViewById(R.id.tw_obj_no);
        TextView tw_priority = (TextView) convertView.findViewById(R.id.tw_priority);
        TextView tw_room_no = (TextView) convertView.findViewById(R.id.tw_room_no);
        TextView tw_status = (TextView) convertView.findViewById(R.id.tw_status);
        TextView tw_topic = (TextView) convertView.findViewById(R.id.tw_topic);

        tw_description.setText(faultModel.getDescription());
        tw_floor_no.setText(faultModel.getFloor_no());
        tw_issuer.setText(faultModel.getIssuer());
        tw_obj_name.setText(faultModel.getObj_name());
        tw_obj_no.setText(faultModel.getObj_no());
        tw_priority.setText(faultModel.getPriority());
        tw_room_no.setText(faultModel.getRoom_no());
        tw_status.setText(faultModel.getStatus());
        tw_topic.setText(faultModel.getTopic());

        return convertView;
    }
}
