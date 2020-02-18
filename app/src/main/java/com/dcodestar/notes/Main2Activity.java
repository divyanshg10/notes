package com.dcodestar.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText editText;
    int index;
    private static final String TAG = "Main2Activity";

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: called");
        Log.d(TAG, "onPause: "+MainActivity.notes.toString());
        if(index==-1){
            MainActivity.notes.add(0,editText.getText().toString());
        }else{
            MainActivity.notes.set(index,editText.getText().toString());
        }
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText=findViewById(R.id.editText);
        Intent intent=getIntent();
        index=intent.getIntExtra("noteIndex",-1);
        if(index!=-1){
            editText.setText(MainActivity.notes.get(index));
        }

    }
}
