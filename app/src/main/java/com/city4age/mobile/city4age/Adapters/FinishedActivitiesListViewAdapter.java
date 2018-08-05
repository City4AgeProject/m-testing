package com.city4age.mobile.city4age.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.city4age.mobile.city4age.Model.ActivityData;
import com.city4age.mobile.city4age.Model.BluetoothData;
import com.city4age.mobile.city4age.Model.GPSData;
import com.city4age.mobile.city4age.Model.RecognitionData;
import com.city4age.mobile.city4age.Model.WifiData;
import com.city4age.mobile.city4age.R;
import java.util.List;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class FinishedActivitiesListViewAdapter extends ArrayAdapter {

    List<ActivityData> mActivitiesList;
    private static LayoutInflater mInflater = null;

    public FinishedActivitiesListViewAdapter(Context context, List<ActivityData> list) {
        super(context, 0, list);
        mActivitiesList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_finished_list_item, parent, false);
            // inflate custom layout called row
            holder = new ViewHolder();
            holder.number = (TextView) convertView.findViewById(R.id.tv_finished_activity_number);
            holder.name = (TextView) convertView.findViewById(R.id.tv_finished_activity_name);
            holder.date = (TextView) convertView.findViewById(R.id.tv_finished_activity_start_date);
            holder.date2 = (TextView) convertView.findViewById(R.id.tv_finished_activity_end_date);

            // PROBA
            /*
            holder.cordinates = (TextView) convertView.findViewById(R.id.tv_finished_activity_coordinates);
            holder.bt = (TextView) convertView.findViewById(R.id.tv_finished_activity_bt);
            holder.wifi = (TextView) convertView.findViewById(R.id.tv_finished_activity_wifi);
            holder.rd = (TextView) convertView.findViewById(R.id.tv_finished_activity_rd);
            */

            // initialize textview
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ActivityData in = (ActivityData) mActivitiesList.get(position);
        String positionNumber = String.valueOf(position + 1);
        holder.number.setText(positionNumber);
        holder.name.setText(in.getActivity_name());
        String formattedDate = in.getActivity_start_date().toString();//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(in.getActivity_start_date());
        holder.date.setText(formattedDate);
        String formattedDate2 = in.getActivity_end_date().toString();//new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).format(in.getActivity_end_date());
        holder.date2.setText(formattedDate2);
        // set the name to the text;

        // PROBA
        /*
        StringBuilder cords = new StringBuilder();
        for (GPSData sensData:in.getGpsData()) {
            cords.append(sensData.getLatitude());
            cords.append("\n");
            cords.append("|");
            cords.append("\n");
            cords.append(sensData.getLongitude());
            cords.append("\n");
            cords.append("\n");
        }

        StringBuilder bt = new StringBuilder();
        for (BluetoothData sensData:in.getBluetoothData()) {
            bt.append(sensData.getDevice());
            bt.append("\n");
            bt.append("\n");
        }

        StringBuilder wifi = new StringBuilder();
        for (WifiData sensData:in.getWifiData()) {
            wifi.append(sensData.getDevices());
        }

        StringBuilder rd = new StringBuilder();
        for (RecognitionData recData:in.getRecognitionData()) {
            rd.append(recData.getType());
            rd.append("\n");
        }
        holder.cordinates.setText(cords.toString());
        holder.bt.setText(bt.toString());
        holder.wifi.setText(wifi.toString());
        holder.rd.setText(rd.toString());
        */
        return convertView;
    }

    private static class ViewHolder {
        TextView number;
        TextView name;
        TextView date;
        TextView date2;
        TextView cordinates;
        TextView bt;
        TextView wifi;
        TextView rd;
    }
}
