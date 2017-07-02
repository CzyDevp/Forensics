package com.example.anukoolsrivastav.forensics;
import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;
public class DBAdapter
        //extends SQLiteOpenHelper
{
    public static final String KEY_ROWID = "U_id";
    public static final String KEY_NAME = "Name";
    public static final String KEY_UNAME = "UName";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_MOBILENO = "MobileNo";
    public static final String KEY_EMAIL = "Email";
    //Database
    public static final String DATABASE_NAME = "ForensictDB.db";
    //Tabes
    public static final String DATABASE_TABLE = "Cases";
    public static final String TABLE_NAME = "User_img";
    public static final String DATABASE_TABLE1 = "User";
    public static final String DATABASE_TABLE2 = "Victim";
    public static final String DATABASE_TABLE3 = "Evidence";
    String tb1_c="Cases";
    String tb1_d="CaseUid";
    public static final int DATABASE_VERSION = 1;
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
       @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS User(U_id integer primary key autoincrement,Name text not null,Password text not null,MobileNo text not null,Email text not null)");
            db.execSQL("CREATE TABLE  IF NOT EXISTS Victim(V_id integer primary key autoincrement,Name text not null,Ssn integer not null,MobileNo text not null,Email text not null)");
            db.execSQL("CREATE TABLE  IF NOT EXISTS Cases(C_id integer primary key autoincrement,name text not null,type text not null,location text not null,status text not null,start text not null,Addtime text not null,Des text not null)");
            db.execSQL("CREATE TABLE  IF NOT EXISTS Evidence(E_id integer primary key autoincrement,C_id integer not null,Name text not null,U_id integer not null,Type text not null,Detail text not null,Image text null,time not null,date text not null,video text null)");
            db.execSQL("CREATE TABLE  IF NOT EXISTS User_img(U_id integer primary key,UName text,Image blob)");
            db.execSQL("CREATE TABLE  IF NOT EXISTS CaseUid(C_id integer not null,U_id integer not null)");
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_ROWID, "1");
            initialValues.put(KEY_NAME, "admin");
            initialValues.put(KEY_PASSWORD, "admin");
            initialValues.put(KEY_MOBILENO, "9872832353");
            initialValues.put(KEY_EMAIL, "navsingh191@gmail.com");
            db.insert(DATABASE_TABLE1, null, initialValues);
            //db.insert()
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
    public DBAdapter open() throws SQLiteException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close()

    {
        DBHelper.close();
    }


    public void insertRecord(String name, String pass, String no, String mail) {
       ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PASSWORD, pass);
        initialValues.put(KEY_MOBILENO, no);
        initialValues.put(KEY_EMAIL, mail);
        db.insert(DATABASE_TABLE1, null, initialValues);
    }


