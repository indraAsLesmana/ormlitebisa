package com.tutor93.ormlitebisa;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by indra on 14/09/2016.
 */
public class EmpAdapterGridView extends BaseAdapter {

    private final Context mContext;
    private final Employee[] employees;

    public EmpAdapterGridView(Context mContext, Employee[] employees) {
        this.mContext = mContext;
        this.employees = employees;
    }


    @Override
    public int getCount() {
        return employees.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setText(String.valueOf(position));
        return dummyTextView;
    }
}
