package com.example.admin.callardar.Classes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.ArrayList;

//篝(かがり)火
public class 篝火
{
    private boolean isRunning = true;

    private Handler h;
    private ImageView view;
    private int[][] pixels;

    private final int width;
    private final int height;
    private final int[][][] object;
    private ArrayList<Fire> fires;

    public 篝火(int[][][] fire, final Handler handler, final ImageView imageView)
    {
        h = handler;
        view = imageView;

        width = imageView.getWidth();
        height = imageView.getHeight();
        object = fire;
        fires = new ArrayList<Fire>();

        pixels = new int[height][width];
    }

    public void open()
    {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        paint.setStrokeWidth(5);

        new Thread(new Runnable()
        {
            private long sysTime = System.currentTimeMillis();

            @Override
            public void run()
            {
                while(isRunning)
                {
                    if(System.currentTimeMillis() - sysTime > 500)
                    {
                        for(int i = 0 ; i < height ; i += 1)
                        {
                            for(int j = 0 ; j < width ; j += 1)
                            {
                                int A = Color.alpha(pixels[i][j]);
                                int R = Color.red(pixels[i][j]);
                                int G = Color.green(pixels[i][j]);
                                int B = Color.blue(pixels[i][j]);

                                paint.setARGB(A, R, G, B);
                                canvas.drawPoint(j, i, paint);
                            }
                        }

                        Message message = new Message();
                        message.obj = view;
                        h.sendMessage(message);
                        sysTime = System.currentTimeMillis();
                    }
                }
            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(isRunning)
                {
                    //toDO
                    new Fire(object, 0, 0, 0);

                    try
                    {
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(isRunning)
                {
                    for(int i = 0 ; i < fires.size() ; i += 1)
                    {
                        final int index = i;

                        new Thread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                //toDO
                                fires.get(index).next(0, 0);
                            }
                        }).start();
                    }
                }
            }
        }).start();
    }

    public void close()
    {
        isRunning = false;
        h.removeCallbacksAndMessages(0);
        Message message = new Message();
        message.what = -1;
        h.sendMessage(message);
    }

    private class Fire
    {
        private int x;
        private int y;
        private int speed;
        private long sysTime;
        private boolean operation;
        //a r g b
        private int[][][] matrix;

        private Fire(int[][][] object, int x, int y, int speed)
        {
            this.x = x;
            this.y = y;
            this.speed = speed;
            sysTime = System.currentTimeMillis();

            matrix = new int[8][y][x];

            for(int i = 0 ; i < 8 ; i += 1)
            {
                for(int j = 0 ; j < object[i].length ; j += 1)
                {
                    for(int k = 0 ; k < object[i][j].length ; k += 1)
                    {
                        matrix[i][j][k] = object[i][j][k];
                    }
                }
            }

            fires.add(this);
        }

        public void next(int x_TO, int y_TO)
        {
            if( ! operation && System.currentTimeMillis() - sysTime < speed)
            {
                return;
            }

            operation = true;

            x = x_TO;
            y = y_TO;

            int[] copy = new int[y];

            for(int i = 0 ; i < y ; i += 1)
            {
                copy[i] = matrix[0][i][0];
            }

            for(int i = 0 ; i < 8 - 1; i += 1)
            {
                for(int j = 0 ; j < matrix[i].length ; j += 1)
                {
                    for(int k = 0 ; k < matrix[i][j].length - 1; k += 1)
                    {
                        matrix[i][j][k] = matrix[i][j][k + 1];
                    }

                    matrix[i][j][matrix[i][j].length - 1] = matrix[i + 1][j][0];
                }
            }

            for(int i = 0 ; i < matrix[7].length ; i += 1)
            {
                for(int j = 0 ; j < matrix[7][i].length - 1; j += 1)
                {
                    matrix[7][i][j] = matrix[7][i][j + 1];
                }
            }

            for(int i = 0 ; i < copy.length ; i += 1)
            {
                matrix[7][i][matrix[7][i].length - 1] = copy[i];
            }

            for(int i = 0 ; i < matrix[0][i].length ; i += 1)
            {
                for(int j = 0 ; j < matrix[0][i].length ; j += 1)
                {
                    pixels[i + y][j + x] = Algorithm.setTransparent(Color.alpha(matrix[0][i][j]), matrix[0][i][j], pixels[i + y][j + x]);
                }
            }

            sysTime = System.currentTimeMillis();
            operation = false;
        }
    }
}
