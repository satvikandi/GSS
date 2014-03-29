package com.example.gss;

 
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
 
public class SplashScreen extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 6000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        ImageView image = (ImageView)findViewById(R.id.imageView2);
        TextView tv= (TextView) findViewById (R.id.tv);
        
        ImageView imgGSS = (ImageView)findViewById(R.id.imageView1);       
        
        
        Animation animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        imgGSS.startAnimation(animationFadeIn);
        image.startAnimation(animationFadeIn);
       
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Lucida Calligraphy Italic.ttf");
        tv.setTypeface(tf);
        tv.startAnimation(animationFadeIn);
        
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}