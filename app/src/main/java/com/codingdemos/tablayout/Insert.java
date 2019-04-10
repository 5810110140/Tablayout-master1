package com.codingdemos.tablayout;

import android.app.DatePickerDialog;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class Insert extends AppCompatActivity {
        TextView mTdate, mTtime;
        Button mBtdate, mBttime;

        Calendar c;
        DatePickerDialog dpd;

        @Override
        protected void  onCreate(Bundle saveInstanceState) {

            super.onCreate(saveInstanceState);
            setContentView(R.layout.activity_insert);

            mTdate = (TextView)findViewById(R.id.textdate);
            mBtdate = (Button)findViewById(R.id.btndate);

            mBtdate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    c = Calendar.getInstance();
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);

                    dpd = new DatePickerDialog(Insert.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                            mTdate.setText(mDay + "/" + (mMonth+1)+ "/" + mYear);
                        }
                    },day, month, year);
                    dpd.show();
                }
            });

        }
}