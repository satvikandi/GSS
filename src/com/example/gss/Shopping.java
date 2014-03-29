package com.example.gss;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.AnimationDrawable;
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
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
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
public class Shopping extends Activity implements SensorEventListener{

	String fruits[]={"pear", "apple", "strawberry", "plum", "orange", "grapes", "pear","pear", "apple", "strawberry", "plum", "orange",  "grapes", "pear"};
    String costs[]={"$10","$20","$30","$10","20","30","10","20","30","10","20","30","10","20"};
	String bakery[]={"orange", "banana", "strawberry", "apple", "plum", "banana", "grapes", "pear"};
	String tag;
    static int id;
	RelativeLayout.LayoutParams params,param;
    private int REL_SWIPE_MIN_DISTANCE; 
    private int REL_SWIPE_MAX_OFF_PATH;
    private int REL_SWIPE_THRESHOLD_VELOCITY;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private int fposition;
    int ressourceId1;
    int ressourceId2;
    String value;
    RelativeLayout r;
    Drawable drawable;
    ImageButton img,tmp,tmp1,tmp2;
    TextView tv,tv1,tv2;

    AnimationDrawable logoAnimation;
    ListView l;
    int next =0;
	int prev =0;
	
	int next_tv=100;
	int prev_tv=100;
	
	
	Animation forward1 = new TranslateAnimation(1200f, 150f,0f, 0f);
	Animation forward2 = new TranslateAnimation(1650f, 600f,0f, 0f);
	Animation forward3 = new TranslateAnimation(2100f, 1050f,0f, 0f);
	Animation forward4 = new TranslateAnimation(2550f, 1500f,0f, 0f);
	
	Animation f = new TranslateAnimation(1950f, 1500f,0f, 0f);
	Animation f1 = new TranslateAnimation(150, -300,0, 0);
	Animation f2 = new TranslateAnimation(600, 150,0, 0);
	Animation f3 = new TranslateAnimation(1050, 600,0, 0);
	Animation f4 = new TranslateAnimation(1500, 1050,0, 0);
    
