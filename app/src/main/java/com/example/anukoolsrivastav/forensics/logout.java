package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Anukool Srivastav on 26-09-2014.
 */
public class logout extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_account);
    }
}
