package com.example.czydevp.forensics;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Created by Anukool Srivastav on 25-09-2014.
 */
public class ModifyCase extends Activity
{
    String bd="",users="",users1="";
   // long cui;
    String[] toArr,toArr1;
    Cursor ids;
    String[] uniques;
    String[] m = new String[3];
    private  static final int PICK_CONTACT=1;
    EditText et3,et4;
    AutoCompleteTextView actv;
    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String>dataAdapter12;
    Button b,con;
    int casercrd=0;
    String[] m1 = new String[7];
    MultiAutoCompleteTextView actv2;
    List<String> labels;
    List<String>labels1;
    String id1,stts,desc,nm,status,description,number,a1="",a,mail0,uid,mbl;
    DBAdapter db=new DBAdapter(this);
     @Override
   protected void onCreate(Bundle savedInstanceState)
   {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_case);
       final Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
       err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
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
        b=(Button)findViewById(R.id.modify);
        et3 = (EditText) findViewById(R.id.e3);
        et4 = (EditText) findViewById(R.id.e4);
       Bundle getName=getIntent().getExtras();
       a=getName.getString("user");
       mail0=getName.getString("mail");
       uid=getName.getString("id");
       mbl=getName.getString("mobile");
       b.setEnabled(false);
        db.open();
       labels = db.getTableValuescase();
       labels1 = db.getTableValues12();
       dataAdapter12 = new ArrayAdapter<String>(this, R.layout.spinner, labels1);
       actv2 = (MultiAutoCompleteTextView) findViewById(R.id.auto2);
       actv2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
       actv2.setAdapter(dataAdapter12);
        dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner,labels);
        actv = (AutoCompleteTextView) findViewById(R.id.auto1);
        actv.setAdapter(dataAdapter);
       et3.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
                       et3.setError(null);
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
               et3.setError(null);
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
        actv2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               actv2.addTextChangedListener(new TextWatcher() {
                   @Override
                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                   }
                   @Override
                   public void onTextChanged(CharSequence s, int start, int before, int count)
                   {
                    //a1="";
                       bd="";
                     //  users="";
                       toArr=null;
                       actv2.setError(null);
                      //users="";
                  }
                   @Override
                   public void afterTextChanged(Editable s) {
                            // m11=actv2.getText().toString().trim();
                   }
               });
           }
       });
       actv2.setOnItemClickListener(new AdapterView.OnItemClickListener()
       {
           @Override
           public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                   long id)
           {

               toArr = actv2.getText().toString().split(", ");
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
                   public void onTextChanged(CharSequence s, int start, int before, int count)
                   {
                       users="";
                       et3.setText("");
                       et4.setText("");
                       actv2.setText("");
                       m1[0]="";
                       m1[4]="";
                       m1[2]="";
                       m1[6]="";
                       m1[3]="";
                       m1[5]="";
                     //  toArr=null;
                       bd="";
//                      b.setEnabled(false);
                     //  actv2.setText("");
                       actv.setError(null);
                       et3.setError(null);
                       et4.setError(null);
                       actv2.setError(null);
                   }
                   @Override
                   public void afterTextChanged(Editable s) {

                   }
               });
           }
       });
       actv.setOnItemClickListener(new AdapterView.OnItemClickListener()
       {
           @Override
           public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id)
           {
               String id0 = dataAdapter.getItem(pos);
               users="";
               b.setEnabled(true);
               db.open();
               et3.setEnabled(true);
               et4.setEnabled(true);
               m1 = db.getRecordsrchcase(id0);
               if (m1[3] == "k" && m1[5] == "k")
               {
                   //Toast.makeText(getApplication(),"Sorry More then 1 account exist with same Name",Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 account exist with same Name");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   actv.setError("Try By Id",err1);
                   //casercrd=1;
                  // b.setEnabled(false);
               }
               else if(m1[3]!="" && m1[5]!="" && m1[3]!="k" && m1[5]!="k")
               {
                   //b.setEnabled(true);
               et3.setText("" + m1[3]);
               et4.setText("" + m1[5]);
               ids=db.getusercase(Integer.parseInt(m1[0]));
               ids.moveToFirst();
               while(ids.isAfterLast()==false)
               {
                   users+= ids.getString(ids.getColumnIndex("U_id"))+", ";
                   ids.moveToNext();
               }
               users1=users.trim();
               ids.close();
               actv2.setText("" + users);
               stts=et3.getText().toString().trim();
               desc=et4.getText().toString().trim();
               b.setEnabled(true);
               db.close();
            //   dataAdapter.getItem(pos);
             // Toast.makeText(getApplicationContext(),users1, Toast.LENGTH_LONG).show();
                }
               else
               {
                  // Toast.makeText(getApplicationContext(),"Invalid Attempt", Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   actv.setError("Invalid Attempt",err1);
                   //b.setEnabled(false);
               }
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
                   status = et3.getText().toString().trim();
                   description = et4.getText().toString().trim();
                    db.open();
                    toArr = actv2.getText().toString().split(", ");
                   if(toArr!=null)
                   {
                       for (int i = 0; i < toArr.length; i++)
                         {
                          a1+=db.getRecordcase(toArr[i]) + ",";
                         }
                       toArr1 = a1.split(",");
                       Set<String> set = new HashSet<String>();
                       Collections.addAll(set, toArr1);
                       Log.d("SET VALUE",set.toArray().toString());
                       uniques = set.toArray(new String[0]);
                       for (int p1 = 0; p1 < uniques.length; p1++)
                       {
                           bd+= uniques[p1]+", ";
                       }
                       }
                   else
                   {
                       bd="";
                   }
                   number=bd.trim();
                   String bc = db.getcase(actv.getText().toString().trim());
                   if(bc.equals(null)|| bc.length()==0)
                   {
                       //Toast.makeText(getApplication(), "Case Not Found", Toast.LENGTH_LONG).show();
                       LayoutInflater inflater = getLayoutInflater();
                       View layout = inflater.inflate(R.layout.toast_xml,
                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Case Not Found");
                       Toast toast = new Toast(getApplicationContext());
                       toast.setDuration(Toast.LENGTH_LONG);
                       toast.setView(layout);
                       toast.setGravity(Gravity.CENTER,0,80);
                       toast.show();
                       Log.d("addnew","121");
                       a1="";
                       bd="";
                       number="";
                      // status="";
                       actv.setError("Invalid Attempt",err1);
                       //description="";
                   }
                   else if(bc.equals("More then 1 Account exist with Same Name"))
                   {
                      // Toast.makeText(getApplication(),"More then 1 Account exist with Same Name",Toast.LENGTH_LONG).show();
                       LayoutInflater inflater = getLayoutInflater();
                       View layout = inflater.inflate(R.layout.toast_xml,
                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("More then 1 Account exist with Same Name");
                       Toast toast = new Toast(getApplicationContext());
                       toast.setDuration(Toast.LENGTH_LONG);
                       toast.setView(layout);
                       toast.setGravity(Gravity.CENTER,0,80);
                       toast.show();
                       a1="";
                       bd="";
                       number="";
                       status="";
                       description="";
                       actv.setError("Try By Id",err1);
                   }
               else if (  status.equals("") && description.equals("") && number.equals("") )
                   {
                       //Toast.makeText(getApplication(), "Please Provide Information", Toast.LENGTH_LONG).show();
                       LayoutInflater inflater = getLayoutInflater();
                       View layout = inflater.inflate(R.layout.toast_xml,
                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Information");
                       Toast toast = new Toast(getApplicationContext());
                       toast.setDuration(Toast.LENGTH_LONG);
                       toast.setView(layout);
                       toast.setGravity(Gravity.CENTER,0,80);
                       toast.show();
                       Log.d("addnew","121");
                       a1="";
                       bd="";
                       number="";
                       actv2.setError("Plaese Prove Investies",err1);
                       et3.setError("Plaese Prove Status",err1);
                       et4.setError("Plaese Prove Description", err1);
                       //a+=1;
                   }
                  else if ( status.equals(""))
                  {
                     // Toast.makeText(getApplication(), "Please Provide Status", Toast.LENGTH_LONG).show();
                      LayoutInflater inflater = getLayoutInflater();
                      View layout = inflater.inflate(R.layout.toast_xml,
                              (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                      ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Status");
                      Toast toast = new Toast(getApplicationContext());
                      toast.setDuration(Toast.LENGTH_LONG);
                      toast.setView(layout);
                      toast.setGravity(Gravity.CENTER,0,80);
                      toast.show();
                      Log.d("addnew","121");
                      a1="";
                      bd="";
                      et3.setError("Plaese Prove Status",err1);
                      number="";
                 }
                  else if ( bd.equals("") || actv2.getText().toString().trim()==null)
                  {
                    //  Toast.makeText(getApplication(), "Please Provide Investies Or Mentioned Users may not Exist Now ", Toast.LENGTH_LONG).show();
                      LayoutInflater inflater = getLayoutInflater();
                      View layout = inflater.inflate(R.layout.toast_xml,
                              (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                      ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Investies Or Mentioned Users may not Exist Now");
                      Toast toast = new Toast(getApplicationContext());
                      toast.setDuration(Toast.LENGTH_LONG);
                      toast.setView(layout);
                      toast.setGravity(Gravity.CENTER,0,80);
                      toast.show();
                      Log.d("addnew","121");
                      a1="";
                      bd="";
                      actv2.setError("Provide Incesties",err1);
                      number="";
                  }
                      else if (  description.equals(""))
                   {
                       //Toast.makeText(getApplication(), "Please Provide Description", Toast.LENGTH_LONG).show();
                       LayoutInflater inflater = getLayoutInflater();
                       View layout = inflater.inflate(R.layout.toast_xml,
                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Description");
                       Toast toast = new Toast(getApplicationContext());
                       toast.setDuration(Toast.LENGTH_LONG);
                       toast.setView(layout);
                       toast.setGravity(Gravity.CENTER,0,80);
                       toast.show();
                       Log.d("addnew","121");
                       a1="";
                       et4.setError("Plaese Prove Description",err1);
                       bd="";
                       number="";
                   }
                      else if ( status.equals(stts) && description.equals(desc) && bd.equals(users))
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
                       Log.d("addnew","121");
                       //a+=1;
                       a1="";
                       bd="";
                       actv2.setError("Not Modified", err1);
                       et3.setError("Not Modified",err1);
                       et4.setError("Not Modified",err1);
                       number="";
                   }
                   else
                   {
                       if (  status.length() > 0 && description.length() > 0 && bd.length()>0  || !stts.equals(status) ||  !desc.equals(description) )
                       {
                           AlertDialog.Builder alertDialog = new AlertDialog.Builder(ModifyCase.this);
                           alertDialog.setTitle(s3);
                           alertDialog.setMessage(s2);
                           alertDialog.setIcon(R.drawable.alert);
                           alertDialog.setPositiveButton(s4, new DialogInterface.OnClickListener()
                           {
                               public void onClick(DialogInterface dialog, int which)
                               {
                                   db.open();
                                   String idd = actv.getText().toString().trim();
                                   if (idd.length() > 0)
                                   {
                                       id1 = idd;
                                       int b12 = db.updateRecordCase(id1, status,description);
                                        String cid=db.getcaseidmdfy(id1);  //getting case id if by name search
                                  //     Toast.makeText(getApplication(),"case:"+cid,Toast.LENGTH_LONG).show();
                                       //unique user
                                       long cui;
                                       for (int p11 = 0; p11 < uniques.length; p11++)
                                       {
                                           // b += uniques[p1] + ",";
                                           cui=db.insertcaseusers(Integer.parseInt(cid),Integer.parseInt(uniques[p11]));
                                         //  Toast.makeText(getApplication(),"Users  "+uniques[p11].toString(),Toast.LENGTH_LONG).show();

                                       }
                                         if (b12 == 1)
                                       {
                                          // Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_LONG).show();
                                           LayoutInflater inflater = getLayoutInflater();
                                           View layout = inflater.inflate(R.layout.toast_xml,
                                                   (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                           ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Updated");
                                           Toast toast = new Toast(getApplicationContext());
                                           toast.setDuration(Toast.LENGTH_LONG);
                                           toast.setView(layout);
                                           toast.setGravity(Gravity.CENTER,0,80);
                                           toast.show();
                                           a1="";
                                          // bd="";
                                         //  cid="";
                                           et3.setText("Status");
                                           et4.setText("Description");
                                           actv.setText("");
                                           actv2.setText("");
                                           List<String> labels12 = db.getTableValuescase();
                                           dataAdapter12 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, labels12);
                                           actv.setAdapter(dataAdapter12);
                                           actv.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                           {
                                               @Override
                                               public void onItemClick(AdapterView<?> parent, View arg1, int pos,long id)
                                               {
                                                //   String m12[]=new String[7];
                                                   String id0 = dataAdapter12.getItem(pos);
                                                   b.setEnabled(true);
                                                   users="";
                                                   db.open();
                                                   m1= db.getRecordsrchcase(id0);
                                                   if (m1[3] == "k" && m1[5] == "k")
                                                   {
                                                       //Toast.makeText(getApplication(),"Sorry More then 1 account exist with same Name",Toast.LENGTH_LONG).show();
                                                       LayoutInflater inflater = getLayoutInflater();
                                                       View layout = inflater.inflate(R.layout.toast_xml,
                                                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 account exist with same Name");
                                                       Toast toast = new Toast(getApplicationContext());
                                                       toast.setDuration(Toast.LENGTH_LONG);
                                                       toast.setView(layout);
                                                       toast.setGravity(Gravity.CENTER,0,80);
                                                       toast.show();

                                                    //  Button b1;
                                                    //   b1=(Button)findViewById(R.id.modify);
                                                       //b.setEnabled(false);
                                                   }
                                                   else if(m1[3]!="" && m1[5]!="" && m1[3]!="k" && m1[5]!="k")
                                                   {
                                                       et3.setText("" + m1[3]);
                                                       et4.setText("" + m1[5]);
                                                       ids=db.getusercase(Integer.parseInt(m1[0]));
                                                       ids.moveToFirst();
                                                       while(ids.isAfterLast()==false)
                                                       {
                                                           users+= ids.getString(ids.getColumnIndex("U_id"))+", ";
                                                           ids.moveToNext();
                                                       }
                                                       users1=users.trim();
                                                       ids.close();
                                                       actv2.setText("" + users);
                                                       stts=et3.getText().toString().trim();
                                                       desc=et4.getText().toString().trim();
                                                    //   b.setEnabled(true);
                                                       db.close();
                                                       //   dataAdapter.getItem(pos);
                                                       Toast.makeText(getApplicationContext(),users, Toast.LENGTH_LONG).show();
                                                   }
                                                   else
                                                   {
                                                      // Toast.makeText(getApplicationContext(),"Invalid Attempt", Toast.LENGTH_LONG).show();
                                                       LayoutInflater inflater = getLayoutInflater();
                                                       View layout = inflater.inflate(R.layout.toast_xml,
                                                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt");
                                                       Toast toast = new Toast(getApplicationContext());
                                                       toast.setDuration(Toast.LENGTH_LONG);
                                                       toast.setView(layout);
                                                       toast.setGravity(Gravity.CENTER,0,80);
                                                       toast.show();
                                                    //   Button b1;
                                                      // b1=(Button)findViewById(R.id.modify);
                                                      // b.setEnabled(false);
                                                   }
                                                  // dataAdapter.getItem(pos);
                                                  // Toast.makeText(getApplicationContext(), " selected" + dataAdapter.getItem(pos), Toast.LENGTH_LONG).show();
                                               }

                                           });
                                           }
                                       else if(b12==0)
                                       {
                                          // Toast.makeText(getApplicationContext(), "Invalid Attempt Record Not Found", Toast.LENGTH_LONG).show();
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
                                       else
                                       {
                                           LayoutInflater inflater = getLayoutInflater();
                                           View layout = inflater.inflate(R.layout.toast_xml,
                                                   (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                           ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt Record Not Found");
                                           Toast toast = new Toast(getApplicationContext());
                                           toast.setDuration(Toast.LENGTH_LONG);
                                           toast.setView(layout);
                                           toast.setGravity(Gravity.CENTER,0,80);
                                           toast.show();
                                          // Toast.makeText(getApplicationContext(), "More then 1Record Exist with Name", Toast.LENGTH_LONG).show();
                                       }
                                   } else {
                                       LayoutInflater inflater = getLayoutInflater();
                                       View layout = inflater.inflate(R.layout.toast_xml,
                                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Invalid Attempt Record Not Found");
                                       Toast toast = new Toast(getApplicationContext());
                                       toast.setDuration(Toast.LENGTH_LONG);
                                       toast.setView(layout);
                                       toast.setGravity(Gravity.CENTER,0,80);
                                       toast.show();
                                      // Toast.makeText(getApplicationContext(), "Invalid Attempt Record Not Found", Toast.LENGTH_LONG).show();
                                   }
                                   labels = db.getTableValues12();
                                   dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner, labels);
                               }

                           });

                           // Setting Negative "NO" Button
                                   alertDialog.setNegativeButton(s5, new DialogInterface.OnClickListener()
                                   {
                               public void onClick(DialogInterface dialog, int which)
                               {
                                   // User pressed No button. Write Logic Here
                                 //  Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                   LayoutInflater inflater = getLayoutInflater();
                                   View layout = inflater.inflate(R.layout.toast_xml,
                                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Confirm Again");
                                   Toast toast = new Toast(getApplicationContext());
                                   toast.setDuration(Toast.LENGTH_LONG);
                                   toast.setView(layout);
                                   toast.setGravity(Gravity.CENTER,0,80);
                                   toast.show();
                                   a1="";
                                   bd="";
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
                                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Opertion Cancelled");
                                   Toast toast = new Toast(getApplicationContext());
                                   toast.setDuration(Toast.LENGTH_LONG);
                                   toast.setView(layout);
                                   toast.setGravity(Gravity.CENTER,0,80);
                                   toast.show();
                                   a1="";
                                   bd="";
                               }
                           });

                           // Showing Alert Message
                           alertDialog.show();
                       } else {
                           //Toast.makeText(getApplicationContext(), "Sorry Vacant Field EXIST", Toast.LENGTH_LONG).show();
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
            Intent cases8 = new Intent(ModifyCase.this,MyAccount.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(ModifyCase.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            //finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(ModifyCase.this,Change.class);
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
