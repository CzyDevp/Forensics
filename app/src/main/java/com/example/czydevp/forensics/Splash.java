package com.example.czydevp.forensics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)            //implements LoaderCallbacks<Cursor>
public class Splash extends Activity
{
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Splash.this,Login.class);
                startActivity(intent);
                finishscreen();
            }
        };
        Timer t = new Timer();
        t.schedule(task,3000);
    }
    private void finishscreen() {
        this.finish();
    }
}



