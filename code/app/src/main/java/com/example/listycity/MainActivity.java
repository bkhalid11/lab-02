package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;

    ArrayList<String> dataList;

    Button btnAddCity, btnDeleteCity, btnConfirm;
    EditText etCity;
    LinearLayout addRow;

    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        btnAddCity = findViewById(R.id.btn_add_city);
        btnDeleteCity = findViewById(R.id.btn_delete_city);
        btnConfirm = findViewById(R.id.btn_confirm);
        etCity = findViewById(R.id.et_city);
        addRow = findViewById(R.id.add_row);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this,R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        //select city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });


        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRow.setVisibility(View.VISIBLE);
                etCity.requestFocus();
            }
        });

        //adds typed city
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = etCity.getText().toString().trim();

                if (newCity.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter a city name", Toast.LENGTH_SHORT).show();
                    return;
                }

                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();

                etCity.setText("");
                addRow.setVisibility(View.GONE);
            }
        });

        //removes selected city
        btnDeleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex < 0 || selectedIndex >= dataList.size()) {
                    Toast.makeText(MainActivity.this, "Tap a city to select it first", Toast.LENGTH_SHORT).show();
                    return;
                }

                dataList.remove(selectedIndex);
                cityAdapter.notifyDataSetChanged();
                selectedIndex = -1;
            }
        });



    }
}