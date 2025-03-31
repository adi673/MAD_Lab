package com.example.q1;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends ArrayAdapter<AppInfo> {

    public AppAdapter(Context context, List<AppInfo> apps) {
        super(context, 0, apps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_app, parent, false);
        }

        AppInfo app = getItem(position);

        TextView appName = convertView.findViewById(R.id.appName);
        TextView packageName = convertView.findViewById(R.id.packageName);

        appName.setText(app.getName());
        packageName.setText(app.getPackageName());

        return convertView;
    }
}
