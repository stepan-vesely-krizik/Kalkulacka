package com.example.ukol_kalkulacka;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Definice UI prvků
    private TextInputEditText editTextNumber1, editTextNumber2;
    private Spinner spinnerOperation;
    private MaterialButton buttonCalculate;
    private TextView textViewResult;

    // Pole operací
    private String[] operations = {
            "Sčítání",
            "Odčítání",
            "Násobení",
            "Dělení",
            "Modulo",
            "Ntou mocnina",
            "Ntou odmocnina",
            "Faktoriál"
    };