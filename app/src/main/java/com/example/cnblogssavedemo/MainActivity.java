package com.example.cnblogssavedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread() {
            public void run() {

                TextView Text = (TextView) findViewById(R.id.textView2);
                BlogSave BlogSave= new BlogSave();
                String V=BlogSave.get();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //更改UI；
                        Text.setText(V);
                    }
                });

            }}.start();




    }


    public void button(View view){

        new Thread() {
            public void run() {
                TextView Text = (TextView) findViewById(R.id.textView2);
                EditText ed =(EditText) findViewById(R.id.editTextPhone2);

                BlogSave BlogSave= new BlogSave();

                String x = BlogSave.get();
//                Thread.sleep();
                BlogSave.push(ed.getText().toString()+"|换行|"+x);

                String V=BlogSave.get();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //更改UI；
                        Text.setText(V);
                    }
                });


            }}.start();

    }


}