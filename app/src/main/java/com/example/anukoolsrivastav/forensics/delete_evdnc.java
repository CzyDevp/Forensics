package com.example.anukoolsrivastav.forensics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by Anukool Srivastav on 25-09-2014.
 */
public class delete_evdnc extends Activity {
    ArrayList<String> items=new ArrayList<String>();
    ArrayList<String> items1=new ArrayList<String>();
    Spinner id,id2;
    String a,email,uid,nmbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.del_evidence);
        items.add("1");
        items.add("2");
        Bundle getName=getIntent().getExtras();
        a=getName.getString("user");
        email=getName.getString("mail");
        uid=getName.getString("id");
        nmbr=getName.getString("mobile");
        id =(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner, items);
        id.setAdapter(adapter);
        items1.add("1");
        items1.add("2");
        id2 =(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.spinner, items1);
        id2.setAdapter(adapter1);
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
            Intent cases8 = new Intent(delete_evdnc.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",email);
            cases8.putExtra("nmbr",nmbr);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(delete_evdnc.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
           // finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(delete_evdnc.this,change.class);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            //finish();
        }
        return true;

    }
}
