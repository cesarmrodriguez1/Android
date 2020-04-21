package mx.itesm.volleyexample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

public class MainActivity extends AppCompatActivity implements RequestProduct.Callback {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager manejadorLayout;
    ProductAdapter adapter;
    private String filtro = "";
    List<Product> product_list;
    private Handler handler = new Handler();

    JSONArray json;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        product_list = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        manejadorLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manejadorLayout);


        RequestProduct.getRequest(this,this);
        Toast.makeText(getApplicationContext(),"Getting data from server. . .",Toast.LENGTH_LONG).show();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                try {
                    adapter.notifyDataSetChanged();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 3000);

        populateRecyclerView();
    }
    private void populateRecyclerView()
    {
        adapter = new ProductAdapter(product_list, this, recyclerView);
        recyclerView.setAdapter(adapter);
    }
    private String getResponse(String response)
    {
        return response;
    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void processJSON(String response) {
        try{
            json = new JSONArray(response);

            for(int i = 0; i<json.length();i++)
            {
                JSONArray jsonArray = json.getJSONArray(i);

                Product product = new Product();
                product.setId(jsonArray.getJSONObject(0).getString("id"));
                product.setName(jsonArray.getJSONObject(0).getString("name"));
                product.setBrand(jsonArray.getJSONObject(0).getString("brand"));
                product.setPrice(jsonArray.getJSONObject(0).getString("price"));
                product.setUrl_image(jsonArray.getJSONObject(0).getString("urlImage"));
                product_list.add(product);
            }
        }catch(JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onError()
    {

    }
}

