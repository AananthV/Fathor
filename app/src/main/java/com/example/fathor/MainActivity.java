package com.example.fathor;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private float height, weight, bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);
    }

    private void calculateBMI() {
        this.bmi = (this.weight*10000)/(this.height*this.height);
    }

    private void displayBMI() {
        LinearLayout displayField = (LinearLayout) findViewById(R.id.display_field);
        displayField.removeAllViews();

        TextView displayText = new TextView(this);
        LinearLayout.LayoutParams dtParams = (LinearLayout.LayoutParams) new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dtParams.gravity = Gravity.CENTER_VERTICAL;
        displayText.setLayoutParams(dtParams);
        displayText.setText("Your BMI is");
        displayText.setTextSize(32);
        displayText.setTextColor(Color.WHITE);

        TextView displayBMI = new TextView(this);
        dtParams.leftMargin = 16;
        displayBMI.setLayoutParams(dtParams);
        displayBMI.setText(Float.toString((float) Math.round(this.bmi*10)/10) + "!"); // Rounding Off to 1 Decimal Place
        displayBMI.setTextSize(48);

        displayField.addView(displayText);
        displayField.addView(displayBMI);

        TextView displayMessage = (TextView) findViewById(R.id.display_message);
        if(this.bmi < 18.5) {
            displayMessage.setText("You're underweight!");
            displayBMI.setTextColor(Color.RED);
        } else if(this.bmi < 24.9) {
            displayMessage.setText("You're Healthy! Keep it up!");
            displayBMI.setTextColor(Color.GREEN);
        } else {
            displayMessage.setText("You're Overweight! Take Mjolnir for a ride!");
            displayBMI.setTextColor(Color.RED);
        }
    }

    public void submitButton(View view) {
        EditText heightInput = (EditText) findViewById(R.id.height_input);
        EditText weightInput = (EditText) findViewById(R.id.weight_imput);
        String heightString = heightInput.getText().toString();
        String weightString = weightInput.getText().toString();
        Toast enterProperValues = Toast.makeText(this, "You're a god, but even gods have positive heights and weights!", Toast.LENGTH_SHORT);
        if(!(heightString.equals("") || weightString.equals(""))) {
            this.height = Float.parseFloat(heightString);
            this.weight = Float.parseFloat(weightString);
            if (this.height <= 0 || this.weight <= 0) {
                enterProperValues.show();
            } else {
                this.calculateBMI();
                this.displayBMI();
            }
        } else {
            enterProperValues.show();
        }
    }
}
