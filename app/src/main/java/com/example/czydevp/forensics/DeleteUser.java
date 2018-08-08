package com.example.czydevp.forensics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anukool Srivastav on 26-09-2014.
 */
public class DeleteUser extends Activity {
    ArrayList<String> items=new ArrayList<String>();
    ArrayList<String> items1=new ArrayList<String>();
    //Spinner delete;
    Button del;
    TextView tv01,tv1,tv3,tv4,tv5;
    String id1,a,mail0,uid,mbl;;
    int pos1;
    String[] m = new String[3];
    AutoCompleteTextView actv;
    List<String> labels;
    ArrayAdapter<String> dataAdapter;
    ImageView uimg;
    ArrayAdapter<String>    dataAdapter12;
    DBAdapter db=new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.deluser);
        final Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
        err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
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
        actv = (AutoCompleteTextView) findViewById(R.id.auto1);
        uimg=(ImageView)findViewById(R.id.imageView);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
        uimg.setImageBitmap(largeIcon);
        del=(Button)findViewById(R.id.del);
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        mail0=getName.getString("mail");
        uid=getName.getString("id");
        mbl=getName.getString("mobile");
        db.open();
        labels = db.getTableValues12();
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
                        tv1 = (TextView) findViewById(R.id.tv2);
                        tv1.setText("Name-:");
                        tv01 = (TextView) findViewById(R.id.tv1);
                        tv01.setText("Id-:");
                        tv3 = (TextView) findViewById(R.id.tv3);
                        tv4 = (TextView) findViewById(R.id.tv4);
                        tv5 = (TextView) findViewById(R.id.tv5);
                        tv3.setText("MobileN0-:");
                        tv4.setText("EmailId-:");
                        tv5.setText("Cases-:");
                         Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                      uimg.setImageBitmap(largeIcon);
                        actv.setError(null);
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
                        String id0 = dataAdapter.getItem(pos);
                       db.open();
                      tv1 = (TextView) findViewById(R.id.tv2);
                      tv01 = (TextView) findViewById(R.id.tv1);
                      tv3 = (TextView) findViewById(R.id.tv3);
                      tv4 = (TextView) findViewById(R.id.tv4);
                      tv5 = (TextView) findViewById(R.id.tv5);
                      m = db.getRecord123(id0);
                  if(m[0] == "k" && m[1] == "k" && m[2] == "k" && m[3] == "k")
                  {
                      LayoutInflater inflater = getLayoutInflater();

                      View layout = inflater.inflate(R.layout.toast_xml,
                              (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                      //layout.setBackgroundColor(Color.BLACK);
                      ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Try By Id");
                      Toast toast = new Toast(getApplicationContext());
                      toast.setDuration(Toast.LENGTH_LONG);
                      toast.setView(layout);
                      toast.setGravity(Gravity.CENTER,0,80);
                      toast.show();
                       Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                          uimg.setImageBitmap(largeIcon);
                      actv.setError("Try By Id",err1);
                  }
                  else   if (m[0] != "" && m[1] != "" && m[2] != "" && m[3] != ""  && m[0] != "k" && m[1] != "k" && m[2] != "k" && m[3] != "k")
                  {
                      tv1.append("" + m[0]);
                      tv3.append("" + m[1]);
                      tv4.append("" + m[2]);
                      tv01.append("" + m[3]);
                      db.open();
                      String cases=db.getcasesallotedusers(m[3].toString());
                      tv5.append(""+cases);
                  byte[] path=db.readFromDB(id0);
                  if(path!=null)
                  {
                     Bitmap bm,bm12;
                     bm = BitmapFactory.decodeByteArray(path,0,path.length);
                     bm12=Bitmap.createScaledBitmap(bm,90,100,false);
                     Corner c=new Corner();
                     bm12= c.getRoundedCornerBitmapdel(bm12,getApplicationContext());
                     uimg.setImageBitmap(bm12);
                  }
                      else
                  {
                                         Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                                       uimg.setImageBitmap(largeIcon);
                  }
                      db.close();
                      dataAdapter.getItem(pos);
                     }
                  else
                  {
                      LayoutInflater inflater = getLayoutInflater();

                      View layout = inflater.inflate(R.layout.toast_xml,
                              (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                      //layout.setBackgroundColor(Color.BLACK);
                      ((TextView) layout.findViewById(R.id.toast_text_1)).setText("No Record Found");
                      Toast toast = new Toast(getApplicationContext());
                      toast.setDuration(Toast.LENGTH_LONG);
                      toast.setView(layout);
                      toast.setGravity(Gravity.CENTER,0,80);
                      toast.show();
                      Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                      uimg.setImageBitmap(largeIcon);
                  }
              }

          });
                   del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actv1 = actv.getText().toString().trim();
                if (actv.getText().length() == 0 || actv.getText().toString() == "" || actv.getText().toString() == null || actv1 == "")

                {
                    LayoutInflater inflater = getLayoutInflater();

                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    //layout.setBackgroundColor(Color.BLACK);
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Select Valid Id or Name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                    uimg.setImageBitmap(largeIcon);
                    actv.setError("Please Select Valid Id Or Name",err1);
                }
                else
                {
                           AlertDialog.Builder alertDialog = new AlertDialog.Builder(DeleteUser.this);
                            alertDialog.setTitle(s3);
                            alertDialog.setMessage(s2);
                            alertDialog.setIcon(R.drawable.alert);
                            alertDialog.setPositiveButton(s4, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                     db.open();
                                    String idd = actv.getText().toString().trim();
                                    if (idd.length() > 0) {
                                        id1 = idd;
                                       int b = db.deleteRecord(id1);
                                       int c = db.deleteRecordimg(id1);
                                        if (b==1 || c==1) {
                                            LayoutInflater inflater = getLayoutInflater();

                                            View layout = inflater.inflate(R.layout.toast_xml,
                                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                            //layout.setBackgroundColor(Color.BLACK);
                                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Deleted");
                                            Toast toast = new Toast(getApplicationContext());
                                            toast.setDuration(Toast.LENGTH_LONG);
                                            toast.setView(layout);
                                            toast.setGravity(Gravity.CENTER,0,80);
                                            toast.show();
                                            tv1.setText("Name-:");
                                            tv3.setText("MobileNo-:");
                                            tv4.setText("Email-:");
                                            tv01.setText("Id-:");
                                            tv01.setText("Email-:");
                                            tv5.setText("Cases-:");
                                            actv.setText("");
                                            List<String> labels12 = db.getTableValues12();
                                            dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, labels12);
                                            actv.setAdapter(dataAdapter12);
                                            actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                                                        long id) {
                                                    String id111 = dataAdapter12.getItem(pos);
                                                    db.open();
                                                    tv1 = (TextView) findViewById(R.id.tv2);
                                                    tv01 = (TextView) findViewById(R.id.tv1);
                                                    tv3 = (TextView) findViewById(R.id.tv3);
                                                    tv4 = (TextView) findViewById(R.id.tv4);
                                                    tv5 = (TextView) findViewById(R.id.tv5);
                                                   String[] m = new String[4];
                                                    m = db.getRecord123(id111);
                                                    if(m[0] == "k" && m[1] == "k" && m[2] == "k" && m[3] == "k")
                                                    {
                                                        LayoutInflater inflater = getLayoutInflater();

                                                        View layout = inflater.inflate(R.layout.toast_xml,
                                                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                                        //layout.setBackgroundColor(Color.BLACK);
                                                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Try By Id");
                                                        Toast toast = new Toast(getApplicationContext());
                                                        toast.setDuration(Toast.LENGTH_LONG);
                                                        toast.setView(layout);
                                                        toast.setGravity(Gravity.CENTER,0,80);
                                                        toast.show();
                                                        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                                                        uimg.setImageBitmap(largeIcon);
                                                        actv.setError("Try By Id",err1);

                                                    }
                                                    else   if (m[0] != "" && m[1] != "" && m[2] != "" && m[3] != ""  && m[0] != "k" && m[1] != "k" && m[2] != "k" && m[3] != "k")
                                                    {
                                                        tv1.append("" + m[0]);
                                                        tv3.append("" + m[1]);
                                                        tv4.append("" + m[2]);
                                                        tv01.append("" + m[3]);
                                                         String cases=db.getcasesallotedusers(m[3].toString());
                                                        tv5.append(""+cases);
                                                        byte[] path=db.readFromDB(id111);
                                                        if(path!=null) {
                                                             Bitmap bm,bm12;
                                                            bm = BitmapFactory.decodeByteArray(path,0,path.length);
                                                            bm12=Bitmap.createScaledBitmap(bm,90,100,false);
                                                            Corner c=new Corner();
                                                             bm12= c.getRoundedCornerBitmapdel(bm12,getApplicationContext());
                                                            uimg.setImageBitmap(bm12);
                                                        }
                                                         }
                                                    else
                                                    {
                                                        LayoutInflater inflater = getLayoutInflater();

                                                        View layout = inflater.inflate(R.layout.toast_xml,
                                                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                                        //layout.setBackgroundColor(Color.BLACK);
                                                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("No Record Found");
                                                        Toast toast = new Toast(getApplicationContext());
                                                        toast.setDuration(Toast.LENGTH_LONG);
                                                        toast.setView(layout);
                                                        toast.setGravity(Gravity.CENTER,0,80);
                                                        toast.show();
                                                        actv.setError("No Record Found",err1);
                                                    }
                                                                              }

                                            });
                                                               }
                                        else if(b==2 || c==2) {
                                            LayoutInflater inflater = getLayoutInflater();
                                            View layout = inflater.inflate(R.layout.toast_xml,
                                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                             ((TextView) layout.findViewById(R.id.toast_text_1)).setText("More then 1 Account with Same Name Exist Please Try with Id");
                                            Toast toast = new Toast(getApplicationContext());
                                            toast.setDuration(Toast.LENGTH_LONG);
                                            toast.setView(layout);
                                            toast.setGravity(Gravity.CENTER,0,80);
                                            toast.show();
                                             actv.setError("Try By id",err1);
                                        }
                                        else {
                                           LayoutInflater inflater = getLayoutInflater();
                                            View layout = inflater.inflate(R.layout.toast_xml,
                                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Not Found");
                                            Toast toast = new Toast(getApplicationContext());
                                            toast.setDuration(Toast.LENGTH_LONG);
                                            toast.setView(layout);
                                            toast.setGravity(Gravity.CENTER,0,80);
                                            toast.show();
                                            actv.setError("No Record Found",err1);
                                        }
                                    }
                                    else
                                    {
                                        actv.setError("No Record Found",err1);
                                       //Toast.makeText(getApplicationCont ext(), "Invalid Attempt Record Not Found", Toast.LENGTH_LONG).show();
                                        LayoutInflater inflater = getLayoutInflater();

                                        View layout = inflater.inflate(R.layout.toast_xml,
                                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                        //layout.setBackgroundColor(Color.BLACK);
                                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Not Found");
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
                                    LayoutInflater inflater = getLayoutInflater();
                                   View layout = inflater.inflate(R.layout.toast_xml,
                                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Still You'r not  Ready");
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
              //      }
                //}
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
        s.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s.length(), 0);
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
        return true;
    }
      @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==1)
        {
            Intent cases8 = new Intent(DeleteUser.this,MyAccount.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(DeleteUser.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            //finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(DeleteUser.this,Change.class);
            cases8.putExtra("id",uid);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
           // finish();
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
