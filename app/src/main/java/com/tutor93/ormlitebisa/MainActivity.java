package com.tutor93.ormlitebisa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    EditText etEntry;
    ListView listView;
    EmpAdapter adapter = null;
    DatabaseHelper helper;
    List<Employee> list;
    Button btnAdd, btnDeleteAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView1);
      //  listView.setOnClickListener(this);

        etEntry = (EditText) findViewById(R.id.etentry);
        btnAdd = (Button) findViewById(R.id.button1);
        btnDeleteAll = (Button) findViewById(R.id.button2);

        helper = new DatabaseHelper(this);

        setDataToAdapter();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddata();
            }
        });

        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

    }

    public void adddata(){
        String strName = etEntry.getText().toString().trim();
        if(TextUtils.isEmpty(strName)){
            showToast("Please add your name !!!");
            return;
        }
        Employee person = new Employee();
        person.setName(strName);
        helper.addData(person);
        showToast("Data susccesfully added");

        setDataToAdapter();
    }

    private void showToast(String setMessage) {
        Toast.makeText(MainActivity.this, setMessage, Toast.LENGTH_SHORT).show();

    }

    private void setDataToAdapter() {
        list = helper.GetData();

        adapter = new EmpAdapter(this, R.layout.row, list);
        listView.setAdapter(adapter);
    }

    public void deleteData() {
        list = helper.GetData();
        helper.deleteAll();
        etEntry.setText("");
        showToast("Removed All data!!!");
        setDataToAdapter();
    }




    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        showToast(list.get(position).getName());
    }
}
