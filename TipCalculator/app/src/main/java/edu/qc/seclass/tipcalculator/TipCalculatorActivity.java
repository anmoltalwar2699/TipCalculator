package edu.qc.seclass.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

public class TipCalculatorActivity extends AppCompatActivity {
    private Button buttonCompute;
    private EditText fifteenPercentTipValue;
    private EditText twentyPercentTipValue;
    private EditText twentyfivePercentTipValue;
    private EditText fifteenPercentTotalValue;
    private EditText twentyPercentTotalValue;
    private EditText twentyfivePercentTotalValue;
    private EditText checkAmountValue;
    private EditText partySizeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        checkAmountValue = findViewById(R.id.checkamountvalue);
        partySizeValue = findViewById(R.id.partysizevalue);
        fifteenPercentTipValue = findViewById(R.id.fifteenpercenttipvalue);
        twentyPercentTipValue = findViewById(R.id.twentypercenttipvalue);
        twentyfivePercentTipValue = findViewById(R.id.twentyfivepercenttipvalue);
        fifteenPercentTotalValue = findViewById(R.id.fifteenpercenttotalvalue);
        twentyPercentTotalValue = findViewById(R.id.twentypercenttotalvalue);
        twentyfivePercentTotalValue = findViewById(R.id.twentyfivepercenttotalvalue);
        buttonCompute = findViewById(R.id.buttonCompute);

        buttonCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplay();
                hideKeyboard();
            }
        });
    }

    private void calculateAndDisplay() {
        try {
            float amount = Float.parseFloat(checkAmountValue.getText().toString());
            int party = Integer.parseInt(partySizeValue.getText().toString());

            int x15 = (int) (((amount / party) * 15) / 100) + 1;
            int x20 = (int) ((amount / party) * 20 / 100);
            int x25 = (int) (((amount / party) * 25) / 100) + 1;

            int y15 = (int) ((amount / party) + x15);
            int y20 = (int) ((amount / party) + x20);
            int y25 = (int) ((amount / party) + x25);

            fifteenPercentTipValue.setText(String.valueOf(x15));
            twentyPercentTipValue.setText(String.valueOf(x20));
            twentyfivePercentTipValue.setText(String.valueOf(x25));
            fifteenPercentTotalValue.setText(String.valueOf(y15));
            twentyPercentTotalValue.setText(String.valueOf(y20));
            twentyfivePercentTotalValue.setText(String.valueOf(y25));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Empty or incorrect value(s)!", Toast.LENGTH_SHORT).show();
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
