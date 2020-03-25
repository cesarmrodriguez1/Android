package mx.itesm.otherintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button button_web, button_webActivity, button_caller, button_email;
EditText eWebsite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_web=(Button)findViewById(R.id.open_web);
        button_webActivity = (Button)findViewById(R.id.open_webActivity);
        button_email=(Button)findViewById(R.id.open_email);
        button_caller=(Button)findViewById(R.id.open_phone);

        eWebsite = (EditText)findViewById(R.id.edit_website);


        button_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String url = eWebsite.getText().toString();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        button_webActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String url = eWebsite.getText().toString();
                Intent browserIntent = new Intent(MainActivity.this, Web.class);
                browserIntent.putExtra("url", url);
                startActivity(browserIntent);
            }
        });

        button_caller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Caller.class);
                startActivity(intent);
                finish();
            }
        });

        button_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              Intent intent = new Intent(MainActivity.this, email.class);
              startActivity(intent);
              finish();
            }
        });
    }
}
