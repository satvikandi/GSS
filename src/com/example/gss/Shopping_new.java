package com.example.gss;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class Shopping_new extends Activity implements SensorEventListener{


    private int REL_SWIPE_MIN_DISTANCE; 
    private int REL_SWIPE_MAX_OFF_PATH;
    private int REL_SWIPE_THRESHOLD_VELOCITY;
    private int fposition;
    ListView l;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView title;
    ImageButton img;
    ImageButton abc,xyz;
    ImageView i;
    //TextView iv;
    ImageButton image;
    ImageButton img1;
    ImageButton img2;
    String value;
    int next=2;
    int prev=0;
     //String fruits[];
    
    String fruits[]={"pear", "apple", "strawberry", "plum", "orange", "banana", "grapes", "pear"};
    String bakery[]={"orange", "banana", "strawberry", "apple", "plum", "banana", "grapes", "pear"};
    
    RelativeLayout r;
    Drawable drawable;
    RelativeLayout.LayoutParams params;

    
 @SuppressWarnings("deprecation")
@Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);

	
  
  setContentView(R.layout.activity_ws);
  System.out.println("Entered main");
  Intent intent = getIntent();         
   value  = intent.getStringExtra("value");
   Button ws=(Button) findViewById(R.id.ib);
   
   ws.setOnClickListener(new OnClickListener()
   {
   	public void onClick(View arg0)
   	{
   	Intent i=new Intent(Shopping_new.this,Login.class);
   	startActivity(i);
   	}
   });
   
  DisplayMetrics dm = getResources().getDisplayMetrics();
  REL_SWIPE_MIN_DISTANCE = (int)(120.0f * dm.densityDpi / 160.0f + 0.5); 
  REL_SWIPE_MAX_OFF_PATH = (int)(250.0f * dm.densityDpi / 160.0f + 0.5);
  REL_SWIPE_THRESHOLD_VELOCITY = (int)(200.0f * dm.densityDpi / 160.0f + 0.5);
  //findViewById(R.id.imageButton2).setOnTouchListener(Listen);
  //findViewById(R.id.imageButton1).setOnTouchListener(Listen);
  //ImageButton btn1=(ImageButton) findViewById(R.id.imageButton1);
  
  l = (ListView) findViewById(R.id.listview);
  String[] values = new String[] {"Fruits", "Bakery", "Dairy", "Desserts",
		   "Frozen Section", "Condiments", "Sauces", "Personal Care", "Kitchen Ware" };
  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_row, values);
    l.setAdapter(adapter);
    
    final GestureDetector gestureDetector = new GestureDetector(new MyGestureDetector());
    View.OnTouchListener gestureListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);  
          
        }};
    l.setOnTouchListener(gestureListener);
    
    final ImageButton ib=(ImageButton) findViewById (R.id.handle);
    
    SlidingDrawer slidingDrawer=(SlidingDrawer)findViewById(R.id.slidingDrawer);

    slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {

        @Override
        public void onDrawerOpened() {
            Log.d("Debug","Drawer Opened");
            //
            ib.setImageDrawable(getResources().getDrawable(R.drawable.up));
            
            
    }
       
    });

    slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {

        public void onDrawerClosed() {
            Log.d("Debug","Drawer Closed");
            //  
            ib.setImageDrawable(getResources().getDrawable(R.drawable.down));

        }
    });

    
    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    mAccelerometer = mSensorManager
      .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
   // title = (TextView) findViewById(R.id.txt);
    
    
    img1=new ImageButton(this);
    img1.setId(0);
    if(value.matches("0"))
    {
    drawable = getResources().getDrawable(getResources()
            .getIdentifier(fruits[0], "drawable", getPackageName()));
    }
    if(value.matches("1"))
    {
    drawable = getResources().getDrawable(getResources()
            .getIdentifier(bakery[0], "drawable", getPackageName()));
    }
  img1.setImageDrawable(drawable); 
  img1.setBackground(null);
  img1.setOnTouchListener(Listen);
    //img1.setImageResource(R.drawable.down);
    img2=new ImageButton(this);
    img2.setId(1);
    if(value.matches("0"))
    {
    drawable = getResources().getDrawable(getResources()
            .getIdentifier(fruits[1], "drawable", getPackageName()));
    }
    
    if(value.matches("1"))
    {
    drawable = getResources().getDrawable(getResources()
            .getIdentifier(bakery[1], "drawable", getPackageName()));
    }
  img2.setImageDrawable(drawable);
  img2.setBackground(null);
    //img2.setImageResource(R.drawable.up);
   img2.setOnLongClickListener(new View.OnLongClickListener() {
		
		public boolean onLongClick(View v) {
			//Toast.makeText(MainActivity.this,"Retrieving Data",Toast.LENGTH_SHORT).show();
			//startActivity(new Intent(MainActivity.this,ItemDescription.class));
			/*Intent myIntent = new Intent(v.getContext(),ItemDescription.class);
			startActivityForResult(myIntent, 0);*/
			Intent i=new Intent();
			i.setAction("android.intent.action.LAUNCH_IT");
			startActivityForResult(i,0);
			return false;
		}
});
   
    r=(RelativeLayout) findViewById(R.id.firstLayout);
    
    
    params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    params.leftMargin = 500;
    params.topMargin = 150;
    r.addView(img1, params);

   
    params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    params.leftMargin = 950;
    params.topMargin = 150;
    r.addView(img2, params);

   

    
   /* l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            String str = MessageFormat.format("Item long clicked = {0,number}", position);
            Toast.makeText(Shopping.this, str, Toast.LENGTH_SHORT).show();
            return true;
        }
    });*/
 

 /*btn1.setOnLongClickListener(new View.OnLongClickListener() {
		
		public boolean onLongClick(View v) {
			//Toast.makeText(MainActivity.this,"Retrieving Data",Toast.LENGTH_SHORT).show();
			//startActivity(new Intent(MainActivity.this,ItemDescription.class));
			/*Intent myIntent = new Intent(v.getContext(),ItemDescription.class);
			startActivityForResult(myIntent, 0);*/
		/*	Intent i=new Intent();
			i.setAction("android.intent.action.LAUNCH_IT");
			startActivityForResult(i,0);
			return false;
		}
 });

	*/
 }
	
 private void myOnItemClick(int position) {
 	fposition=position;
   }
 
 private void onLTRFling() {
	 	Intent i=new Intent(Shopping_new.this,Shopping.class);
	 	i.putExtra("value", Integer.toString(fposition));
	 	startActivity(i);
 }
 
 public void changeHandle() {
	 System.out.println("Entered function");
	 ImageButton handleButton= (ImageButton) findViewById (R.id.handle);
	 handleButton.setBackgroundResource(R.drawable.up);
	 
 }
 private void onRTLFling() {
	 	Intent i=new Intent(Shopping_new.this,Deals.class);
	 	i.putExtra("value", Integer.toString(fposition));
	 	startActivity(i);
 }
 
 class MyGestureDetector extends SimpleOnGestureListener{ 

     // Detect a touch down and call my own handler.
     @Override 
     public boolean onDown(MotionEvent e) {
    	 Toast.makeText(Shopping_new.this, "Swipe left to Shop and Swipe right for Deals!", Toast.LENGTH_SHORT).show();
         
         int pos = l.pointToPosition((int)e.getX(), (int)e.getY());
         myOnItemClick(pos);
         return false;
     }

     @Override 
     public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) { 
         if (Math.abs(e1.getY() - e2.getY()) > REL_SWIPE_MAX_OFF_PATH) 
             return false; 
         if(e1.getX() - e2.getX() > REL_SWIPE_MIN_DISTANCE && 
             Math.abs(velocityX) > REL_SWIPE_THRESHOLD_VELOCITY) { 
             onRTLFling(); 
         }  else if (e2.getX() - e1.getX() > REL_SWIPE_MIN_DISTANCE && 
             Math.abs(velocityX) > REL_SWIPE_THRESHOLD_VELOCITY) { 
             onLTRFling(); 
         } 
         return false; 
     } 

 } 
 
 @Override
 public void onBackPressed()
 {
	 Intent i=new Intent(Shopping_new.this,PreShopping.class);
	 startActivity(i);
      super.onBackPressed();  // optional depending on your needs
 }

 OnTouchListener Listen = new OnTouchListener()
	{

		@SuppressLint("NewApi")
		@Override
		public boolean onTouch(View v, MotionEvent me) 
		{
			DragShadow dragShadow = new DragShadow(v);
			
			ClipData data = ClipData.newPlainText("", "");
			v.startDrag(data, dragShadow, v, 0);
			return false;
		}

		};
		
		@SuppressLint("NewApi")
		private class DragShadow extends View.DragShadowBuilder
		{
			ColorDrawable greyBox;
			
			public DragShadow(View view) 
			{
				
				super(view);
				greyBox = new ColorDrawable(Color.LTGRAY);
				
			}

			@Override
			public void onDrawShadow(Canvas canvas)
			{
				// TODO Auto-generated method stub
				greyBox.draw(canvas);
			}

			@Override
			public void onProvideShadowMetrics(Point shadowSize,Point shadowTouchPoint) 
			{
				View v = getView();
				int height = (int)v.getHeight();
				int width = (int)v.getWidth();
				
				greyBox.setBounds(0, 0, width, height);
				
				shadowSize.set(width,height);
				
				shadowTouchPoint.set((int)width/2,(int)height/2);
			}
			
		}
		
	@SuppressLint("NewApi")
	OnDragListener DropListener = new OnDragListener()
	{

		@Override
		public boolean onDrag(View v, DragEvent event) 
		{
			int dragAction = event.getAction();
			
			if(dragAction == DragEvent.ACTION_DROP)
			{
				
			}
			return false;
		}
		
	};
	@SuppressLint("NewApi")
	@Override
	 public void onSensorChanged(SensorEvent event) {
	  float x = event.values[0];
	  float y = event.values[1];
	  float z = event.values[2];
	  float temp=0;
	  temp=x;
	  x=y;
	  y= -(temp); 
	  	
	  
	  TranslateAnimation forward1 = new TranslateAnimation(950f, 400f, 0f, 0f); 
	  forward1.setDuration(1500);
	  TranslateAnimation forward2 = new TranslateAnimation(400f, -950f, 0f, 0f);
	  forward2.setDuration(1500); 
	  TranslateAnimation forward3 = new TranslateAnimation(2000f, 950f, 0f, 0f);
	  forward3.setDuration(1500);
	  
	  
	  
	  
	 /* TranslateAnimation backward1 = new TranslateAnimation(-100f, 500f, 0f, 0f); 
	  backward1.setDuration(2000);
	  TranslateAnimation backward2 = new TranslateAnimation(500f, 900f, 0f, 0f);
	  backward2.setDuration(2000);
	  TranslateAnimation backward3 = new TranslateAnimation(900f, 1500f, 0f, 0f);
	  backward3.setDuration(2000);  
	   */
	  
	   
	  //final Animation a = AnimationUtils.loadAnimation(this, R.anim.slide);
	  //forward1.reset();
	  if (Math.abs(x) > Math.abs(y)) {
	   if (x > 3 && next<7) {
		   onPause(); 
		   img=new ImageButton(this);
		   img.setBackground(null);
		  // img.setVisibility(View.INVISIBLE);
		   img.setId(next);
		   if(value.matches("0"))
		    {
		    drawable = getResources().getDrawable(getResources()
		            .getIdentifier(fruits[next], "drawable", getPackageName()));
		    }
		   if(value.matches("1"))
		    {
		   drawable = getResources().getDrawable(getResources()
		                  .getIdentifier(bakery[next], "drawable", getPackageName()));
		    }
	       img.setImageDrawable(drawable);
	       img.setBackground(null);
	      
	       params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	       params.topMargin = 150; 
	       r.addView(img, params);
	      
		
	    int ressourceId1 = getResources().getIdentifier(
	    	    String.valueOf(next-1),
	    	    "id",
	    	    this.getPackageName());
	   
	    
	    int ressourceId2 = getResources().getIdentifier(
	    	    String.valueOf(next-2),
	    	    "id",
	    	    this.getPackageName());
	    
	    
	    
	    abc=(ImageButton) findViewById(ressourceId1);
	    abc.setOnLongClickListener(new View.OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				//Toast.makeText(MainActivity.this,"Retrieving Data",Toast.LENGTH_SHORT).show();
				//startActivity(new Intent(MainActivity.this,ItemDescription.class));
				/*Intent myIntent = new Intent(v.getContext(),ItemDescription.class);
				startActivityForResult(myIntent, 0);*/
				Intent i=new Intent();
				i.setAction("android.intent.action.LAUNCH_IT");
				startActivityForResult(i,0);
				return false;
			}
	 });
		
	    xyz=(ImageButton) findViewById(ressourceId2);
		xyz.setOnTouchListener(Listen);

		abc.startAnimation(forward1);
		forward1.setFillAfter(true);
	    
	    

		xyz.startAnimation(forward2);
		//xyz.setAlpha(0f); 
		forward2.setFillAfter(true);
	    
		img.startAnimation(forward3);
		forward3.setFillAfter(true);
	     
		next++;
	    prev++;
		forward1.setAnimationListener(new Animation.AnimationListener(){
	        @Override
	        public void onAnimationStart(Animation arg0) {
	        }           
	        @Override
	        public void onAnimationRepeat(Animation arg0) { 
	        }            
	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        	//onResume();
	        	//abc.setVisibility(View.VISIBLE); 
	        } 
	    });
		forward2.setAnimationListener(new Animation.AnimationListener(){
	        @Override
	        public void onAnimationStart(Animation arg0) {
	        }           
	        @Override
	        public void onAnimationRepeat(Animation arg0) {
	        }           
	        @SuppressLint("NewApi")
			@Override
	        public void onAnimationEnd(Animation arg0) {
	        	//onResume();
	        	xyz.setAlpha(0f);
	        	xyz.setVisibility(View.GONE);
	        }
	    });
		forward3.setAnimationListener(new Animation.AnimationListener(){
	        @Override
	        public void onAnimationStart(Animation arg0) {
	        }           
	        @Override
	        public void onAnimationRepeat(Animation arg0) {
	        }           
	        @Override
	        public void onAnimationEnd(Animation arg0) {

	        	//img.setVisibility(View.VISIBLE);
	        	onResume();
	        }
	    });
	   
	    
	   }
	   
	   if (x < -3 && prev>-1) {
		   onPause();
			Intent i=new Intent(Shopping_new.this,Shopping_new.class);
		 	i.putExtra("value", Integer.toString(fposition));
		 	startActivity(i);

		   onResume();
		  
	   }
	  } 
	  if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
	  // iv.setText("Center");
	  }
	  
	 }

	 @Override
	 protected void onResume() {
	  super.onResume();
	  mSensorManager.registerListener(this, mAccelerometer,
	    SensorManager.SENSOR_DELAY_NORMAL);
	 }


	   @Override
	   public void onAccuracyChanged(Sensor arg0, int arg1) {
	    // TODO Auto-generated method stub
	   }

	 
	 @Override
	 protected void onPause() {
	  super.onPause();
	  mSensorManager.unregisterListener(this);
	 }
}