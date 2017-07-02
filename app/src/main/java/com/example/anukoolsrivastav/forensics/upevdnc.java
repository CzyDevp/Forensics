package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Anukool Srivastav on 25-09-2014.
 */
public class upevdnc extends Activity {
    List<String> labels,evdns;
    AutoCompleteTextView actv,actv1;
    Button up,cam,vid;
    ArrayAdapter dataAdapter,adapter;
    String a,email,uid,nmbr,evdnc,mediaimg="",media1="",descrptn="";
    File image;
    String casen="";
    EditText desc;
    boolean b=false;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_TAKE_VIDEO = 1;
    DBAdapter db =new DBAdapter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.up_evidence);
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        email=getName.getString("mail");
        uid=getName.getString("id");
        nmbr=getName.getString("mobile");
        actv=(AutoCompleteTextView)findViewById(R.id.auto2);
        actv1=(AutoCompleteTextView)findViewById(R.id.auto1);
        up=(Button)findViewById(R.id.update);
        cam=(Button)findViewById(R.id.cam);
        vid=(Button)findViewById(R.id.video);
        desc=(EditText)findViewById(R.id.ed1);
        final Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
        err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
        db.open();
        labels = db.getcasenames(uid);
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner,labels);
        actv.setAdapter(dataAdapter);
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id)
            {
                String cs=dataAdapter.getItem(pos).toString().trim();
                if (Collections.frequency(labels, cs)>1)
                {
                   LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 Case exist with same Name,Please Try With Case Id");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                   up.setEnabled(false);
                    actv.setError("Sorry More then 1 Case exist with same Name",err1);
                }
                else if(Collections.frequency(labels,cs)==1 )
                {
                    up.setEnabled(false);
                    evdns=db.getevdnsbyuser(dataAdapter.getItem(pos).toString(),Integer.parseInt(uid));
                    adapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner,evdns);
                    actv1.setAdapter(adapter);
                }
                else
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Record Not Found");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    up.setEnabled(false);
                    actv.setError("Sorry Record is Not Exist",err1);
                }
            }
        });
        actv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                evdnc = adapter.getItem(pos).toString().trim();
                if (Collections.frequency(evdns, evdnc) > 1) {
                 //   Toast.makeText(getApplication(), "Sorry More then 1 Evidence exist with same Name,Please Try With Evidence Id", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 Evidence exist with same Name,Please Try With Evidence Id");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    up.setEnabled(false);
                    actv1.setError("Please Try With Evidence Id", err1);
                }
                else if (Collections.frequency(evdns, evdnc) == 1) {
                    up.setEnabled(true);
                    String m = db.getevdncup(evdnc, Integer.parseInt(uid));
                    if (m.equals("")) {
                        cam.setEnabled(true);
                        vid.setEnabled(true);
                    } else {
                        //Toast.makeText(getApplicationContext(), "Sorry Evidence is Not Exist ", Toast.LENGTH_LONG).show();
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_xml,
                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Evidence is Not Exist");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER,0,80);
                        toast.show();
                        up.setEnabled(false);
                        actv1.setError("Evidence Not Exist", err1);
                    }
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
                       // actv.setAdapter(null);
                        cam.setEnabled(false);
                        vid.setEnabled(false);
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
                       evdnc="";
                       evdnc="";
                       // adapter=null;
                        actv1.setAdapter(null);
                        cam.setEnabled(false);
                        vid.setEnabled(false);
                        actv.setError(null);
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        });
            //camera for pic
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        //video
        vid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntentVideo();
            }
        });




        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                descrptn=desc.getText().toString().trim();
                String bc = db.getcase(actv.getText().toString().trim());
                evdnc=actv1.getText().toString().trim();
                int a=0,a1=0;
                for(int i=0;i<labels.size();i++)
                {
                    //Toast.makeText(getApplicationContext(),labels.get(i),Toast.LENGTH_LONG).show();
                    if(bc.equals(labels.get(i)))
                    {
                        //  bc=labels.get(i);
                        casen=bc;
                        a=1;
                        break;
                    }
                    else
                    {
                        a=0;
                        // bc="";
                    }
                }

                if(bc.length()==0 || a==0)
                {
                    //Toast.makeText(getApplicationContext(), "Please Select Valid Case Name", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Select Valid Case Name");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();

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
                    Calendar c5 = Calendar.getInstance();
                    SimpleDateFormat sdf5 = new SimpleDateFormat("dd.MM.yy h:m:s a");
                    String strdate = sdf5.format(c5.getTime());
                     if(descrptn.equals("")&& media1.equals("")&& mediaimg.equals(""))
                     {
                        // Toast.makeText(getApplicationContext(),"Sorry Not Any Modification Done by You",Toast.LENGTH_LONG).show();
                         LayoutInflater inflater = getLayoutInflater();
                         View layout = inflater.inflate(R.layout.toast_xml,
                                 (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                         ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Not Any Modification Done by You");
                         Toast toast = new Toast(getApplicationContext());
                         toast.setDuration(Toast.LENGTH_LONG);
                         toast.setView(layout);
                         toast.setGravity(Gravity.CENTER,0,80);
                         toast.show();
                     }
                    else
                     {
                         if(!descrptn.equals(""))
                         {
                             descrptn+="/"+strdate;
                         }
                        //Toast.makeText(getApplicationContext(),"Additional "+mediaimg +" "+media1 + " "+descrptn,Toast.LENGTH_LONG).show();
                        int b=0;
                        b=db.updateEvidence(evdnc,media1,mediaimg,descrptn);
                         if(b==1)
                         {
                             //Toast.makeText(getApplicationContext(),"Record Updated",Toast.LENGTH_LONG).show();
                             LayoutInflater inflater = getLayoutInflater();
                             View layout = inflater.inflate(R.layout.toast_xml,
                                     (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                             ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Record Updated");
                             Toast toast = new Toast(getApplicationContext());
                             toast.setDuration(Toast.LENGTH_LONG);
                             toast.setView(layout);
                             toast.setGravity(Gravity.CENTER,0,80);
                             toast.show();
                             desc.setText("");
                             actv1.setText("");
                             actv.setText("");
                         }
                         else
                         {
                           //  Toast.makeText(getApplicationContext(),"Evidence id or name may be Wrong",Toast.LENGTH_LONG).show();
                             LayoutInflater inflater = getLayoutInflater();
                             View layout = inflater.inflate(R.layout.toast_xml,
                                     (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                             ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Evidence id or name may be Wrong");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //    if (a1 == 1)
        //    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==REQUEST_TAKE_VIDEO)
        {
            cam.setEnabled(false);
            vid.setEnabled(false);
            media1=image.toString();
               }
        else if(resultCode==RESULT_OK && requestCode==REQUEST_TAKE_PHOTO)
        {
            cam.setEnabled(false);
            vid.setEnabled(false);
            mediaimg=image.toString();
        }
        else if(resultCode==RESULT_CANCELED)
        {
            b=image.delete();
            if(b) {
                // Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_xml,
                        (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Deleted");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(layout);
                toast.setGravity(Gravity.CENTER, 0, 80);
                toast.show();

            }
            media1 = "";
            mediaimg = "";
        }

        //  }
    }
    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = casen+" "+uid+"_"+ timeStamp + "_";
        //File storageDir = Environment.getExternalStoragePublicDirectory(
        //    Environment.DIRECTORY_PICTURES);
        File storageDir = getDir("Fes", Context.MODE_PRIVATE);
        image = File.createTempFile(
                imageFileName,  /* prefix */
                ".bmp",         /* suffix */
                storageDir      /* directory */
        );
        //  Bitmap bm=image;
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        //Toast.makeText(this, "path"+image.toString(), Toast.LENGTH_LONG).show();
        return image;
    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    //Saving Video


    String mCurrentPhotoPath12;
    private File createVideoFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = casen+" "+uid+"_"+ timeStamp + "_";
        //File storageDir = Environment.getExternalStoragePublicDirectory(
        //    Environment.DIRECTORY_PICTURES);
        File storageDir = getDir("Fes",Context.MODE_PRIVATE);
        image = File.createTempFile(
                imageFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir      /* directory */
        );
        //  Bitmap bm=image;
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath12 = "file:" + image.getAbsolutePath();
       // Toast.makeText(this, "path"+image.toString(), Toast.LENGTH_LONG).show();
        return image;
    }
    private void dispatchTakePictureIntentVideo() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoVideo = null;
            try {
                photoVideo = createVideoFile();
            } catch (IOException ex) {
            }
            if (photoVideo != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoVideo));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_VIDEO);
            }
        }
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
            Intent cases8 = new Intent(upevdnc.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",email);
            cases8.putExtra("nmbr",nmbr);
            startActivity(cases8);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(upevdnc.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            //finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(upevdnc.this,change.class);
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
