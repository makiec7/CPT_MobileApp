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

import com.mobile.cpt.cpt_mobileapp.model.FaultModel;
import static com.mobile.cpt.cpt_mobileapp.Constant.*;

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
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        FaultModelHolder holder;
        if (convertView == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
            holder = new FaultModelHolder();
            holder.tv_datetime = (TextView) convertView.findViewById(TV_DATETIME);
            holder.tv_topic = (TextView) convertView.findViewById(TV_TOPIC);
            holder.iv_status = (ImageView) convertView.findViewById(IV_STATUS);
            convertView.setTag(holder);
        } else {
            holder = (FaultModelHolder) convertView.getTag();
        }
        FaultModel object = objects.get(position);
        holder.tv_datetime.setText(object.getDate_time());
        holder.tv_topic.setText(object.getTopic());
        if (holder.iv_status != null)
            if (object.getStatus() == 0)
                holder.iv_status.setImageResource(CROSS_LOGO);
            else if (object.getStatus() == 1)
                holder.iv_status.setImageResource(ONGOING_ICON);
            else
                holder.iv_status.setImageResource(TICK_LOGO);
        convertView.setBackgroundColor(position % 2 == 0 ? Color.WHITE : Color.rgb(RED, GREEN, BLUE));
        return convertView;
    }
}
