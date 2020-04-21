package mx.itesm.op;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class OpenGLView extends GLSurfaceView
{
    public OpenGLView(Context context)
    {
        super(context);
        initGraphics();
    }

    public OpenGLView (Context context, AttributeSet attrs)
    {
        super(context,attrs);
        initGraphics();
    }
    private void initGraphics()
    {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(new GraphicsRenderer());
    }
}




