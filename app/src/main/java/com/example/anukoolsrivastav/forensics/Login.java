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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
@TargetApi(Build.VERSION_CODES.HONEYCOMB)            //implements LoaderCallbacks<Cursor>
public class Login extends Activity
{
    public  static  String name,name1,id,mobile,mail,id01,mob,mail1,pass,pass1;
    private EditText e1;
    private EditText e2;
   //String username,password;
    DBAdapter db=new DBAdapter(this);
   @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
       // TextView txt = (TextView) findViewById(R.id.textView);
        //Typeface font = Typeface.createFromAsset(getAssets(), "FontleroyBrownNF.ttf");
        //txt.setTypeface(font);
        e1=(EditText)findViewById(R.id.user);
        e2=(EditText)findViewById(R.id.pass);
        Button login = (Button) findViewById(R.id.log_in);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName1 = e1.getText().toString();
                String password1 = e2.getText().toString();
                    if (userName1.equals("")  && password1.equals(""))
                {
                   // Toast.makeText(getApplication(), "Please Provide Credentials", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Credentials");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
                else if (userName1.equals(""))
                {
                   // Toast.makeText(getApplication(), "Please Provide User Name", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide User Name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
                else if (password1.equals("")) {
                    //Toast.makeText(getApplication(), "Please Provide Password", Toast.LENGTH_LONG).show();
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_xml,
                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Password");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER,0,80);
                        toast.show();
                }
                else
                {
                    db.open();
                    Cursor m = db.getRecord12("1");
                    if(m!=null) {
                        m.moveToFirst();
                        name = m.getString(m.getColumnIndex("Name"));
                        id01 = m.getString(m.getColumnIndex("U_id"));
                        mob = m.getString(m.getColumnIndex("MobileNo"));
                        mail1 = m.getString(m.getColumnIndex("Email"));

                        m.close();
                    }
                    String userName = e1.getText().toString();
                    String password = e2.getText().toString();
                    // fetch the Password form database for respective user name
                    String storedPassword = db.getSinlgeEntry(userName);
                   Cursor m1 = db.getRecord12(userName);
                    if(m1!=null) {
                        m1.moveToFirst();
                        name1 = m1.getString(m1.getColumnIndex("Name"));
                        id = m1.getString(m1.getColumnIndex("U_id"));
                        mobile = m1.getString(m1.getColumnIndex("MobileNo"));
                        mail = m1.getString(m1.getColumnIndex("Email"));

                        m1.close();
                    }
                    if (password.equals(storedPassword)) {
                        if (userName.equals(name)) {
                            Intent h = new Intent(Login.this, admin.class);
                            h.putExtra("UserName", name);
                            h.putExtra("id", id01);
                            h.putExtra("mail", mail1);
                            h.putExtra("mobile", mob);
                            overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
                            startActivity(h);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                           // Toast.makeText(Login.this, "Congrats Admin: Login Successfull", Toast.LENGTH_LONG).show();
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Congrats Admin: Login Successfull");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();

                            finish();
                        } else if (password.equals(storedPassword)) {
                            //Toast.makeText(Login.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Login Successfull");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();
                            Intent home = new Intent(Login.this, investigator.class);
                            home.putExtra("UserName", name1);
                            home.putExtra("id", id);
                            home.putExtra("mail", mail);
                            home.putExtra("mobile", mobile);

                            overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
                            startActivity(home);
                            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                           // overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
                            finish();
                        }
                    } else {
                        Toast.makeText(Login.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
          db.close();
           }

    @Override
    public void onBackPressed() {
        // finish() is called in super: we only override this method to be able to override the transition
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

}



