package sg.edu.rp.c364.id22037903.billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amountEdit;
    EditText paxEdit;
    ToggleButton svs;
    ToggleButton gst;
    EditText discountEdit;
    RadioButton cash;
    RadioButton payNow;
    TextView totalBill;
    TextView eachPay;
    Button reset;
    Button split;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEdit = findViewById(R.id.editAmount);
        paxEdit = findViewById(R.id.editPax);
        svs = findViewById(R.id.tbtnNoSVS);
        gst = findViewById(R.id.tbtnGST);
        discountEdit = findViewById(R.id.editDiscount);
        cash = findViewById(R.id.rbtnCash);
        payNow = findViewById(R.id.rbtnPayNow);
        totalBill = findViewById(R.id.viewBill);
        eachPay = findViewById(R.id.viewPay);
        reset = findViewById(R.id.btnReset);
        split = findViewById(R.id.btnSplit);

        findViewById(R.id.btnSplit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amountString = amountEdit.getText().toString();
                String paxString = paxEdit.getText().toString();
                String discountString = discountEdit.getText().toString();


                if (TextUtils.isEmpty(amountString) || TextUtils.isEmpty(paxString)) {
                    totalBill.setText("Please enter a valid amount and number of people");
                    eachPay.setText("");
                    return;
                }

                double amount = Double.parseDouble(amountString);
                int pax = Integer.parseInt(paxString);
                double discount = TextUtils.isEmpty(discountString) ? 0 : Double.parseDouble(discountString);

                double total = amount;

                if (svs.isChecked()) {
                    total += amount * 0.1;
                }

                if (gst.isChecked()) {
                    total += amount * 0.07;
                }

                total -= total * discount / 100.0;

                totalBill.setText(String.format("Total bill: $%.2f", total));
                eachPay.setText(String.format("Each person pays: $%.2f", total / pax));
            }
        });

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountEdit.setText("");
                paxEdit.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discountEdit.setText("");
             }
            });
         }
}

