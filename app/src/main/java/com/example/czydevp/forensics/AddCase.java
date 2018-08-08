package com.example.czydevp.forensics;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddCase extends Activity implements OnClickListener {
     ArrayList<String> items=new ArrayList<String>();
     Spinner id;
     private ImageButton ib;
     private Calendar cal;
     private int day;
     private int month;
     private int year;
     private TextView tv1;
    String[] toArr,toArr1,uniques;
    String a="",b="",a12,mail0,uid,mbl;
    EditText status,loc,dec,name;
    ArrayAdapter<String> dataAdapter;
    List<String> labels;
    AutoCompleteTextView actv;
    MultiAutoCompleteTextView actv1;
    Button btn;
   EditText ctp;
    String ctype="";
  DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_case);
        ib = (ImageButton) findViewById(R.id.imgbtn);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        ctp = (EditText) findViewById(R.id.ctp);
        year = cal.get(Calendar.YEAR);
        tv1 = (TextView) findViewById(R.id.textView);
        status=(EditText)findViewById(R.id.editstatus);
        loc=(EditText)findViewById(R.id.editText6);
        dec=(EditText)findViewById(R.id.editText);
        status.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                status.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loc.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        dec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dec.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tv1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                 tv1.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn = (Button) findViewById(R.id.button);
        ib.setOnClickListener(this);
        final Drawable err1 = getResources().getDrawable(R.drawable.err);
        err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
        items.add("Actual / grievous bodily harm, wounding & other assaults");
        items.add("Armed robbery");
        items.add("Arson");
        items.add("Burglary, robbery & theft");
        items.add("Child abuse / protection");
        items.add("Criminal damage");
        items.add("Damage to property");
        items.add("Document fraud");
        items.add("Drink / drug driving");
        items.add("Endangering life");
        items.add(" Firearms offences");
        items.add(" Fire insurance");
        items.add(" Fraud & forgeries");
        items.add("Illicit drugs production / cultivation /supply / importation");
        items.add(" Kidnap");
        items.add("Money laundering");
        items.add(" Motor vehicle crime");
        items.add(" Murder / homicide & manslaughter");
        items.add(" Poisoning");
        items.add("Proceeds Of Crime (POCA)");
        items.add(" Product contamination");
        items.add("Rape & other sexual assaults(including drug facilitated sexual assault – “date rape”)");
        items.add("Road traffic accidents");
        items.add(" Sexual abuse");
        items.add("Street drugs & drugs cultivation");
        items.add("Suspicious deaths");
        items.add(" Terrorism");
        items.add(" Torture");
        items.add("Others");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner, items);
        actv = (AutoCompleteTextView) findViewById(R.id.auto2);
        actv.setAdapter(adapter);
        Bundle getName=getIntent().getExtras();
        a12=getName.getString("user");
        mail0=getName.getString("mail");
        uid=getName.getString("id");
        mbl=getName.getString("mobile");
        db.open();
        labels = db.getTableValues12();
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner, labels);
        actv1 = (MultiAutoCompleteTextView) findViewById(R.id.auto1);
        actv1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        actv1.setAdapter(dataAdapter);
        name=(EditText) findViewById(R.id.name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            name.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        actv1.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
               // actv1.setError(null);
                actv1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                         a="";
                         b="";
                      actv1.setError(null);
                    }
                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
   actv1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
       {

       }

       @Override
       public void onNothingSelected(AdapterView<?> parent)
       {
            // actv1.setText("");
       }
   });
        actv1.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id)
            {

                toArr = actv1.getText().toString().split(", ");
            }

        });


        //Case Type
         actv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                         //  actv.setText("");
                        ctype="";
                        ctp.setText("");
                        ctp.setVisibility(View.GONE);
                        actv.setError(null);
                        //ctp.setEnabled(false);
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
               ctype =adapter.getItem(pos);
            //  Toast.makeText(getApplicationContext(), " Selected " + ctype, Toast.LENGTH_LONG).show();
                if(ctype.equals("Others"))
                {
                     ctp.setVisibility(View.VISIBLE);
                     ctp.setEnabled(true);
                }
                else
                {
                    ctp.setVisibility(View.GONE);
                    ctp.setText("");
                }
                        }
        });
      //Add Case Into Database
                 btn.setOnClickListener(new OnClickListener()
                 {
            @Override
            public void onClick(View v)

            {
               // String[] uniques;
                 //Storing Time Hodden from user
                 db.open();
                 Calendar c1 = Calendar.getInstance();
                 SimpleDateFormat sdf1 = new SimpleDateFormat("h:m:s a");
                 String strdate1 = sdf1.format(c1.getTime());
                 TextView txtdate1 = (TextView) findViewById(R.id.textView1);
                 txtdate1.setText(strdate1);
                String invsts=actv1.getText().toString().trim();
                toArr=invsts.split(", ");
                //toArr=actv1.getText().toString().split(", ");
                 if(toArr!=null) {
                    for (int i = 0; i < toArr.length; i++) {
                        a += db.getRecordcase(toArr[i]) + ",";
                    }
                    //Toast.makeText(getApplicationContext(), " " + a, Toast.LENGTH_LONG).show();
                    toArr1 = a.split(",");
                    // String b="";
                    Set<String> set = new HashSet<String>();
                    Collections.addAll(set, toArr1);
                     uniques = set.toArray(new String[0]);
                    //println(uniques);
                    for (int p1 = 0; p1 < uniques.length; p1++) {
                        b += uniques[p1] + ", ";
                       // Toast.makeText(getApplicationContext(), "univalues" + uniques[p1], Toast.LENGTH_LONG).show();
                    }
                  //  Toast.makeText(getApplicationContext(), "repet tu baad " + b, Toast.LENGTH_LONG).show();
                }
                else
                {
                    b="";
                  //  Toast.makeText(getApplicationContext(), "" + b, Toast.LENGTH_LONG).show();
                }
               status=(EditText)findViewById(R.id.editstatus);
               loc=(EditText)findViewById(R.id.editText6);
               dec=(EditText)findViewById(R.id.editText);
                if(tv1.getText().toString().equals("")  && status.getText().toString().equals("")&& loc.getText().toString().equals("")&& dec.getText().toString().trim().equals("")&& ctype.length()==0
                        || ctype.equals("")&&name.getText().toString().trim().equals("")&& b.equals("") )
                {
                   // Toast.makeText(getApplication(),"No Information Provided",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("No Information Provided");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();

                    a="";
                    b="";
                }
             else  if(tv1.getText().toString().trim().equals(""))
               {
                   //Toast.makeText(getApplication(),"Please Select Date",Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Select Date");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   tv1.setError("Please Provide Date", err1);
                   a="";
                   b="";
               }
               else if(status.getText().toString().trim().equals(""))
               {
                  // Toast.makeText(getApplication(),"Please Provide Current Status",Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Current Status");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   status.setError("Please Provide Status", err1);
                   a="";
                   b="";
               }
                else if(loc.getText().toString().trim().equals(""))
               {
                   //Toast.makeText(getApplication(),"Please Provide Location",Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Location");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   loc.setError("Please Provide Location", err1);
                   a="";
                   b="";
               }
               else if(dec.getText().toString().trim().equals(""))
               {
                  // Toast.makeText(getApplication(),"Please Provide Description",Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Description");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   dec.setError("Please Provide Description", err1);
                   a="";
                   b="";
               }

                else if(ctype.length()==0  || ctype.equals(""))
               {
                  Toast.makeText(getApplication(),"Please Provide CaseType",Toast.LENGTH_LONG).show();
                   AutoCompleteTextView at=(AutoCompleteTextView)findViewById(R.id.auto2);
                   at.setError("Please Provide CaseType", err1);
                   a="";
                   b="";
               }
               else if(name.getText().toString().trim().equals(""))
               {
                 //  Toast.makeText(getApplication(),"Please Provide Name",Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Name");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   name.setError("Please Provide Name", err1);
                   a="";
                   b="";
               }
                else if(actv1.getText().toString().trim().equals(""))
                {
                    // cstype=ctp.getText().toString();

                  //  Toast.makeText(getApplication(),"Please Provide InvetiGators for this Case",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide InvetiGators for this Case");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    actv1.setError("Please Provide InvetiGators",err1);
                }
               else if(b.equals(""))
               {
                  // cstype=ctp.getText().toString();
                   //Toast.makeText(getApplication(),"Please Provide InvetiGators for this Case",Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide InvetiGators for this Case");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();

               }
             //  else if(ctype.equals("Others"))
               //{
               //    ctype=ctp.getText().toString();
                   //Toast.makeText(get)
              // }

                else
               {
                    if(ctype.equals("Others"))
               {
                   ctype=ctp.getText().toString();
                   //Toast.makeText(get)
               }
                   Long cui;
                   Long id = db.insertcase(name.getText().toString().trim(), ctype, loc.getText().toString().trim(), status.getText().toString().trim(), tv1.getText().toString().trim(),
                           txtdate1.getText().toString().trim(), dec.getText().toString().trim());
                  // Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Added");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   String id1 = db.getcaseid(name.getText().toString());
                   int  cid=  Integer.parseInt(id1);
                  // Toast.makeText(getApplicationContext(), "Case Id is " + id1, Toast.LENGTH_LONG).show();
                   LayoutInflater inflater2 = getLayoutInflater();
                   View layout2 = inflater2.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout2.findViewById(R.id.toast_text_1)).setText("Case Id is " + id1);
                   Toast toast2 = new Toast(getApplicationContext());
                   toast2.setDuration(Toast.LENGTH_LONG);
                   toast2.setView(layout2);
                   toast2.setGravity(Gravity.CENTER,0,80);
                   toast2.show();
                   for (int p11 = 0; p11 < uniques.length; p11++)
                   {
                      // b += uniques[p1] + ",";
                       cui=db.insertcaseusers(cid,Integer.parseInt(uniques[p11]));
                       //Toast.makeText(getApplication(),"Users  "+uniques[p11].toString(),Toast.LENGTH_LONG).show();

                   }
                   b="";
                   a="";
                   ctype="";
                   actv.setText("");
                   actv1.setText("");
                   name.setText("");
                   dec.setText("");
                   loc.setText("");
                   status.setText("");
                   tv1.setText("");
               }
                 db.close();
            }
        });
    }
    @Override
    public void onClick(View v) {
        showDialog(0);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        DatePickerDialog dialog = new DatePickerDialog(this, datePickerListener,year,month,day);
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        return dialog;
       // return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener()
    {

        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            tv1.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };
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
            Intent cases8 = new Intent(AddCase.this,MyAccount.class);
            cases8.putExtra("user",a12);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(AddCase.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            //startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
           // finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(AddCase.this,Change.class);
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
