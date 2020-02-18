package com.dcodestar.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes;
    static SharedPreferences sharedPreferences;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onPause() {
        try {
            sharedPreferences.edit().putString("notes", ObjectSerializer.serialize(notes)).apply();
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes=new ArrayList<>();
        sharedPreferences=getSharedPreferences("com.dcodestar.notes",MODE_PRIVATE);
        try {
            notes = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("notes", ObjectSerializer.serialize(new ArrayList<String>())));
        }catch(Exception e){
            e.printStackTrace();
        }
        listView=findViewById(R.id.listView);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("noteIndex",position);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
        return true;
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: called");
        Log.d(TAG, "onResume: "+notes.toString());
        arrayAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
