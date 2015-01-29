package com.puri.AbcBasics;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
 
public class AbcVoice extends Activity {
 
	Button button;
	public int currentimageindex=0;
	Animation shake;
	MediaPlayer mp;
	Resources resources;
    private int[] IMAGE_IDS = {R.drawable.apple, R.drawable.boy, R.drawable.cat, R.drawable.dog,
					    	   R.drawable.elephant, R.drawable.fish, R.drawable.girl, R.drawable.hen,
					    	   R.drawable.icecream, R.drawable.jug, R.drawable.kite, R.drawable.lion,
					    	   R.drawable.monkey, R.drawable.nest, R.drawable.owl, R.drawable.parrot,
					    	   R.drawable.queen, R.drawable.rat, R.drawable.ship, R.drawable.teapot,
					    	   R.drawable.umbrella, R.drawable.van, R.drawable.watch, R.drawable.xmas,
					    	   R.drawable.yacht, R.drawable.zebra};
    
    private int[] CHAR_IDS = {R.drawable.image_a, R.drawable.image_b, R.drawable.image_c, R.drawable.image_d,
	    	   R.drawable.image_e, R.drawable.image_f, R.drawable.image_g, R.drawable.image_h,
	    	   R.drawable.image_i, R.drawable.image_j, R.drawable.image_k, R.drawable.image_l,
	    	   R.drawable.image_m, R.drawable.image_n, R.drawable.image_o, R.drawable.image_p,
	    	   R.drawable.image_q, R.drawable.image_r, R.drawable.image_s, R.drawable.image_t,
	    	   R.drawable.image_u, R.drawable.image_v, R.drawable.image_w, R.drawable.image_x,
	    	   R.drawable.image_y, R.drawable.image_z};

    private int[] rawIds = {
            R.raw.a, R.raw.b, R.raw.c, R.raw.d,
            R.raw.e, R.raw.f, R.raw.g, R.raw.h,
            R.raw.i, R.raw.j, R.raw.k, R.raw.l,
            R.raw.m, R.raw.n, R.raw.o, R.raw.p,
            R.raw.q, R.raw.r, R.raw.s, R.raw.t, 
            R.raw.u, R.raw.v, R.raw.w, R.raw.x,
            R.raw.y, R.raw.z
        };
    
