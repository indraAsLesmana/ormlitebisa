package com.tutor93.ormlitebisa;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by indra on 12/09/2016.
 */
public class Add_employee extends AppCompatActivity implements
        View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText mName;
    private Spinner mJabatan;
    private RadioButton mAdd_Male;
    private RadioButton mAdd_Female;
    private EditText mAdd_JoinDate;
    private CheckBox mBookmark;
    private EditText mAdd_Age;
    private EditText mNotes;

    private Button mAdd_camera;
    private Button mAdd_save;
    private ImageView mProfile_pict;


    DatabaseHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployee);

         /*define, locate & set START*/
        mName = (EditText) findViewById(R.id.add_name);
        mJabatan = (Spinner) findViewById(R.id.add_jabatan);
        mAdd_Male = (RadioButton) findViewById(R.id.add_male);
        mAdd_Female = (RadioButton) findViewById(R.id.add_female);
        mAdd_JoinDate = (EditText) findViewById(R.id.add_joindate);
        mBookmark = (CheckBox) findViewById(R.id.add_bookmark);
        mAdd_Age = (EditText) findViewById(R.id.add_age);
        mNotes = (EditText) findViewById(R.id.add_note);
        /*button*/
        mAdd_save = (Button) findViewById(R.id.btn_saveemployee);
        /*define, locate & set END*/

        mAdd_save.setOnClickListener(this);

        helper = new DatabaseHelper(this);

        spinnerData();

        mAdd_JoinDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Add_employee.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

    }

    private void showToast(String setMessage) {
        Toast.makeText(Add_employee.this, setMessage, Toast.LENGTH_SHORT).show();

    }

    private void spinnerData() {
        mJabatan = (Spinner) findViewById(R.id.add_jabatan);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Android develover");
        list.add("iOS developer");
        list.add("Rubby developer");

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mJabatan.setAdapter(adapter);
    }

    public void adddata() {
//        int strAge = 0;

        String strName = mName.getText().toString();
        String strJabatan = mJabatan.getSelectedItem().toString();
        boolean bolIs_male = mAdd_Male.isChecked();
        boolean bolBookmark = mBookmark.isChecked();

        int strAge = mAdd_Age.getText().toString().equals("")
                ? 0 : Integer.parseInt(mAdd_Age.getText().toString());

        String strJoin = mAdd_JoinDate.getText().toString();
        String strNote = mNotes.getText().toString();
        String strAvatar = "masih di ulik";


        if (TextUtils.isEmpty(strName)) {
            showToast("Please add your name !!!");
            return;
        }

        Employee person = new Employee();

        person.setName(strName);
        person.setJobs(strJabatan);
        person.setIs_male(bolIs_male);
        person.setBookmark(bolBookmark);
        person.setAge(strAge);
        person.setJoin(strJoin);
        person.setNotes(strNote);
        person.setAvatar(strAvatar);

        helper.addData(person);
        showToast("Data susccesfully added");
//        setDataToAdapter();
    }

    @Override
    public void onClick(View view) {
        if (view == mAdd_save) {
            adddata();
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        mAdd_JoinDate.setText(date);
    }
}
