package com.example.gss;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainDescriptionActivity extends Activity {
	
	DataHandler d;
	
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_description);
		
		 final TextView  ImageDescription  = (TextView)findViewById(R.id.textView1);
		 final TextView  ImageName  = (TextView)findViewById(R.id.textView2);
		 String getName = " ", getDescription=" ";
		d = new DataHandler(getBaseContext());
	    d.open();
	   //long id = d.insertItemData();
	   // Toast.makeText(getBaseContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
	   Cursor c = d.returnItemData();
	   if (c.moveToFirst())
	   {
		   do
		   { 	getName = c.getString(0);
			   getDescription = c.getString(1);
	   }while(c.moveToNext());
		   
	   }
	   ImageDescription.setText(getDescription);
	   ImageName.setText(getName);
	 
	   //Toast.makeText(getBaseContext(), "ITEMNAME : " + getName + " " + "ITEMDESCRIPTION : " + getDescription, Toast.LENGTH_LONG).show();
	   d.close();
	
	}
	
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_description, menu);
		return true; 
	} */
}
