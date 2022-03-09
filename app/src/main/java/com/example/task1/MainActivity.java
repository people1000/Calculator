package com.example.task1;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    boolean isDegree;
    boolean checkRadio = false;
    String[] operations = {"sin", "cos", "tg", "ctg"};
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Spinner spinner = findViewById(R.id.spinner);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, operations);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Получаем выбранный объект
                item = (String)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    public void onRadioButtonClicked(View view) {
        // если переключатель отмечен
        boolean checked = ((RadioButton) view).isChecked();
        Toast toast;
        checkRadio = true;

        // Получаем нажатый переключатель
        switch(view.getId()) {
            case R.id.degree:
                if (checked){
                    toast = Toast.makeText(this, "Единица измерения - градусы", Toast.LENGTH_SHORT);
                    toast.show();
                    isDegree = true;
                }
                break;
            case R.id.radian:
                if (checked){
                    toast = Toast.makeText(this, "Единица измерения - радианы", Toast.LENGTH_LONG);
                    toast.show();
                    isDegree = false;
                }
                break;
        }
    }


    public void calculations(View view) {
        EditText editText = findViewById(R.id.number);
        TextView resultt = findViewById(R.id.result);
        Editable text = editText.getText();
        String textToString = text.toString();
        if (!checkRadio) {
            Toast.makeText(this, "Выберите единицу измерения", Toast.LENGTH_SHORT).show();
        } else {
            if (!textToString.isEmpty()) {
                double number;
                double numberInput = Double.parseDouble(text.toString());
                DecimalFormat newFormat = new DecimalFormat("#.####");
                if (isDegree) {
                    number = Math.toRadians(numberInput);
                }
                else{
                    number = numberInput;
                }
                switch (item) {
                    case ("sin"):
                        resultt.setText("sin(" + Double.valueOf(newFormat.format(numberInput)) + ") = " + Double.valueOf(newFormat.format(Math.sin(number))));
                        break;
                    case ("cos"):
                        resultt.setText("cos(" + Double.valueOf(newFormat.format(numberInput)) + ") = " + Double.valueOf(newFormat.format(Math.cos(number))));
                        break;
                    case ("tg"):
                        resultt.setText("tg(" + Double.valueOf(newFormat.format(numberInput)) + ") = " + Double.valueOf(newFormat.format(Math.tan(number))));
                        break;
                    case ("ctg"):
                        resultt.setText("ctg(" + Double.valueOf(newFormat.format(numberInput)) + ") = " + Double.valueOf(newFormat.format(1 / Math.tan(number))));
                        break;
                }
            } else {
                Toast.makeText(this, "Введите значение!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
