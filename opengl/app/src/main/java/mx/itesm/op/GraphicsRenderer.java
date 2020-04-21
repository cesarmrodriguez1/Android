package mx.itesm.op;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GraphicsRenderer implements GLSurfaceView.Renderer
{
     private Triangle myTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
      myTriangle = new Triangle();
      GLES20.glClearColor(0.0f, 0.0f, 1.0f, 1f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {

    }
    @Override
    public void onDrawFrame(GL10 gl)
    {
         GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
         myTriangle.draw();
    }

    public static int loadShader(int type, String shaderCode)
    {
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }


}