//insert user
    public long insertRecord1(String name, String pass, String no, String mail) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PASSWORD, pass);
        initialValues.put(KEY_MOBILENO, no);
        initialValues.put(KEY_EMAIL, mail);
        return db.insert(DATABASE_TABLE1, null, initialValues);
    }


    //Insert Case
    public long insertcase(String name, String type, String loc, String status, String start, String time, String dec) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("name", name);
        initialValues.put("type", type);
        initialValues.put("location", loc);
        initialValues.put("status", status);
        initialValues.put("start", start);
        initialValues.put("Addtime", time);
        initialValues.put("Des", dec);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    ///insert user id's'
    public long insertcaseusers(int cid, int uid)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put("C_id", cid);
        initialValues.put("U_id", uid);
      return db.insert("CaseUid", null, initialValues);
        //return a;
    }

    //insert evidence
    public long insertevd(int cd,int ud,String nm, String tp, String dtl,String img,String time,String date,String vid) {
        ContentValues initialValues = new ContentValues();
        initialValues.put("C_id",cd);
        initialValues.put("U_id",ud);
        initialValues.put("Type",tp);
        initialValues.put("Detail",dtl);
        initialValues.put("Image",img);
        initialValues.put("time",time);
        initialValues.put("Date",date);
        initialValues.put("video",vid);
        initialValues.put("Name",nm);
        return db.insert("Evidence", null, initialValues);
    }

    //get evidence id
    public String getevdid(String evdname) {
        Cursor cursor = db.query("Evidence", null, " Name=?", new String[]{evdname}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String id = cursor.getString(cursor.getColumnIndex("E_id"));
        cursor.close();
        return id;
    }



    //get  case id
    public String getcaseid(String cname) {
        String id=null;
        Cursor cursor = db.query("Cases", null, " Name=?", new String[]{cname}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            id=null;
        }
       // cursor.moveToFirst();
        cursor.moveToLast();
         id = cursor.getString(cursor.getColumnIndex("C_id"));
        cursor.close();
        return id;
    }
    public String getcaseidforevdns(String cname) {
        String id=null;
        String where = ("C_id" + "='" + cname + "'") + " OR " + ("name" + "='" + cname + "'");
        //Cursor cursor = db.query("Cases", null, " Name=?", new String[]{cname}, null, null, null);
        Cursor cursor = db.query(true, "Cases", new String[]{"C_id"}, where, null, null, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            id=null;
        }
        // cursor.moveToFirst();
        cursor.moveToFirst();
        id = cursor.getString(cursor.getColumnIndex("C_id"));
        cursor.close();
        return id;
    }

    //modify case
    public String getcaseidmdfy(String cname)
    {
        String id=cname;
        Cursor cursor = db.query("Cases", null, " Name=?", new String[]{cname}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
                   }
        else {
            cursor.moveToFirst();
            id = cursor.getString(cursor.getColumnIndex("C_id"));
            cursor.close();
        }
        return id;
    }

    public int getLastId() {
        int id = 0;
        final String MY_QUERY = "SELECT MAX(U_id)  FROM User";
        Cursor mCursor = db.rawQuery(MY_QUERY, null);
        try {
            if (mCursor.getCount() > 0) {
                mCursor.moveToFirst();
                id = mCursor.getInt(mCursor.getColumnIndex("U_id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        }
        return id;

    }

    public int getid1() {
        //final String MY_QUERY = "SELECT MAX(U_id) FROM User";
        String query = "SELECT MAX(U_id)  FROM User";
        final String MY_QUERY = "SELECT * FROM User ORDER BY U_id DESC LIMIT 1";
        Cursor mCursor = db.rawQuery(MY_QUERY, null);
        if (mCursor.getCount() > 0) {
            mCursor.moveToFirst();
            String id = mCursor.getString(mCursor.getColumnIndex("U_id"));
            int a = Integer.parseInt(id);
            mCursor.close();
            return a;
        }
        else
            return 0;
    }

    public String getcasesallotedusers(String uid) {
        //final String MY_QUERY = "SELECT MAX(U_id) FROM User";
String id="";
       // final String MY_QUERY = "SELECT C_id FROM CasesUid WHERE U_id ="+;
        String where = ("U_id" + "='" + uid + "'");
        Cursor iii = db.query(true, "CaseUid", new String[]{"C_id"}, where, null, null, null, null, null);
        if(iii!=null) {
            try {
                // Cursor allrows = db.rawQuery("SELECT * FROM User", null);
                if (iii.moveToFirst()) {
                    do {
                       // String ID = iii.getString(0);
                       // String Name = iii.getString(1);
                       // my_array.add(ID);
                       // my_array.add(Name);
                        id+=iii.getString(0)+",";
                    } while (iii.moveToNext());
                }
                iii.close();
            }
            catch (Exception e) {
            }
        }
        else
        {
           id="";
        }
        return id;
    }

    public String getid12(String userName) {
        Cursor cursor = db.query("User", null, " Name=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        //cursor.moveToFirst();
        cursor.moveToLast();
        String id = cursor.getString(cursor.getColumnIndex("U_id"));
        cursor.close();
        return id;
    }

    public void insert(byte[] bytes) {
        ContentValues cv = new ContentValues();
        cv.put("Image", bytes);
        // Log.e("inserted", "inserted");
        db.insert("User_Image", "Image", cv);
    }

    public String getSinlgeEntry(String userName) {
        //db.open();
        Cursor cursor = db.query("User", null, " Name=?", new String[]{userName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return password;
    }
    ///getting case id for insertting users

    public String getAllrecords(String id) {
        String where = ("C_id" + "='" + id + "'") + " OR " + ("name" + "='" + id + "'");
        Cursor m = db.query(true, "Cases", new String[]{"C_id"}, where, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();
            if (m.getCount() > 0) {
                id = m.getString(m.getColumnIndex("C_id"));
            } else {
                id = "";
            }
        }
        return id;
    }

    public Cursor getRecord(long rowid) throws SQLiteException {
        Cursor m = db.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO}, KEY_ROWID + "=" + rowid, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();
        }
        return m;
    }

    public String[] getRecord123(String rowid) throws SQLiteException {
        String name = "", mobile = "", email = "", id = "";
        String[] a = new String[4];
        String where = (KEY_ROWID + "='" + rowid + "'") + " OR " + (KEY_NAME + "='" + rowid + "'");
        Cursor m = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, where, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();
            if (m.getCount()>1)
            {
               // name = m.getString(m.getColumnIndex("Name"));
                a[0] = "k";
             //   mobile = m.getString(m.getColumnIndex("MobileNo"));
                a[1] = "k";
               // email = m.getString(m.getColumnIndex("Email"));
                a[2] = "k";
              //  id = m.getString(m.getColumnIndex("U_id"));
                a[3] = "k";
            }
          else  if (m.getCount() > 0 && m.getCount()==1) {
                name = m.getString(m.getColumnIndex("Name"));
                a[0] = name;
                mobile = m.getString(m.getColumnIndex("MobileNo"));
                a[1] = mobile;
                email = m.getString(m.getColumnIndex("Email"));
                a[2] = email;
                id = m.getString(m.getColumnIndex("U_id"));
                a[3] = id;
                // return a;
            }
            else
            {
                a[0] = "";
                a[1] = "";
                a[2] = "";
                a[3] = "";
            }
        }
        return a;
    }


    public String[] getRecordsrchcase(String rowid) throws SQLiteException {
        String name = "", idd = "", uid = "", type = "", status = "", started = "", des = "";
        String[] a = new String[7];
        String where = ("C_id" + "='" + rowid + "'") + " OR " + ("name" + "='" + rowid + "'");
        Cursor m = db.query(true, DATABASE_TABLE, new String[]{"C_id", "name", "type", "location", "status", "start", "Addtime", "Des"}, where, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();

            if (m.getCount() > 0 && m.getCount()==1) {
                idd = m.getString(m.getColumnIndex("C_id"));
                a[0] = idd;
                name = m.getString(m.getColumnIndex("name"));
                a[1] = name;
//           uid = m.getString(m.getColumnIndex("U_id"));
                //     a[2] = uid;
                type = m.getString(m.getColumnIndex("type"));
                a[2] = type;
                status = m.getString(m.getColumnIndex("status"));
                a[3] = status;
                started = m.getString(m.getColumnIndex("start"));
                a[4] = started;
                des = m.getString(m.getColumnIndex("Des"));
                a[5] = des;                // return a;
            }
            else if(m.getCount()>1)
            {
                a[0] = "k";
                a[1] = "k";
                a[2] = "k";
                a[3] = "k";
                a[4] = "k";
                a[5] = "k";
                a[6] = "k";
            }
            else
            {
                a[0] = "";
                a[1] = "";
                a[2] = "";
                a[3] = "";
                a[4] = "";
                a[5] = "";
                a[6] = "";
            }
        }
        return a;
    }

    //Investigators of case
    public Cursor getusercase(int rowid) throws SQLiteException {
      //  String name = "", idd1 = "", uid = "", type = "", status = "", started = "", des = "";
        String where = ("C_id" + "='" + rowid + "'");
        Cursor idd = db.query(true, "CaseUid", new String[]{"C_id", "U_id"}, where, null, null, null, null, null);
        return idd;

    }

    //for delete case
    public String[] getRecordsrchcase1(String rowid) throws SQLiteException {
        String name = "", idd = "", uid = "", type = "", status = "", started = "", cid = "";
        String[] a = new String[7];
        String where = ("C_id" + "='" + rowid + "'") + " OR " + ("name" + "='" + rowid + "'");
        Cursor m = db.query(true, DATABASE_TABLE, new String[]{"status","C_id","type","name"}, where, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();
            if (m.getCount() > 0 && m.getCount()==1) {
                idd = m.getString(m.getColumnIndex("status"));
                cid = m.getString(m.getColumnIndex("C_id"));
                name = m.getString(m.getColumnIndex("name"));
                type = m.getString(m.getColumnIndex("type"));
                a[0] = idd;
                a[1] = name;
                a[2] = cid;
                a[3] = type;
                // return a;
            }
            else if(m.getCount()>1)
            {
                a[0] = "k";
                a[1] = "k";
                a[2] = "k";
                a[3] = "k";
            }
                        else
            {
                a[0] = "";
                a[1] = "";
                a[2] = "";
                a[3] = "";
            }
        }
        return a;
    }
//get user id for assiging case to user by name

public String getcase(String rowid) throws SQLiteException {
    String name = "", mobile = "", email = "", id = "";
     String where = ("C_id" + "='" + rowid + "'") + " OR " + ("name" + "='" + rowid + "'");
    Cursor m = db.query(true,"Cases", new String[]{"name"}, where, null, null, null, null, null);
    if (m != null) {
        m.moveToFirst();
        if (m.getCount()==1) {
            id = m.getString(m.getColumnIndex("name"));
            Log.d("Total Records ", m.getCount() + "");
        }
        else if(m.getCount()==0)
        {
            id = "";
            Log.d("Total Records ", m.getCount() + "");
        }
        else
        {
            id="More then 1 Account exist with Same Name";
            Log.d("Total Records ", m.getCount() + "");
        }
    }
    return id;
}

    public String getRecordcase(String rowid) throws SQLiteException {
        String name = "", mobile = "", email = "", id = "";
        // String[] a=new String[4];
        // String  where=(KEY_ROWID + "='"+ rowid+"'")+" OR "+(KEY_NAME + "='"+ rowid+"'" );
        String where = (KEY_ROWID + "='" + rowid + "'") + " OR " + (KEY_NAME + "='" + rowid + "'");
        Cursor m = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID}, where, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();
            if (m.getCount() > 0) {
                id = m.getString(m.getColumnIndex("U_id"));
            } else {
                id = "";
            }
        }
        return id;
    }

    public Cursor getRecord12(String rowid) throws SQLiteException {
        String where = (KEY_ROWID + "='" + rowid + "'") + " OR " + (KEY_NAME + "='" + rowid + "'");
        Cursor m = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, where, null, null, null, null, null);
        if (m != null) {
            if(m.getCount()==0) {
            m=null;
            }
            else {
                m.moveToFirst();
            }
        }
        return m;
    }

    public String getRecordEmail(String email) throws SQLiteException {
        String mail = null;
        Log.d("getRecordEmail", "21");
        Cursor m = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_EMAIL + "='" + email + "'", null, null, null, null, null);
        Log.d("getRecordEmail", "22");
        Log.d("Cursor is ", m.toString());
        if (m != null) {
            Log.d("getRecordEmail", "23");
            m.moveToFirst();
            Log.d("getRecordEmail", "24");
            Log.d("Column Index is ", m.getColumnIndex("Email") + "");
            int index = m.getColumnIndex("Email");
            Log.d("Total Records ", m.getCount() + "");
            if (m.getCount() == 0) {
                mail = "";
            } else {
                mail = m.getString(index);
            }
            Log.d("getRecordEmail", "25");
            Log.d("getRecordEmail", mail);
        }
        return mail;
    }

    public String getRecordEmailUP(String email, String ddi) throws SQLiteException {
        String mail = null;
        String where = (KEY_ROWID + "!='" + ddi + "'") + " AND " + (KEY_NAME + "!='" + ddi + "'") + " AND " + (KEY_EMAIL + "='" + email + "'");
        Log.d("getRecordEmail", "21");
        Cursor m = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, where, null, null, null, null, null);
        Log.d("getRecordEmail", "22");
        Log.d("Cursor is ", m.toString());
        if (m != null) {
            Log.d("getRecordEmail", "23");
            m.moveToFirst();
            Log.d("getRecordEmail", "24");
            Log.d("Column Index is ", m.getColumnIndex("Email") + "");
            int index = m.getColumnIndex("Email");
            Log.d("Total Records ", m.getCount() + "");
            if (m.getCount() == 0) {
                mail = "";
            } else {
                mail = m.getString(index);
            }
            Log.d("getRecordEmail", "25");
            Log.d("getRecordEmail", mail);
        }
        return mail;
    }

    public Cursor getRecordEmail1(String email) throws SQLiteException {
        Cursor m = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_EMAIL + "='" + email + "'", null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();

        }
        return m;
    }

    public String getRecordMobile(String mobile) throws SQLiteException {
        String mobile1 = null;
        Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_MOBILENO + "='" + mobile + "'", null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            int index = m1.getColumnIndex("MobileNo");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                mobile1 = "";
            } else {
                mobile1 = m1.getString(index);
            }
            Log.d("getRecordMobile", "25");
            Log.d("getRecordMobile", mobile1);
        }
        return mobile1;
    }
