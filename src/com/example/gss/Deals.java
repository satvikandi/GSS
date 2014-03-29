package com.example.gss;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Deals extends Activity{
	
	 private GestureDetector gestureDetector;
	    
	 @Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_left);
	        setContentView(R.layout.activity_deals);
	        TextView tv=(TextView) findViewById (R.id.textView);
	        
	        Intent intent = getIntent();         
	        String value  = intent.getStringExtra("value");
	        
	        if(value.matches("0"))
	        	value="Bakery";
	        else if(value.matches("1"))
	        	value="Dairy";
	        else if(value.matches("2"))
	        	value="Desserts";
	        else if(value.matches("3"))
	        	value="Fruits";
	        else if(value.matches("4"))
	        	value="Frozen Section";
	        else if(value.matches("5"))
	        	value="Condiments";
	        else if(value.matches("6"))
	        	value="Sauces";
	        else if(value.matches("7"))
	        	value="Personal Care";
	        else if(value.matches("8"))
	        	value="Kitchen Ware";
	     
	        tv.setText("Deals for "+value+"!");
	        
	        final LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
	        gestureDetector = new GestureDetector(this, new GestureListener());
	 
	        ll.setOnTouchListener(new OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	 
	                if (gestureDetector.onTouchEvent(event)) {
	                	Intent i=new Intent(Deals.this,Shopping.class);
	                 	startActivity(i);
	                    return true;
	                }
	                return false;
	            }
	        });
	    }
}


