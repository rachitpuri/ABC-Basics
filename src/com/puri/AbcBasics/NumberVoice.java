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

public class NumberVoice extends Activity {
	 
	Button button;
	public int currentimageindex = 0;
	Animation shake;
	MediaPlayer mp;
	Resources resources;
    private int[] IMAGE_IDS = {R.drawable.image_one, R.drawable.image_two, R.drawable.image_three, R.drawable.image_four,
					    	   R.drawable.image_five, R.drawable.image_six, R.drawable.image_seven, R.drawable.image_eight,
					    	   R.drawable.image_nine, R.drawable.image_ten};
    
    private int[] NUMBER_IDS = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4,
	    	   				  R.drawable.image_5, R.drawable.image_6, R.drawable.image_7, R.drawable.image_8,
	    	   				  R.drawable.image_9, R.drawable.image_10};
    
    private int[] TEXT_IDS = {R.drawable.text_1, R.drawable.text_2, R.drawable.text_3, R.drawable.text_4,
				  R.drawable.text_5, R.drawable.text_6, R.drawable.text_7, R.drawable.text_8,
				  R.drawable.text_9, R.drawable.text_10};

    private int[] rawIds = {
            R.raw.one, R.raw.two, R.raw.three, R.raw.four,
            R.raw.five, R.raw.six, R.raw.seven, R.raw.eight,
            R.raw.nine, R.raw.ten };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_voice);
        resources = getResources();
        
        final ImageView imageview = (ImageView)findViewById(R.id.ImageViewPreviewNumber);
        final int position = getIntent().getExtras().getInt("imgPosNumber");
	     mp = MediaPlayer.create(getBaseContext(), rawIds[position]);  
	     mp.start();
        imageview.setImageResource(IMAGE_IDS[position]); 
        
        final ImageView imageviewchar = (ImageView)findViewById(R.id.ImageViewNumber);
        Animation inFromTop = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromTop.setDuration(500);
        imageviewchar.startAnimation(inFromTop);
        imageviewchar.setImageResource(NUMBER_IDS[position]);
        
        //Number incoming
        final ImageView NumberText = (ImageView)findViewById(R.id.NumberView);
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        NumberText.startAnimation(inFromRight);
        NumberText.setImageResource(TEXT_IDS[position]);
        
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
        
        
        button = (Button) findViewById(R.id.forward_number_btn);
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
	            imageviewchar.setImageResource(NUMBER_IDS[currentimageindex + 1]);
	                
	            // Text incoming         
	            Animation inFromRight1 = new TranslateAnimation(
	                   Animation.RELATIVE_TO_PARENT, +1.0f,
	                   Animation.RELATIVE_TO_PARENT, 0.0f,
	                   Animation.RELATIVE_TO_PARENT, 0.0f,
	                   Animation.RELATIVE_TO_PARENT, 0.0f);
	            inFromRight1.setDuration(500);
	            NumberText.startAnimation(inFromRight1);
	            NumberText.setImageResource(TEXT_IDS[currentimageindex + 1]);
	            
	            currentimageindex++;
	            mp = MediaPlayer.create(getBaseContext(), rawIds[currentimageindex]);  
	            mp.start();
	            }
			}
 
		});
        
        button = (Button) findViewById(R.id.backward_number_btn);
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
		            Animation inFromTop = new TranslateAnimation(
		                   Animation.RELATIVE_TO_PARENT, 0.0f,
		                   Animation.RELATIVE_TO_PARENT, 0.0f,
		                   Animation.RELATIVE_TO_PARENT, -1.0f,
		                   Animation.RELATIVE_TO_PARENT, 0.0f);
		            inFromTop.setDuration(500);
		            imageviewchar.startAnimation(inFromTop);
		            imageviewchar.setImageResource(NUMBER_IDS[currentimageindex - 1]);
		            
		            // Text incoming         
		            Animation inFromRight1 = new TranslateAnimation(
		                   Animation.RELATIVE_TO_PARENT, +1.0f,
		                   Animation.RELATIVE_TO_PARENT, 0.0f,
		                   Animation.RELATIVE_TO_PARENT, 0.0f,
		                   Animation.RELATIVE_TO_PARENT, 0.0f);
		            inFromRight1.setDuration(500);
		            NumberText.startAnimation(inFromRight1);
		            NumberText.setImageResource(TEXT_IDS[currentimageindex - 1]);
		            
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
        	    Intent intent = new Intent(NumberVoice.this, MainActivity.class);
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