package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class newuser extends Activity
{
    String err;
    File pic;
    String DB_NAME = DBAdapter.DATABASE_NAME;
    String b,c,d,number;
    String imageFileName;
    byte[] b1;
    int img=0;           // Environment.getExternalStorageDirectory() + "/test.db";
    String TABLE_NAME = "User_Image",a12,a,mail0,uid,mbl;
      TextView tv0,textView;
      EditText tv11,tv2,tv3,tv4;
    OutputStream imagefile=null;
    String imageEncoded;
      Button add,gal1,cam1,con;
    private String selectedImagePath;
    private  static final int PICK_CONTACT=2;
    int a1=1;
    ImageView first;
    private static final int SELECT_PICTURE = 1;
     @Override
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
         requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_user);
        gal1=(Button)findViewById(R.id.gal);
         cam1=(Button)findViewById(R.id.cam);
         con=(Button)findViewById(R.id.con);
         tv3=(EditText) findViewById(R.id.tv3);
         tv11=(EditText) findViewById(R.id.tv1);
         gal1.setEnabled(true);
        Bundle getName=getIntent().getExtras();
         a12=getName.getString("user");
         mail0=getName.getString("mail");
         uid=getName.getString("id");
         mbl=getName.getString("mobile");
        first=(ImageView)findViewById(R.id.userimage);
         tv11.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                                   tv11.setError(null);
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });
         gal1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 a1=2;
                 Intent intent = new Intent();
                 intent.setType("image/*");
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(
                         Intent.createChooser(intent, "Select Picture"),SELECT_PICTURE);
             }
         });
         con.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                 intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                 a1=3;
                 startActivityForResult(intent,PICK_CONTACT );
             }
         });
         cam1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 a1=1;
                 Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 startActivityForResult(intent,0);
             }
         });

        // EditText etcity=   ((EditText) findViewById(R.id.city));
      // tv11.setFilters(new InputFilter[]{filter(tv11,get)});


            }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SELECT_PICTURE)
        {
              if (resultCode==RESULT_OK)
            {
                Uri selectedImage = data.getData();
//                ImageView photo = (ImageView) findViewById(R.id.add_contact_label_photo);
                Bitmap mBitmap = null;
                try
                {
                  //  mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    InputStream stream = getContentResolver().openInputStream(
                            data.getData());
                    mBitmap=BitmapFactory.decodeStream(stream);
                    stream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                mBitmap = Bitmap.createScaledBitmap(mBitmap,90,100,false);
                first.setImageBitmap(mBitmap);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                first.setVisibility(View.VISIBLE);
               corner c=new corner();
               // mBitmap.compress(Bitmap.CompressFormat.JPEG,60, bos);
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(mBitmap,90,100,false);
              // mBitmap= c.getRoundedCornerBitmap12(mBitmap,200);
            //   resizedBitmap= c.getRoundedCornerBitmap(resizedBitmap, 200, getApplicationContext());
                Bitmap m1=Bitmap.createScaledBitmap(mBitmap,90,100,false);
                m1.compress(Bitmap.CompressFormat.JPEG,60,bos);
                  b1 = bos.toByteArray();
               img = 1;
                }
            }

                              switch (requestCode) {
                                  case (PICK_CONTACT):
                                      if (resultCode == RESULT_OK) {
                                          // Get the URI that points to the selected contact
                                          Uri contactUri = data.getData();
                                          // We only need the NUMBER column, because there will be only one row in the result
                                          String[] projection = {
                                                  ContactsContract.CommonDataKinds.Phone.NUMBER};
                                          Cursor cursor = getContentResolver()
                                                  .query(contactUri, projection, null, null, null);
                                          cursor.moveToFirst();

                                          // Retrieve the phone number from the NUMBER column
                                          int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                                          number = cursor.getString(column);
                                         // Toast.makeText(this, "contact info : " + number, Toast.LENGTH_LONG).show();
                                          //String[] ary = number.toString().split("");
                                          //Toast.makeText(this, "contact info : " + ary[0], Toast.LENGTH_LONG).show();
                                          tv3.setText("");
                                          char[] stringArray;
                                          //convert string into array using toCharArray() method of string class
                                          stringArray = number.toCharArray();
                                          String as = "";
                                          if (stringArray[0] == '0') {
                                              for (int i = 1; i < stringArray.length; i++) {
                                                  // tv3.append(stringArray[i]);
                                                  as += stringArray[i];
                                              }
                                              //  }
                                             // Toast.makeText(this, "contact info : " + as, Toast.LENGTH_LONG).show();
                                              tv3.setText(as);
                                              // Do something with the phone number...
                                          } else if (stringArray[0] == '9' && stringArray[1] == '1') {
                                              for (int i = 2; i < stringArray.length; i++) {
                                                  // tv3.append(stringArray[i]);
                                                  as += stringArray[i];
                                              }
                                              //  }
                                             // Toast tost=new Toast(this);
                                             // tost.makeText(this, "contact info : " + as, Toast.LENGTH_LONG).show();
                                              //tost.setGravity(Gravity.CENTER,0,0);
                                              tv3.setText(as);
                                          } else if (stringArray[0] == '+' && stringArray[1] == '9' && stringArray[2] == '1') {
                                              for (int i = 3; i < stringArray.length; i++) {
                                                  // tv3.append(stringArray[i]);
                                                  as += stringArray[i];
                                              }
                                              //  }
                                             // Toast.makeText(this, "contact info : " + as, Toast.LENGTH_LONG).show();
                                              tv3.setText(as);
                                          } else {

                                              //Toast.makeText(this, "contact info : " + number, Toast.LENGTH_LONG).show();
                                              tv3.setText(number);
                                          }

                   /* if (resultCode == Activity.RESULT_OK) {
                        Uri contactData = data.getData();
                           Cursor c = managedQuery(contactData, null, null, null, null);
                        if (c.moveToFirst()) {
                            String id =
                                    c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                            String hasPhone =
                                    c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

                            if (hasPhone.equalsIgnoreCase("1")) {
                                Cursor phones = getContentResolver().query(
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                        null, null);
                                phones.moveToFirst();
                                String phn_no = phones.getString(phones.getColumnIndex("data1"));
                                //    String name = c.getString(c.getColumnIndex(StructuredPostal.DISPLAY_NAME));
                                Toast.makeText(this, "contact info : " + phn_no, Toast.LENGTH_LONG).show();

                            }
                        }
                    }*/
                                      }
                              }

            if (requestCode==0)
            {
              //  super.onActivityResult(requestCode, resultCode, data);
                if(resultCode==RESULT_OK)
                {
                    Uri selectedImage = data.getData();
                    Bitmap mBitmap = null;
                    Bundle extras = data.getExtras();
                  mBitmap= (Bitmap) extras.get("data");
                  ByteArrayOutputStream bos = new ByteArrayOutputStream();
                   // first.setVisibility(View.VISIBLE);
                    corner c=new corner();
                   // mBitmap= c.getRoundedCornerBitmap(mBitmap,200,getApplicationContext());
                   // mBitmap= c.getRoundedCornerBitmap1(mBitmap);
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(mBitmap,90,100,false);
                    // mBitmap= c.getRoundedCornerBitmap12(mBitmap,200);
                    resizedBitmap= c.getRoundedCornerBitmap(resizedBitmap,200,getApplicationContext());
                    // first.setBackgroundColor(Color.BLUE);
                    first.setImageBitmap(resizedBitmap);
                    first.setBackgroundColor(Color.WHITE);
                  //  first.setImageBitmap(mBitmap);
                  //  mBitmap.compress(Bitmap.CompressFormat.JPEG,60, bos);
                    Bitmap m1=Bitmap.createScaledBitmap(mBitmap,90,100,false);
                    m1.compress(Bitmap.CompressFormat.JPEG,60,bos);
                     b1 = bos.toByteArray();
                   // imageEncoded = Base64.encodeToString(b1, Base64.DEFAULT);
                    img=1;
                }
                  }
    }

      public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
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
    DBAdapter db=new DBAdapter(this);

    //db.insertRecord("n","fdb","fbf","dbd");
    String mail;

    public void addnew(View v)
    {
        Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
        err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
         tv11=(EditText) findViewById(R.id.tv1);
         tv2=(EditText) findViewById(R.id.tv2);
         Log.d("addnew","3");
         tv4=(EditText) findViewById(R.id.tv4);
         a=tv11.getText().toString().trim();
         Log.d("addnew","4");
         b=tv2.getText().toString().trim();
         c=tv3.getText().toString().trim();
         d=tv4.getText().toString().trim();
         Log.d("addnew","5");
         String email = tv4.getText().toString().trim();
         String mobile = tv3.getText().toString().trim();
         String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
         Log.d("addnew","6");
           // onClick of button perform this simplest code.
           if(a.equals("") && b.equals("")&&c.equals("")&& d.equals("") && email.equals("") && c.equals(""))
             {
                  //Toast.makeText(getApplicationContext(),"All Fields Vaccant", Toast.LENGTH_LONG).show();
                 LayoutInflater inflater = getLayoutInflater();
                 View layout = inflater.inflate(R.layout.toast_xml,
                         (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                 ((TextView) layout.findViewById(R.id.toast_text_1)).setText("All Fields Vaccant");
                 Toast toast = new Toast(getApplicationContext());
                 toast.setDuration(Toast.LENGTH_LONG);
                 toast.setView(layout);
                 toast.setGravity(Gravity.CENTER,0,80);
                 toast.show();
                 tv4.setError("Please Provide Email", err1);
                 tv3.setError("Please Provide MobleNo", err1);
                 tv2.setError("Please Provide Password",err1);
                 tv11.setError("Please Provide Name",err1);
               //gal1.setEnabled(false);
                  return;
             }
           else if(email.equals(""))
           {
              // Toast.makeText(getApplicationContext(),"Please Provide Email", Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Email");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
               tv4.setError("Please Provide Email", err1);
           }
           else if(!email.matches(emailPattern))
           {
               //Toast.makeText(getApplicationContext(),"Email Patteren Not Right", Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Email Patteren Not Right");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
               tv4.setError("Email Pattern Not Right", err1);
           }
           else if(c.equals(""))
           {
              // Toast.makeText(getApplicationContext(),"Please Provide MobileNo", Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide MobileNo");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
               tv3.setError("Please Provide MobileNo",err1);
           }
           else if (!tv3.getText().toString().trim().matches("[0-9]+"))
           {
               tv3.setError("Accept Numeric Only.",err1);
               //Toast.makeText(getApplicationContext(),"Non-Numeric Found",Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Non-Numeric Found");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
               // valid_name = null;
           }
           else if(c.length()<10)
           {
               //Toast.makeText(getApplicationContext(),"Mobile Number Shoulb be 10 Digits", Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Mobile Number Shoulb be 10 Digits");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
               tv3.setError("Length Not Appropriate", err1);
           }
           else if(b.length()==0 || b.equals(""))
           {
             //  Toast.makeText(getApplicationContext(),"Please Provide Password", Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Password");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
               tv2.setError("Please Provide Password",err1);
           }
           else if(tv11.getText().toString().trim().equals("")|| tv11.getText().toString().trim().length()==0)
           {
              // Toast.makeText(getApplicationContext(),"Please Provide Name", Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Name");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
               tv11.setError("Provid Name.",err1);
           }
           else if (!tv11.getText().toString().trim().matches("[a-zA-Z ]+"))
           {
               tv11.setError("Accept Alphabets Only.",err1);
               //Toast.makeText(getApplicationContext(),"Illegal Character Found",Toast.LENGTH_LONG).show();
               LayoutInflater inflater = getLayoutInflater();
               View layout = inflater.inflate(R.layout.toast_xml,
                       (ViewGroup) findViewById(R.id.custom_toast_layout_id));
               ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Illegal Character Found");
               Toast toast = new Toast(getApplicationContext());
               toast.setDuration(Toast.LENGTH_LONG);
               toast.setView(layout);
               toast.setGravity(Gravity.CENTER,0,80);
               toast.show();
              // valid_name = null;
           }
               else {
              // gal1.setEnabled(true);
               Log.d("addnew","9");
                 db.open();
               int a=0;
               Log.d("addnew","10");
              String m = db.getRecordEmail(email);
               if(m!="")
               {
                   a=1;
                 //  gal1.setEnabled(false);
               }
               String n=db.getRecordMobile(mobile);
               if(n!="")
               {
                   a+=1;
            //       gal1.setEnabled(false);
               }
               Log.d("addnew","11");
               if(a==2)
               {
                  // Toast.makeText(getApplication(), "Mobile and Email both are Already Registered", Toast.LENGTH_LONG).show();
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
              //     gal1.setEnabled(false);
               }
                 else  if (email.equals(m))
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
                   Log.d("addnew","12");
               //    gal1.setEnabled(false);
                   //a=1;
               }
               else if (mobile.equals(n))
               {
                  // Toast.makeText(getApplication(), "Mobile Already Registered", Toast.LENGTH_LONG).show();
                   LayoutInflater inflater = getLayoutInflater();
                   View layout = inflater.inflate(R.layout.toast_xml,
                           (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                   ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Mobile Already Registered");
                   Toast toast = new Toast(getApplicationContext());
                   toast.setDuration(Toast.LENGTH_LONG);
                   toast.setView(layout);
                   toast.setGravity(Gravity.CENTER,0,80);
                   toast.show();
                   Log.d("addnew","121");
              //     gal1.setEnabled(false);
                   //a+=1;
               }
                  else
               {
                   gal1.setEnabled(true);
                   Log.d("addnew","13");
                   try {
                       db.open();
                       Long id = db.insertRecord1(tv11.getText().toString(), tv2.getText().toString(), tv3.getText().toString(), tv4.getText().toString());
                     //  Toast.makeText(this, "Record Added", Toast.LENGTH_LONG).show();
                       LayoutInflater inflater = getLayoutInflater();
                       View layout = inflater.inflate(R.layout.toast_xml,
                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                       ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Added");
                       Toast toast = new Toast(getApplicationContext());
                       toast.setDuration(Toast.LENGTH_LONG);
                       toast.setView(layout);
                       toast.setGravity(Gravity.CENTER,0,80);
                       toast.show();
                       String id1 = db.getid12(tv11.getText().toString());
                      // Toast.makeText(this, "Id is " + id1, Toast.LENGTH_LONG).show();
                       LayoutInflater inflater1 = getLayoutInflater();
                       View layout1 = inflater1.inflate(R.layout.toast_xml,
                               (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                       ((TextView) layout1.findViewById(R.id.toast_text_1)).setText("Id is " + id1);
                       Toast toast1 = new Toast(getApplicationContext());
                       toast1.setDuration(Toast.LENGTH_LONG);
                       toast1.setView(layout1);
                       toast1.setGravity(Gravity.CENTER,0,80);
                       toast1.show();
                        int nl=tv11.getText().toString().trim().length();
                       String aa="Error";
                     // *************Message to User***********************************************************
                       Map<String, String> networkDetails = getConnectionDetails();
                       if (networkDetails.isEmpty())
                       {
                       // Toast.makeText(getApplicationContext(),"Message Not Sent Because Internet is not Connected",Toast.LENGTH_LONG).show();
                           LayoutInflater inflater11 = getLayoutInflater();
                           View layout11 = inflater11.inflate(R.layout.toast_xml,
                                   (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                           ((TextView) layout11.findViewById(R.id.toast_text_1)).setText("Message Not Sent Because Internet is not Connected");
                           Toast toast11 = new Toast(getApplicationContext());
                           toast11.setDuration(Toast.LENGTH_LONG);
                           toast11.setView(layout1);
                           toast11.setGravity(Gravity.CENTER,0,80);
                           toast11.show();
                       }
                          else
                       {
                           String url = "https://site2sms.p.mashape.com/index.php?"; //msg=hi&phone=7355452203&pwd=81468485a&uid=8146837421";
                           String phn="8146012941";
                           String msg="bjbjg";
                           StringBuilder sb = new StringBuilder(url);
                           sb.append("msg="+msg + "&phone=" + phn + "&pwd=400163&uid=9872832353" );
                           SMSsend obj2 = new SMSsend();
                           obj2.execute(sb.toString());
                           //String url = "https://site2sms.p.mashape.com/index.php?"; //msg=hi&phone=7355452203&pwd=81468485a&uid=8146837421";
                          // Toast.makeText(getApplicationContext(),"Message  Sent ",Toast.LENGTH_LONG).show();
                           LayoutInflater inflater13 = getLayoutInflater();
                           View layout13 = inflater13.inflate(R.layout.toast_xml,
                                   (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                           ((TextView) layout13.findViewById(R.id.toast_text_1)).setText("Message Sent");
                           Toast toast13 = new Toast(getApplicationContext());
                           toast13.setDuration(Toast.LENGTH_LONG);
                           toast13.setView(layout13);
                           toast13.setGravity(Gravity.CENTER,0,80);
                           toast13.show();
                       }
          //   *************************************************Message************************************
                       if(nl>0 && img==1) {
                          // gal1.setEnabled(true);
                         // aa = db.saveInDB(id1,pic+"/"+imageFileName,tv11.getText().toString().trim());
                           aa = db.saveInDB(id1,b1,tv11.getText().toString().trim());
                       }
                       /*else
                       {
                           Toast.makeText(getApplicationContext(),"Please Provide UseR Name",Toast.LENGTH_LONG).show();
                       }*/
                       if(aa.equals("done"))
                       {
                        //   Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_LONG).show();
                           LayoutInflater inflater12 = getLayoutInflater();
                           View layout12 = inflater12.inflate(R.layout.toast_xml,
                                   (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                           ((TextView) layout12.findViewById(R.id.toast_text_1)).setText("Image Saved");
                           Toast toast12 = new Toast(getApplicationContext());
                           toast12.setDuration(Toast.LENGTH_SHORT);
                           toast12.setView(layout12);
                           toast12.setGravity(Gravity.CENTER,0,80);
                           toast12.show();
                       }
                       else
                       {
                        //   Toast.makeText(getApplicationContext(), "Image Not Saved", Toast.LENGTH_LONG).show();
                           LayoutInflater inflater14 = getLayoutInflater();
                           View layout14 = inflater14.inflate(R.layout.toast_xml,
                                   (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                           ((TextView) layout14.findViewById(R.id.toast_text_1)).setText("Image Not Saved");
                           Toast toast14 = new Toast(getApplicationContext());
                           toast14.setDuration(Toast.LENGTH_SHORT);
                           toast14.setView(layout14);
                           toast14.setGravity(Gravity.CENTER,0,80);
                           toast14.show();
                       }
                       tv11.setText("");
                       tv2.setText("");
                       tv3.setText("");
                       tv4.setText("");
                       db.close();
                       }
                   catch (Exception ex)
                   {
                       Log.d("Error",ex.toString());
                   }
                           }
               //m.close();
               //n.close();
             }
             }



    private Map<String, String> getConnectionDetails() {
        Map<String, String> networkDetails = new HashMap<String, String>();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnected()) {

                networkDetails.put("Type", wifiNetwork.getTypeName());
                networkDetails.put("Sub type", wifiNetwork.getSubtypeName());
                networkDetails.put("State", wifiNetwork.getState().name());
            }

            NetworkInfo mobileNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnected()) {
                networkDetails.put("Type", mobileNetwork.getTypeName());
                networkDetails.put("Sub type", mobileNetwork.getSubtypeName());
                networkDetails.put("State", mobileNetwork.getState().name());
                if (mobileNetwork.isRoaming()) {
                    networkDetails.put("Roming", "YES");
                } else {
                    networkDetails.put("Roming", "NO");
                }
            }
        } catch (Exception e) {
            networkDetails.put("Status", e.getMessage());
        }
        return networkDetails;
    }
    String valid_name;
    public void Is_Valid_Person_Name(EditText edt) throws NumberFormatException {
        if (edt.getText().toString().trim().length() <= 0)
        {
            edt.setError("Accept Alphabets Only.");
          //  Toast.makeText(getApplicationContext(),"fuck",Toast.LENGTH_LONG).show();
            valid_name = null;
        }
        else if (!edt.getText().toString().trim().matches("[a-zA-Z ]+"))
        {
            edt.setError("Accept Alphabets Only.");
           // Toast.makeText(getApplicationContext(),"fuck",Toast.LENGTH_LONG).show();
            valid_name = null;
        }
        else
        {
            valid_name = edt.getText().toString();
            //Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_LONG).show();
        }

    }

    void saveInDB()
    {
        SQLiteDatabase myDb = openOrCreateDatabase(DB_NAME,Context.MODE_PRIVATE, null);
        byte[] byteImage1 = null;
        String s = myDb.getPath();
       // textView=(TextView)findViewById(R.id.tv44);
       // myDb.execSQL("delete from " + TABLE_NAME);          // clearing the table
        ContentValues newValues = new ContentValues();
        String name = tv11.getText().toString();
        newValues.put("Name", name);
        try
        {
            FileInputStream instream = new FileInputStream(selectedImagePath);
            BufferedInputStream bif = new BufferedInputStream(instream);
            byteImage1 = new byte[bif.available()];
            bif.read(byteImage1);
            newValues.put("image", byteImage1);
            long ret = myDb.insert(TABLE_NAME, null, newValues);
            if (ret < 0)
                textView.append("Error");
        }
        catch (IOException e)
        {
            textView.append("Error Exception : " + e.getMessage());
        }
        myDb.close();
        textView.append("\n Saving Details \n Name : " + name);
       // textView.append("\n Image Size : " + byteImage1.length + " KB");
       // textView.append("\n Saved in DB : " + s + "\n");
        Toast.makeText(this.getBaseContext(),"Image Saved in DB successfully.", Toast.LENGTH_SHORT).show();
        }


    public  void Display1(Cursor c)
    {
   //    a=c.getString(0);
   //     tv0.setText(a);
     //  Toast.makeText(this,"id:"+c.getString(0),Toast.LENGTH_LONG).show();
     }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==1)
        {
            Intent cases8 = new Intent(newuser.this,myaccnt.class);
            cases8.putExtra("user",a12);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(newuser.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
          //  finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(newuser.this,change.class);
            cases8.putExtra("id",uid);
            startActivity(cases8);
            //finish();
        }
        return true;

    }

    public CharSequence filter(CharSequence source, int start,
                               int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; i++) {
            if (!Character.isLetter(source.charAt(i))) {
                return "";
            }
        }                     return null;
    }





    //message to User**************************************************************************************Message
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("X-Mashape-Key","mnS2OXkw2imshkaaFS4ShvKpsigtp1eCIkrjsnJMFPmCR0alCH");
            // Connecting to url
            urlConnection.connect();
            // Reading data from url
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {
            Log.d("Exception while downloading url", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }







    private class SMSsend extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... url) {
            String data = null;

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }


        @Override
        protected void onPostExecute(String result) {
        }

    }

}

