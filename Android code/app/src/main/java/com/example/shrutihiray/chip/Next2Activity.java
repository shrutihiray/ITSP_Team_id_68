package com.example.shrutihiray.chip;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Next2Activity extends ActionBarActivity {
    private Button findBtn;
   // String text;
   private TextView text;

    int rssi=0;

    private BluetoothAdapter myBluetoothAdapter;
    private ArrayAdapter<String> BTArrayAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next2);
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (myBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Your device does not support Bluetooth",
                    Toast.LENGTH_LONG).show();
        } else {
            findBtn = (Button) findViewById(R.id.search);

            findBtn.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    // TODO Auto-generated method stub

                    find(v);

                }


            });
            BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        }
    }
    final BroadcastReceiver bReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
        //    Toast.makeText(getApplicationContext(), "in onReceive", Toast.LENGTH_SHORT).show();
            String action = intent.getAction();

            // When discovery finds a device


            if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                // Get the BluetoothDevice object from the Intent

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String stinfo = device.getName();
                if (stinfo.equals("HC-05")) {


                      rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE);
                    Toast.makeText(getApplicationContext(), "  RSSI: " + rssi + "dBm", Toast.LENGTH_SHORT).show();

                }
                text = (TextView) findViewById(R.id.text);

                if(rssi>=0)
                    {text.setText("No signal");}
                else if(rssi>(-60))
                     {text.setText("Strong signal");}
                 else
                    {text.setText("Weak signal");}
                // add the name and the MAC address of the object to the arrayAdapter

                //   BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());

                //text.setText(stinfo);

                BTArrayAdapter.notifyDataSetChanged();

            }

        }


    };
    public void find(View view) {


           /* if (myBluetoothAdapter.isDiscovering()) {

                // the button is pressed when it discovers, so cancel the discovery

                myBluetoothAdapter.cancelDiscovery();
                Toast.makeText(getApplicationContext(), "Cancelling Showing Available Devices", Toast.LENGTH_SHORT).show();

            } else {*/

        BTArrayAdapter.clear();

        myBluetoothAdapter.startDiscovery();
        Toast.makeText(getApplicationContext(), "Showing Signal Strength ", Toast.LENGTH_SHORT).show();


         registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));

        //   startActivity(intent0);


        //  }

    }
    public void FindClicked ( View view) {
        //  Intent intent = new Intent(this, bluetooth.class);
        Intent intent = new Intent(this, Next3Activity.class);

        startActivity(intent);
    }
    @Override

    protected void onDestroy() {
        // TODO Auto-generated method stub

        super.onDestroy();

          unregisterReceiver(bReceiver);
        // if(myThreadConnectBTdevice!=null){
        //     myThreadConnectBTdevice.cancel();
        // }

    }



}
