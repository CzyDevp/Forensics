package com.example.anukoolsrivastav.forensics;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.text.style.ForegroundColorSpan;
import android.text.SpannableString;

public class admin extends ActionBarActivity
{
    String a="",mail0,uid,nmbr;
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
          setContentView(R.layout.activity_admin);
          Button newcase = (Button) findViewById(R.id.new_case);
          Button viewcases = (Button) findViewById(R.id.view_case);
          Button modifycases = (Button) findViewById(R.id.update_case);
          Button deletecases = (Button) findViewById(R.id.delete_case);
          Button newusers = (Button) findViewById(R.id.new_user);
          Button viewusers = (Button) findViewById(R.id.search_user);
          Button modifyusers = (Button) findViewById(R.id.update_user);
          Button deleteusers = (Button) findViewById(R.id.delete_user);
       // Button chngepwd = (Button) findViewById(R.id.change_pwd);
        //TextView tvName= (TextView) findViewById(R.id.name);
      //  String dName;
          Bundle getName=getIntent().getExtras();
          a=getName.getString("UserName");
          mail0=getName.getString("mail");
          uid=getName.getString("id");
          nmbr=getName.getString("mobile");
       // tvName.setText(dName);
          newcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent case1 = new Intent(admin.this, addcase.class);
                case1.putExtra("user",a);
                case1.putExtra("id",uid);
                case1.putExtra("mail",mail0);
                case1.putExtra("mobile",nmbr);
                startActivity(case1);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                //finish();
            }
        });
          viewcases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases2 = new Intent(admin.this, viewcase.class);
                cases2.putExtra("user",a);
                cases2.putExtra("id",uid);
                cases2.putExtra("mail",mail0);
                cases2.putExtra("mobile",nmbr);
                startActivity(cases2);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
          modifycases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases3 = new Intent(admin.this, modifycase.class);
                cases3.putExtra("user",a);
                cases3.putExtra("id",uid);
                cases3.putExtra("mail",mail0);
                cases3.putExtra("mobile",nmbr);
                startActivity(cases3);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
          deletecases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases4 = new Intent(admin.this, deletecase.class);
                cases4.putExtra("user",a);
                cases4.putExtra("id",uid);
                cases4.putExtra("mail",mail0);
                cases4.putExtra("mobile",nmbr);
                startActivity(cases4);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
          newusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases5 = new Intent(admin.this, newuser.class);
                cases5.putExtra("user",a);
                cases5.putExtra("id",uid);
                cases5.putExtra("mail",mail0);
                cases5.putExtra("mobile",nmbr);
                startActivity(cases5);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
          viewusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases6 = new Intent(admin.this, searchuser.class);
                cases6.putExtra("user",a);
                cases6.putExtra("id",uid);
                cases6.putExtra("mail",mail0);
                cases6.putExtra("mobile",nmbr);
                startActivity(cases6);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });

          modifyusers.setOnClickListener(new View.OnClickListener()
          {
            @Override
            public void onClick(View v) {
                Intent cases7 = new Intent(admin.this, updateUser.class);
                cases7.putExtra("user",a);
                cases7.putExtra("id",uid);
                cases7.putExtra("mail",mail0);
                cases7.putExtra("mobile",nmbr);
                startActivity(cases7);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
         deleteusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases8 = new Intent(admin.this, deleteuser.class);
                cases8.putExtra("user",a);
                cases8.putExtra("id",uid);
                cases8.putExtra("mail",mail0);
                cases8.putExtra("mobile",nmbr);
                startActivity(cases8);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });


       /*chngepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cases9 = new Intent(admin.this, myaccnt.class);
                startActivity(cases9);
            }
        });*/

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
        //tv.setText("Six Clicked");
        return true;
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId()==1)
        {
            Intent cases8 = new Intent(admin.this,myaccnt.class);
            cases8.putExtra("user",a);
            cases8.putExtra("id",uid);
            cases8.putExtra("mail",mail0);
            cases8.putExtra("nmbr",nmbr);
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }
        if(item.getItemId()==3)
        {
            Intent cases8 = new Intent(admin.this,Login.class);
            cases8.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK );
            startActivity(cases8);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
           // finish();
        }
        if(item.getItemId()==2)
        {
            Intent cases8 = new Intent(admin.this,change.class);
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
