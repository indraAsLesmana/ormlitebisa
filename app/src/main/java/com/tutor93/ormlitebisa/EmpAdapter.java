package com.tutor93.ormlitebisa;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by indra on 11/09/2016.
 */
public class EmpAdapter extends ArrayAdapter<Employee> implements View.OnClickListener {

    private Context mContext;
    private int row;
    private List<Employee> list;
    DatabaseHelper helper;
   /* ViewHolder holder;*/

    public EmpAdapter(Context context, int textViewResourceId, List<Employee> list) {
        super(context, textViewResourceId, list);
        this.mContext = context;
        this.row = textViewResourceId;
        this.list = list;

        /*initial data*/
        this.helper = new DatabaseHelper(mContext);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

//        if ((list == null) || ((position + 1) > list.size()))
//            return view;
        final Employee obj = list.get(position);

        holder.name = (TextView) view.findViewById(R.id.tvname);
        holder.job = (TextView) view.findViewById(R.id.tvjobs);
        holder.age = (TextView) view.findViewById(R.id.tvage);
        holder.gender = (TextView) view.findViewById(R.id.tvgender);

        holder.btnBookmark = (Button) view.findViewById(R.id.btnBookmark);
        holder.btnEdit = (Button) view.findViewById(R.id.btnEdit);

        holder.startBookmark = (ImageView) view.findViewById(R.id.startBookmarkIcon);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Log.d("EmpAdapter", "hallo world");*/
                Intent myIntent = new Intent(mContext, Add_employee.class);
                myIntent.putExtra("id", obj.getId());
                mContext.startActivity(myIntent);
            }
        });

        holder.btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.setBookmark(!obj.bookmark);
                /*helper.updateData(obj.getName().toString());*/
                //harus ada update data kedatabase disini
//                helper.updateEmployee(Em);
                helper.updateData(obj);
                notifyDataSetChanged();
            }
        });

        if (null != holder.name && null != obj && obj.getName().length() != 0) {
            holder.name.setText(obj.getName());
            holder.job.setText(obj.getJobs());
            holder.age.setText(Integer.toString(obj.getAge()));
            holder.gender.setText(obj.getIs_male() == false ? "Female" : "Male");
            holder.startBookmark.setVisibility(obj.isBookmark() ? View.VISIBLE : View.GONE);

        }

        return view;
    }


    @Override
    public void onClick(View view) {

    }

    public static class ViewHolder {
        public TextView name;
        public TextView job;
        public TextView age;
        public TextView gender;

        public Button btnBookmark;
        public Button btnEdit;
        public ImageView startBookmark;
    }
}
