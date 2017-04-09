package com.example.anmol.thirstquencher.controller;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.anmol.thirstquencher.model.References;
import com.example.anmol.thirstquencher.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anmol on 3/2/17.
 */
class ListViewAdapter extends BaseAdapter {

    private final ArrayList<HashMap<String, String>> list;
    private final Activity activity;
    private TextView txtFirst;
    private TextView txtSecond;
    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.water_report_activity, null);

            txtFirst=(TextView) convertView.findViewById(R.id.reportNumber);
            txtSecond=(TextView) convertView.findViewById(R.id.reportDateSubmitted);
        }

        HashMap<String, String> map = list.get(position);
        txtFirst.setText(map.get(References.REPORT_ID));
        txtSecond.setText(map.get(References.DATE_SUBMITTED));
        return convertView;
    }
}
