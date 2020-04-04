package mx.itesm.servicesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button bPlay, bStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPlay = (Button) findViewById(R.id.bPlay);
        bStop = (Button) findViewById(R.id.bStop);

        bPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myservice = new Intent(getApplicationContext(), MusicService.class);
                getApplicationContext().startService(myservice);
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent myservice = new Intent(getApplicationContext(), MusicService.class);
                getApplicationContext().stopService(myservice);
            }
        });
    }
}
