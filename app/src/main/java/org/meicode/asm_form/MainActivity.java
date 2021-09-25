package org.meicode.asm_form;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button bProceed;
    EditText etUserName, etPrice, etNotes, date_time_in;
    RadioGroup radioGroup, radioBed;
    TextView disablePastDate, disableTime;

    // one boolean variable to check whether all the text fields
    // are filled by the user, properly or not.
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        // register buttons with their proper IDs.
        bProceed = findViewById(R.id.proceedButton);
        etUserName = findViewById(R.id.userName);
        etPrice = findViewById(R.id.price);
        etNotes = findViewById(R.id.notes);
        radioGroup = (RadioGroup) findViewById(R.id.rbGroup);
        radioBed = (RadioGroup) findViewById(R.id.rbBed);

//        date_time_in = findViewById(R.id.date_time_input);
//        date_time_in.setInputType(InputType.TYPE_NULL);

//        date_time_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showDateTimeDialog(date_time_in);
//
//            }
//        });

        disablePastDate = findViewById(R.id.disable_past_date);
        disablePastDate.setInputType(InputType.TYPE_NULL);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        disableTime = findViewById(R.id.disabletime);
        disableTime.setInputType(InputType.TYPE_NULL);

        disablePastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayofMonth) {
                        CharSequence sDate = DateFormat.format("EEE, d MMM yyyy", calendar);
                        disablePastDate.setText(sDate);
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        disableTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int HOUR = calendar.get(Calendar.HOUR);
                int MINUTE = calendar.get(Calendar.MINUTE);
                boolean is24HourFormat = DateFormat.is24HourFormat(MainActivity.this);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.set(Calendar.HOUR, hour);
                        calendar1.set(Calendar.MINUTE, minute);
                        CharSequence charSequence = DateFormat.format("hh:mm a", calendar1);
                        disableTime.setText(charSequence);
                    }
                }, HOUR, MINUTE, is24HourFormat);

                timePickerDialog.show();
            }
        });


        // handle the PROCEED button
        bProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // the boolean variable turns to be true then
                // only the user must be proceed to the activity2
                if (isAllFieldsChecked) {
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                }
            }
        });
    }

//    private void showDateTimeDialog(final EditText date_time_in) {
//        final Calendar calendar = Calendar.getInstance();
//        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                calendar.set(Calendar.YEAR, year);
//                calendar.set(Calendar.MONTH, month);
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//
//                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                        calendar.set(Calendar.MINUTE, minute);
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
//                        date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
//                    }
//                };
//                new TimePickerDialog(MainActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
//            }
//        };
//        new DatePickerDialog(MainActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//    }


    // function which checks all the text fields
    // are filled or not by the user.
    // when user clicks on the PROCEED button
    // this function is triggered.
    private boolean CheckAllFields() {
        int isSelected = radioGroup.getCheckedRadioButtonId();
        int isSelectedBed = radioBed.getCheckedRadioButtonId();

        if (isSelected == -1 && isSelectedBed == -1 && disablePastDate.length() == 0 && disableTime.length() == 0 && etPrice.length() == 0 && etUserName.length() == 0) {
            Toast.makeText(MainActivity.this, Html.fromHtml("<font color='red' ><big>" + "Please fill all required field" + "</big></font>"), Toast.LENGTH_LONG).show();
            return false;
        }

        if (isSelected == -1) {
            Toast.makeText(MainActivity.this, Html.fromHtml("<font color='red' ><big>" + "Please select opinions Property Type field" + "</big></font>"), Toast.LENGTH_LONG).show();
            return false;
        }

        if (isSelectedBed == -1) {
            Toast.makeText(MainActivity.this, Html.fromHtml("<font color='red' ><big>" + "Please select opinions BedRooms field" + "</big></font>"), Toast.LENGTH_LONG).show();
            return false;
        }

        if (disablePastDate.length() == 0) {
            Toast.makeText(MainActivity.this, Html.fromHtml("<font color='red' ><big>" + "Field Date is required" + "</big></font>"), Toast.LENGTH_LONG).show();
            return false;
        }

        if (disableTime.length() == 0) {
            Toast.makeText(MainActivity.this, Html.fromHtml("<font color='red' ><big>" + "Field Time is required" + "</big></font>"), Toast.LENGTH_LONG).show();
            return false;
        }

        if (etPrice.length() == 0) {
            etPrice.setError("Field Monthly rent price is required");
            return false;
        }

        if (!etNotes.getText().toString().matches("")) {
            if (etNotes.length() > 30) {
                etNotes.setError("Notes just maximum 30 characters");
                return false;
            }

        }

        if (etUserName.length() == 0) {
            etUserName.setError("Field User Name field is required");
            return false;
        }

        // after all validation return true.
        return true;
    }
}
