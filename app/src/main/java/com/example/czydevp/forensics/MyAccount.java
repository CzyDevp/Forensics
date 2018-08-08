package com.example.czydevp.forensics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anukool Srivastav on 26-09-2014.
 */
public class MyAccount extends Activity {
    String a2,uid,mail,nmbr;
    Bitmap bm12;
    ImageView image2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_account);
        DBAdapter db=new DBAdapter(this);
        Bundle getName = getIntent().getExtras();
        a2=getName.getString("user");
        nmbr=getName.getString("nmbr");
        mail=getName.getString("mail");
        uid=getName.getString("id");
        TextView tv,tv1,tv2,tv3,tv4,tv5;
        tv=(TextView)findViewById(R.id.editText8);
        image2=(ImageView)findViewById(R.id.uimg);
        tv1=(TextView)findViewById(R.id.editText9);
        tv2=(TextView)findViewById(R.id.editText10);
        tv3=(TextView)findViewById(R.id.editText7);
        tv.setText("Name "+a2);
        tv3.setText("Id "+uid);
        tv1.setText("MobileNo "+nmbr);
        tv2.setText("Mail "+mail);
        db.open();
        byte[] path = db.readFromDB(uid);
        if (path!=null) {
             Bitmap bitmap= BitmapFactory.decodeByteArray(path, 0, path.length);
            bm12=Bitmap.createScaledBitmap(bitmap,98,108,false);
            Corner c=new Corner();
            bm12= c.getRoundedCornerBitmap1(bm12);
            image2.setImageBitmap(bm12);
             }
        else
        {
            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.user1);
            image2.setImageBitmap(largeIcon);
            bm12=largeIcon;
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
            Intent cases8 = new Intent(MyAccount.this,MyAccount.class);
            startActivity(cases8);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(MyAccount.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            //finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(MyAccount.this,Change.class);
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
