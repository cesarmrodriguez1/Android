package mx.itesm.multimediakotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenImage.setOnClickListener()
        {
            var intent = Intent(this, LocalImage::class.java)
            startActivity(intent)
        }

        btnOpenVideo.setOnClickListener()
        {
            var intent = Intent(this, LocalVideo::class.java)
            startActivity(intent)
        }

        btnYouTube.setOnClickListener()
        {
            var intent = Intent(this, YouTubeVideo::class.java)
            startActivity(intent)
        }
    }

}
