package com.city4age.mobile.city4age.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.city4age.mobile.city4age.Model.ActivityC4A;
import com.city4age.mobile.city4age.Model.SensorDataC4A;
import com.city4age.mobile.city4age.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static android.R.attr.data;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */

public class FinishedActivitiesListViewAdapter extends ArrayAdapter {

    List<ActivityC4A> activitiesList;
    private static LayoutInflater inflater = null;

    public FinishedActivitiesListViewAdapter(Context context, List<ActivityC4A> list) {
        super(context, 0, list);
        activitiesList = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_finished_list_item, parent, false);
            // inflate custom layout called row
            holder = new ViewHolder();
            holder.number = (TextView) convertView.findViewById(R.id.tv_finished_activity_number);
            holder.name = (TextView) convertView.findViewById(R.id.tv_finished_activity_name);
            holder.date = (TextView) convertView.findViewById(R.id.tv_finished_activity_start_date);
            holder.date2 = (TextView) convertView.findViewById(R.id.tv_finished_activity_end_date);
            holder.cordinates = (TextView) convertView.findViewById(R.id.tv_finished_activity_coordinates);
            // initialize textview
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ActivityC4A in = (ActivityC4A) activitiesList.get(position);
        String positionNumber = String.valueOf(position + 1);
        holder.number.setText(positionNumber);
        holder.name.setText(in.getActivity_name());
        String formattedDate = in.getActivity_start_date().toString();//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(in.getActivity_start_date());
        holder.date.setText(formattedDate);
        String formattedDate2 = in.getActivity_end_date().toString();//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(in.getActivity_end_date());
        holder.date2.setText(formattedDate2);
        // set the name to the text;

        StringBuilder cords = new StringBuilder();

        for (SensorDataC4A sensData:in.getSensor_data()) {
            cords.append(sensData.getLatitude());
            cords.append(',');
            cords.append(sensData.getLongitude());
            cords.append('|');
        }

        holder.cordinates.setText(cords.toString());

        return convertView;
    }

    private static class ViewHolder {
        TextView number;
        TextView name;
        TextView date;
        TextView date2;
        TextView cordinates;
    }
}
