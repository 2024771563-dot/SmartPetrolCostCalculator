package com.example.individuass;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerPetrol;
    EditText etPrice, etUsage;
    RadioButton rbYes;
    TextView txtTotalCost, txtRebate, txtSaving;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("BUDI MADANI");
        }

        spinnerPetrol = findViewById(R.id.spinnerPetrol);
        etPrice = findViewById(R.id.etPrice);
        etUsage = findViewById(R.id.etUsage);
        rbYes = findViewById(R.id.rbYes);

        txtTotalCost = findViewById(R.id.txtTotalCost);
        txtRebate = findViewById(R.id.txtRebate);
        txtSaving = findViewById(R.id.txtSaving);

        Button btnCalculate = findViewById(R.id.btnCalculate);

        String[] petrolTypes = {"RON95", "RON97", "Diesel"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                petrolTypes);

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerPetrol.setAdapter(adapter);

        btnCalculate.setOnClickListener(v -> calculate());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(
                R.menu.main_menu,
                menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_about){

            Intent intent = new Intent(
                    MainActivity.this,
                    AboutActivity.class);

            startActivity(intent);

            return true;
        }

        if(id == R.id.menu_home){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculate() {

        if(etPrice.getText().toString().isEmpty() ||
                etUsage.getText().toString().isEmpty()) {

            Toast.makeText(this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        double price =
                Double.parseDouble(etPrice.getText().toString());

        double usage =
                Double.parseDouble(etUsage.getText().toString());

        String petrolType =
                spinnerPetrol.getSelectedItem().toString();

        boolean eligible = rbYes.isChecked();

        double totalCost = usage * price;
        double rebate = 0;

        if(petrolType.equals("RON95") && eligible) {
            rebate = usage * 1.99;
        }

        double saving = totalCost - rebate;

        txtTotalCost.setText(
                String.format("Total Petrol Cost: RM%.2f",
                        totalCost));

        txtRebate.setText(
                String.format("BUDI Rebate: RM%.2f",
                        rebate));

        txtSaving.setText(
                String.format("Total Saving: RM%.2f",
                        saving));
    }

}

