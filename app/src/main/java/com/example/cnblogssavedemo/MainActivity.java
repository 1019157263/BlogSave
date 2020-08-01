package com.example.cnblogssavedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            public void run() {


                BlogSave BlogSave = new BlogSave();

                String data=BlogSave.get();
                //System.out.println();
                String jsonString = data;

                //因为json字符串是大括号包围，所以用JSONArray解析

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(jsonString);
//                    jsonArray.put("{'name':'admin','mes':'这里是消息3','time':'2020-08-01 10:34:11'}");

                    for (int i =jsonArray.length()-1;i!=0;i--) {
//                        //System.out.println(jsonArray.get(i));
                        JSONObject json = new JSONObject(jsonArray.get(i).toString());

                        String name = json.getString("name");
                        String mes = json.getString("mes")
                                .replace("}}",">")
                                .replace("{{","<");
                        String ti = json.getString("time");

                        //System.out.println("姓名："+name);
                        //System.out.println("消息："+mes);
                        //System.out.println("时间："+ti);

  
                        LayoutInflater factory = LayoutInflater.from(MainActivity.this);

//获取dialog布局文件获取View

                        final View textEntryView = factory.inflate(R.layout.item, null);

                        TextView TV3=textEntryView.findViewById(R.id.textView);
                        TextView TV4=textEntryView.findViewById(R.id.textView2);
                        TV4.setText(Html.fromHtml(name+":"+mes));
                        TV3.setText(ti);



                        LinearLayout v=(LinearLayout) findViewById(R.id.rongqi);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //更改UI；
                                v.addView(textEntryView);
                            }
                        });


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }}.start();


        new Thread() {
            public void run() {
                while (true){
                    String data_new =BlogSave.get();
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(!data_new.equals(BlogSave.get())){

                        BlogSave BlogSave = new BlogSave();

                        String data=BlogSave.get();
                        //System.out.println();
                        String jsonString = data;

                        //因为json字符串是大括号包围，所以用JSONArray解析

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(jsonString);
//                    jsonArray.put("{'name':'admin','mes':'这里是消息3','time':'2020-08-01 10:34:11'}");

                            LinearLayout v=(LinearLayout) findViewById(R.id.rongqi);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //更改UI；
                                    v.removeAllViews();
                                }});


                            for (int i =jsonArray.length()-1;i!=0;i--) {
//                        //System.out.println(jsonArray.get(i));
                                JSONObject json = new JSONObject(jsonArray.get(i).toString());

                                String name = json.getString("name");
                                String mes = json.getString("mes")
                                        .replace("}}",">")
                                        .replace("{{","<");
                                String ti = json.getString("time");

                                //System.out.println("姓名："+name);
                                //System.out.println("消息："+mes);
                                //System.out.println("时间："+ti);

                                LayoutInflater factory = LayoutInflater.from(MainActivity.this);

//获取dialog布局文件获取View

                                final View textEntryView = factory.inflate(R.layout.item, null);

                                TextView TV3=textEntryView.findViewById(R.id.textView);
                                TextView TV4=textEntryView.findViewById(R.id.textView2);
                                TV4.setText(Html.fromHtml(name+":"+mes));
                                TV3.setText(ti);


//                                LinearLayout v=(LinearLayout) findViewById(R.id.rongqi);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //更改UI；

                                        v.addView(textEntryView);
                                    }
                                });


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }}}.start();

    }


    public void button(View view){
        EditText ed =(EditText) findViewById(R.id.editTextPhone2);
        if(ed.getText().toString().equals("")) {
            Toast.makeText(this, "不能为空，刷屏了", Toast.LENGTH_SHORT).show();
        }else {
        new Thread() {
            public void run() {
                EditText ed =(EditText) findViewById(R.id.editTextPhone2);

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String time=df.format(new Date());

                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("name", android.os.Build.MODEL);
                map.put("mes", ed.getText().toString().replace("<","{{").replace(">","}}"));
                map.put("time", time);
                JSONObject json1 = new JSONObject(map);
                String data=BlogSave.get();
                //System.out.println();
                String jsonString = data;

                //因为json字符串是大括号包围，所以用JSONArray解析
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(jsonString);
                    jsonArray.put(json1);

                    for (int i =0;i<jsonArray.length();i++) {
//                        //System.out.println(jsonArray.get(i));
                        JSONObject json = new JSONObject(jsonArray.get(i).toString());
                        String name = json.getString("name");
                        String mes = json.getString("mes");
                        String ti = json.getString("time");
                        //System.out.println("姓名："+name);
                        //System.out.println("消息："+mes);
                        //System.out.println("时间："+ti);

                    }
                    BlogSave.push(jsonArray.toString());
                    ed.setText("");

                } catch (JSONException e) {
                    e.printStackTrace();
                }







//                //System.out.println(json1.toString());






//
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//                String time=df.format(new Date());
//                //System.out.println(time);// new Date()为获取当前系统时间
//
//                TextView Text = (TextView) findViewById(R.id.textView2);
//                EditText ed =(EditText) findViewById(R.id.editTextPhone2);
//
//                BlogSave BlogSave= new BlogSave();
//                String x = BlogSave.get();
//
//                BlogSave.push(ed.getText().toString().replace("<","{{").replace(">","}}")+"-----"+time+"{{br/}}"+x);
//
//                String V=BlogSave.get()
//                        .replace("}}",">")
//                        .replace("|换行|","<br/>")
//                        .replace("{{","<");
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //更改UI；
////                        Text.setText(V.replace("|换行|","\n"));
//                        Text.setText(Html.fromHtml(V));
//                    }
//                });
//
//
            }}.start();}

    }


}