    private int[] TextIds = {
            R.drawable.text_a, R.drawable.text_b, R.drawable.text_c, R.drawable.text_d,
            R.drawable.text_e, R.drawable.text_f, R.drawable.text_g, R.drawable.text_h,
            R.drawable.text_i, R.drawable.text_j, R.drawable.text_k, R.drawable.text_l,
            R.drawable.text_m, R.drawable.text_n, R.drawable.text_o, R.drawable.text_p,
            R.drawable.text_q, R.drawable.text_r, R.drawable.text_s, R.drawable.text_t,
            R.drawable.text_u, R.drawable.text_v, R.drawable.text_w, R.drawable.text_x,
            R.drawable.text_y, R.drawable.text_z
        };
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abc_voice);
        resources = getResources();

        final ImageView imageview = (ImageView)findViewById(R.id.ImageViewPreview);
        final int position = getIntent().getExtras().getInt("imgPos");
	     mp = MediaPlayer.create(getBaseContext(), rawIds[position]);  
	     mp.start();
        imageview.setImageResource(IMAGE_IDS[position]); 
        
        final ImageView imageviewchar = (ImageView)findViewById(R.id.ImageViewChar);
        Animation inFromTop = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromTop.setDuration(500);
        imageviewchar.startAnimation(inFromTop);
        imageviewchar.setImageResource(CHAR_IDS[position]);
        
        final ImageView textview = (ImageView)findViewById(R.id.TextView);
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        textview.startAnimation(inFromRight);
        textview.setImageResource(TextIds[position]);
        
        currentimageindex = position;
        
        //image click
    	shake = AnimationUtils.loadAnimation(this, R.anim.shake); 
    	
        imageview.setOnClickListener(new OnClickListener() {	 
			@Override
			public void onClick(View arg0) {
		    	mp.stop();	               
	            mp = MediaPlayer.create(getBaseContext(), rawIds[currentimageindex]);  
	            mp.start();            
	            imageview.startAnimation(shake);
	            }
		});
        
        
        button = (Button) findViewById(R.id.forward_btn);
        button.setOnClickListener(new OnClickListener() {
        	 
			@Override
			public void onClick(View arg0) {
		     if ((IMAGE_IDS.length) > (currentimageindex + 1)) {
		    	mp.stop();
				Animation inFromRight = new TranslateAnimation(
		                Animation.RELATIVE_TO_PARENT, +1.0f,
		                Animation.RELATIVE_TO_PARENT, 0.0f,
		                Animation.RELATIVE_TO_PARENT, 0.0f,
		                Animation.RELATIVE_TO_PARENT, 0.0f);
		            inFromRight.setDuration(500);
		        imageview.startAnimation(inFromRight);		      
	            imageview.setImageResource(IMAGE_IDS[currentimageindex + 1]);
	               
	            //char incoming
	            Animation inFromTop = new TranslateAnimation(
	                   Animation.RELATIVE_TO_PARENT, 0.0f,
	                   Animation.RELATIVE_TO_PARENT, 0.0f,
	                   Animation.RELATIVE_TO_PARENT, -1.0f,
	                   Animation.RELATIVE_TO_PARENT, 0.0f);
	            inFromTop.setDuration(500);
	            imageviewchar.startAnimation(inFromTop);
	            imageviewchar.setImageResource(CHAR_IDS[currentimageindex + 1]);
	                
	            // Text incoming
	            Animation inFromRight1 = new TranslateAnimation(
	                    Animation.RELATIVE_TO_PARENT, +1.0f,
	                    Animation.RELATIVE_TO_PARENT, 0.0f,
	                    Animation.RELATIVE_TO_PARENT, 0.0f,
	                    Animation.RELATIVE_TO_PARENT, 0.0f);
	            inFromRight1.setDuration(500);
	            textview.startAnimation(inFromRight1);
	            textview.setImageResource(TextIds[currentimageindex + 1]);
	            
	            currentimageindex++;
	            mp = MediaPlayer.create(getBaseContext(), rawIds[currentimageindex]);  
	            mp.start();
	            }
			}
 
		});
        
        button = (Button) findViewById(R.id.backward_btn);
        button.setOnClickListener(new OnClickListener() {
        	 
			@Override
			public void onClick(View arg0) {
		        if (currentimageindex >= 1) {
		        mp.stop();
				Animation inFromLeft = new TranslateAnimation(
		                Animation.RELATIVE_TO_PARENT, -1.0f,
		                Animation.RELATIVE_TO_PARENT, 0.0f,
		                Animation.RELATIVE_TO_PARENT, 0.0f,
		                Animation.RELATIVE_TO_PARENT, 0.0f);
		            inFromLeft.setDuration(500);
		        imageview.startAnimation(inFromLeft);    
	            imageview.setImageResource(IMAGE_IDS[currentimageindex - 1]);
	                
	                //char incoming
		            final ImageView imageviewchar = (ImageView)findViewById(R.id.ImageViewChar);
		            Animation inFromTop = new TranslateAnimation(
		                   Animation.RELATIVE_TO_PARENT, 0.0f,
		                   Animation.RELATIVE_TO_PARENT, 0.0f,
		                   Animation.RELATIVE_TO_PARENT, -1.0f,
		                   Animation.RELATIVE_TO_PARENT, 0.0f);
		            inFromTop.setDuration(500);
		            imageviewchar.startAnimation(inFromTop);
		            imageviewchar.setImageResource(CHAR_IDS[currentimageindex - 1]);
		            
		            // Text incoming
		            Animation inFromRight1 = new TranslateAnimation(
		                    Animation.RELATIVE_TO_PARENT, +1.0f,
		                    Animation.RELATIVE_TO_PARENT, 0.0f,
		                    Animation.RELATIVE_TO_PARENT, 0.0f,
		                    Animation.RELATIVE_TO_PARENT, 0.0f);
		            inFromRight1.setDuration(500);
		            textview.startAnimation(inFromRight1);
		            textview.setImageResource(TextIds[currentimageindex - 1]);
		            
	                currentimageindex--;
					mp = MediaPlayer.create(getBaseContext(), rawIds[currentimageindex]);  
					mp.start();
	            }
			}
 
		});
        
        button = (Button) findViewById(R.id.home_btn);
        button.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0){
        	    Intent intent = new Intent(AbcVoice.this, MainActivity.class);
        	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
        	    startActivity(intent);
        	  }
        	});
        }
    
	  @Override
	  public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance(this).activityStart(this);  // Add this method.
	  }

	  @Override
	  public void onStop() {
	    super.onStop();
	    EasyTracker.getInstance(this).activityStop(this);  // Add this method.
	  }
	  
	@Override
	public void onPause(){
	    super.onStop();	  
	    mp.stop();
	}
    }
    