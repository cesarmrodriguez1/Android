package mx.itesm.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Button boton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle2 = new Bundle(getIntent().getExtras());

        String name = bundle2.getString("Name");

        boton3 = findViewById(R.id.button3);

         //display Message:
        Toast.makeText(getApplicationContext(),"This is the name you typed: "+name,Toast.LENGTH_LONG).show();

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
                finish(); //End this window
            }
        });
    }
}
