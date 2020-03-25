package mx.itesm.otherintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Caller extends AppCompatActivity {
Button bCall;
EditText phone_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caller);

        bCall = (Button)findViewById(R.id.button_call);
        phone_edit = (EditText)findViewById(R.id.edit_phone);

        bCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             try{
                 String number=phone_edit.getText().toString();
                 Intent callIntent = new Intent(Intent.ACTION_CALL);
                 callIntent.setData(Uri.parse("tel:+"+number));

                 if (ContextCompat.checkSelfPermission(Caller.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                     ActivityCompat.requestPermissions(Caller.this, new String[]{Manifest.permission.CALL_PHONE}, 1); //One means REQUEST_CALL code
                 }
                 else
                 {
                     startActivity(callIntent);
                 }
             }
                catch(SecurityException e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "User declined permission", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
