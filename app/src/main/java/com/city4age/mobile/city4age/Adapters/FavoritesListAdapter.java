package com.city4age.mobile.city4age.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.city4age.mobile.city4age.Model.ActivityData;
import com.city4age.mobile.city4age.R;
import java.util.List;

/**
 * Created by Srle on 4/21/2018.
 */
public class FavoritesListAdapter extends ArrayAdapter {

    List<ActivityData> mActivitiesList;
    private static LayoutInflater mInflater = null;

    public FavoritesListAdapter(Context context, List<ActivityData> list) {
        super(context, 0, list);
        mActivitiesList = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FavoritesListAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.favorites_list_item, parent, false);
            // inflate custom layout called row
            holder = new FavoritesListAdapter.ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.activity_name);
            convertView.setTag(holder);
        } else {
            holder = (FavoritesListAdapter.ViewHolder) convertView.getTag();
        }

        holder.name.setText(mActivitiesList.get(position).getActivity_name());
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
    }
}