package mx.itesm.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Design of this Activity is defined in /layout/activity_main.xml

public class MainActivity extends AppCompatActivity {

    Button boton1, boton2;
    EditText edit1, edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //References
        boton1 = findViewById(R.id.button);
        boton2 = findViewById(R.id.button2);

        edit1 = findViewById(R.id.editText);
        edit2 = findViewById(R.id.editText2);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Bundle bundle1 = new Bundle();
                //Data is inserted to Bundle:
                bundle1.putString("Name", String.valueOf(edit1.getText()));

                Intent firstIntent = new Intent(getApplicationContext(), Main2Activity.class);
                firstIntent.putExtras(bundle1);
                startActivity(firstIntent);
            }
        });
    }
}
