package mx.itesm.bluetoothexample;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    TextView textStatus, textPaired;
    Button bEnable, bDisable, bDisc, bPaired;

    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textStatus = findViewById(R.id.viewStatus);
        textPaired = findViewById(R.id.viewDevices);
        bEnable = findViewById(R.id.bEnable);
        bDisable = findViewById(R.id.bDisable);
        bDisc = findViewById(R.id.bDiscoverable);
        bPaired = findViewById(R.id.bPaired);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null){
            textStatus.setText("Bluetooth is not available");
        }
        else {
            textStatus.setText("Bluetooth is available");
        }

        bEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetoothAdapter.isEnabled()){
                    Toast.makeText(getApplicationContext(), "Activating Bluetooth", Toast.LENGTH_SHORT).show();
                    //Intent to on bluetooth
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BT);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Bluetooth is ON", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bDisc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bluetoothAdapter.isDiscovering()){
                    Toast.makeText(getApplicationContext(), "Setting up device to be discoverable", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(intent, REQUEST_DISCOVER_BT);
                }
            }
        });

        bDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()){
                    bluetoothAdapter.disable();
                    Toast.makeText(getApplicationContext(), "Activating Bluetooth. . .", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Bluetooth is already off", Toast.LENGTH_SHORT).show();

            }
        });

        bPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()){
                    textPaired.setText("Paired Devices");
                    Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
                    for (BluetoothDevice device: devices){
                        textPaired.append("\nDevice: " + device.getName()+ ", " + device);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "Turn on bluetooth to get paired devices", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode == RESULT_OK)
                    Toast.makeText(this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Unable to start Bluetooth", Toast.LENGTH_SHORT).show();

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}