//mobile record for updating
    public String getRecordMobileUP(String mobile, String ddi) throws SQLiteException {
        String mobile1 = null;
        String where = (KEY_ROWID + "!='" + ddi + "'") + " AND " + (KEY_NAME + "!='" + ddi + "'") + " AND " + (KEY_MOBILENO + "='" + mobile + "'");
        Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordMobile", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            int index = m1.getColumnIndex("MobileNo");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                mobile1 = "";
            } else {
                mobile1 = m1.getString(index);
            }
            Log.d("getRecordMobile", "25");
            Log.d("getRecordMobile", mobile1);
        }
        return mobile1;
    }

    //
//    //delete record by id
    public int deleteRecord(String rowid) {
        int b = 0;
        boolean b1=false;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = (KEY_ROWID + "='" + rowid + "'") + " OR " + (KEY_NAME + "='" + rowid + "'");
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, where, null, null, null, null, null);

        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            //   int index=m1.getColumnIndex("MobileNo");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                // mobile1="";
                b=0;
            }
            else if(m1.getCount()==1)
            {
                String where2 = (KEY_ROWID + "='" + rowid + "'") + " OR " + (KEY_NAME + "='" + rowid + "'");
                b1 = db.delete("User", where2, null) > 0;
                if(b1)
                {
                    b=1;
                }
            }
            else
            {
                b=2;
            }
        }
        return b;
    }


    //delete case
    public int deleteRecordcase(String rowid) {
      int b =0,c=0;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = ("C_id" + "='" + rowid + "'") + " OR " + ("name" + "='" + rowid + "'");
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true, DATABASE_TABLE, new String[]{"C_id", "name", "type", "location", "status", "start", "Addtime", "Des"}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                // mobile1="";
                b=0;
            }
            else
            {
                if(m1.getCount()>1)
                {
                    b=2;
                    Log.d("total records"+m1.getCount(),"27");
                }
                else
                {
                    boolean b1;
                    c = deletecaseusers(m1.getString(0));
                    String where2 = ("C_id" + "='" + rowid + "'") + " OR " + ("name" + "='" + rowid + "'");
                    b1 = db.delete("Cases", where2, null) > 0;
                    if(b1)
                    b=1;
                }
            }
        }
        return b;
    }

    //deleting u_id's related to case
    public int deletecaseusers(String rowid) {
        int b = 0;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = ("C_id" + "='" + rowid + "'");
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true, "caseUid", new String[]{"C_id", "U_id"}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("C_id") + "");
            //   int index=m1.getColumnIndex("MobileNo");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                b=0;
            }
            else
            {
                boolean b1;
                String where2 = ("C_id" + "='" + rowid + "'");
                b1 = db.delete("CaseUid", where2, null) > 0;
                if(b1);
                b=1;
            }
        }
        return b;
    }