	Animation b = new TranslateAnimation(-300,150,0, 0);
	Animation b1 = new TranslateAnimation(150,600,0, 0);
	Animation b2 = new TranslateAnimation( 600,1050,0, 0);
	Animation b3 = new TranslateAnimation(1050,1500,0, 0);
	Animation b4 = new TranslateAnimation(1500,1950,0, 0);
    
@SuppressLint("NewApi")
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);

	setContentView(R.layout.activity_shopping);
	Intent intent = getIntent();
	value  = intent.getStringExtra("value");
	
	
	ImageButton trolleyImage=(ImageButton) findViewById(R.id.ib);
	 trolleyImage.setBackgroundResource(R.drawable.frame_anim);
     logoAnimation=(AnimationDrawable) trolleyImage.getBackground();
	
	 DisplayMetrics dm = getResources().getDisplayMetrics();
	  REL_SWIPE_MIN_DISTANCE = (int)(120.0f * dm.densityDpi / 160.0f + 0.5); 
	  REL_SWIPE_MAX_OFF_PATH = (int)(250.0f * dm.densityDpi / 160.0f + 0.5);
	  REL_SWIPE_THRESHOLD_VELOCITY = (int)(200.0f * dm.densityDpi / 160.0f + 0.5);
	  
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
	    ImageButton imgbtn = (ImageButton) findViewById (R.id.ib);
	    ImageButton i1 = (ImageButton) findViewById (R.id.imageButton1); 
	    //ImageButton img1 = (ImageButton) findViewById (R.id.)
	 //   imgbtn.setOnDragListener(DropListener);
	    i1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tag = (String) v.getTag();
				 Toast.makeText(Shopping.this, "tag is" + tag, Toast.LENGTH_SHORT).show();
			}
		});
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


	
	    params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	       params.topMargin = 150; 
	       
	       param = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	       param.topMargin = 330; 
	
	       logoAnimation.start();
	r=(RelativeLayout) findViewById(R.id.firstLayout);
		 
		for(int i=0;i<4;i++)
		{
			img=new ImageButton(this);
			tv=new TextView(this);
			img.setBackground(null);
			img.setId(next);
			tv.setId(next_tv);
			drawable = getResources().getDrawable(getResources()
		            .getIdentifier(fruits[i], "drawable", getPackageName()));
			img.setImageDrawable(drawable);
			img.setOnTouchListener(Listen);
			r.addView(img,params);
			tv.setBackgroundResource(R.drawable.grey_button);
			tv.setTextColor(Color.BLACK);
			tv.setTextSize(14);
			tv.setGravity(Gravity.CENTER);
			tv.setText(costs[next]);
			r.addView(tv,param);
			
			if(i==0)
			{
				forward1.setDuration(2000);
				forward1.setFillAfter(true);
				img.startAnimation(forward1);
				tv.startAnimation(forward1);
				img.setVisibility(0);
			}
			
			else if(i==1)
			{
				forward2.setDuration(2000);
				forward2.setFillAfter(true);
				img.startAnimation(forward2);
				tv.startAnimation(forward2);
				img.setVisibility(0); 
			}
			
			else if(i==2)
			{
				forward3.setDuration(2000);
				forward3.setFillAfter(true);
				img.startAnimation(forward3);
				tv.startAnimation(forward3);
				img.setVisibility(0); 
			}
			
			else
			{
				forward4.setDuration(2000);
				forward4.setFillAfter(true);
				img.startAnimation(forward4);
				tv.startAnimation(forward4);
				img.setVisibility(0); 
			}
			next++;
			next_tv++;
		}
	
		
		//next++;
		 
	
	/*	ressourceId1 = getResources().getIdentifier(
   	    	    String.valueOf(0),
   	    	    "id",
   	    	    this.getPackageName());
		
		ressourceId2 = getResources().getIdentifier(
   	    	    String.valueOf(1),
   	    	    "id",
   	    	    this.getPackageName());
		 
	*/
			forward4.setAnimationListener(new Animation.AnimationListener(){
        @Override
        public void onAnimationStart(Animation arg0) {
        }           
        @Override
        public void onAnimationRepeat(Animation arg0) { 
        }            
        @Override
        public void onAnimationEnd(Animation arg0) {
        	//onResume();
        	 logoAnimation.stop();  
    		 } 
    });
}

private void myOnItemClick(int position) {
 	fposition=position;
   }
 
 private void onLTRFling() {
	 	Intent i=new Intent(Shopping.this,Shopping.class);
	 	i.putExtra("value", Integer.toString(fposition));
	 	startActivity(i);
 }
 
 public void changeHandle() {
	 System.out.println("Entered function");
	 ImageButton handleButton= (ImageButton) findViewById (R.id.handle);
	 handleButton.setBackgroundResource(R.drawable.up);
	 
 }
 private void onRTLFling() {
	 	Intent i=new Intent(Shopping.this,Deals.class);
	 	i.putExtra("value", Integer.toString(fposition));
	 	startActivity(i);
 }

 
 
class MyGestureDetector extends SimpleOnGestureListener{ 

    // Detect a touch down and call my own handler.
    @Override 
    public boolean onDown(MotionEvent e) {
   	
        
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


OnTouchListener Listen = new OnTouchListener()
	{

		@SuppressLint("NewApi")
		@Override
		public boolean onTouch(View v, MotionEvent me) 
		{
			//tag = (String)v.getTag();
			id = v.getId();
			Toast.makeText(Shopping.this,"Id is "+id,Toast.LENGTH_SHORT).show();
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
				String usrnm = Login.usrnm;
				//id = v.getId();	
				//String id = (String) v.getTag();
				Toast.makeText(Shopping.this,"Id is "+id,Toast.LENGTH_SHORT).show();
				String getId = null;
				String getCost = null ;
				String getName = "";
				int count = 0;
				DataHandler dh = new DataHandler(getBaseContext());
				dh.open();
				
				//Toast.makeText(Shopping.this,"Product selected is "+id,Toast.LENGTH_SHORT).show();
				
				//dh.insertUserList(usrnm, getName);
				
				
				Cursor c = dh.returnData(String.valueOf(id));
				
				
				if (c.moveToFirst()) {
					do {
						//getId = c.getString(0) ;
						 getName = c.getString(1);
						 getCost = c.getString(3);
						 
						// count += Integer.parseInt(getCost);
						Toast.makeText(
								getBaseContext(),
								"ITEMNAME : " + getName
										+ " " + "ITEMCOST : " + getCost,
								Toast.LENGTH_LONG).show(); 
					} while (c.moveToNext());
					dh.insertUserList(usrnm, getName);
					
			  }  
				//prod_cost.setText("$ "+ count);
				//String cnt = Integer.toString(count) + "dollars";
				//tts.speak(cnt,TextToSpeech.QUEUE_FLUSH,null);
			}
			return true;

		}
		
	};
	
