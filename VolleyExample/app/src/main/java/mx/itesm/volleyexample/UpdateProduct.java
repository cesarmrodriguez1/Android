package mx.itesm.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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

public class UpdateProduct extends AppCompatActivity {

    static private Activity activity;
    Bundle extras;
    String id;
    String wsgetProduct, wsUpdateProduct;
    JSONArray product_data;
    EditText editName, editPrice, editURL;
    Button bSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        extras = getIntent().getExtras();

        editName = (EditText)findViewById(R.id.updated_name);
        editPrice = (EditText)findViewById(R.id.updated_price);
        editURL = (EditText)findViewById(R.id.updated_urlImage);
        bSend = (Button)findViewById(R.id.button_update);

        if(extras!=null)
        {
            id = extras.getString("id");
            wsgetProduct = "https://10.0.0.2/services/getProduct.php?id="+id;
            getProductInfo(wsgetProduct);
        }

        bSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String name = editName.getText().toString();
                String price = editPrice.getText().toString();
                String urlImage = editURL.getText().toString();
                sendDataToServer("http://10.0.0.2/services/updateproduct.php?id="+id+"&name="+name+"&price="+price+"&url="+urlImage);
            }
        });
    }

    private void getProductInfo(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray json;
                try {
                    json = new JSONArray(response);

                    editName.setText(json.getString(0));
                    editPrice.setText(json.getString(1));
                    editURL.setText(json.getString(2));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Wrong data!", Toast.LENGTH_SHORT).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Code to handle errors in response.
                    }
                }
        );
        queue.add(stringRequest);
    }

    private void sendDataToServer(String url)
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    if(response.equals("success"))
                    {
                        Toast.makeText(getApplicationContext(),"Product was updated",Toast.LENGTH_LONG).show();
                        Intent goMain = new Intent(UpdateProduct.this, MainActivity.class);
                        startActivity(goMain);
                        finish();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"There was a problem while updating product",Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Code to handle errors in response.
                    }
                }
        );
        queue.add(stringRequest);
    }
}
