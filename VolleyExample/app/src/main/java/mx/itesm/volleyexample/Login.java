package mx.itesm.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class Login extends AppCompatActivity {

    EditText edittext_user;
    EditText edittext_password;
    Button bLogin;

    JSONArray json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edittext_user = (EditText)findViewById(R.id.inUser);
        edittext_password = (EditText) findViewById(R.id.inPassword);

        bLogin = (Button)findViewById(R.id.button_login);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MD5 encoder = new MD5();
                String user = edittext_user.getText().toString();
                String password = encoder.getMd5(edittext_password.getText().toString());
                String url = "http://192.168.100.51:80/services/Login.php?username="+user+"&password="+password;
                sendDataToServer(url);
            }
        });
    }

    public void sendDataToServer(String URL)
    {
        RequestQueue queue = Volley.newRequestQueue(this);

       // Toast.makeText(getApplicationContext(), "This is URL: "+URL, Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.equals("success"))
                    {
                        //Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Wrong data!", Toast.LENGTH_SHORT);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "An Error has occurred"+ error.toString(), Toast.LENGTH_SHORT).show();
                        error.toString();
                    }
                }
        );
        queue.add(stringRequest);
    }
}
