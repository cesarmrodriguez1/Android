package mx.itesm.navigationdrawerexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class FragmentOthers extends Fragment
{
    Button bScan;
    WifiManager wifiManager;
    String [] wifis;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_others, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         bScan = (Button) view.findViewById(R.id.buttonwifi);

        wifiManager = (WifiManager)
                getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
bScan.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                boolean success = intent.getBooleanExtra(
                        WifiManager.EXTRA_RESULTS_UPDATED, false);
                if (success) {
                    Toast.makeText(getActivity().getApplicationContext(),"Successful! :)",Toast.LENGTH_LONG).show();
                    scanSuccess();
                } else {
                    // scan failure handling
                    Toast.makeText(getActivity().getApplicationContext(),"Error in scan handling",Toast.LENGTH_LONG).show();
                    scanFailure();
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        getActivity().getApplicationContext().registerReceiver(wifiScanReceiver, intentFilter);

        boolean success = wifiManager.startScan();
        if (!success) {
            // scan failure handling
            Toast.makeText(getActivity().getApplicationContext(),"Error in scan handling after starScan()",Toast.LENGTH_LONG).show();
            scanFailure();
        }

    }
});
    }
    private void scanSuccess() {
        List<ScanResult> results = wifiManager.getScanResults();
        wifis = new String[results.size()];
        // Get Each network detail
        for (int i=0; i<results.size();i++) {
            wifis[i] = results.get(i).toString();
        }
    }

    private void scanFailure() {
        // handle failure: new scan did NOT succeed
        // consider using old scan results: these are the OLD results!
        List<ScanResult> results = wifiManager.getScanResults();

    }
}
