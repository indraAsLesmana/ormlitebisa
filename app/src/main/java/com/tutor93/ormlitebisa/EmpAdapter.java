package com.tutor93.ormlitebisa;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by indra on 11/09/2016.
 */
public class EmpAdapter extends ArrayAdapter<Employee> implements View.OnClickListener {

    private Context mContext;
    private int row;
    private List<Employee> list;

    ViewHolder holder;

    public EmpAdapter(Context context, int textViewResourceId, List<Employee> list) {
        super(context, textViewResourceId, list);
        this.mContext = context;
        this.row = textViewResourceId;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if ((list == null) || ((position + 1) > list.size()))
            return view;
        Employee obj = list.get(position);

        holder.name = (TextView) view.findViewById(R.id.tvname);
        holder.job = (TextView) view.findViewById(R.id.tvjobs);
        holder.age = (TextView) view.findViewById(R.id.tvage);
        holder.gender = (TextView) view.findViewById(R.id.tvgender);

        holder.btnBookmark = (Button) view.findViewById(R.id.btnBookmark);
        holder.startBookmark = (ImageView) view.findViewById(R.id.startBookmarkIcon);

        holder.btnBookmark.setOnClickListener(this);

        if (null != holder.name && null != obj && obj.getName().length() != 0) {
            holder.name.setText(obj.getName());
            holder.job.setText(obj.getJobs());
            holder.age.setText(Integer.toString(obj.getAge()));
            holder.gender.setText(obj.getIs_male() == false ? "Female" : "Male");
        }

        return view;
    }





    @Override
    public void onClick(View view) {
        if (view == holder.btnBookmark) {
            holder.startBookmark.setVisibility(View.GONE);
            Log.d("Empadapter", "udah diklik terusss");
        }

    }

    public static class ViewHolder {
        public TextView name;
        public TextView job;
        public TextView age;
        public TextView gender;

        public Button btnBookmark;
        public ImageView startBookmark;

    }
}
