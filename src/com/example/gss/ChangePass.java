
package com.example.gss;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePass extends Activity
{
	DataHandler dh;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpass);
        /*Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String username =(String) b.get("user_identity");
            
        } */
        
        final String username;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
            } else {
                username = extras.getString("user_identity");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("user_identity");
        }
 
	
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
    
	final EditText  ednewpass1  = (EditText)findViewById(R.id.editText1);
    final EditText  ednewpass2  = (EditText)findViewById(R.id.editText2);
    final Button btn = (Button)findViewById(R.id.button1);
    
    btn.setOnClickListener(new OnClickListener()
    {
         public void onClick(View arg0)
         {
              String pass1  = ednewpass1.getText().toString();
              String pass2 = ednewpass2.getText().toString();
                  
              if(pass1.matches("")||pass2.matches(""))
              	{
            	  Toast.makeText(ChangePass.this, "Enter a valid password", Toast.LENGTH_LONG).show();
              	}
              
              else if(pass1.matches(pass2))
              {
            	  insertPassword(pass1,username);
              
             
              	Intent i=new Intent(ChangePass.this,Login.class);
              	startActivity(i);
              }
              
              else
              {
            	  Toast.makeText(ChangePass.this, "Password Mismatch. Please re-enter!!", Toast.LENGTH_LONG).show();  
              }
         }  
    });
}

    private void insertPassword(String password,String username)
	 {

    	String SAMPLE_TABLE_NAME = dh.SAMPLE_TABLE_NAME;
    	
    	// String pass_check="";
	       //cursor = sampleDB.rawQuery("SELECT MAX(ROWID) FROM " +
	    String query = "update " + SAMPLE_TABLE_NAME+ " set password='"+password+"' where username='"+username+"'";
	    // System.out.print(query);
	    dh.db.execSQL(query);
	    dh.close(); 
    	
    /*	ContentValues newValues = new ContentValues();
    	newValues.put("password", password);

    	dh.db.update(SAMPLE_TABLE_NAME, newValues, username+"=?",new String[]{password}); */
    //	dh.close();
	 }
}

    

