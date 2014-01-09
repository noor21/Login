package com.noor.login;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database1<DataAdapter> {
	public static String KEY_FNAME="fname";
	public static String KEY_LNAME="lname";
	public static String KEY_UNAME="uname";
	public static String KEY_PWD="pwd";
	
	
	public static  String DB_NAME="SignupDetails";
	public static  String DB_TABLE="signup";
	public static  int DB_VERSION=4;
	private static String DB_CREATE= "create table signup(fname text not null,"+" lname text not null,"+" uname text not null,"+" pwd text not null);";
	 private final Context context; 
	 private DatabaseHelper DBHelper;
	 private SQLiteDatabase Sb;
	 public Database1(Context ctx) 
	    {
	        this.context = ctx;
	        DBHelper = new DatabaseHelper(context);
	    }
	 private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase Sb) {
			// TODO Auto-generated method stub
		Sb.execSQL(DB_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase Sb, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Sb.execSQL("DROP TABLE IF EXISTS Lagahare");
			onCreate(Sb);
		}
		 
	 }//---opens the database---
	 public Database1<DataAdapter> open() throws SQLException
	 {
		 Sb=DBHelper.getWritableDatabase();
		 return this;
	 }
	 //---closes the database--- 
	 public void close() {
		DBHelper.close();
		
}//---insert a title into the database---
 public long InsertTitles(String fname,String lname,String uname,String pwd ){
	 ContentValues cv= new ContentValues();
	 cv.put(KEY_FNAME,fname);
	 cv.put(KEY_LNAME,lname);
	 cv.put(KEY_UNAME,uname);
	 cv.put(KEY_PWD,pwd);
	 
	return Sb.insert(DB_TABLE, null, cv);
	  }
//---deletes a particular title---
// public boolean deleteTitle(long rowId){
	//return Sb.delete(DB_TABLE, KEY_NO+"="+rowId, null)>0;
	 
 //}
 //---retrieves all the titles---
 public Cursor getAllTitles(){
	return Sb.query(DB_TABLE, new String[]{KEY_FNAME,KEY_LNAME,KEY_UNAME,KEY_PWD},null, null, null, null, null);
	 
 }
 //---retrieves a particular title---
 public Cursor getTitle(long rowId){
	// return Sb.query(DB_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy)
	 Cursor cc=Sb.query(true,DB_TABLE, new String[]{KEY_FNAME,KEY_LNAME,KEY_UNAME,KEY_PWD},KEY_FNAME+"="+rowId,null,null,null,null,null);
	 if(cc!=null){
		 cc.moveToFirst();
	 }
	return cc;
 }
 //---updates a title---
 public boolean updateTitles(String fname,String lname,String uname,String pwd){
	 ContentValues args=new ContentValues();
	 args.put(KEY_FNAME,fname);
	 args.put(KEY_LNAME,lname);
	 args.put(KEY_UNAME,uname);
	 args.put(KEY_PWD,pwd);
	
	return Sb.update(DB_TABLE,args, KEY_FNAME,null)>0;
	 
 }
}