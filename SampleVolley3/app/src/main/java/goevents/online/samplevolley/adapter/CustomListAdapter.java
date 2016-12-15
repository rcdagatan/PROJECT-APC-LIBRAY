package goevents.online.samplevolley.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import goevents.online.samplevolley.R;
import goevents.online.samplevolley.models.Apc;

/**
 * Created by asus on 16/11/2016.
 */

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Apc> apc;


    public CustomListAdapter(Activity activity, ArrayList<Apc> apc) {
        this.activity = activity;
        this.apc = apc;
    }

    @Override
    public int getCount() {
        return apc.size();
    }

    @Override
    public Object getItem(int location) {
        return apc.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_layout, null);


        TextView name = (TextView) convertView.findViewById(R.id.textView);
        TextView idnumber = (TextView) convertView.findViewById(R.id.textView2);
        TextView email = (TextView) convertView.findViewById(R.id.textView3);
        TextView type = (TextView) convertView.findViewById(R.id.textView4);
        TextView id = (TextView) convertView.findViewById(R.id.textView5);
        TextView book = (TextView) convertView.findViewById(R.id.textView6);
        TextView date = (TextView) convertView.findViewById(R.id.textView7);
        Apc item = apc.get(position);


        name.setText("Name: " + item.getName());
        idnumber.setText("ID Number: " + item.getIdNumber());
        email.setText("Email: " + item.getEmail());
        type.setText("Type: " + item.getType());
        id.setText("ID: " + item.getID());
        book.setText("Book: " + item.getBook());
        date.setText("Date: " + item.getDate());


        return convertView;
    }
}
