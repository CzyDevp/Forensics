package com.example.czydevp.forensics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Anukool Srivastav on 26-09-2014.
 */
public class Change extends Activity {
    Button btn;
    String id,id1,pass,newpass;
    TextView ed,edp;
    EditText newp;
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.change);
        final SpannableString s2 = new SpannableString("Do You Want to Save It ??  ");
        s2.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s2.length(), 0);
        final SpannableString s3 = new SpannableString("Password Change ");
        s3.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s3.length(), 0);
        final SpannableString s4 = new SpannableString("Yes");
        s4.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s4.length(), 0);
        final SpannableString s5 = new SpannableString("No");
        s5.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s5.length(), 0);
        final SpannableString s6 = new SpannableString("Cancel");
        s6.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s6.length(), 0);
        Bundle  getName=getIntent().getExtras();
        id=getName.getString("id");
        ed=(TextView)findViewById(R.id.edi);
        edp=(TextView)findViewById(R.id.current);
        newp=(EditText)findViewById(R.id.newp);
        db.open();
        Cursor m1 = db.getRecord12(id);
        if(m1!=null) {
            m1.moveToFirst();
            //name1 = m1.getString(m.getColumnIndex("Name"));
            id1 = m1.getString(m1.getColumnIndex("U_id"));
            pass = m1.getString(m1.getColumnIndex("Password"));
           // mail = m1.getString(m.getColumnIndex("Email"));
            m1.close();
        }
        ed.setText(id1);
        edp.setText(pass);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                newpass=newp.getText().toString().trim();
              if(  newpass.equals(edp.getText().toString().trim()))
                {
                   // Toast.makeText(getApplicationContext(),"Current and New Password Both Are Same ",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Current and New Password Both Are Same");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
              else if (!newpass.equals(""))
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(Change.this);
                    alertDialog.setTitle(s3);
                    alertDialog.setMessage(s2);
                    alertDialog.setIcon(R.drawable.alert);
                    alertDialog.setPositiveButton(s4, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User pressed YES button. Write Logic Here
                            int b = db.updatepass(id1,newpass);
                            if (b == 1) {
                               // Toast.makeText(getApplicationContext(), "Password Changed Successfully,Now Please LogIn by New Password", Toast.LENGTH_SHORT).show();
                                LayoutInflater inflater = getLayoutInflater();
                                View layout = inflater.inflate(R.layout.toast_xml,
                                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Password Changed Successfully,Now Please LogIn by New Password");
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(layout);
                                toast.setGravity(Gravity.CENTER,0,80);
                                toast.show();
                                Intent cases8 = new Intent(Change.this, Login.class);
                                cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(cases8);
                                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                            }
                            // finish();
                            //  Toast.makeText(getApplicationContext(), "You clicked on YES",Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton(s5, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User pressed No button. Write Logic Here
                               LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Confim Again");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();
                        }
                    });

                    // Setting Netural "Cancel" Button
                    alertDialog.setNeutralButton(s6, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User pressed Cancel button. Write Logic Here
                                                        LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Operation Cancelled");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();


                }
                else
                {
                    //Toast.makeText(getApplicationContext(),"Please Provide New Password",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide New Password");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        // finish() is called in super: we only override this method to be able to override the transition
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
