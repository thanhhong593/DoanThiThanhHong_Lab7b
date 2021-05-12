package com.example.lab7_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<City> arrList = new ArrayList<>();
    CityAdapter adt;
    Button btnSave;
    Button btnCancel;
    EditText txtName;
    EditText txtID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        DatabaseHandler databaseHandler= new DatabaseHandler(MainActivity.this);

//         databaseHandler.saveCity(new City(1,"Đà lạt"));
//        databaseHandler.saveCity(new City(2,"Buôn Ma Thuật"));
//        databaseHandler.saveCity(new City(3,"Cần Thơ"));
//        databaseHandler.saveCity(new City(4,"Phú Quốc "));
//        databaseHandler.saveCity(new City(5,"Lý Sơn"));
//        databaseHandler.saveCity(new City(6,"Cần Giờ"));
//        databaseHandler.saveCity(new City(7,"Côn Đảo"));
//        databaseHandler.saveCity(new City(8,"Vũng Tàu"));

       arrList =databaseHandler.getAllStudents();

        adt= new CityAdapter(this, R.layout.item_layout,arrList);
        listView.setAdapter(adt);
        btnSave = findViewById(R.id.btnSave);
        txtName = findViewById(R.id.textCity);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =txtName.getText().toString();
                int max=arrList.size();
                databaseHandler.saveCity(new City(max+1,name));
                arrList =databaseHandler.getAllStudents();
                adt= new CityAdapter(this, R.layout.item_layout,arrList);
                listView.setAdapter(adt);
            }
        });
        btnCancel = findViewById(R.id.btnCancel);


    }
}