//delete image
    public int deleteRecordimg(String rowid) {
      int b = 0;
      boolean b1=false;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = (KEY_ROWID + "='" + rowid + "'") + " OR " + (KEY_UNAME + "='" + rowid + "'");
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true, TABLE_NAME, new String[]{KEY_ROWID, KEY_UNAME, "Image"}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            //   int index=m1.getColumnIndex("MobileNo");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0)
            {
              b=0;
            } else if(m1.getCount()==1)
            {
                String where2 = (KEY_ROWID + "='" + rowid + "'") + " OR " + (KEY_UNAME + "='" + rowid + "'");
                // b= db.delete("User", "U_id" + "=" + rowid, null) > 0;
                b1 = db.delete("User_img", where2, null) > 0;
               if(b1)
               {
                   b=1;
               }
            }
            else
            {
                b=2;
            }
        }
        return b;
    }
//updating user
    public int updateRecord(String id, String Name, String MobileNo, String Email) {
        int f = 0;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = (KEY_ROWID + "='" + id + "'") + " OR " + (KEY_NAME + "='" + id + "'");
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            //   int index=m1.getColumnIndex("MobileNo");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                f=0;
            } else if(m1.getCount()==1) {
                String where2 = (KEY_ROWID + "='" + id + "'") + " OR " + (KEY_NAME + "='" + id + "'");
                // b= db.delete("User", "U_id" + "=" + rowid, null) > 0;
                ContentValues initialValues = new ContentValues();
                initialValues.put("Name", Name);
                initialValues.put("MobileNo", MobileNo);
                initialValues.put("Email", Email);
                //if (c!=null) {
                f = db.update("User", initialValues, where2, null);
            }
            else
            {
                f=2;
            }
        }
        return f;
    }
    //updatepassword
    public int updatepass(String id, String pass) {
        int f = 0;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = (KEY_ROWID + "='" + id + "'") + " OR " + (KEY_NAME + "='" + id + "'");
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            //   int index=m1.getColumnIndex("MobileNo");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                return f;
            }
            else
            {
                String where2 = (KEY_ROWID + "='" + id + "'") + " OR " + (KEY_NAME + "='" + id + "'");
                // b= db.delete("User", "U_id" + "=" + rowid, null) > 0;
                ContentValues initialValues = new ContentValues();
                initialValues.put("Password",pass);
               //if (c!=null) {
                f = db.update("User", initialValues, where2, null);
            }
        }
        return f;
    }
