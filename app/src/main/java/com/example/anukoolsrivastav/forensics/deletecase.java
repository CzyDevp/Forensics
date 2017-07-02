package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView;
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
 * Created by Anukool Srivastav on 25-09-2014.
 */
public class deletecase extends Activity
{
   // ArrayList<String> items=new ArrayList<String>();
    ArrayList<String> items=new ArrayList<String>();
    ArrayList<String> items1=new ArrayList<String>();
    //Spinner delete;
    Button del;
    TextView tv1,tv2,tv3,tv4;
    String id1,a,mail0,uid,mbl;;
    int pos1;
    AutoCompleteTextView actv;
    List<String> labels;
    ArrayAdapter<String> dataAdapter;
    ImageView uimg;
    ArrayAdapter<String>    dataAdapter12;
    DBAdapter db=new DBAdapter(this);
    Spinner id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delete_case);
        final SpannableString s2 = new SpannableString("Do You Want to Delete It ??  ");
        s2.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s2.length(), 0);
        final SpannableString s3 = new SpannableString("Delete Record ");
        s3.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s3.length(), 0);
        final SpannableString s4 = new SpannableString("Yes");
        s4.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s4.length(), 0);
        final SpannableString s5 = new SpannableString("No");
        s5.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s5.length(), 0);
        final SpannableString s6 = new SpannableString("Cancel");
        s6.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s6.length(), 0);
        items1.add("1");
        items1.add("2");
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        mail0=getName.getString("mail");
        uid=getName.getString("id");
        mbl=getName.getString("mobile");
        actv = (AutoCompleteTextView) findViewById(R.id.auto1);
        uimg=(ImageView)findViewById(R.id.imageView);
        //   delete =(Spinner)findViewById(R.id.spinner);
        del=(Button)findViewById(R.id.del);
        db.open();
        labels = db.getTableValuescase();
        dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner,labels);
         actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        tv1 = (TextView) findViewById(R.id.tv01);
                        tv2 = (TextView) findViewById(R.id.tv1);
                        tv3 = (TextView) findViewById(R.id.tv2);
                        tv4 = (TextView) findViewById(R.id.tv3);
                        tv1.setText("Status");
                        tv2.setText("Name");
                        tv3.setText("Case Id");
                        tv4.setText("Type");
                          }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
        actv.setAdapter(dataAdapter);
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                // pos1=pos;
                //String[] ds=dataAdapter[pos];
                String id0 = dataAdapter.getItem(pos);
                db.open();
                tv1 = (TextView) findViewById(R.id.tv01);
                tv2 = (TextView) findViewById(R.id.tv1);
                tv3 = (TextView) findViewById(R.id.tv2);
                tv4 = (TextView) findViewById(R.id.tv3);
                String[] m = new String[4];
                m = db.getRecordsrchcase1(id0);
                if(m[0] == "k" && m[1] == "k" &&  m[2] == "k" && m[3] == "k")
                {
                   // Toast.makeText(getApplicationContext(),"More then 1 account exist with Same Name ",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("More then 1 account exist with Same Name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    tv1.setText("Status");
                    tv2.setText("Name");
                    tv3.setText("Case Id");
                    tv4.setText("Type");
                }
              else  if(m[0]!="" && m[0]!="k"  && m[1]!="" && m[1]!="k"  && m[2]!="" && m[2]!="k" && m[3]!="" && m[3]!="k")
                {
                tv1.setText("Status-: " + m[0]);
                tv2.setText("Name-: " + m[1]);
                tv3.setText("Case Id-: " + m[2]);
                tv4.setText("Type-: " + m[3]);
                db.close();
                dataAdapter.getItem(pos);
               // Toast.makeText(getApplicationContext(), " Selected" + dataAdapter.getItem(pos), Toast.LENGTH_LONG).show();
                 }
                else
                {
                   // Toast.makeText(getApplicationContext(),"No Record Found ",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("No Record Found");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    tv1.setText("Status");
                    tv2.setText("Name");
                    tv3.setText("Case Id");
                    tv4.setText("Type");
                }
            }

        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.open();
                String actv1=actv.getText().toString().trim();
                String bc = db.getcase(actv1);
                if (bc.length()==0 || actv.getText().length() == 0 || actv.getText().toString() == "" || actv.getText().toString() == null || actv1 == "" || bc.equals(null))

                {
                   // Toast.makeText(getApplicationContext(), "Sorry Case Not Found", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Case Not Found");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    tv1.setText("Status");
                    tv2.setText("Name");
                    tv3.setText("Case Id");
                    tv4.setText("Type");
                }
                else
                {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(deletecase.this);
                    alertDialog.setTitle(s3);
                    alertDialog.setMessage(s2);
                    alertDialog.setIcon(R.drawable.alert);
                    alertDialog.setPositiveButton(s4, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                                                       db.open();
                            String idd = actv.getText().toString().trim();
                            if (idd.length() > 0)
                            {
                                id1 = idd;
                                int b = db.deleteRecordcase(id1);
                             //   boolean c=db.deletecaseusers(id1);
                                   if (b==1) {
                                    //Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show();
                                       LayoutInflater inflater = getLayoutInflater();
                                       View layout = inflater.inflate(R.layout.toast_xml,
                                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Deleted");
                                       Toast toast = new Toast(getApplicationContext());
                                       toast.setDuration(Toast.LENGTH_LONG);
                                       toast.setView(layout);
                                       toast.setGravity(Gravity.CENTER,0,80);
                                       toast.show();
                                     tv1.setText("Status");
                                    actv.setText("");
                                    List<String>      labels12 = db.getTableValuescase();
                                    dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner,labels12);
                                    actv.setAdapter(dataAdapter12);
                                    actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                                                long id) {
                                            String id0=dataAdapter12.getItem(pos);
                                            db.open();
                                            tv1 = (TextView) findViewById(R.id.tv01);
                                            tv2 = (TextView) findViewById(R.id.tv1);
                                            tv3 = (TextView) findViewById(R.id.tv2);
                                            tv4 = (TextView) findViewById(R.id.tv3);
                                            String[] m = new String[4];
                                            m = db.getRecordsrchcase1(id0);
                                            if(m[0] == "k" && m[1] == "k" &&  m[2] == "k" && m[3] == "k")
                                            {
                                               // Toast.makeText(getApplicationContext(),"More then 1 account exist with Same Name ",Toast.LENGTH_LONG).show();
                                                LayoutInflater inflater = getLayoutInflater();
                                                View layout = inflater.inflate(R.layout.toast_xml,
                                                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                                ((TextView) layout.findViewById(R.id.toast_text_1)).setText("More then 1 account exist with Same Name");
                                                Toast toast = new Toast(getApplicationContext());
                                                toast.setDuration(Toast.LENGTH_LONG);
                                                toast.setView(layout);
                                                toast.setGravity(Gravity.CENTER,0,80);
                                                toast.show();
                                            }
                                            else  if(m[0]!="" && m[0]!="k"  && m[1]!="" && m[1]!="k"  && m[2]!="" && m[2]!="k" && m[3]!="" && m[3]!="k")
                                            {
                                                tv1.setText("Status-: " + m[0]);
                                                tv2.setText("Name-: " + m[1]);
                                                tv3.setText("Case Id-: " + m[2]);
                                                tv4.setText("Type-: " + m[3]);
                                                db.close();
                                                dataAdapter.getItem(pos);
                                                //Toast.makeText(getApplicationContext(), " Selected" + dataAdapter.getItem(pos), Toast.LENGTH_LONG).show();
                                            }
                                            else
                                            {
                                               // Toast.makeText(getApplicationContext(),"No Record Found ",Toast.LENGTH_LONG).show();
                                                LayoutInflater inflater = getLayoutInflater();
                                                View layout = inflater.inflate(R.layout.toast_xml,
                                                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                                ((TextView) layout.findViewById(R.id.toast_text_1)).setText("No Record Found");
                                                Toast toast = new Toast(getApplicationContext());
                                                toast.setDuration(Toast.LENGTH_LONG);
                                                toast.setView(layout);
                                                toast.setGravity(Gravity.CENTER,0,80);
                                                toast.show();
                                                tv1.setText("Status");
                                                tv2.setText("Name");
                                                tv3.setText("Case Id");
                                                tv4.setText("Type");
                                            }
                                           // tv1.append(" "+m[0]);
                                          //  db.close();
                                            dataAdapter12.getItem(pos);
                                           // Toast.makeText(getApplicationContext()," selected"+   dataAdapter.getItem(pos), Toast.LENGTH_LONG).show();
                                        }

                                    });

                                }
                                else if(b==2)
                                   {
                                  //  Toast.makeText(getApplicationContext(), "More then 1 account exist with Same Name ", Toast.LENGTH_LONG).show();
                                       LayoutInflater inflater = getLayoutInflater();
                                       View layout = inflater.inflate(R.layout.toast_xml,
                                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("More then 1 account exist with Same Name");
                                       Toast toast = new Toast(getApplicationContext());
                                       toast.setDuration(Toast.LENGTH_LONG);
                                       toast.setView(layout);
                                       toast.setGravity(Gravity.CENTER,0,80);
                                       toast.show();
                                       tv1.setText("Status");
                                       tv2.setText("Name");
                                       tv3.setText("Case Id");
                                       tv4.setText("Type");
                                }
                                else
                                {
                                  //  Toast.makeText(getApplicationContext(), "Invalid Attempt Record Not Found. ", Toast.LENGTH_LONG).show();
                                    LayoutInflater inflater = getLayoutInflater();
                                    View layout = inflater.inflate(R.layout.toast_xml,
                                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt Record Not Found.");
                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setDuration(Toast.LENGTH_LONG);
                                    toast.setView(layout);
                                    toast.setGravity(Gravity.CENTER,0,80);
                                    toast.show();
                                    tv1.setText("Status");
                                    tv2.setText("Name");
                                    tv3.setText("Case Id");
                                    tv4.setText("Type");
                                }
                            }
                            else
                            {
                               /* Toast.makeText(getApplicationContext(), "Invalid Attempt Record Not Found", Toast.LENGTH_LONG).show();*/
                                LayoutInflater inflater = getLayoutInflater();
                                View layout = inflater.inflate(R.layout.toast_xml,
                                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt Record Not Found.");
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(layout);
                                toast.setGravity(Gravity.CENTER,0,80);
                                toast.show();
                                tv1.setText("Status");
                                tv2.setText("Name");
                                tv3.setText("Case Id");
                                tv4.setText("Type");
                            }
                            labels = db.getTableValuescase();
                            dataAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner,labels);
                        }

                    });

                    // Setting Negative "NO" Button
                    alertDialog.setNegativeButton(s5, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // User pressed No button. Write Logic Here
                           //Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Confirm Again");
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
                           // Toast.makeText(getApplicationContext(), "You clicked on Cancel",
                                  //  Toast.LENGTH_SHORT).show();
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
            }
        });
       // items.add("1");
       // items.add("2");
       // id =(Spinner)findViewById(R.id.spinner);
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner, items);
       // id.setAdapter(adapter);
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
            Intent cases8 = new Intent(deletecase.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(deletecase.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            //finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(deletecase.this,change.class);
            cases8.putExtra("id",uid);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
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
