package com.tutor93.ormlitebisa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener,
        View.OnClickListener {

    EditText etEntry;
    ListView listView;
    GridView gridView;
    EmpAdapter adapter = null;
    DatabaseHelper helper;
    List<Employee> list;
    //    Button btnAdd, btnDeleteAll;
    Button btnGrid, btnList;
    LinearLayout gridViewFrame;

    Button buttonBookmark; //bookmark
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.halamandepan_listview);
        //  listView.setOnClickListener(this);
        gridView = (GridView) findViewById(R.id.gridview);


        gridViewFrame = (LinearLayout) findViewById(R.id.gridView);

        btnGrid = (Button) findViewById(R.id.gridBtn);
        btnList = (Button) findViewById(R.id.btnList);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        //  buttonBookmark = (Button) findViewById(R.id.btnBookmark);

        helper = new DatabaseHelper(this);

        btnGrid.setOnClickListener(this);
        btnList.setOnClickListener(this);
//        buttonBookmark.setOnClickListener(this);

        setDataToAdapter();

        /*langsung diklik ketika, onCreate
        * */
        btnList.performClick();

        //setDataToAdapterGridview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    private void showToast(String setMessage) {
        Toast.makeText(MainActivity.this, setMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        setDataToAdapter();
        super.onResume();
    }

    private void setDataToAdapter() {
        list = helper.GetData();

        adapter = new EmpAdapter(this, R.layout.row2, list);
        listView.setAdapter(adapter);
    }


    public void deleteData() {
        list = helper.GetData();
        helper.deleteAll();
//        etEntry.setText("");
        showToast("Removed All data!!!");
        setDataToAdapter();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Intent intent = new Intent(this, Add_employee.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        /*showToast(list.get(position).getName());*/
        if (view == buttonBookmark) {
            deleteData();
        }


    }

    @Override
    public void onClick(View view) {
        if (view == btnGrid) {
            listView.setVisibility(View.GONE);
            gridViewFrame.setVisibility(View.VISIBLE);
        } else if (view == btnList) {
            gridViewFrame.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
        }

    }
}
