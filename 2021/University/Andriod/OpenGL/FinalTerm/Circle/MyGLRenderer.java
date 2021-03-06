package com.example.minseoblim_201621228_final_2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class MyGLRenderer implements GLSurfaceView.Renderer
{
    private Final_2 mFinal_2;

    private final float[] vPMatrix = new float[16];
    private final float[] viewMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];

    public void onSurfaceCreated(GL10 unused, EGLConfig config)
    {
        // Set the background frame color

        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        mFinal_2 = new Final_2();
    }

    public void onDrawFrame(GL10 unused)
    {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        //Matrix.setIdentityM(vPMatrix,0);
        //Matrix.setIdentityM(viewMatrix,0);
        //Matrix.setIdentityM(projectionMatrix,0);

        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -3,0f ,0f, 0f,0f, 1.0f, 0.0f);
        Matrix.multiplyMM(vPMatrix,0,projectionMatrix,0,viewMatrix,0);

        mFinal_2.draw(vPMatrix);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height)
    {
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        Matrix.frustumM(projectionMatrix,0,ratio,-ratio,-1,1,3,7);
    }

    public static int loadShader(int type, String shaderCode)
    {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
