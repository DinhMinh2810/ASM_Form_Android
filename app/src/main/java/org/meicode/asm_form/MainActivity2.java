package org.meicode.asm_form;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    TextView disablePastDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


//        disablePastDate = findViewById(R.id.disable_past_date);
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        final int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minutes = calendar.get(Calendar.MINUTE);

//        disablePastDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity2.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayofMonth) {
//                        String sDate = dayofMonth + "/" + month + '/' + year;
//                        disablePastDate.setText(sDate);
//                    }
//                }, year, month, day);
//                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//                datePickerDialog.show();
//            }
//        });
    }
}