//updating Record case
    public int updateRecordCase(String id, String status, String des) {
        int f = 0;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = ("C_id" + "='" + id + "'") + " OR " + ("name" + "='" + id + "'");
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true, DATABASE_TABLE, new String[]{"C_id", "name", "type", "location", "status", "start", "Addtime", "Des"}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
            Log.d("getRecordEmail", "26");
            Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                // mobile1="";
               f=0;
            }
            else if(m1.getCount()==1) {
                String where2 = ("C_id" + "='" + id + "'") + " OR " + ("name" + "='" + id + "'");
                // b= db.delete("User", "U_id" + "=" + rowid, null) > 0;
                ContentValues initialValues = new ContentValues();
                initialValues.put("status", status);
                initialValues.put("Des", des);
                f = db.update("Cases", initialValues, where2, null);
                deletecaseusers(m1.getString(0));
            }
            else
            {
                f=2;
            }
        }
        return f;
    }
//Storing image of user
    public String saveInDB(String id1, byte[] pic, String UName) {
        String id = id1;
        String back = "done";
        ContentValues newValues = new ContentValues();
        newValues.put("U_id", id);
        newValues.put("UName", UName);
        //Blob image = pic;
        newValues.put("Image",pic);
        long ret = db.insert(TABLE_NAME, null, newValues);
        if (ret <= 0) {

            back = "Error";
        }
        return back;
    }
