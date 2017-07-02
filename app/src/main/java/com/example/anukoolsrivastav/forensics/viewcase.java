package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anukool Srivastav on 24-09-2014.
 */
public class viewcase extends Activity {
    Button btn;
    AutoCompleteTextView actv;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7;
    String id1,a,mail0,uid,mbl,pass;
    Cursor ids;
    TextView tv12,getpd;
            Button pdf;
    ArrayAdapter<String> dataAdapter;
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_case);
        btn=(Button)findViewById(R.id.button);
        actv = (AutoCompleteTextView) findViewById(R.id.auto1);
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        mail0=getName.getString("mail");
        uid=getName.getString("id");
        mbl=getName.getString("mobile");
        tv12=(TextView)findViewById(R.id.viewall);
        pdf=(Button)findViewById(R.id.pdf);
        getpd=(TextView)findViewById(R.id.getpdf);
        getpd.setEnabled(false);
        getpd.setVisibility(View.GONE);
        pdf.setEnabled(false);
        pdf.setVisibility(View.GONE);
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = tv2.getText().toString();
                String filecontent = tv1.getText().toString();
                String name = tv2.getText().toString();
                String Users=tv3.getText().toString();
                String type=tv4.getText().toString();
                String status=tv5.getText().toString();
                String Description=tv6.getText().toString();
                String startedon=tv7.getText().toString();
                FileOperations fop = new FileOperations();
                fop.write(filename, filecontent,name,Users,type,status,Description,startedon,pass);
                if (fop.write(filename, filecontent,name,Users,type,status,Description,startedon,pass))
                {
                    Toast.makeText(getApplicationContext(),
                            filename + ".pdf created", Toast.LENGTH_SHORT)
                            .show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "I/O error",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent case1 = new Intent(viewcase.this,listevdnc.class);
                case1.putExtra("user",a);
                case1.putExtra("id",uid);
                case1.putExtra("mail",mail0);
                case1.putExtra("mobile",mbl);
                startActivity(case1);
            }
        });
         db.open();
        List<String> labels = db.getTableValuescase();
        dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner,labels);
        actv.setAdapter(dataAdapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actv1 = actv.getText().toString().trim();
                actv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actv.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                tv1 = (TextView) findViewById(R.id.tv1);
                                tv2 = (TextView) findViewById(R.id.tv2);
                                tv3 = (TextView) findViewById(R.id.tv3);
                                tv4 = (TextView) findViewById(R.id.tv4);
                                tv5 = (TextView) findViewById(R.id.tv5);
                                tv6 = (TextView) findViewById(R.id.tv6);
                                tv7 = (TextView) findViewById(R.id.tv7);
                                tv1.setText("Id");
                                tv2.setText("Name");
                                tv3.setText("Investigators");
                                tv4.setText("Type");
                                tv5.setText("Status");
                                tv6.setText("Description");
                                tv7.setText("StartedOn");
                                pdf.setEnabled(false);
                                pdf.setVisibility(View.GONE);
                                getpd.setVisibility(View.GONE);
                                pass="";
                                 }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }
                });
                if (actv.getText().length() == 0 || actv.getText().toString() == "" || actv.getText().toString() == null || actv1 == "")

                {
                         LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Soelect Valid Id");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    pdf.setEnabled(false);
                    pdf.setVisibility(View.GONE);
                    getpd.setVisibility(View.GONE);
                    pass="";

                }
                       else
                {
                    String id = actv.getText().toString().trim();
                    id1 = id.toString().trim();
                    db.open();
                    String[] m = new String[6];
                    String[] u;
                    String users="";
                    if (id1.length() > 0)
                    {
                        m = db.getRecordsrchcase(id1);
                       if (m[0] == "k" && m[1] == "k" && m[2] == "k" && m[3] == "k" && m[4] == "k" && m[5] == "k")
                        {
                                    LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 account exist with same Name");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();
                                  pdf.setEnabled(false);
                                  pdf.setVisibility(View.GONE);
                            getpd.setVisibility(View.GONE);
                                  pass="";
                        }
                    else if(m[0]!="" && m[1]!="" && m[2]!="" && m[3]!=""&& m[4]!="" && m[5]!="" &&m[0] != "k" && m[1] != "k" && m[2] != "k" && m[3] != "k" && m[4] != "k" && m[5] != "k" )
                        {
                            pdf.setEnabled(true);
                            pdf.setVisibility(View.VISIBLE);
                            getpd.setVisibility(View.VISIBLE);
                            tv1 = (TextView) findViewById(R.id.tv1);
                            tv2 = (TextView) findViewById(R.id.tv2);
                            tv3 = (TextView) findViewById(R.id.tv3);
                            tv4 = (TextView) findViewById(R.id.tv4);
                            tv5 = (TextView) findViewById(R.id.tv5);
                            tv6 = (TextView) findViewById(R.id.tv6);
                            tv7 = (TextView) findViewById(R.id.tv7);
                            tv1.setText("Id  " + m[0]);
                            tv2.setText("Name " + m[1]);
                          ids=db.getusercase(Integer.parseInt(m[0]));
                            ids.moveToFirst();
                            while(ids.isAfterLast()==false)
                            {
                                users+= ids.getString(ids.getColumnIndex("U_id"))+",";
                                ids.moveToNext();
                            }
                            ids.close();
                            tv3.setText("Investigators " +users);
                            tv4.setText("Type " + m[2]);
                            tv5.setText("Status " + m[3]);
                            tv7.setText("Des-: " + m[5]);
                            tv6.setText("Started On " + m[4]);
                            pass=m[1].toString().trim();
                            db.close();
                        }
                        else
                        {
                            pdf.setEnabled(false);
                            pdf.setVisibility(View.GONE);
                            getpd.setVisibility(View.GONE);
                             LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Record is Not Exist");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();
                            tv1 = (TextView) findViewById(R.id.tv1);
                            tv2 = (TextView) findViewById(R.id.tv2);
                            tv3 = (TextView) findViewById(R.id.tv3);
                            tv4 = (TextView) findViewById(R.id.tv4);
                            tv5 = (TextView) findViewById(R.id.tv5);
                            tv6 = (TextView) findViewById(R.id.tv6);
                            tv7 = (TextView) findViewById(R.id.tv7);
                            pass="";
                            tv1.setText("Id");
                            tv2.setText("Name");
                            tv3.setText("Investigators");
                            tv4.setText("Type");
                            tv5.setText("Status");
                            tv6.setText("Description");
                            pdf.setEnabled(false);
                            tv7.setText("StartedOn");
                        }
                        }
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //final TextView tv=(TextView)findViewById(R.id.tv1);
        getMenuInflater().inflate(R.menu.admin, menu);
        menu.add(0, Menu.FIRST,  Menu.NONE,"My Account").setIcon(R.drawable.user);
        SubMenu sub1=menu.addSubMenu(0,Menu.FIRST+1,Menu.NONE,"Change Password");
        SubMenu sub3=menu.addSubMenu(0,Menu.FIRST+2,Menu.NONE,"Logout");
        int positionOfMenuItem0 = 0; // or whatever...
        MenuItem item = menu.getItem(positionOfMenuItem0);
        SpannableString s = new SpannableString("My Account");
        s.setSpan(new ForegroundColorSpan(Color.argb(255, 67, 192, 251)), 0, s.length(), 0);
        item.setTitle(s);
        int positionOfMenuItem1 = 1; // or whatever...
        MenuItem item1 = menu.getItem(positionOfMenuItem1);
        SpannableString s1 = new SpannableString("Change Password");
        s1.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s1.length(), 0);
        item1.setTitle(s1);
        int positionOfMenuItem2 = 2; // or whatever...
        MenuItem item2 = menu.getItem(positionOfMenuItem2);
        SpannableString s2 = new SpannableString("LogOut");
        s2.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s2.length(), 0);
        item2.setTitle(s2);
        //tv.setText("Six Clicked");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==1)
        {
            Intent cases8 = new Intent(viewcase.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(viewcase.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
          //  finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(viewcase.this,change.class);
            cases8.putExtra("id",uid);
            startActivity(cases8);
            //finish();
        }
        return true;

    }
    @Override
    public void onBackPressed() {
        // finish() is called in super: we only override this method to be able to override the transition
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
