package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.provider.ContactsContract;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Anukool Srivastav on 26-09-2014.
 */
public class updateUser extends Activity {
    ArrayList<String> items=new ArrayList<String>();
    String m1,n;
    String[] m = new String[3];
    private  static final int PICK_CONTACT=1;
    EditText et3,et2,et4;
    AutoCompleteTextView actv;
    ArrayAdapter<String> dataAdapter,dataAdapter12;
    Button b,con;
    String a,uid,mail0,mbl;
    int c=0,d=0,e=0;
    String id1,naam,nmbr,mail,nm,mb,ml,number,m12;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    List<String> labels;
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.updateuser);
        final Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
        err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        mail0=getName.getString("mail");
        uid=getName.getString("id");
        mbl=getName.getString("mobile");
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        final SpannableString s2 = new SpannableString("Do You Want to Save It ??  ");
        s2.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s2.length(), 0);
        final SpannableString s3 = new SpannableString("Update Record ");
        s3.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s3.length(), 0);
        final SpannableString s4 = new SpannableString("Yes");
        s4.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s4.length(), 0);
        final SpannableString s5 = new SpannableString("No");
        s5.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s5.length(), 0);
        final SpannableString s6 = new SpannableString("Cancel");
        s6.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s6.length(), 0);
        b=(Button)findViewById(R.id.btn_modify);
        con=(Button)findViewById(R.id.con);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                //a1=3;
                startActivityForResult(intent,PICK_CONTACT );
            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                       et2.setError(null);
                et3.setError(null);
                et4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et2.setError(null);
                et3.setError(null);
                et4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                et2.setError(null);
                et3.setError(null);
                et4.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        db.open();
       labels = db.getTableValues12();
         dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner,labels);
        actv = (AutoCompleteTextView) findViewById(R.id.auto1);
        actv.setAdapter(dataAdapter);
        actv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                        et2.setText("");
                        et3.setText("");
                        et4.setText("");
                        m[0]="";
                        m[1]="";
                        m[2]="";
                        actv.setError(null);
                       // m[3]="";
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id)
            {
                String id0 = dataAdapter.getItem(pos);
                db.open();
                et2.setEnabled(true);
                et3.setEnabled(true);
                et4.setEnabled(true);
                m = db.getRecord123(id0);
                if(m[0] == "k" && m[1] == "k" && m[2] == "k" && m[3] == "k")
                {
                    //Toast.makeText(getApplicationContext(),"More then 1 account exist with Same Name ",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("More then 1 account exist with Same Name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    actv.setError("Try By Id",err1);
                }
                else   if (m[0] != "" && m[1] != "" && m[2] != "" && m[3] != ""  && m[0] != "k" && m[1] != "k" && m[2] != "k" && m[3] != "k")
                {
                    et2.setText("" + m[0]);
                    et3.setText("" + m[1]);
                    et4.setText("" + m[2]);
                    nm=et2.getText().toString().trim();
                    mb=et3.getText().toString().trim();
                    m12=et4.getText().toString().trim();
                    b.setEnabled(true);
                  //   db.close();
                    dataAdapter.getItem(pos);
                   // Toast.makeText(getApplicationContext(), " Selected" + dataAdapter.getItem(pos), Toast.LENGTH_LONG).show();
                }
                else
                {
                  //  Toast.makeText(getApplicationContext(),"No Record Found ",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("No Record Found");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
              //  db.close();
                dataAdapter.getItem(pos);
                     }

        });
        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String ddi=actv.getText().toString().trim();
                if(ddi!=null)
                {
                    int a=0;
                    naam = et2.getText().toString().trim();
                    nmbr = et3.getText().toString().trim();
                    mail = et4.getText().toString().trim();
                    db.open();
                    m1=db.getRecordEmailUP(mail,ddi);
                          if(m1!="")
                           {
                                  a=1;
                           }
                  n=db.getRecordMobileUP(nmbr,ddi);
                         if(n!="")
                            {
                             a+=1;
                                Toast.makeText(getApplication(),""+n,Toast.LENGTH_LONG).show();
                            }
                Log.d("addnew", "11");
                if(a==2)
                {
                    //Toast.makeText(getApplication(), "Mobile and Email both are Already Registered", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Mobile and Email both are Already Registered");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    Log.d("addnew","121");
                }
                else if (naam.equals("") && nmbr.equals("") && mail.equals(""))
                {
                   // Toast.makeText(getApplication(), "Please Provide Information", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Information");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    et2.setError("Please Provide Name", err1);
                    et3.setError("Please Provide MobileNo.", err1);
                    et4.setError("Please Provide Email", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }
                else if (naam.equals(""))
                {
                   // Toast.makeText(getApplication(), "Please Provide Name", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    et2.setError("Please Provide Name", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }
                else if ( nmbr.equals(""))
                {
                   // Toast.makeText(getApplication(), "Please Provide MobileNo", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide MobileNo");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    et3.setError("Please Provide MobileNo.", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }
                else if (  mail.equals(""))
                {
                    //Toast.makeText(getApplication(), "Please Provide MailId", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide MalId");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    et4.setError("Please Provide Email", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }
                    else if (nm.equals(naam) && mb.equals(nmbr) && m12.equals(mail))
                {
                   // Toast.makeText(getApplication(), "Sorry Same Information of this Account Already Exist", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Same Information of this Account Already Exist");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    et2.setError("Please Provide Name", err1);
                    et3.setError("Please Provide MobileNo.", err1);
                    et4.setError("Please Provide Email", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }
                       else  if (mail.equals(m1))
                {
                   // Toast.makeText(getApplication(), "Email Already Registered", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Email Already Registered");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                           et4.setError("Please Provide Different Email", err1);
                    Log.d("addnew","12");
                    //a=1;
                }
                else if (nmbr.length()<10)
                {
                    //Toast.makeText(getApplication(), "MobileNO Should be 10 Digits", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("MobileNO Should be 10 Digits");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                   // et2.setError("Please Provide Name", err1);
                    et3.setError("Please Provide 10 Digit MobileNo.", err1);
                   // et4.setError("Please Provide Email", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }
                else if (nmbr.equals(n))
                {
                    //Toast.makeText(getApplication(), "Mobile Already Registered", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Mobile Already Registered");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    et3.setError("Please Provide Different MobileNo.", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }

                else if (!mail.matches(emailPattern) || mail.length()==0)
                {
                   // Toast.makeText(getApplication(), "Invalid Email", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Email");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    et3.setError("Invalid EMail", err1);
                    Log.d("addnew","121");
                    //a+=1;
                }

                else
                {
                    if (naam.length() > 0 && nmbr.length() > 0 && mail.length() > 0   || !naam.equals(nm) || !nmbr.equals(mb) || !mail.equals(ml)) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(updateUser.this);
                        alertDialog.setTitle(s3);
                        alertDialog.setMessage(s2);
                        alertDialog.setIcon(R.drawable.alert);
                        alertDialog.setPositiveButton(s4, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                        db.open();
                                String idd = actv.getText().toString().trim();
                                if (idd.length() > 0) {

                                    id1 = idd;
                                    int b = db.updateRecord(id1, naam, nmbr, mail);
                                    if (b == 1) {
                                        //Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_LONG).show();
                                        LayoutInflater inflater = getLayoutInflater();
                                        View layout = inflater.inflate(R.layout.toast_xml,
                                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Updated");
                                        Toast toast = new Toast(getApplicationContext());
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);
                                        toast.setGravity(Gravity.CENTER,0,80);
                                        toast.show();
                                        et2.setText("Name");
                                        et3.setText("MobileNo");
                                        et4.setText("Email");
                                        // db.open();
                                        // actv.setText("");
                                        //  actv.clearListSelection();
                                        actv.setText("");
                                        List<String> labels12 = db.getTableValues12();
                                        dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, labels12);
                                         actv.setAdapter(dataAdapter12);
                                        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                                                    long id) {
                                                                    String id0 = dataAdapter12.getItem(pos);
                                                  db.open();
                                                et2 = (EditText) findViewById(R.id.et2);
                                                et3 = (EditText) findViewById(R.id.et3);
                                                et4 = (EditText) findViewById(R.id.et4);
                                                String[] m = new String[4];
                                                m = db.getRecord123(id0);
                                                if(m[0] == "k" && m[1] == "k" && m[2] == "k" && m[3] == "k")
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
                                                else   if (m[0] != "" && m[1] != "" && m[2] != "" && m[3] != ""  && m[0] != "k" && m[1] != "k" && m[2] != "k" && m[3] != "k")
                                                {
                                                    et2.setText("" + m[0]);
                                                    et3.setText("" + m[1]);
                                                    et4.setText("" + m[2]);
                                                    nm=et2.getText().toString().trim();
                                                    mb=et3.getText().toString().trim();
                                                    m12=et4.getText().toString().trim();
                                                 dataAdapter.getItem(pos);
                                                                            }
                                                else
                                                {
                                                    LayoutInflater inflater = getLayoutInflater();
                                                    View layout = inflater.inflate(R.layout.toast_xml,
                                                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("No Record Found ");
                                                    Toast toast = new Toast(getApplicationContext());
                                                    toast.setDuration(Toast.LENGTH_LONG);
                                                    toast.setView(layout);
                                                    toast.setGravity(Gravity.CENTER,0,80);
                                                    toast.show();
                                                    //Toast.makeText(getApplicationContext(),"No Record Found ",Toast.LENGTH_LONG).show();
                                                }
                                                                                           db.close();
                                                dataAdapter.getItem(pos);
                                               // Toast.makeText(getApplicationContext(), " selected" + dataAdapter.getItem(pos), Toast.LENGTH_LONG).show();
                                            }

                                        });

                                    }
                                    else if(b==2)
                                    {
                                       // Toast.makeText(getApplicationContext(), " More then 1 Account with Same Name Exist Try to Update with Id", Toast.LENGTH_LONG).show();
                                        LayoutInflater inflater = getLayoutInflater();
                                        View layout = inflater.inflate(R.layout.toast_xml,
                                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("More then 1 Account with Same Name Exist Try to Update with Id");
                                        Toast toast = new Toast(getApplicationContext());
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);
                                        toast.setGravity(Gravity.CENTER,0,80);
                                        toast.show();
                                    }
                                    else
                                    {
                                        //Toast.makeText(getApplicationContext(), "Invalid Attempt Record Not Found", Toast.LENGTH_LONG).show();
                                        LayoutInflater inflater = getLayoutInflater();
                                        View layout = inflater.inflate(R.layout.toast_xml,
                                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt Record Not Found");
                                        Toast toast = new Toast(getApplicationContext());
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);
                                        toast.setGravity(Gravity.CENTER,0,80);
                                        toast.show();
                                    }
                                } else
                                {
                                    //Toast.makeText(getApplicationContext(), "Invalid Attempt Record Not Found", Toast.LENGTH_LONG).show();
                                    LayoutInflater inflater = getLayoutInflater();
                                    View layout = inflater.inflate(R.layout.toast_xml,
                                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt Record Not Found");
                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setDuration(Toast.LENGTH_LONG);
                                    toast.setView(layout);
                                    toast.setGravity(Gravity.CENTER,0,80);
                                    toast.show();

                                }
                                labels = db.getTableValues12();
                                dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, labels);
                            }

                        });

                        // Setting Negative "NO" Button
                        alertDialog.setNegativeButton(s5, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // User pressed No button. Write Logic Here
                               // Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                LayoutInflater inflater = getLayoutInflater();
                                View layout = inflater.inflate(R.layout.toast_xml,
                                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Confirm");
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
                              //  Toast.makeText(getApplicationContext(), "You clicked on Cancel",
                                //        Toast.LENGTH_SHORT).show();
                                LayoutInflater inflater = getLayoutInflater();
                                View layout = inflater.inflate(R.layout.toast_xml,
                                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                ((TextView) layout.findViewById(R.id.toast_text_1)).setText("cancelled");
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(layout);
                                toast.setGravity(Gravity.CENTER,0,80);
                                toast.show();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                    } else {
                       // Toast.makeText(getApplicationContext(), "Sorry Vacant Field EXIST", Toast.LENGTH_LONG).show();
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_xml,
                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Vacant Field EXIST");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER,0,80);
                        toast.show();
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
            Intent cases8 = new Intent(updateUser.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(updateUser.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            //finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(updateUser.this,change.class);
            cases8.putExtra("id",uid);
            startActivity(cases8);
           // finish();
        }
        return true;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
     et3=(EditText)findViewById(R.id.et3);
        if (requestCode==PICK_CONTACT) {

                    if (resultCode == RESULT_OK) {
                        // Get the URI that points to the selected contact
                        Uri contactUri = data.getData();
                        // We only need the NUMBER column, because there will be only one row in the result
                        String[] projection = {
                           ContactsContract.CommonDataKinds.Phone.NUMBER};
                       // Perform the query on the contact to get the NUMBER column
                        // We don't need a selection or sort order (there's only one result for the given URI)
                        // CAUTION: The query() method should be called from a separate thread to avoid blocking
                        // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                        // Consider using CursorLoader to perform the query.
                        Cursor cursor = getContentResolver()
                                .query(contactUri, projection, null, null, null);
                        cursor.moveToFirst();
                        // Retrieve the phone number from the NUMBER column
                        int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        number = cursor.getString(column);
                        Toast.makeText(this, "contact info : " + number, Toast.LENGTH_LONG).show();
                        //String[] ary = number.toString().split("");
                        //Toast.makeText(this, "contact info : " + ary[0], Toast.LENGTH_LONG).show();
                        et3.setText("");
                        char[] stringArray;
                        //convert string into array using toCharArray() method of string class
                        stringArray = number.toCharArray();
                        String as="";
                        if(stringArray[0]=='0')
                        {
                            for(int i=1;i<stringArray.length;i++)
                            {
                                // tv3.append(stringArray[i]);
                                as+=stringArray[i];
                            }
                            //  }
                            Toast.makeText(this, "contact info : " + as, Toast.LENGTH_LONG).show();
                            et3.setText(as);
                            // Do something with the phone number...
                        }
                        else if(stringArray[0]=='9' && stringArray[1]=='1')
                        {
                            for(int i=2;i<stringArray.length;i++)
                            {
                                // tv3.append(stringArray[i]);
                                as+=stringArray[i];
                            }
                            //  }
                            Toast.makeText(this, "contact info : " + as, Toast.LENGTH_LONG).show();
                            et3.setText(as);
                        }
                        else if(stringArray[0]=='+' && stringArray[1]=='9' &&  stringArray[2]=='1')
                        {
                            for(int i=3;i<stringArray.length;i++)
                            {
                                // tv3.append(stringArray[i]);
                                as+=stringArray[i];
                            }
                            //  }
                            Toast.makeText(this, "contact info : " + as, Toast.LENGTH_LONG).show();
                            et3.setText(as);
                        }
                        else
                        {

                            Toast.makeText(this, "contact info : " + number, Toast.LENGTH_LONG).show();
                            et3.setText(number);
                        }

                    }
            }


        }
    @Override
    public void onBackPressed() {
        // finish() is called in super: we only override this method to be able to override the transition
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
