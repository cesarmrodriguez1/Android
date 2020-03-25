package mx.itesm.mockitotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
 private EditText myusername, mypassword;
 private Button bSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myusername = (EditText)findViewById(R.id.edit_username);
        mypassword = (EditText)findViewById(R.id.edit_password);
        bSubmit = (Button)findViewById(R.id.button_submit);

        String username = myusername.getText().toString();
        String password = mypassword.getText().toString();

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });


    }
}
