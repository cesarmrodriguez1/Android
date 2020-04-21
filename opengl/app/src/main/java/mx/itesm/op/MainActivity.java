package mx.itesm.op;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    private OpenGLView openGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openGLView = (OpenGLView) findViewById(R.id.openGLView);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        openGLView.onResume();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        openGLView.onPause();
    }
}
