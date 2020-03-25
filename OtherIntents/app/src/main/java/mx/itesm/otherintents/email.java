package mx.itesm.otherintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class email extends AppCompatActivity {
EditText email_destination, email_subject, email_message;
Button button_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        email_destination = (EditText)findViewById(R.id.txtTo);
        email_subject = (EditText)findViewById(R.id.txtSub);
        email_message = (EditText)findViewById(R.id.txtMsg);

        button_send = (Button)findViewById(R.id.btnSend);

        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{email_destination.getText().toString()});
                it.putExtra(Intent.EXTRA_SUBJECT,email_subject.getText().toString());
                it.putExtra(Intent.EXTRA_TEXT,email_message.getText());
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it,"Choose Mail App"));
            }
        });
    }
}
