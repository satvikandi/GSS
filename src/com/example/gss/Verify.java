	package com.example.gss;

	import android.app.Activity;
	import android.content.Intent;
	import android.database.Cursor;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteException;
	import android.os.Bundle;
	import android.util.Log;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.Toast;

	public class Verify extends Activity
	{
		DataHandler dh;
		
		@Override
		public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_verify);
	 
	    
		try
	    {
	       // userDB = openOrCreateDatabase("/sdcard/gss/DB/user.db", MODE_PRIVATE, null);
	        dh = new DataHandler(getBaseContext());
	        dh.open();
	    }
	    
	    catch (SQLiteException se)
	    {
	        Log.e(getClass().getSimpleName(), "Could not create or Open the database");
	    }
	    
		final EditText  edusername  = (EditText)findViewById(R.id.editText1);
	    final EditText  edemailid  = (EditText)findViewById(R.id.editText2);
	    
	    Button btn_verify = (Button)findViewById(R.id.button1);
	    
	    btn_verify.setOnClickListener(new OnClickListener()
	    {
	         public void onClick(View arg0)
	         {
	              String uname  = edusername.getText().toString();
	              String email= edemailid.getText().toString();
	         
	    
	              if(uname.matches("")||email.matches(""))
	              	{
	            	  Toast.makeText(Verify.this, "Enter a valid username and emailid", Toast.LENGTH_LONG).show();
	              	}
	              
	              else
	              {
	            	  boolean user_check = validateUser(uname,email);
	              
	            	  if(user_check)
	            	  	{
	            		  Intent intent = new Intent(Verify.this,ChangePass.class);
	            		  intent.putExtra("user_identity", uname);
	            		  startActivity(intent);
	                 	  
	            	  	}
	            	  else
	            	  	{
	            		  Toast.makeText(Verify.this, "Username and/or email id doesn't exist", Toast.LENGTH_LONG).show();
	            	  	}
	              }
	         }  
	    });
	}

	    private boolean validateUser(String username, String emailid)
		 {

	    	String SAMPLE_TABLE_NAME = dh.SAMPLE_TABLE_NAME;
	    	
	    	String user_check="";
		       //cursor = sampleDB.rawQuery("SELECT MAX(ROWID) FROM " +
		    		   String query = "SELECT emailid FROM " + SAMPLE_TABLE_NAME+ " WHERE username='"+username+"'";
		    		   System.out.print(query);
		    		   Cursor cursor = dh.db.rawQuery(query, null);

		         
		       if (cursor.moveToFirst())
		       {
		           do
		           {           
		               user_check = cursor.getString(0);
		               
		           } while(cursor.moveToNext());           
		       }

	           cursor.close();
		       if(user_check.matches(emailid))
	        	   return true;
		       else
		    	   return false;
		        
		         
		 }
	} 
	    


