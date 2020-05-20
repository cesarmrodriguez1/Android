package com.example.qrsupport

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cameraSource: CameraSource? = null
    var barCodeDetector: BarcodeDetector? = null
    var cameraView: SurfaceView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barCodeDetector = BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build()

        cameraSource = CameraSource.Builder(this, barCodeDetector).setRequestedPreviewSize(640, 480).build()
            cameraView = findViewById(R.id.cameraView)

            cameraView!!.holder.addCallback(object: SurfaceHolder.Callback{
                override fun surfaceCreated(holder: SurfaceHolder?) {
                    if(ActivityCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE)
                        surfaceCreated(holder)// To call the function again with permissions granted
                        return
                    }
                    cameraSource!!.start(holder)
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder?,
                    format: Int,
                    width: Int,
                    height: Int
                ) {
                    //Nothing to do here by now
                }

                override fun surfaceDestroyed(holder: SurfaceHolder?) {
                    cameraSource!!.stop()
                }
            })



        barCodeDetector!!.setProcessor(object: Detector.Processor<Barcode>{

            override fun release() {

            }

            override fun receiveDetections(p0: Detector.Detections<Barcode>?) {
               var qrCodes: SparseArray<Barcode> = p0!!.detectedItems

                if(qrCodes.size()!=0)
                {

                    text_obtained.post{
                        val vibrator = applicationContext?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        if (Build.VERSION.SDK_INT >= 26) {
                            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                            text_obtained.setText(qrCodes.valueAt(0).displayValue)
                        } else {
                            //Support for older APIs
                            vibrator.vibrate(200)
                            text_obtained.setText(qrCodes.valueAt(0).displayValue)
                        }
                    }
                }
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraView!!.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder?) {
                cameraSource!!.start(holder)
            }

            override fun surfaceChanged(
                holder: SurfaceHolder?,
                format: Int,
                width: Int,
                height: Int
            ) {
                //Nothing to do here by now
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                cameraSource!!.stop()
            }
        })

    }
    companion object {
        private const val MY_CAMERA_PERMISSION_CODE = 100
    }
}
