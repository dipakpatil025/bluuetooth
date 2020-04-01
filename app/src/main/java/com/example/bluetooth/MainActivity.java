package com.example.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQUES_ENABLE_BT = 1 ;
    public BluetoothAdapter mybluetoothAdapter;
    private Set<BluetoothDevice> paireDevices;
    private ArrayAdapter <String> BTArrayAdapter ;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =findViewById(R.id.listview);

        mybluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BTArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(BTArrayAdapter);
    }
    public void start(View v)
    {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent,REQUES_ENABLE_BT);
        Toast.makeText(this, "Turn on", Toast.LENGTH_SHORT).show();

    }
    public void stop(View v)
    {
        mybluetoothAdapter.disable();
    }
    public void list(View view)
    {
        paireDevices = mybluetoothAdapter.getBondedDevices();
        for(BluetoothDevice device : paireDevices)
            BTArrayAdapter.add(device.getName()+ "\n" +device.getAddress());
        Toast.makeText(getApplicationContext(),"Show Paired Devices",Toast.LENGTH_SHORT).show();
    }
    public void Search(View v)
    {
        if(mybluetoothAdapter.isDiscovering())
        {
            mybluetoothAdapter.cancelDiscovery();
        }
        else
        {
            BTArrayAdapter.clear();
            mybluetoothAdapter.startDiscovery();

        }
    }

}