//get user image path
    public byte[] readFromDB(String idd) {
        String path = "";
        String id = idd;
        String KEY_NAME = "U_id";
        byte[] image=null;
        String where = (KEY_NAME + "='" + idd + "'") + " OR " + (KEY_UNAME + "='" + idd + "'");
        Cursor m = db.query(true, TABLE_NAME, new String[]{"Image"}, where, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();
            if (m.getCount() > 0) {
              //  path = m.getString(m.getColumnIndex("Image"));
                 image = m.getBlob(0);
            } else {
               image=null;
            }
        }

        m.close();
        return image;
    }
//get all case with name and id's
    public ArrayList<String> getTableValuescase() {
        ArrayList<String> my_array = new ArrayList<String>();
        //String con;
        try {
            Cursor allrows = db.rawQuery("SELECT * FROM Cases", null);
            if (allrows.moveToFirst()) {
                do {
                    String ID = allrows.getString(0);
                    String Name = allrows.getString(1);
                    my_array.add(ID);
                    my_array.add(Name);
                } while (allrows.moveToNext());
            }
            allrows.close();
          //  db.close();
        } catch (Exception e) {
        }
        return my_array;
    }
  // list All cases with name and id's for admin
    public ArrayList<String> getTableValuescase1() {
        ArrayList<String> my_array = new ArrayList<String>();
        String con;
        try {
            Cursor allrows = db.rawQuery("SELECT * FROM Cases", null);
            if (allrows.moveToFirst()) {
                do {
                    String ID = allrows.getString(0);
                    String Name = allrows.getString(1);
                    con= "Id "+ID+"\nName "+Name;
                    my_array.add(con);
                    //my_array.add(Name);
                } while (allrows.moveToNext());
            }
            allrows.close();
            //  db.close();
        } catch (Exception e) {
        }
        return my_array;
    }
//adding cases with name and id
   public ArrayList<String> getTableValues12() {
        ArrayList<String> my_array = new ArrayList<String>();

        try {
            Cursor allrows = db.rawQuery("SELECT * FROM User", null);
            if (allrows.moveToFirst()) {
                do {
                    String ID = allrows.getString(0);
                    String Name = allrows.getString(1);
                    my_array.add(ID);
                    my_array.add(Name);
                } while (allrows.moveToNext());
            }
            allrows.close();
        } catch (Exception e) {

        }
        return my_array;
    }
