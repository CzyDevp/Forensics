package com.example.czydevp.forensics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by Anukool Srivastav on 26-09-2014.
 */
public class Investigator extends Activity
{
    String a="",email,uid,nmbr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.investigator);
        Button btn1=(Button) findViewById(R.id.newev);
        Button btn2=(Button) findViewById(R.id.view_eve);
        Button btn3=(Button) findViewById(R.id.update);
    //    Button btn4=(Button) findViewById(R.id.delete_eve);
        Button btn5=(Button) findViewById(R.id.srchcase);
        Bundle  getName=getIntent().getExtras();
               a=getName.getString("UserName");
               email=getName.getString("mail");
               uid=getName.getString("id");
               nmbr=getName.getString("mobile");
       // Toast.makeText(getApplication()," "+a+email+uid+nmbr,Toast.LENGTH_LONG).show();
          btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases=new Intent(Investigator.this, AddEvidence.class);
                cases.putExtra("user",a);
                cases.putExtra("id",uid);
                cases.putExtra("mail",email);
                cases.putExtra("mobile",nmbr);
                startActivity(cases);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases=new Intent(Investigator.this, SearchEvidence.class);
                cases.putExtra("user",a);
                cases.putExtra("id",uid);
                cases.putExtra("mail",email);
                cases.putExtra("mobile",nmbr);
                startActivity(cases);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases=new Intent(Investigator.this, UpdateEvidence.class);
                cases.putExtra("user",a);
                cases.putExtra("id",uid);
                cases.putExtra("mail",email);
                cases.putExtra("mobile",nmbr);
                startActivity(cases);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
      /*  btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases=new Intent(Investigator.this, DeleteEvidence.class);
                cases.putExtra("user",a);
                cases.putExtra("id",uid);
                cases.putExtra("mail",email);
                cases.putExtra("mobile",nmbr);
                startActivity(cases);
            }
        });*/

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases=new Intent(Investigator.this, ViewCase1.class);
                cases.putExtra("user",a);
                cases.putExtra("id",uid);
                cases.putExtra("mail",email);
                cases.putExtra("mobile",nmbr);
                startActivity(cases);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
          }
    public boolean onCreateOptionsMenu(Menu menu) {
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
            Intent cases8 = new Intent(Investigator.this,MyAccount.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",email);
            cases8.putExtra("nmbr",nmbr);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(Investigator.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
          //  finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(Investigator.this,Change.class);
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
