package com.example.anukoolsrivastav.forensics;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)            //implements LoaderCallbacks<Cursor>
public class splash extends Activity
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
                Intent intent = new Intent(splash.this,Login.class);
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



