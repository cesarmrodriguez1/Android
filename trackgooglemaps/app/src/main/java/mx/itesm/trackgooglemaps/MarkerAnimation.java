package mx.itesm.trackgooglemaps;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class MarkerAnimation
{
    public static void animateMarker(final Marker marker, final LatLng finalPosition, final LatLngInterpolator latLngInterpolator)
    {
        final LatLng startPosition = marker.getPosition();
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        final Interpolator interpolator = new AccelerateDecelerateInterpolator();
        final float durationMs = 2000;

        handler.post(new Runnable() {
            long elapsed;
            float t;
            float v; // interpolation
            @Override
            public void run()
            {
                elapsed = SystemClock.uptimeMillis() - start;
                t = elapsed / durationMs;
                v = interpolator.getInterpolation(t);

                marker.setPosition(latLngInterpolator.interpolate(v, startPosition, finalPosition));

                if(t < 1)
                {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }
}
