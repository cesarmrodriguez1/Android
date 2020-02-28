package mx.itesm.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  DataManager dataManager;
  RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView)findViewById(R.id.recyclerView);
        dataManager = new DataManager(getApplicationContext());
        dataManager.initialPopulate();
        ArrayList<User> users_list = dataManager.getUsersToDisplay();
        if(users_list.isEmpty())
            Log.e(null, "List is empty");
        else
            Log.e(null, "List has data.");

        DataAdapter dataAdapter = new DataAdapter(this, users_list);

        rv.setLayoutManager(
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(dataAdapter);
    }
}
