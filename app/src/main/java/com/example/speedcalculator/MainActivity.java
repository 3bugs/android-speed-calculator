package com.example.speedcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

   EditText mDistanceEditText, mTimeEditText;
   TextView mSpeedTextView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mDistanceEditText = findViewById(R.id.distance_edit_text);
      mTimeEditText = findViewById(R.id.time_edit_text);
      mSpeedTextView = findViewById(R.id.speed_text_view);

      Button calculateButton = findViewById(R.id.calculate_button);
      calculateButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            String distanceText = mDistanceEditText.getText().toString().trim();
            String timeText = mTimeEditText.getText().toString().trim();
            if (distanceText.isEmpty() || timeText.isEmpty()) {
               Toast.makeText(MainActivity.this, R.string.distance_and_time_required, Toast.LENGTH_LONG).show();
               return;
            }

            double distance = Double.parseDouble(distanceText);
            double time = Double.parseDouble(timeText);
            if (time == 0) {
               Toast.makeText(MainActivity.this, R.string.time_must_be_greater_than_zero, Toast.LENGTH_LONG).show();
               return;
            }

            double speed = (distance / 1000) / (time / 3600);
            mSpeedTextView.setText(String.format(Locale.getDefault(), "%.2f", speed));
            if (speed > 80) {
               new AlertDialog.Builder(MainActivity.this)
                     .setTitle("SPEED CALCULATOR")
                     .setMessage(R.string.speed_over_limit)
                     .setPositiveButton("OK", null)
                     .show();
            }
         }
      });

      Button clearButton = findViewById(R.id.clear_button);
      clearButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            clearForm();
            mDistanceEditText.requestFocus();
         }
      });
   }

   void clearForm() {
      mDistanceEditText.setText("");
      mTimeEditText.setText("");
      mSpeedTextView.setText("");
   }
}
