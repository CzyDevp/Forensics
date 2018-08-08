package com.example.czydevp.forensics;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anukool Srivastav on 28-09-2014.
 */
public class AddEvidence extends Activity {
    ArrayList<String> items=new ArrayList<String>();
     static final int REQUEST_TAKE_PHOTO = 1;
     TextView tvAddress;
    static final int REQUEST_TAKE_VIDEO = 1;
    File pic;
    String imageFileName;
    Intent intent;
    OutputStream imagefile=null;
    boolean b=false;
    String ctype="",media1="",img="",vid="",mediaimg="";
    File image;
    String casen="";
    Cursor iii;
     EditText ctp;
    int a1;
    Button cam1,cam2,add;
    List<String> labels;
    String n;
    private String selectedImagePath;
    ArrayAdapter dataAdapter;
    String a="",email,uid,nmbr;
    AutoCompleteTextView actv,actv1;
    EditText enm,etdesc;
    AppLocationService appLocationService;
    DBAdapter db =new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_evidence);
       items.add("Hairs");
        items.add("Fibers");
        items.add("Glass");
        items.add("Fractured Objects");
        items.add("Shoe Impression");
        items.add("Tire Impression");
        items.add("Bare Foot Impression");
        items.add("Fire accelerants");
        items.add("Body fluids (blood, semen, saliva)");
        items.add("Skin cells (items touched or worn)");
        items.add(" Latent fingerprints");
        items.add("Gunshot residue and patterns");
        items.add("Toolmarks (focus on point of entry)");
        items.add("Firearms");
        items.add("Other weapons (knives, clubs)");
        items.add("Illicit drugs production / cultivation /supply / importation");
        items.add("Fired bullets");
        items.add("Fired cartridge cases");
        items.add("Unfired cartridges");
        items.add("Drugs and paraphernalia\n");
        items.add("Documents (checks, notes, receipts)");
        items.add("Computers and devices");
        items.add("Others");
       cam1=(Button)findViewById(R.id.cam);
        cam2=(Button)findViewById(R.id.video);
        add=(Button)findViewById(R.id.add);
        add.setEnabled(false);
        enm=(EditText)findViewById(R.id.etname);
        etdesc=(EditText)findViewById(R.id.desc);
        ctp=(EditText)findViewById(R.id.ctp);
        tvAddress=(TextView)findViewById(R.id.location);

        enm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               enm.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etdesc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etdesc.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //****************************************************Location***********************************//
        appLocationService = new AppLocationService(AddEvidence.this);

        Map<String, String> networkDetails = getConnectionDetails();
        if (networkDetails.isEmpty())
        {
            //value.setText("Connection unavailable");
            showSettingsAlert12();
        }
          else
        {
            Location gpsLocation = appLocationService
                    .getLocation(LocationManager.GPS_PROVIDER);
            if (gpsLocation != null)
            {
                double latitude1 = gpsLocation.getLatitude();
                double longitude1 = gpsLocation.getLongitude();
                String result = "Latitude: " + gpsLocation.getLatitude() +
                        " Longitude: " + gpsLocation.getLongitude();
                //tvAddress.setText(result);
                Location location = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);
                //you can hard-code the lat & long if you have issues with getting it
                //remove the below if-condition and use the following couple of lines
                //double latitude = 37.422005;
                //double longitude = -122.084095
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    LocationAddress locationAddress = new LocationAddress();
                    locationAddress.getAddressFromLocation(latitude, longitude,
                            getApplicationContext(), new GeocoderHandler());
                } else {
                    showSettingsAlert();
                }
            }
            else
            {
                showSettingsAlert();
            }
        }





     enm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) AddEvidence.this
                        .getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.showSoftInput(enm, 0);
            }
        });
        etdesc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) AddEvidence.this
                        .getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etdesc, 0);
            }
        });
       ctp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) AddEvidence.this
                        .getSystemService(Service.INPUT_METHOD_SERVICE);
                imm.showSoftInput(ctp, 0);
            }
        });
     //   InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
     //   mgr.hideSoftInputFromWindow(id.getWindowToken(), 0);
      //  mgr.hideSoftInputFromWindow(pass.getWindowToken(), 0);
       // mgr.showSoftInput(enm, InputMethodManager.SHOW_IMPLICIT);
       // mgr.showSoftInput(etdesc, InputMethodManager.SHOW_IMPLICIT);
        db.open();
  //   iii=db.getJoinedInfo("6");
    //    if(iii!=null) {
      //      Toast.makeText(getApplicationContext(), " " + iii.getCount(), Toast.LENGTH_LONG).show();
       // }


     //   ctp = (EditText) findViewById(R.id.ctp);
        Bundle  getName=getIntent().getExtras();
        n=getName.getString("user");
        email=getName.getString("mail");
        uid=getName.getString("id");
        nmbr=getName.getString("mobile");
        actv=(AutoCompleteTextView)findViewById(R.id.auto2);
        actv1=(AutoCompleteTextView)findViewById(R.id.auto1);
        db.open();
        labels = db.getcasenames(uid);
       // dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner, labels);
       // labels = db.getTableValues12();
        dataAdapter = new ArrayAdapter<String>(this, R.layout.spinner, labels);
        actv.setAdapter(dataAdapter);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner, items);
        actv1.setAdapter(adapter);
        actv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                ctype = adapter.getItem(pos);
                Toast.makeText(getApplicationContext(), " Selected " + ctype, Toast.LENGTH_LONG).show();
                if (ctype.equals("Others")) {
                    ctp.setVisibility(View.VISIBLE);
                    ctp.setEnabled(true);
                } else {
                    ctp.setVisibility(View.GONE);
                    ctp.setText("");
                }
            }
        });

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                casen = dataAdapter.getItem(pos).toString();
                cam1.setEnabled(true);
                cam2.setEnabled(true);
              //  Toast.makeText(getApplicationContext(), " Selected " + casen, Toast.LENGTH_LONG).show();
               int count= Collections.frequency(labels,casen);
                if (actv.getText().length() == 0 || actv.getText().toString() == "" || actv.getText().toString() == null)

                {
                    //Toast.makeText(getApplicationContext(), "Please Select Valid ID", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Select Valid ID");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    add.setEnabled(false);
                }
                else
                {
                    String id12 = actv.getText().toString().trim();
                          if (Collections.frequency(labels,id12)>1)
                    {
                       // Toast.makeText(getApplication(),"Sorry More then 1 account exist with same Name,Please Try With Case Id",Toast.LENGTH_LONG).show();

                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_xml,
                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry More then 1 account exist with same Name,Please Try With Case Id");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER,0,80);
                        toast.show(); add.setEnabled(false);
                    }
                    else if(Collections.frequency(labels,id12)==1 )
                    {
                        add.setEnabled(true);
                    }
                    else
                    {
                        //Toast.makeText(getApplicationContext(),"Sorry Record is Not Exist ",Toast.LENGTH_LONG).show();
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_xml,
                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Record is Not Exist ");
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.setGravity(Gravity.CENTER,0,80);
                        toast.show();
                        add.setEnabled(false);
                        add.setEnabled(false);
                    }

                }



                     }
        });
               cam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dispatchTakePictureIntent();
            }
        });
        cam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a1=1;
               dispatchTakePictureIntentVideo();
            }
        });
        actv1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                actv.setError(null);
                actv1.addTextChangedListener(new TextWatcher()
                {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after)
                    {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                        ctp.setVisibility(View.GONE);
                        ctype="";
                       // actv1.setError(null);
                    }
                    @Override
                    public void afterTextChanged(Editable s)
                    {

                    }
                });
            }
        });

        actv.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                actv.addTextChangedListener(new TextWatcher()
                {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after)
                    {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {

                       // ctp.setVisibility(View.GONE);
                        casen="";
                        cam1.setEnabled(false);
                        cam2.setEnabled(false);
                        actv.setError(null);
                        etdesc.setError(null);
                        enm.setError(null);
                        actv1.setError(null);
                    }
                    @Override
                    public void afterTextChanged(Editable s)
                    {

                    }
                });
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
                err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
                String casname=actv.getText().toString().trim(),cid=null,evtype=null,name=null,desc=null,path=null,time,date;
                name=enm.getText().toString().trim();
                desc=etdesc.getText().toString().trim();
                String bc = db.getcase(actv.getText().toString().trim());
                ctype=actv1.getText().toString().trim();
                Calendar c1 = Calendar.getInstance();
                SimpleDateFormat sdf1 = new SimpleDateFormat("h:m:s a");
                time = sdf1.format(c1.getTime());
                Calendar c2 = Calendar.getInstance();
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                date = sdf2.format(c2.getTime());
                int a=0;
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
                   // Toast.makeText(getApplicationContext(),"Please Select Valid Case Name",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Case Name ");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    actv.setError("Please Select Valid Case Name",err1);
                }
                else if(enm.getText().toString().trim().equals("") && etdesc.getText().toString().trim().equals("") && actv1.getText().toString().trim().equals(""))
                {
                   // Toast.makeText(getApplicationContext(),"Please Provide Name,E-Type,and Description",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Name,E-Type,and Description ");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    etdesc.setError("Provide Description",err1);
                    actv1.setError("Please Provide Evidence-Type",err1);
                    enm.setError("Provide Name",err1);
                }
              else  if(enm.getText().toString().trim().equals(""))
                {
                    enm.setError("Provide Name",err1);
                }
                else if(name.equals(""))
                {
                  //  Toast.makeText(getApplicationContext(),"Please Provide Name",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Name ");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
                else if(etdesc.getText().toString().trim().equals(""))
                {
                    etdesc.setError("Provide Description",err1);
                }
                else if(desc.equals(""))
                {
                   // Toast.makeText(getApplicationContext(),"Please Provide Description",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Description");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
                else if(actv1.getText().toString().trim().equals(""))
                {
                   // Toast.makeText(getApplication(),"Please Provide Evidence-Type",Toast.LENGTH_LONG).show();
                    actv1.setError("Please Provide Evidence-Type",err1);
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Evidence-Type");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
                else if(ctype.length()==0  || ctype.equals(""))
                {
                    //Toast.makeText(getApplication(),"Please Provide Evidence-Type",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Evidence-Type");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    //actv1.setError("Please Provide Evidence-Type",err1);
                 }

                else
                {
                         if(ctype.equals("Others"))
                    {
                        ctype=ctp.getText().toString();
                        //Toast.makeText(get)
                    }
                    if(!ctype.equals("")) {
                        cid = db.getcaseid(bc);
                        Toast.makeText(getApplicationContext(), cid + " " + time + " " + date + "/" + ctype, Toast.LENGTH_LONG).show();
                        if(media1.equals("") && mediaimg.equals(""))
                        {
                           // Toast.makeText(getApplication(),"Not Any Media Added ",Toast.LENGTH_LONG).show();
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Not Any Media Added");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();

                        }
                        else
                        {
                            //Toast.makeText(getApplication()," Media Added "+media1,Toast.LENGTH_LONG).show();
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_xml,
                                    (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                            ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Media Added");
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.setGravity(Gravity.CENTER,0,80);
                            toast.show();
                        }
                        long id=db.insertevd(Integer.parseInt(cid),Integer.parseInt(uid),name,ctype,desc,mediaimg,time,date,media1);
                        String eid=db.getevdid(name);
                        //Toast.makeText(getApplicationContext(),"Evidence added with  id"+eid+ "Media "+mediaimg,Toast.LENGTH_LONG).show();
                        ctype="";
                        enm.setText("");
                        etdesc.setText("");
                         }
                    else
                    {
                       // Toast.makeText(getApplication(),"Please Provide Evidence-Type",Toast.LENGTH_LONG).show();
                        LayoutInflater inflater = getLayoutInflater();
                        View layout = inflater.inflate(R.layout.toast_xml,
                                (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                        ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Provide Evidence-Type");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
           //    if (a1 == 1)
    //    {
            super.onActivityResult(requestCode, resultCode, data);
            if(resultCode==RESULT_OK && requestCode==REQUEST_TAKE_VIDEO)
            {
                cam2.setEnabled(false);
                cam1.setEnabled(false);
                media1=image.toString();
                          }
     else if(resultCode==RESULT_OK && requestCode==REQUEST_TAKE_PHOTO)
        {
            cam1.setEnabled(false);
            cam2.setEnabled(false);
           // mediaimg=image.toString();
        }
        else if(resultCode==RESULT_CANCELED)
            {
               //File f=new File(""+image);
                //f.delete();
                b=image.delete();
                if(b) {
                    //  Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("deleted");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER, 0, 80);
                    toast.show();
                }
                media1="";
                mediaimg="";
            }

            //  }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = casen+" "+uid+"_"+ timeStamp + "_";
        //File storageDir = Environment.getExternalStoragePublicDirectory(
            //    Environment.DIRECTORY_PICTURES);
        File storageDir = getDir("Fes",Context.MODE_PRIVATE);
        image = File.createTempFile(
                imageFileName,  /* prefix */
                ".bmp",         /* suffix */
                storageDir      /* directory */
        );
        //  Bitmap bm=image;
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        Toast.makeText(this, "path"+image.toString(), Toast.LENGTH_LONG).show();
        mediaimg=image.getAbsolutePath().toString();
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
        Toast.makeText(this, "path"+image.toString(), Toast.LENGTH_LONG).show();
        media1=image.getAbsolutePath().toString();
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==1)
        {
            Intent cases8 = new Intent(AddEvidence.this,MyAccount.class);
            cases8.putExtra("user",n);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",email);
            cases8.putExtra("nmbr",nmbr);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(AddEvidence.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            //finish();
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(AddEvidence.this,Change.class);
            cases8.putExtra("id",uid);
            startActivity(cases8);
            //finish();
        }
        return true;
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
    public void showSettingsAlert12() {
        final SpannableString Message = new SpannableString("Enable Internet");
        Message.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, Message.length(), 0);
        final SpannableString s3 = new SpannableString("Settings");
        s3.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s3.length(), 0);
        final SpannableString s4 = new SpannableString("Settings");
        s4.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s4.length(), 0);
        final SpannableString s5 = new SpannableString("Cancel");
        s5.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s5.length(), 0);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddEvidence.this);
        alertDialog.setTitle(s3);
       alertDialog.setMessage(Message);
        alertDialog.setPositiveButton(s4,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        intent = new Intent(Settings.ACTION_SETTINGS);
                       AddEvidence.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton(s5,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress=null;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
               //locationAddress = null;
                default:
            }
            tvAddress.setText(locationAddress);
        }
    }
    public void showSettingsAlert() {
        final SpannableString Message = new SpannableString("Enable Location Provider! Go to settings Menu?");
        Message.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, Message.length(), 0);
        final SpannableString s3 = new SpannableString("Settings");
        s3.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s3.length(), 0);
        final SpannableString s4 = new SpannableString("Settings");
        s4.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s4.length(), 0);
        final SpannableString s5 = new SpannableString("Cancel");
        s5.setSpan(new ForegroundColorSpan(Color.argb(255,67,192,251)), 0, s5.length(), 0);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddEvidence.this);
        alertDialog.setTitle(s3);
        alertDialog.setMessage(""+Message);
               alertDialog.setPositiveButton(s4,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        AddEvidence.this.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton(s5,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        // finish() is called in super: we only override this method to be able to override the transition
        super.onBackPressed();

        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
      }

