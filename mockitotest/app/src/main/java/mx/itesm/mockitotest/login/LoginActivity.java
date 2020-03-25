package mx.itesm.mockitotest.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.itesm.mockitotest.R;
import mx.itesm.mockitotest.app.MainActivity;

public class LoginActivity extends AppCompatActivity implements Login.Activity {
 private EditText myusername, mypassword;
 private Button bSubmit;
 private Login.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myusername = (EditText)findViewById(R.id.edit_username);
        mypassword = (EditText)findViewById(R.id.edit_password);
        bSubmit = (Button)findViewById(R.id.button_submit);

        presenter = new LoginPresenter(this);

        String username = myusername.getText().toString();
        String password = mypassword.getText().toString();

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });


    }

    @Override
    public void userIsValid()
    {
        Intent goMain = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(goMain);
        finish();
    }

    @Override
    public String getUsername()
    {
        return null;
    }

    @Override
    public String getPassword()
    {
        return null;
    }

    @Override
    public void data_error()
    {
       Toast.makeText(getApplicationContext(),"Invalid user",Toast.LENGTH_SHORT).show();
    }
}
