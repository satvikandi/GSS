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
import android.widget.ImageButton;
import android.widget.Toast;
public class Login extends Activity {
    /** Called when the activity is first created. */
	String check;
	// private static String SAMPLE_TABLE_NAME = "user_data";
	 
	//    private SQLiteDatabase userDB = null;\
	
	DataHandler dh;
	public static String usrnm;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        

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
       
        final EditText  edUsername  = (EditText)findViewById(R.id.editText1);
        final EditText  edPassword  = (EditText)findViewById(R.id.editText2);
        ImageButton btnValidate = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton changepass=(ImageButton)findViewById(R.id.imageButton3);
        ImageButton new_user=(ImageButton)findViewById(R.id.imageButton1);
        ImageButton ws=(ImageButton)findViewById(R.id.imageButton4);
        
        btnValidate.setOnClickListener(new OnClickListener()
           {
                public void onClick(View arg0)
                {
                     String uname  = edUsername.getText().toString();
                     String pass= edPassword.getText().toString();
                     
                     if(uname.matches("")||pass.matches(""))
                     {
                    	 Toast.makeText(Login.this, "Invalid Username password pair.", Toast.LENGTH_LONG).show();
                         
                     }
                     
                     else
                     {
                     boolean auth_login=checkData(uname,pass);
                     
                     if(auth_login)
                     {
                    	 usrnm = uname;
                    	 Intent intent = new Intent(Login.this,PreShopping.class);
                         startActivity(intent);
                     }
                     else
                     {
                           Toast.makeText(Login.this, "Invalid Username password pair.", Toast.LENGTH_LONG).show();
                     }
                     }
              }
           });
        
        changepass.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	{
        	Intent i=new Intent(Login.this,Verify.class);
        	startActivity(i);
        	}
        });
        
        new_user.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	{
        	Intent i=new Intent(Login.this,Register.class);
        	startActivity(i);
        	}
        });
        
        ws.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	{
        	Intent i=new Intent(Login.this,Shopping_new.class);
        	i.putExtra("value", "0");
        	startActivity(i);
        	}
        });
    }
    
    private boolean checkData(String username, String password)
	 {

    	String SAMPLE_TABLE_NAME = dh.SAMPLE_TABLE_NAME;
    	
    	String check_pass="";
	       //cursor = sampleDB.rawQuery("SELECT MAX(ROWID) FROM " +
	    		   String query = "SELECT password FROM " + SAMPLE_TABLE_NAME+ " WHERE username='"+username+"'";
	    		   System.out.print(query);
	    		   Cursor cursor = dh.db.rawQuery(query, null);
	    		  		         
	       if (cursor.moveToFirst())
	       {
	           do
	           {           
	               check_pass = cursor.getString(0);
	               
	           } while(cursor.moveToNext());           
	       }

           cursor.close();
	       if(check_pass.matches(password))
        	   return true;
	       else
	    	   return false;
	        
	         
	 }
}