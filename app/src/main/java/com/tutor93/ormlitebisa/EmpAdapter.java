package com.tutor93.ormlitebisa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by indra on 11/09/2016.
 */
public class EmpAdapter extends ArrayAdapter<Employee> {
    private Context mContext;
    private int row;
    private List<Employee> list;

    public EmpAdapter(Context context, int textViewResourceId, List<Employee> list) {
        super(context, textViewResourceId, list);

        this.mContext = context;
        this.row = textViewResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View view = convertView;
        ViewHolder holder;

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

        if (null != holder.name && null != obj && obj.getName().length() != 0) {
            holder.name.setText(obj.getName());
        }

        return view;
    }

    public static class ViewHolder {
        public TextView name;
    }
}
