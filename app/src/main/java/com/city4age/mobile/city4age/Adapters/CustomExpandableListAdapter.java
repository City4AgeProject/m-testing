package com.city4age.mobile.city4age.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import java.util.HashMap;
import com.city4age.mobile.city4age.R;
import com.city4age.mobile.city4age.Model.*;
import com.city4age.mobile.city4age.TrackActivity;
import com.google.gson.Gson;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    //Temp variable
    static final String ACTIVITY_DATA = "ACTIVITY_DATA";

    private Context mContext;
    private List<String> mListDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<ActivityData>> mListDataChild;

    public CustomExpandableListAdapter(Context context, List<String> listDataHeader,
                                       HashMap<String, List<ActivityData>> listChildData) {
        mContext = context;
        mListDataHeader = listDataHeader;
        mListDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ActivityData childObject = (ActivityData) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childObject.getActivity_name());

        convertView.setTag(childObject);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                ActivityData activity = (ActivityData) v.getTag();
                String activityObject = gson.toJson(activity);

                Intent intentToActivity = new Intent(mContext, TrackActivity.class);
                intentToActivity.putExtra(ACTIVITY_DATA, activityObject);
                mContext.startActivity(intentToActivity);
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mListDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.mListDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.exp_list_group, null);
        }

        if (isExpanded) {
            ImageView imgArrow = (ImageView) convertView.findViewById(R.id.img_exp_list_arrow);
            imgArrow.setImageResource(R.drawable.c4a_expandable_list_arrow_up);
        } else {
            ImageView imgArrow = (ImageView) convertView.findViewById(R.id.img_exp_list_arrow);
            imgArrow.setImageResource(R.drawable.c4a_expandable_list_arrow_down);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
