package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anukool Srivastav on 28-09-2014.
 */
public class searchevdnc extends Activity {
   // ArrayList<String> items=new ArrayList<String>();
    // Spinner id,id2;
    List<String> labels,evdns;
    AutoCompleteTextView actv,actv1;
    String a,email,nmbr,uid,evdnc;
    Button search;
    ArrayAdapter dataAdapter,adapter;
    TextView ed,nam,descc;
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_evidence);
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        email=getName.getString("mail");
        uid=getName.getString("id");
        nmbr=getName.getString("mobile");
        actv=(AutoCompleteTextView)findViewById(R.id.auto2);
        actv1=(AutoCompleteTextView)findViewById(R.id.auto1);
        final Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
        err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
        search=(Button)findViewById(R.id.srch);
        ed=(TextView)findViewById(R.id.id);
        nam=(TextView)findViewById(R.id.name);
        descc=(TextView)findViewById(R.id.desc);
        db.open();
        labels = db.getcasenames(uid);
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner, labels);
      //  adapter = new ArrayAdapter<String>(this,R.layout.spinner,evdns);
       // actv1.setAdapter(adapter);
        actv.setAdapter(dataAdapter);
        //particular evdns
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id)
            {
             String cs=dataAdapter.getItem(pos).toString().trim();
                if (Collections.frequency(labels,cs)>1)
                {
                   // Toast.makeText(getApplication(),"Sorry More then 1 Case exist with same Name,Please Try With Case Id",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 Case exist with same Name,Please Try With Case Id");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    search.setEnabled(false);
                    actv.setError("Sorry More then 1 Case exist with same Name",err1);
                }
                else if(Collections.frequency(labels,cs)==1 )
                {
                    search.setEnabled(false);
                    evdns=db.getevdnsbyuser(dataAdapter.getItem(pos).toString(),Integer.parseInt(uid));
                    adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner,evdns);
                    actv1.setAdapter(adapter);
                }
                else
                {
                   // Toast.makeText(getApplicationContext(),"Sorry Record is Not Exist ",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Record is Not Exist");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    search.setEnabled(false);
                    actv.setError("Sorry Record is Not Exist",err1);
                }

            }
        });
        actv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id)
            {
                //   Toast.makeText(this,"Total evdns"+evdns.get
                      evdnc=adapter.getItem(pos).toString().trim();

                if (Collections.frequency(evdns,evdnc)>1)
                {
                   // Toast.makeText(getApplication(),"Sorry More then 1 Evidence exist with same Name,Please Try With Evidence Id",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 Evidence exist with same Name,Please Try With Evidence Id");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    search.setEnabled(false);
                    actv1.setError("Please Try With Evidence Id",err1);
                }
                else if(Collections.frequency(evdns,evdnc)==1 )
                {
                    search.setEnabled(true);
                }
                else
                {
                  //  Toast.makeText(getApplicationContext(),"Sorry Evidence is Not Exist ",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Evidence is Not Exist");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    search.setEnabled(false);
                    actv1.setError("Evidence Not Exist",err1);
                }
            }
        });

        actv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        evdnc="";
                        ed.setText("Id");
                        nam.setText("Name");
                        descc.setText("Description");
                        actv1.setError(null);
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        });



        actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                      // evdns.clear();
                        ed.setText("Id");
                        nam.setText("Name");
                        descc.setText("Description");
                        evdnc="";
                        actv1.setAdapter(null);
                        actv1.setText("");
                        actv.setError(null);
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        });


     //   id =(Spinner)findViewById(R.id.spinner);
     //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner, items);
      //  id.setAdapter(adapter);
      //  items1.add("1");
     //   items1.add("2");
      //  id2 =(Spinner)findViewById(R.id.spinner2);
      //  ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.spinner, items1);
       //id2.setAdapter(adapter1);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bc = db.getcase(actv.getText().toString().trim());
                evdnc=actv1.getText().toString().trim();
                int a=0;
                for(int i=0;i<labels.size();i++)
                {
                    //Toast.makeText(getApplicationContext(),labels.get(i),Toast.LENGTH_LONG).show();
                    if(bc.equals(labels.get(i)))
                    {
                        //  bc=labels.get(i);
                        a=1;
                        break;
                    }
                    else
                    {
                        a=0;
                        // bc="";
                    }
                }

                if(actv.getText().toString().trim().equals(""))
                {
                    //Toast.makeText(getApplicationContext(),"Please Select Valid Case Name",Toast.LENGTH_LONG).show();

                    actv.setError("Please Provide Case Name",err1);
                }
                        else if(bc.length()==0 || a==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Select Valid Case Name",Toast.LENGTH_LONG).show();
                }
                else if(actv1.getText().toString().trim().equals(""))
                {
                    //Toast.makeText(getApplicationContext(),"Please Select Valid Evidence",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Evidence");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    actv1.setError("Please Provide Evidence",err1);
                }
                else if(evdnc.equals(""))
                {
                   // Toast.makeText(getApplicationContext(),"Please Select Valid Evidence",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Select Valid Evidence");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
                else
                {
                    Cursor m=db.getevidencedetail(evdnc,Integer.parseInt(uid));
                    if(m!=null)
                    {
                        m.moveToFirst();
                        if(m.getCount()==0)
                        {
                            //
                        }
                        else
                        {
                             ed.setText("Id "+m.getString(0));
                             nam.setText("Name "+m.getString(1));
                             descc.setText("Desc "+m.getString(2));
                        }
                    }
                    else
                    {
                       // Toast.makeText(getApplication(),"Sorry No Record Found ",Toast.LENGTH_LONG).show();
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_xml,
                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry No Record Found ");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER,0,80);
                        toast.show();
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
            Intent cases8 = new Intent(searchevdnc.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",email);
            cases8.putExtra("nmbr",nmbr);
            startActivity(cases8);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(searchevdnc.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
          //  finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(searchevdnc.this,change.class);
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
