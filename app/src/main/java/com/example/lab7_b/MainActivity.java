package com.example.lab7_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    public ArrayList<City> arrList = new ArrayList<>();
    City city ;

    Button btnSave;
    Button btnCancel;
    EditText txtName;
    ArrayAdapter adt;
    ImageButton btnImgDelete;
    ImageButton btnImgEdit;
    ArrayList idList;

    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
         databaseHandler= new DatabaseHandler(MainActivity.this);
        idList = new ArrayList();
        btnImgEdit =findViewById(R.id.imgEdit);
        btnSave = findViewById(R.id.btnadd);
        txtName = findViewById(R.id.edtinput);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =txtName.getText().toString();
                int max=arrList.size();
                databaseHandler.saveCity(new City(max+1,name));
                recreate();

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnImgDelete = (ImageButton) view.findViewById(R.id.imgDelete);
                btnImgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseHandler.deleteStudent(position);
                        Toast.makeText(MainActivity.this, "Xo?? place th??nh c??ng!!!", Toast.LENGTH_SHORT).show();
                        recreate();
                    }
                });
//                ibtnupdate = view.findViewById(R.id.ibtnupdate);
//                String name = edtInput.getText().toString();
//                ibtnupdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        databaseHandler.updateTravel(name,places, position);
//                        recreate();
//                    }
//                });
            }
        });

//         databaseHandler.saveCity(new City(1,"???? l???t"));
//        databaseHandler.saveCity(new City(2,"Bu??n Ma Thu???t"));
//        databaseHandler.saveCity(new City(3,"C???n Th??"));
//        databaseHandler.saveCity(new City(4,"Ph?? Qu???c "));
//        databaseHandler.saveCity(new City(5,"L?? S??n"));
//        databaseHandler.saveCity(new City(6,"C???n Gi???"));
//        databaseHandler.saveCity(new City(7,"C??n ?????o"));
//        databaseHandler.saveCity(new City(8,"V??ng T??u"));

       arrList =databaseHandler.getAllStudents();

        adt= new ArrayAdapter(this, R.layout.item_layout, R.id.tvName,arrList);

        listView.setAdapter(adt);
    }

}