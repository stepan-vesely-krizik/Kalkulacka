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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializace UI prvků
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        spinnerOperation = findViewById(R.id.spinnerOperation);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);

        // Nastavení adapteru pro Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperation.setAdapter(adapter);

        // Skrývání druhého vstupu u operace "Faktoriál"
        spinnerOperation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (operations[position].equals("Faktoriál")) {
                    findViewById(R.id.textInputLayout2).setVisibility(View.GONE);
                } else {
                    findViewById(R.id.textInputLayout2).setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Zpracování výpočtu
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input1 = editTextNumber1.getText().toString().trim();
                String input2 = editTextNumber2.getText().toString().trim();

                if (input1.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Zadejte první číslo", Toast.LENGTH_SHORT).show();
                    return;
                }

                double num1 = 0;
                double num2 = 0;
                try {
                    num1 = Double.parseDouble(input1);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Neplatné první číslo", Toast.LENGTH_SHORT).show();
                    return;
                }

                int operationIndex = spinnerOperation.getSelectedItemPosition();
                String selectedOperation = operations[operationIndex];
                double result = 0;
                try {
                    switch (selectedOperation) {
                        case "Sčítání":
                            if (input2.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Zadejte druhé číslo", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            num2 = Double.parseDouble(input2);
                            result = num1 + num2;
                            break;
                        case "Odčítání":
                            if (input2.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Zadejte druhé číslo", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            num2 = Double.parseDouble(input2);
                            result = num1 - num2;
                            break;
                        case "Násobení":
                            if (input2.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Zadejte druhé číslo", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            num2 = Double.parseDouble(input2);
                            result = num1 * num2;
                            break;
                        case "Dělení":
                            if (input2.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Zadejte druhé číslo", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            num2 = Double.parseDouble(input2);
                            if (num2 == 0) {
                                Toast.makeText(MainActivity.this, "Nelze dělit nulou", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            result = num1 / num2;
                            break;
                        case "Modulo":
                            if (input2.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Zadejte druhé číslo", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            num2 = Double.parseDouble(input2);
                            if (num2 == 0) {
                                Toast.makeText(MainActivity.this, "Modulo nulou není povoleno", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            result = num1 % num2;
                            break;
                        case "Ntou mocnina":
                            if (input2.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Zadejte exponent", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            num2 = Double.parseDouble(input2);
                            result = Math.pow(num1, num2);
                            break;
                        case "Ntou odmocnina":
                            if (input2.isEmpty()) {
                                Toast.makeText(MainActivity.this, "Zadejte číslo, z kterého se počítá odmocnina", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            // num1 = stupeň odmocniny (např. 2 = druhá odmocnina)
                            num2 = Double.parseDouble(input2);
                            if (num1 == 0) {
                                Toast.makeText(MainActivity.this, "Stupeň odmocniny nesmí být nula", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            result = Math.pow(num2, 1.0 / num1);
                            break;
                        case "Faktoriál":
                            int n = (int) num1;
                            if (n < 0) {
                                Toast.makeText(MainActivity.this, "Faktoriál není definován pro záporná čísla", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            result = factorial(n);
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "Neznámá operace", Toast.LENGTH_SHORT).show();
                            return;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Neplatný vstup", Toast.LENGTH_SHORT).show();
                    return;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Chyba při výpočtu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                textViewResult.setText("Výsledek: " + result);
            }
        });
    }

    /**
     * Metoda pro výpočet faktoriálu čísla n.
     * @param n číslo, pro které se faktoriál počítá
     * @return faktoriál čísla n
     */
    private long factorial(int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
            if (fact < 0) {
                throw new ArithmeticException("Výsledek je příliš velký");
            }
        }
        return fact;
    }
}