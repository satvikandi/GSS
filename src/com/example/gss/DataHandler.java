package com.example.gss;

import java.sql.SQLException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHandler {

	public static final String first_name = "firstname";
	public static final String last_name = "lastname";
	public static final String uname = "username";
	public static final String passwd = "password";
	public static final String email = "emailid";
	public static final String SAMPLE_TABLE_NAME = "user_details";
	public static final String item_description = "itemdescription";
	public static final String item_name = "itemname";
	public static final String item_image_tag = "imagetag";
	// public static final String user_name = "userid";
	public static final String SAMPLE_TABLE_NAME_1 = "item_description_details";
	public static final String SAMPLE_TABLE_NAME_2 = "user_shopping_list";
	public static final String item_cost = "itemcost";
	public static final String db_name_1 = "item_description_db"; 
	public static final String db_name = "user_db"; 
	public static final String item_id = "itemid";
	public static final int db_version = 8; 
	
	public static final String table_create = "create table user_details(firstname text not null,lastname text,username varchar not null,password varchar not null,emailid varchar not null);";
	public static final String table_create_1 = "CREATE TABLE item_description_details(itemid varchar not null,itemname text not null , itemdescription text,itemcost integer(4,2));";
	public static final String table_create_2 = "CREATE TABLE user_shopping_list(username varchar not null, imagetag text not null);";  
	
	DataBaseHelper dbhelper;
	Context ctx;
	SQLiteDatabase db;
	
	public DataHandler(Context c)
	{
		ctx=c;
		dbhelper = new DataBaseHelper(ctx);
	}
		
	private static class DataBaseHelper extends SQLiteOpenHelper
	{

		public DataBaseHelper(Context ctxt) 
		{
			super(ctxt,db_name,null,db_version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			try
			{
				db.execSQL(table_create);
				db.execSQL(table_create_1);
				db.execSQL(table_create_2);
			}
			catch(SQLiteException e)
			{
				e.printStackTrace();
			}
		}

		
		
	//	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
	//		db.execSQL("Drop table if exists user_details");
	//		db.execSQL("DROP TABLE IF EXISTS item_description_details");
	//		onCreate(db);
			
		}
	
		
		}
	
	public DataHandler open()
	{
		db = dbhelper.getWritableDatabase();
		return this;
	}
	
	public void close()
	{
		dbhelper.close();
	}
	
	public long insertUserList(String usrnm,String tag)
	{
		ContentValues content = new ContentValues();
		content.put(uname,usrnm);
		content.put(item_image_tag,tag);
		return db.insertOrThrow(SAMPLE_TABLE_NAME_2, null, content);
	}
	
	public long insertUserData(String fname,String lname,String usrname,String pwd,String emailid)
	{
		ContentValues content = new ContentValues();
		content.put(first_name,fname);
		content.put(last_name,lname);
		content.put(uname,usrname);
		content.put(passwd,pwd);
		content.put(email,emailid);
		return db.insertOrThrow(SAMPLE_TABLE_NAME, null,content);
		
	}
	
	public Cursor returnData()
	{
		return db.query(SAMPLE_TABLE_NAME,new String[] {first_name,last_name,uname,passwd,email},null,null,null,null,null);
	}
	
public Cursor returnData(String x) {
		
				Cursor c;
				final String select_query = "Select " + item_name + "," + item_cost + " from " + SAMPLE_TABLE_NAME_1 + " where itemid =" + x + ";";
				return c = db.rawQuery(select_query,null);
}

	public long insertItemData()
	{
		ContentValues content = new ContentValues();
		content.put(item_name,"Apple");
		content.put(item_description,"Rating : Very Good. Desciption : This item is Manufactured by Fruits Product Company inc. Origin of the product is from the fields of Natick in MA. They are best quality and very healthy.");

		return db.insertOrThrow(SAMPLE_TABLE_NAME_1, null,content);
    }
	
	public Cursor returnItemData() 
	{
		return db.query(SAMPLE_TABLE_NAME_1, new String[] {item_name,item_description},null,null, null, null,null);
		// TODO Auto-generated method stub
	}
}