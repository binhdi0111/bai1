package com.binhdi0111.bka.ex9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity  {
    ListView lvNotification;
    ArrayList<Notification> arrayList= new ArrayList<>();;
    NotificationAdapter adapter;
    TextView txtShow;

    BroadCastReceiver broadCastReceiver = new BroadCastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
            if (intent != null){
                String title = intent.getStringExtra("title");
                String message= intent.getStringExtra("message");
                SetData(title, message);
                Log.d("aaaaaaaaa", "onReceive: "+message);
                Log.d("hhohohohohohohoho", "onReceive: "+title);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        pushNotification();
        IntentFilter filter = new IntentFilter("send-notify_data");
        registerReceiver(broadCastReceiver,filter);
    }


    private void Anhxa() {
        lvNotification = (ListView) findViewById(R.id.listViewNotification);
        adapter = new NotificationAdapter(this,R.layout.dong_noti,arrayList);
        lvNotification.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void SetData(String title,String message){
        arrayList.add(new Notification(title,message));
        adapter.notifyDataSetChanged();
        txtShow = (TextView) findViewById(R.id.textViewShow);
        txtShow.setText(title+"\n"+message);
    }
    private void pushNotification(){
        Intent intent = new Intent(MainActivity.this,Myservice.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCastReceiver);
    }
}