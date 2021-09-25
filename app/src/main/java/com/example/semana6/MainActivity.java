
package com.example.semana6;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Socket socket;
    private Writer writer;
    private int x,y,r,g,b;
    private Button ub,db,rb,lb,cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ub = findViewById(R.id.upBtn);
        db = findViewById(R.id.downBtn);
        rb = findViewById(R.id.rightBtn);
        lb = findViewById(R.id.leftBtn);
        cb = findViewById(R.id.colorBtn);
        cb.setBackgroundColor(Color.rgb(255,255,255));

        ub.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendMsg("upTrue");
                        break;
                   /* case MotionEvent.ACTION_UP:
                        sendMsg("upFalse");
                        break;*/
                }
                return false;
            }
        });

        db.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendMsg("downTrue");
                        break;
                    /*case MotionEvent.ACTION_UP:
                        sendMsg("downFalse");
                        break;*/
                }
                return false;
            }
        });

        rb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendMsg("rightTrue");
                        break;
                    /*case MotionEvent.ACTION_UP:
                        sendMsg("rightFalse");
                        break;*/
                }
                return false;
            }
        });

        lb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendMsg("leftTrue");
                        break;
                    /*case MotionEvent.ACTION_UP:
                        sendMsg("leftFalse");
                        break;*/
                }
                return false;
            }
        });

        cb.setOnClickListener(
                (v)->{
                    r = (int) (Math.random()*255);
                    g = (int) (Math.random()*255);
                    b = (int) (Math.random()*255);
                    sendMsg(""+r+"."+g+"."+b);
                    cb.setBackgroundColor(Color.rgb(r,g,b));
                }
        );
    }

    public void serveriniciar() {
        new Thread(
                ()->{
                    try {
                        //cambiar aqui la direccion ip
                        socket = new Socket("10.0.0.2",5000);
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        writer = new BufferedWriter(osw);


                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }).start();
    }

    public void sendMsg(String msg){
        new Thread(()->{
           try {
               writer.write(msg+"\n");
           } catch (IOException e){
               e.printStackTrace();
           }
        });
    }
}