///get all user with id and name
public ArrayList<String> getuserall() {
    ArrayList<String> my_array = new ArrayList<String>();

    try {
        Cursor allrows = db.rawQuery("SELECT * FROM User", null);
        String con;
        if (allrows.moveToFirst()) {
            do {
                String ID = allrows.getString(0);
                String Name = allrows.getString(1);
               // my_array.add(ID);
                con="Id "+ID+"\nName "+Name;
                my_array.add(con);
            } while (allrows.moveToNext());
        }
        allrows.close();
    } catch (Exception e) {

    }
    return my_array;
}


    public String getjoinrecord(String id) {
        String path="";
       // String where = ("C_id" + "='" + id + "'") + " OR " + ("name" + "='" + id + "'");
       //Cursor m = db.query(true, "Cases", new String[]{"C_id"}, where, null, null, null, null, null);
              //Cursor cursor = db.rawQuery("SELECT Cases.name, " +
              //  "CaseUid.C_id " +
              //  "FROM Cases JOIN CaseUid ON Casess.C_id =  CaseUid.C_id WHERE CaseUid.U_id = '" + id + "'", null);
       // cursor.moveToFirst();
      //  Cursor cursor = db.rawQuery("SELECT   DISTINCT Cases.name, " +
       //         "FROM Cases INNER JOIN CaseUid ON Casess.C_id =  CaseUid.C_id WHERE CaseUid.U_id = '" + id + "'", null);
      //  if (cursor != null) {
        //    cursor.moveToFirst();
          //  if (cursor.getCount() > 0) {
            //    path = cursor.getString(Integer.parseInt(cursor.getColumnName(0)));
          //} else {
             //   path = "";
            //}
       // }
        //
       return id;
    }



    public Cursor getJoinedInfo(String lookingFor)
    {
        ArrayList<String> my_array = new ArrayList<String>();
        Cursor cursor=null;
        String query;
        query="SELECT " +   " Cases.name,Cases.C_id" +  " FROM Cases " +  " JOIN CaseUid  ON CaseUid.C_id = Cases.C_id" + " WHERE CaseUid.U_id ='" + lookingFor +"'";
       cursor=db.rawQuery(query,null);
        //Log.d(TAG, "DB: query complete");
        return cursor;
            }
    //user case names +id
    public ArrayList<String> getcasenames(String uid)
    {
    Cursor iii=getJoinedInfo(uid);
    ArrayList<String> my_array = new ArrayList<String>();
    if(iii!=null) {
        try {
            // Cursor allrows = db.rawQuery("SELECT * FROM User", null);
            if (iii.moveToFirst()) {
                do {
                    String ID = iii.getString(0);
                    String Name = iii.getString(1);
                    my_array.add(ID);
                    my_array.add(Name);
                } while (iii.moveToNext());
            }
            iii.close();
        }
        catch (Exception e) {
        }
    }
    else
    {
        my_array.add("sorry");
    }
    return my_array;
}
    public ArrayList<String> getcasenameslist(String uid)
    {
        Cursor iii=getJoinedInfo(uid);
        ArrayList<String> my_array = new ArrayList<String>();
        String con;
        if(iii!=null) {
            try {
                // Cursor allrows = db.rawQuery("SELECT * FROM User", null);
                if (iii.moveToFirst()) {
                    do {
                        String ID = iii.getString(1);
                        String Name = iii.getString(0);
                        con="Id "+ID+"\nName "+Name;
                       // my_array.add(ID);
                        my_array.add(con);
                    } while (iii.moveToNext());
                }
                iii.close();
            }
            catch (Exception e) {
            }
        }
        else
        {
            my_array.add("sorry");
        }
        return my_array;
    }

    //get user particular evidences
    public ArrayList<String> getevdnsbyuser(String csname,int ud)
    {
    //    Cursor iii=getJoinedInfo(uid);
        String  cd;
        cd=getcaseidforevdns(csname);
        String where = ("C_id" + "='" + cd + "'") + " AND " + ("U_id" + "='" + ud + "'");
        ArrayList<String> my_array = new ArrayList<String>();
        Cursor m1 = db.query(true,"Evidence", new String[]{"E_id","Name"}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
             if (m1.getCount() == 0) {
              my_array.add(null);
            }
            else
            {
                do {
                    my_array.add(m1.getString(0));
                    my_array.add(m1.getString(1));
                } while (m1.moveToNext());
              Log.d("Total Records ", m1.getCount() + "");
             }
        }
           return my_array;
    }
           //get evidence all detail

    public Cursor getevidencedetail(String ed,int ud) throws SQLiteException {
      // int cd;
     // cd=Integer.parseInt(getcaseid(csname));
           String where = ("E_id" + "='" + ed + "'") +" OR " + ("name" + "='" + ed + "'")+ " AND " + ("U_id" + "='" + ud + "'");
     //   String where = ("E_id" + "='" + ed  + "'");
        Cursor m = db.query(true,"Evidence", new String[]{"E_id","Name","Detail","Image","video"}, where, null, null, null, null, null);
        if (m != null) {
            if(m.getCount()==0) {
                m=null;
            }
            else
            {
                m.moveToFirst();
            }
        }
        return m;
    }

    //get case evidencesby user an d case

    public String getevdncbyuser(String cd,int ud) throws SQLiteException {
        // int cd;
        String evds="";
        // cd=Integer.parseInt(getcaseid(csname));
        String where = ("C_id" + "='" + cd + "'")+ " AND " + ("U_id" + "='" + ud + "'");
        //   String where = ("E_id" + "='" + ed  + "'");
        Cursor m = db.query(true,"Evidence", new String[]{"E_id"}, where, null, null, null, null, null);
        if (m != null) {
            m.moveToFirst();
            if(m.getCount()==0) {
                m=null;
                evds="Not Any Evidence Added by You";
            }
            else
            {

                do {
                   evds+=m.getString(0)+",";
                    Log.d("Total Records ", m.getCount() + "");
                    Log.d("Total Records ", evds + "");
                } while (m.moveToNext());
            }
        }
        return evds;
    }

