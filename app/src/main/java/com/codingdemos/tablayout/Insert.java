package com.codingdemos.tablayout;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Insert extends AppCompatActivity {
        TextView mTdate, mTtime;
        Button mBtdate, mBttime, mBtsave;
        DatabaseReference reff;
        Member member;
        Spinner Spinnertype;
        int date;
        int month;
        int year;
        int mHour;
        int mMinute;
        Calendar c;
        DatePickerDialog dpd;
        TimePickerDialog tpd;


        @Override
        protected void  onCreate(Bundle saveInstanceState) {

            super.onCreate(saveInstanceState);
            FirebaseApp.initializeApp(this);
            setContentView(R.layout.activity_insert);

            mTdate = (TextView)findViewById(R.id.textdate);
            mTtime = (TextView)findViewById(R.id.texttime);
            Spinnertype = (Spinner)findViewById(R.id.spinner2);
            mBtdate = (Button)findViewById(R.id.btndate);
            mBttime = (Button)findViewById(R.id.btntime);
            mBtsave = (Button)findViewById(R.id.btsave);
            member = new Member();
            reff = FirebaseDatabase.getInstance().getReference().child("Member");


            final String[] typemushroom = getResources().getStringArray(R.array.mushroom);
            final ArrayAdapter<String> adaptermushroom = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, typemushroom);
            Spinnertype.setAdapter(adaptermushroom);

            mBtdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    c = Calendar.getInstance();
                     date = c.get(Calendar.DATE);
                     month = c.get(Calendar.MONTH);
                     year = c.get(Calendar.YEAR);

                    dpd = new DatePickerDialog(Insert.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            mTdate.setText(mDay + "/" + (mMonth)+ "/" + mYear);
                        }
                    }, date, month, year);
                    dpd.show();
                }
            });
            mBttime.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    final Calendar c = Calendar.getInstance();
                     mHour = c.get(Calendar.HOUR);
                     mMinute = c.get(Calendar.MINUTE);

                    tpd = new TimePickerDialog(Insert.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            mTtime.setText(hourOfDay + ":" + minute);
                        }
                    },mHour, mMinute, false);
                    tpd.show();
                }
            });
           mBtsave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*date = (int) Integer.parseInt(String.valueOf(mBtdate.getText()));
                    month = (int) Integer.parseInt(String.valueOf(mBtdate.getText()));
                    year = (int) Integer.parseInt(String.valueOf(mBtdate.getText()));*/

                    /*mHour = (int) Integer.parseInt(String.valueOf(mBttime.getText().length()));
                    mMinute = (int) Integer.parseInt(String.valueOf(mBttime.getText().length()));*/
                   /* typemushroom = Integer.parseInt(Spinnertype.getTextAlignment());*/
                    member.setDate(date);
                    member.setMonth(month);
                    member.setYear(year);

                    member.setHour(mHour);
                    member.setMinute(mMinute);
                    /*member.setTypemushroom(typemushroom);*/
                    reff.push().setValue(member);
                    Toast.makeText(Insert.this," data inserted sucessfully" ,Toast.LENGTH_LONG);

                }
            });



        }
}