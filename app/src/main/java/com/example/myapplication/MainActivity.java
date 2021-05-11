package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String vl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_01);
        ListView listView = (ListView)findViewById(R.id.listView);
        DatabaseHandler dth= new DatabaseHandler(this);
/*
        dth.addStudent(new Student("Trần Nam"));
        dth.addStudent(new Student("Nguyễn Hoài Ân"));
        dth.addStudent(new Student("Nguyễn Tuấn Thành"));
        */

        List<Student> students= dth.getAllStudents();

        ArrayAdapter<Student> arrayAdapter
                = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1 , students);

        listView.setAdapter(arrayAdapter);

        Button btnAdd =(Button) this.findViewById(R.id.addButton);
        EditText editText = (EditText)findViewById(R.id.edtName);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=(String) editText.getText().toString();
                dth.addStudent(new Student(name));
                List<Student> students= dth.getAllStudents();
                arrayAdapter.clear();
                arrayAdapter.addAll(students);
                arrayAdapter.notifyDataSetChanged();

            }
        });


        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = listView.getItemAtPosition(i);
                String id= null;
                id= ((Student) o).getId()+"";
                Toast.makeText(MainActivity.this,"Da chon student co id la:" + id, Toast.LENGTH_SHORT).show();
                vl=id;
            }
        });
        Button btnRemove =(Button) this.findViewById(R.id.removeButton);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!vl.equalsIgnoreCase("")) dth.deleteStudent(Integer.parseInt(vl));
                List<Student> students= dth.getAllStudents();
                arrayAdapter.clear();
                arrayAdapter.addAll(students);
                arrayAdapter.notifyDataSetChanged();
                vl="";
            }
        });

    }
}