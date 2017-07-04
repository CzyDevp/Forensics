package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Bundle;
import android.support.v4.print.PrintHelper;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.itextpdf.text.Image;

import java.util.ArrayList;
import java.util.List;
public class searchuser extends Activity
{
    String DB_NAME = DBAdapter.DATABASE_NAME;
    // Environment.getExternalStorageDirectory() + "/test.db";
    String TABLE_NAME = "User_Image",a,mail0,uid,mbl;
    ImageView image2;
    DBAdapter db=new DBAdapter(this);
    ArrayList<String> items1=new ArrayList<String>();
   // Spinner search;
    Button btn,pdf,print;
    Bitmap bm12= null;
    AutoCompleteTextView actv;
    TextView tv1,tv2,tv3,tv4,tv12,getpd;
    String id1;
    ArrayAdapter<String> dataAdapter;
    //ArrayAdapter<String>    dataAdapter12;
  @Override
  protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_user);
        final Drawable err1 = (Drawable)getResources().getDrawable(R.drawable.err);
        print = (Button)findViewById(R.id.print);
        err1.setBounds(0, 0, err1.getIntrinsicWidth()/2, err1.getIntrinsicHeight()/2);
        btn=(Button)findViewById(R.id.btn);
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        mail0=getName.getString("mail");
        uid=getName.getString("id");
        mbl=getName.getString("mobile");
        tv12=(TextView)findViewById(R.id.viewall);
        getpd=(TextView)findViewById(R.id.getpdf);
        getpd.setEnabled(false);
        getpd.setVisibility(View.GONE);
        image2 = (ImageView) findViewById(R.id.imageView);
        image2.setVisibility(View.VISIBLE);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
        bm12=largeIcon;
        // image2.setImageResource(R.drawable.usr);
       // corner c=new corner();
      //  largeIcon= c.getRoundedCornerBitmap(largeIcon,200);
        image2.setImageBitmap(largeIcon);
        pdf=(Button)findViewById(R.id.pdf);
        pdf.setEnabled(false);
        pdf.setVisibility(View.GONE);
        print.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                PrintHelper printHelper;
                printHelper = new PrintHelper(searchuser.this);
                printHelper.setScaleMode(PrintHelper.SCALE_MODE_FILL);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.galry);
                printHelper.printBitmap("Gallery",bitmap);
            }
        });
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = tv1.getText().toString();
               // String filecontent = tv1.getText().toString();
                String name = tv1.getText().toString();
                String mobile=tv2.getText().toString();
                String email=tv3.getText().toString();
                String id=tv4.getText().toString();
               // String Description=tv6.getText().toString();
               // String startedon=tv7.getText().toString();
                FileOperations fop = new FileOperations();
                fop.writeuser(name,mobile,email,id,bm12);
                if (fop.writeuser(name,mobile,email,id,bm12))
                {
                    print.setVisibility(View.VISIBLE);
                     LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText(filename + ".pdf created");
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
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("I/O Error");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                }
            }
        });
        tv12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent case1 = new Intent(searchuser.this,listcaseuser.class);
                case1.putExtra("user",a);
                case1.putExtra("id",uid);
                case1.putExtra("mail",mail0);
                case1.putExtra("mobile",mbl);
                startActivity(case1);
            }
        });
        actv = (AutoCompleteTextView) findViewById(R.id.auto1);
        db.open();
        final List<String> labels = db.getTableValues12();
        dataAdapter = new ArrayAdapter<String>(this,R.layout.spinner,labels);
        actv.setAdapter(dataAdapter);
          btn.setOnClickListener(new View.OnClickListener()
          {
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
                                tv4 = (TextView) findViewById(R.id.tid);
                                tv1.setText("");
                                tv2.setText("");
                                tv3.setText("");
                                tv4.setText("");
                                image2 = (ImageView) findViewById(R.id.imageView);
                                actv.setError(null);
                                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                               image2.setImageBitmap(largeIcon);
                                pdf.setEnabled(false);
                                bm12= BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                                pdf.setVisibility(View.GONE);
                                getpd.setVisibility(View.GONE);
                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }
                });
                if (actv.getText().length() == 0 || actv.getText().toString() == "" || actv.getText().toString() == null || actv1 == "")

                {
                  //  Toast.makeText(getApplicationContext(), "Please Select Valid ID", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Please Select Valid ID");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    pdf.setEnabled(false);
                    pdf.setVisibility(View.GONE);
                    getpd.setVisibility(View.GONE);
                   bm12= BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                    actv.setError("Please Provide Id or Name",err1);
                }
                else
                {
                    String id = actv.getText().toString().trim();
                    id1 = id.toString().trim();
                    db.open();
                    String[] m = new String[4];
                    if (id1.length() > 0)
                    {
                        m = db.getRecord123(id1);
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
                    pdf.setEnabled(false);
                    pdf.setVisibility(View.GONE);
                    getpd.setVisibility(View.GONE);
                   bm12= BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                    actv.setError("Try By Id",err1);
                }
                else   if (m[0] != "" && m[1] != "" && m[2] != "" && m[3] != ""  && m[0] != "k" && m[1] != "k" && m[2] != "k" && m[3] != "k")
                    {
                        tv1 = (TextView) findViewById(R.id.tv1);
                        tv2 = (TextView) findViewById(R.id.tv2);
                        tv3 = (TextView) findViewById(R.id.tv3);
                        tv4 = (TextView) findViewById(R.id.tid);
                        tv1.setText("Name-: " + m[0]);
                        tv1.setVisibility(View.VISIBLE);
                        tv2.setText("MobileNo-: " + m[1]);
                        tv2.setVisibility(View.VISIBLE);
                        tv3.setText("Email-: " + m[2]);
                        tv3.setVisibility(View.VISIBLE);
                        tv4.setText("Id-: " + m[3]);
                        tv4.setVisibility(View.VISIBLE);
                        image2 = (ImageView) findViewById(R.id.imageView);
                        image2.setVisibility(View.VISIBLE);
                        byte[] path = db.readFromDB(id1);
                        if (path!=null) {
                           Bitmap bitmap=BitmapFactory.decodeByteArray(path, 0,path.length);
                           bm12=Bitmap.createScaledBitmap(bitmap,98,108,false);
                           corner c=new corner();
                           bm12= c.getRoundedCornerBitmap1(bm12);
                           image2.setImageBitmap(bm12);
                              }
                           else
                        {
                            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
                            image2.setImageBitmap(largeIcon);
                            bm12=largeIcon;
                        }
                        pdf.setEnabled(true);
                        pdf.setVisibility(View.VISIBLE);
                        getpd.setVisibility(View.VISIBLE);
                        db.close();
                    }
                 else
                {
                   // Toast.makeText(getApplicationContext(), "Sorry Record is Not Exist", Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_xml,
                            (ViewGroup) findViewById(R.id.custom_toast_layout_id));
                    ((TextView) layout.findViewById(R.id.toast_text_1)).setText("Sorry Record is Not Exist");
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.setGravity(Gravity.CENTER,0,80);
                    toast.show();
                    actv.setError("Record Not Exist",err1);
                    tv1 = (TextView) findViewById(R.id.tv1);
                    tv2 = (TextView) findViewById(R.id.tv2);
                    tv3 = (TextView) findViewById(R.id.tv3);
                    tv4 = (TextView) findViewById(R.id.tid);
                    tv1.setText("");
                    tv2.setText("");
                    tv3.setText("");
                    tv4.setText("");
                    pdf.setEnabled(false);
                    pdf.setVisibility(View.GONE);
                    getpd.setVisibility(View.GONE);
                }
            }
                         }
                }

        });
       }
    void readFromDB()
    {
        byte[] byteImage2 = null;
        SQLiteDatabase myDb;
        myDb = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        String sql              =   "SELECT * FROM User_Image";
        Cursor cur = myDb.rawQuery(sql, new String[] {});
       cur.moveToFirst();
        byteImage2 = cur.getBlob(cur.getColumnIndex("Image"));
        setImage(byteImage2);
        cur.close();
        myDb.close();
        Toast.makeText(this.getBaseContext(),
                "Image read from DB successfully.", Toast.LENGTH_SHORT).show();
        Toast.makeText(this.getBaseContext(),
                "If your image is big, please scrolldown to see the result.",
                Toast.LENGTH_SHORT).show();
    }
    void setImage(byte[] byteImage2) {
        image2.setImageBitmap(BitmapFactory.decodeByteArray(byteImage2, 0,
                byteImage2.length));
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
            Intent cases8 = new Intent(searchuser.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",mbl);
            startActivity(cases8);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(searchuser.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            //finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(searchuser.this,change.class);
            cases8.putExtra("id",uid);
            startActivity(cases8);
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
