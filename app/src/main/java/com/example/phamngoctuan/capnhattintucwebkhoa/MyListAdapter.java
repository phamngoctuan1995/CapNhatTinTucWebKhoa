package com.example.phamngoctuan.capnhattintucwebkhoa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by phamngoctuan on 14/04/2016.
 */
public class MyListAdapter extends ArrayAdapter<HashMap<String, String>> {

    Context context;
    int resource;
    ArrayList<HashMap<String, String>> listNews;
    LayoutInflater inflater;

    public MyListAdapter(Context context, int resource, ArrayList<HashMap<String, String>> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.listNews = list;
        inflater = LayoutInflater.from(context);
        Log.d("debug", "Adapter init");
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = null;
        try {
            Log.d("debug", "Begin getView");
            row = inflater.inflate(R.layout.listitem, null);
            Log.d("debug", "Inflate view");
            TextView title = (TextView) row.findViewById(R.id.title);
            TextView date = (TextView) row.findViewById(R.id.date);
            Log.d("debug", "Get title");
            title.setText(listNews.get(position).get("title"));
            date.setText(listNews.get(position).get("date"));
            Log.d("debug", "Set text");

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("debug", "begin onclick");
                    ListItemCallback callback = (ListItemCallback) context;
                    callback.onClickCallback(listNews.get(position).get("link"));
                }
            });
        }
        catch (Exception e)
        {
            Log.d("debug", e.getMessage());
        }
        return row;
    }
}
