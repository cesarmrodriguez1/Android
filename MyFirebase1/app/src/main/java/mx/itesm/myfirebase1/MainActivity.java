package mx.itesm.myfirebase1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
  EditText input_name;
  EditText input_dob;
  EditText input_occupation;
  EditText input_role;
  EditText input_email;
  EditText input_password;
  Button button_send;

  //Data to register:

    String name;
    String dob;
    String occupation;
    String role;
    String email;
    String password;

    //Database objects:

    FirebaseAuth auth;
    DatabaseReference ref_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize components:

        input_name =(EditText)findViewById(R.id.myname);
        input_dob =(EditText)findViewById(R.id.mydate);
        input_occupation =(EditText)findViewById(R.id.myoccupation);
        input_role =(EditText)findViewById(R.id.myrole);
        input_email =(EditText)findViewById(R.id.myemail);
        input_password =(EditText)findViewById(R.id.mypassword);

        button_send = (Button) findViewById(R.id.button_send);

        auth = FirebaseAuth.getInstance();
        ref_database = FirebaseDatabase.getInstance().getReference();

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                name = input_name.getText().toString();
                dob = input_dob.getText().toString();
                occupation = input_occupation.getText().toString();
                role = input_role.getText().toString();
                email = input_email.getText().toString();
                password = input_password.getText().toString();

                if(!name.isEmpty() && !dob.isEmpty() && !occupation.isEmpty()&& !role.isEmpty() && !email.isEmpty() && !password.isEmpty())
                    register();
                else
                    Toast.makeText(getApplicationContext(),"Please complete all data",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void register()
    {
       auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task)
           {
               Map<String, Object> map = new HashMap<>();
               map.put("name", name);
               map.put("dob", dob);
               map.put("occupation", occupation);
               map.put("role", role);
               map.put("email", email);
               map.put("password", password);

               if(task.isSuccessful())
               {
                   String id = auth.getCurrentUser().getUid();

                   ref_database.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task)
                       {
                           if(task.isSuccessful())
                           {
                               //Everything is fine
                               Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                               startActivity(intent);
                               finish();
                           }
                           else
                               Toast.makeText(getApplicationContext(),"We had a problem uploading data",Toast.LENGTH_LONG).show();
                       }
                   });
               }
           }
       });
    }
}
