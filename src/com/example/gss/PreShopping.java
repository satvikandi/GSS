package com.example.gss;

import android.os.Bundle;

import android.database.Cursor;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class PreShopping extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preshopping);
		configureImageButton();
		DataHandler dh = new DataHandler(getBaseContext());
	    dh.open();
	}

	private void configureImageButton() {
		ImageButton btn1=(ImageButton) findViewById(R.id.imageButton1);
		ImageButton btn2=(ImageButton) findViewById(R.id.imageButton2);
		ImageButton btn3=(ImageButton) findViewById(R.id.imageButton3);
		
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Toast.makeText(PreShopping.this,"Visit again...Thank you!!",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			
				
			}
		});
		
btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent myIntent = new Intent(arg0.getContext(),oneItem.class);
				startActivityForResult(myIntent, 0);
				
			
				
			}
		});
		
btn3.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
		Intent myIntent = new Intent(arg0.getContext(),Shopping.class);
		myIntent.putExtra("value", "0");
	 	
		startActivityForResult(myIntent, 0);
	
		
	
		
	}
});



	}

	@Override
	 public void onBackPressed()
	 {
		 Intent i=new Intent(PreShopping.this,Login.class);
		 startActivity(i);
	      super.onBackPressed();  // optional depending on your needs
	 }

	
}