	@Override
	 public void onSensorChanged(SensorEvent event) {
	  float x = event.values[0];
	  float y = event.values[1];
	  float z = event.values[2];
	  float temp=0;
	  temp=x;
	  x=y;
	  y= -(temp); 
	  	
	  
	   
	   
	  //final Animation a = AnimationUtils.loadAnimation(this, R.anim.slide);
	  //forward1.reset();
	  if (Math.abs(x) > Math.abs(y)) {
	   if (x > 3 && next<15) {
		   onPause(); 
		        	
		   tmp1=new ImageButton(this);
		   tmp1.setBackground(null);
		   tmp1.setId(next);
		   drawable = getResources().getDrawable(getResources()
		            .getIdentifier(fruits[next], "drawable", getPackageName()));
		   tmp1.setImageDrawable(drawable);
	       tmp1.setBackground(null);
	       r.addView(tmp1,params);
	       
	      tv1=new TextView(this);
	      tv1.setId(next_tv);
			tv1.setBackgroundResource(R.drawable.grey_button);
			tv1.setTextColor(Color.BLACK);
			tv1.setTextSize(14);
			tv1.setGravity(Gravity.CENTER);
			tv1.setText(costs[next]);
			r.addView(tv1,param);
	       
	       f.setDuration(2000);
			f.setFillAfter(true);
			tmp1.startAnimation(f);
			tv1.startAnimation(f);
			tmp1.setVisibility(0); 
			tv1.setVisibility(0); 
		   
    	
	       
	       	for(int i=4;i>=1;i--)
	       {
	    	   ressourceId1 = getResources().getIdentifier(
	   	    	    String.valueOf(next-i),
	   	    	    "id",
	   	    	    this.getPackageName());
	    	   ressourceId2 = getResources().getIdentifier(
		   	    	    String.valueOf(next_tv-i),
		   	    	    "id",
		   	    	    this.getPackageName());
	    	   
	    	   System.out.println(next-i);
	    	   tmp=(ImageButton) findViewById(ressourceId1);
	    	  tv=(TextView) findViewById(ressourceId2);
	    	   
	    	   if(i==4)
	    	   {
	    		f1.setDuration(2000);
	   			f1.setFillAfter(true);
	   			tmp.startAnimation(f1);
	   			tmp.setVisibility(0); 
	   			tv.startAnimation(f1);
	   			tv.setVisibility(0); 
	    	   }
	    	   
	    	   else if(i==3)
	    	   {
	    		   f2.setDuration(2000);
		   		   f2.setFillAfter(true);
		   		   tmp.startAnimation(f2);
		   		   tmp.setVisibility(0); 
		        tv.startAnimation(f2);
	   			tv.setVisibility(0); 
	    	   }
	    	   
	    	   else if(i==2)
	    	   {
	    		   f3.setDuration(2000);
		   		   f3.setFillAfter(true);
		   		   tmp.startAnimation(f3);
		   		   tmp.setVisibility(0); 
		   		tv.startAnimation(f3);
	   			tv.setVisibility(0); 
		   		   
	    	   }
	    	   
	    	   else
	    	   {
	    		   f4.setDuration(2000);
		   		   f4.setFillAfter(true);
		   		   tmp.startAnimation(f4);
		   		   tmp.setVisibility(0);
		   		tv.startAnimation(f4);
	   			tv.setVisibility(0); 
	    	   }
	    	   
	       }
		   
	       	
		  
			next++;
			next_tv++;
			prev++;
			prev_tv++;
	    
	  
		forward4.setAnimationListener(new Animation.AnimationListener(){
	        @Override
	        public void onAnimationStart(Animation arg0) {
	        }           
	        @Override
	        public void onAnimationRepeat(Animation arg0) { 
	        }            
	        @Override
	        public void onAnimationEnd(Animation arg0) {
	        	onResume();
	        	//abc.setVisibility(View.VISIBLE); 
	        } 
	    });
		f.setAnimationListener(new Animation.AnimationListener(){
	        @Override
	        public void onAnimationStart(Animation arg0) {
	        }           
	        @Override
	        public void onAnimationRepeat(Animation arg0) {
	        }           
	        @SuppressLint("NewApi")
			@Override
	        public void onAnimationEnd(Animation arg0) {
	        	onResume();
	        	//xyz.setAlpha(0f);
	        	//xyz.setVisibility(View.GONE);
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
	        	//onResume();
	        }
	    });
	   
	    
	   }
	   
	   if (x < -3 && prev>0) {
		   onPause();
		 
		   next--;
		   next_tv--;
		   ressourceId1 = getResources().getIdentifier(
	   	    	    String.valueOf(prev-1),
	   	    	    "id",
	   	    	    this.getPackageName());
		   ressourceId2 = getResources().getIdentifier(
	   	    	    String.valueOf(prev_tv-1),
	   	    	    "id",
	   	    	    this.getPackageName());
	    	   tmp=(ImageButton) findViewById(ressourceId1);
	    	   tv=(TextView) findViewById(ressourceId2);
	   
	    	   b.setDuration(2000);
	   			b.setFillAfter(true);
	   			tmp.startAnimation(b);
	   			tmp.setVisibility(0); 
	   			tv.startAnimation(b);
	   			tv.setVisibility(0); 
	   			
			for(int i=0;i<4;i++)
		       {
		    	   ressourceId1 = getResources().getIdentifier(
		   	    	    String.valueOf(prev+i),
		   	    	    "id",
		   	    	    this.getPackageName());
		    	   
		    	   ressourceId2 = getResources().getIdentifier(
			   	    	    String.valueOf(prev_tv+i),
			   	    	    "id",
			   	    	    this.getPackageName());
		    	   tmp=(ImageButton) findViewById(ressourceId1);
		    	   tv=(TextView) findViewById(ressourceId2);
		    	   
		    	   if(i==0)
		    	   {
		    		b1.setDuration(2000);
		   			b1.setFillAfter(true);
		   			tmp.startAnimation(b1);
		   			tmp.setVisibility(0);
		   			tv.startAnimation(b1);
		   			tv.setVisibility(0);
		    	   }
		    	   
		    	   else if(i==1)
		    	   {
		    		   b2.setDuration(2000);
			   		   b2.setFillAfter(true);
			   		   tmp.startAnimation(b2);
			   		   tmp.setVisibility(0); 
			   		tv.startAnimation(b2);
			   		   tv.setVisibility(0); 
		    	   }
		    	   
		    	   else if(i==2)
		    	   {
		    		   b3.setDuration(2000);
			   		   b3.setFillAfter(true);
			   		   tmp.startAnimation(b3);
			   		   tmp.setVisibility(0);
			   		tv.startAnimation(b3);
			   		  tv.setVisibility(0); 
		    	   }
		    	   
		    	   else
		    	   {
		    		   b4.setDuration(2000);
			   		   b4.setFillAfter(true);
			   		   tmp.startAnimation(b4);
			   		   tmp.setVisibility(0); 
			   		tv.startAnimation(b4);
			   		   tv.setVisibility(0); 
		    	   }
		    	   
		       }
			   
		       	
			
		   
		   prev--;
		   prev_tv--;
		   
		   b.setAnimationListener(new Animation.AnimationListener(){
		        @Override
		        public void onAnimationStart(Animation arg0) {
		        }           
		        @Override
		        public void onAnimationRepeat(Animation arg0) {
		        }           
		        @SuppressLint("NewApi")
				@Override
		        public void onAnimationEnd(Animation arg0) {
		        	onResume();
		        	//xyz.setAlpha(0f);
		        	//xyz.setVisibility(View.GONE);
		        }
		    });
			
		   
		   
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

