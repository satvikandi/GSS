package com.example.gss;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

	//private static String SAMPLE_TABLE_NAME = "user_details";
	
	//private SQLiteDatabase userDB=null;
	
	DataHandler d;
	
	   public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_register);
	       
	       /* try
	        {
	            userDB = openOrCreateDatabase("/sdcard/user.db", MODE_PRIVATE, null);
	            
	        }
	        catch (SQLiteException se)
	        {
	            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
	        } */
	       
	        final EditText  firstname  = (EditText)findViewById(R.id.firstname);
	        final EditText  lastname  = (EditText)findViewById(R.id.lastname);
	        
	        //final EditText  address  = (EditText)findViewById(R.id.address);
	        final EditText  emailid  = (EditText)findViewById(R.id.emailid);
	        
	        final EditText  username  = (EditText)findViewById(R.id.username);
	        final EditText  password  = (EditText)findViewById(R.id.password);
	        
	        Button registerUser = (Button)findViewById(R.id.register);
	        Button resetDetails = (Button)findViewById(R.id.reset);
	        
	        registerUser.setOnClickListener(new OnClickListener()
	        		{
	        			public void onClick(View arg0)
	        			{
	        				String fname = firstname.getText().toString();
	        				String lname= lastname.getText().toString();
	        	 		
	        			//	String addr = address.getText().toString();
	        				String mail = emailid.getText().toString();
	        	 		
	        				String uname = username.getText().toString();
	        				String pass = password.getText().toString();
	        				
	        				 if(fname.matches("")||lname.matches("")||mail.matches("")||uname.matches("")||pass.matches(""))
	                         {
	                        	 Toast.makeText(Register.this, "All Details have not been entered", Toast.LENGTH_LONG).show();
	                        	 setContentView(R.layout.activity_register);
	                         }
	        				
	        				 else
	        					 createUser(fname,lname,uname,pass,mail);
	        			}
	        		});
	        
	        resetDetails.setOnClickListener(new OnClickListener()
	        		{

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							firstname.setText("");
							lastname.setText("");
							emailid.setText("");
							username.setText("");
							password.setText("");
							
						}
	        			// setting all the text fields to empty strings
	        			// setText() method
	        			
	        		});
	        
	   }
	
	   private void createUser(String fname, String lname,String username, String password,String email)
	   {
		   
		   d = new DataHandler(getBaseContext());
		   d.open();
		   long id = d.insertUserData(fname,lname,username,password,email);
		   Toast.makeText(getBaseContext(), "User details have been saved.", Toast.LENGTH_LONG).show();
		   // dbhelper.onCreate(user_db);
		   d.close();
		   Intent intent = new Intent(Register.this,Login.class);
           startActivity(intent);
		   
		   
		   /*String query = "insert into" + SAMPLE_TABLE_NAME + "(" + fname + "," +lname + "," + email + "," + username + "," + password + ")";
		   userDB.execSQL(query);
		   System.out.print(query);
		    Cursor cursor = userDB.rawQuery(query,null); */
	   }

}