//for  updating evidence gettingexisting media info
public String getevdncup(String cd,int ud) throws SQLiteException {
    // int cd;
    String evds="",det="";
    // cd=Integer.parseInt(getcaseid(csname));
    String where = ("E_id" + "='" + cd + "'")+ " AND " + ("U_id" + "='" + ud + "'");
    //   String where = ("E_id" + "='" + ed  + "'");
    Cursor m = db.query(true,"Evidence", new String[]{"Image","video"}, where, null, null, null, null, null);
    if (m != null) {
        m.moveToFirst();
        if(m.getCount()==0) {
            //m=null;
            evds="";
        }
        else
        {
                if(m.getString(0).toString().equals(""))
                {
                    evds=m.getString(1);
                    //det=m.getString(2);
                }
            else if(m.getString(1).toString().equals(""))
                {
                    evds=m.getString(0);
                  //  det=m.getString(2);
                }
            else
                {
                    evds="";
                    //det=m.getString(2);
                }
        }
    }
    Log.d("media", evds + "");
    return evds;
}
    //updating Evidence Media and Detail
    public int updateEvidence(String ed, String media1, String img,String detl) {
        int f = 0;
        //  return db.delete("User", "U_id" + "=" + rowid, null) > 0;
        String where = ("E_id" + "='" + ed + "'")+ " OR " + ("Name" + "='" + ed + "'");;
        // Cursor m1 = db.query(true, DATABASE_TABLE1, new String[]{KEY_ROWID, KEY_NAME, KEY_PASSWORD, KEY_MOBILENO, KEY_EMAIL}, KEY_ROWID + "='" + rowid + "'", null, null, null, null, null);
        Cursor m1 = db.query(true,"Evidence", new String[]{"E_id","Detail"}, where, null, null, null, null, null);
        if (m1 != null) {
            m1.moveToFirst();
           // Log.d("getRecordEmail", "26");
            //Log.d("Column Index is ", m1.getColumnIndex("MobileNo") + "");
            //   int index=m1.getColumnIndex("MobileNo");
            //Log.d("Total Records ", m1.getCount() + "");
            if (m1.getCount() == 0) {
                return f;
            }
            else
            {
                String exst=m1.getString(1);
                String where2 = ("E_id"+ "='" + ed + "'");
                // b= db.delete("User", "U_id" + "=" + rowid, null) > 0;
                ContentValues initialValues = new ContentValues();
              //  initialValues.put("Detail",detl);
                initialValues.put("Image",img);
                initialValues.put("video",media1);
                //if (c!=null) {
                exst+="/"+detl;
                initialValues.put("Detail",exst);
               // db.execSQL("UPDATE Evidence SET Detail = "+exst+" WHERE id=6 ");
                f = db.update("Evidence", initialValues, where2, null);
            }
        }
        return f;
    }


}

