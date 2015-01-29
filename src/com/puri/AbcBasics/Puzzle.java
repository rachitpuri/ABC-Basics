package com.puri.AbcBasics;

import java.util.Random;

import com.google.analytics.tracking.android.EasyTracker;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class Puzzle extends Activity {

	Button button;
	ImageView image1,image2,image3,image4;
	MediaPlayer mp;
	Animation shake,shake_right,animZoomIn,animZoomIn2,animZoomIn3,animZoomIn4;
	private int[] RandomSet = {-1,-1,-1,-1};
	Random Rnd = null, RndAnswer = null;
	int alphabet,question,answer,value = -1;
	
	
    private int[] CHAR_IDS = {R.drawable.image_a, R.drawable.image_b, R.drawable.image_c, R.drawable.image_d,
				    	      R.drawable.image_e, R.drawable.image_f, R.drawable.image_g, R.drawable.image_h,
				    	      R.drawable.image_i, R.drawable.image_j, R.drawable.image_k, R.drawable.image_l,
				    	      R.drawable.image_m, R.drawable.image_n, R.drawable.image_o, R.drawable.image_p,
				    	      R.drawable.image_q, R.drawable.image_r, R.drawable.image_s, R.drawable.image_t,
				    	      R.drawable.image_u, R.drawable.image_v, R.drawable.image_w, R.drawable.image_x,
				    	      R.drawable.image_y, R.drawable.image_z};
    
    private int[] ERROR_IDS = {R.drawable.image_a_red, R.drawable.image_b_red, R.drawable.image_c_red, R.drawable.image_d_red,
  	      R.drawable.image_e_red, R.drawable.image_f_red, R.drawable.image_g_red, R.drawable.image_h_red,
  	      R.drawable.image_i_red, R.drawable.image_j_red, R.drawable.image_k_red, R.drawable.image_l_red,
  	      R.drawable.image_m_red, R.drawable.image_n_red, R.drawable.image_o_red, R.drawable.image_p_red,
  	      R.drawable.image_q_red, R.drawable.image_r_red, R.drawable.image_s_red, R.drawable.image_t_red,
  	      R.drawable.image_u_red, R.drawable.image_v_red, R.drawable.image_w_red, R.drawable.image_x_red,
  	      R.drawable.image_y_red, R.drawable.image_z_red};
    
    private int[] rawIds = {
            R.raw.find_a, R.raw.find_b, R.raw.find_c, R.raw.find_d,
            R.raw.find_e, R.raw.find_f, R.raw.find_g, R.raw.find_h,
            R.raw.find_i, R.raw.find_j, R.raw.find_k, R.raw.find_l,
            R.raw.find_m, R.raw.find_n, R.raw.find_o, R.raw.find_p,
            R.raw.find_q, R.raw.find_r, R.raw.find_s, R.raw.find_t, 
            R.raw.find_u, R.raw.find_v, R.raw.find_w, R.raw.find_x,
            R.raw.find_y, R.raw.find_z
        };
    
    private int[] replyIds = {
            R.raw.reply_1, R.raw.reply_2, R.raw.reply_3, R.raw.reply_4,
            R.raw.reply_5, R.raw.reply_6, R.raw.reply_7
    };
    
    private int[] failIds = {
    		R.raw.fail_a, R.raw.fail_b, R.raw.fail_c, R.raw.fail_d,
            R.raw.fail_e, R.raw.fail_f, R.raw.fail_g, R.raw.fail_h,
            R.raw.fail_i, R.raw.fail_j, R.raw.fail_k, R.raw.fail_l,
            R.raw.fail_m, R.raw.fail_n, R.raw.fail_o, R.raw.fail_p,
            R.raw.fail_q, R.raw.fail_r, R.raw.fail_s, R.raw.fail_t, 
            R.raw.fail_u, R.raw.fail_v, R.raw.fail_w, R.raw.fail_x,
            R.raw.fail_y, R.raw.fail_z
        };
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puzzle);
		// Show the Up button in the action bar.

		// display characters
		displayChars();
		shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		//shake_right = AnimationUtils.loadAnimation(this, R.anim.shake);
		 animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
	                R.anim.zoom_lt);
		 animZoomIn2 = AnimationUtils.loadAnimation(getApplicationContext(),
	                R.anim.zoom_lb);
		 animZoomIn3 = AnimationUtils.loadAnimation(getApplicationContext(),
	                R.anim.zoom_rt);
		 animZoomIn4 = AnimationUtils.loadAnimation(getApplicationContext(),
	                R.anim.zoom_rb);
		
		 animZoomIn.setAnimationListener(new AnimationListener() {  
		    //@Override
		    public void onAnimationStart(Animation animation) {  
		        // TODO Auto-generated method stub
		    }
		   // @Override
		    public void onAnimationRepeat(Animation animation) {
		        // TODO Auto-generated method stub
		    }
		    //@Override
		    public void onAnimationEnd(Animation animation) {
		    	// pass the intent to switch to other activity
		    	displayChars();
		    	}
		    }); 
		 animZoomIn2.setAnimationListener(new AnimationListener() {  
			    //@Override
			    public void onAnimationStart(Animation animation) {  
			        // TODO Auto-generated method stub
			    }
			   // @Override
			    public void onAnimationRepeat(Animation animation) {
			        // TODO Auto-generated method stub
			    }
			    //@Override
			    public void onAnimationEnd(Animation animation) {
			    	// pass the intent to switch to other activity
			    	displayChars();
			    	}
			    }); 
		 animZoomIn3.setAnimationListener(new AnimationListener() {  
			    //@Override
			    public void onAnimationStart(Animation animation) {  
			        // TODO Auto-generated method stub
			    }
			   // @Override
			    public void onAnimationRepeat(Animation animation) {
			        // TODO Auto-generated method stub
			    }
			    //@Override
			    public void onAnimationEnd(Animation animation) {
			    	// pass the intent to switch to other activity
			    	displayChars();
			    	}
			    }); 
		 animZoomIn4.setAnimationListener(new AnimationListener() {  
			    //@Override
			    public void onAnimationStart(Animation animation) {  
			        // TODO Auto-generated method stub
			    }
			   // @Override
			    public void onAnimationRepeat(Animation animation) {
			        // TODO Auto-generated method stub
			    }
			    //@Override
			    public void onAnimationEnd(Animation animation) {
			    	// pass the intent to switch to other activity
			    	displayChars();
			    	}
			    }); 
    
 	   image1 = (ImageView) findViewById(R.id.lefttop);     
 	   image2 = (ImageView) findViewById(R.id.righttop);
       image3 = (ImageView) findViewById(R.id.leftbottom);
       image4 = (ImageView) findViewById(R.id.rightbottom);
       ImageView question_image = (ImageView) findViewById(R.id.question);
	   
		//Image Click Listener
	   OnClickListener mGlobalListener = new OnClickListener() {

    	    @Override
    	    public void onClick(View v)
    		{
    	    	if(mp.isPlaying())
    	    		return;
    	    	RndAnswer = new Random();
    	    	int ans = RndAnswer.nextInt(7);
    			 switch (v.getId()) {
    		      case R.id.lefttop:
    		         // do your stuff for btn1
    		    	  if(answer == 0){
    		    		  mp = MediaPlayer.create(getBaseContext(), replyIds[ans]);  
    		  			  mp.start();
    		  			 // SystemClock.sleep(1000);
    		  			  findViewById(R.id.lefttop).startAnimation(animZoomIn);
    		        	  clearRandomSet();	        	  	
    		    	  }else{
    		    		  mp = MediaPlayer.create(getBaseContext(), failIds[question]);  
    		  			  mp.start(); 
    		  			  findViewById(R.id.lefttop).startAnimation(shake);
    		  			  image1.setImageResource(ERROR_IDS[RandomSet[0]]);
    		    	  }
    		    		  
    		    	  break;
    		      case R.id.leftbottom:
    		         // do your stuff for btn2
    		    	  if(answer == 2){
    		    		  mp = MediaPlayer.create(getBaseContext(), replyIds[ans]);  
    		  			  mp.start();
    		  			  //SystemClock.sleep(1000);
    		  			  findViewById(R.id.leftbottom).startAnimation(animZoomIn2);
    		        	  clearRandomSet();
    		    	  }else{
    		    		  mp = MediaPlayer.create(getBaseContext(), failIds[question]);  
    		  			  mp.start(); 
    		  			findViewById(R.id.leftbottom).startAnimation(shake);
    		  			image3.setImageResource(ERROR_IDS[RandomSet[2]]);
    		    	  }
    		    	  break;
    		      case R.id.righttop:
    	 	         // do your stuff for btn2
    		    	  if(answer == 1){
    		    		  mp = MediaPlayer.create(getBaseContext(), replyIds[ans]);  
    		  			  mp.start();
    		  			 // SystemClock.sleep(1000);
    		  			  findViewById(R.id.righttop).startAnimation(animZoomIn3);
    		        	  clearRandomSet();
    		    	  }else{
    		    		  mp = MediaPlayer.create(getBaseContext(), failIds[question]);  
    		  			  mp.start(); 
    		  			findViewById(R.id.righttop).startAnimation(shake);
    		  			image2.setImageResource(ERROR_IDS[RandomSet[1]]);
    		    	  }
    		    	  break;
    		      case R.id.rightbottom:
    	 	         // do your stuff for btn2
    		    	  if(answer == 3){
    		    		  mp = MediaPlayer.create(getBaseContext(), replyIds[ans]);  
    		  			  mp.start();
    		  			  //SystemClock.sleep(1000);
    		  			  findViewById(R.id.rightbottom).startAnimation(animZoomIn4);
    		        	  clearRandomSet();
    		    	  }else{
    		    		  mp = MediaPlayer.create(getBaseContext(), failIds[question]);  
    		  			  mp.start();
    		  			findViewById(R.id.rightbottom).startAnimation(shake);
    		  			image4.setImageResource(ERROR_IDS[RandomSet[3]]);
    		    	  }
    		    	  break;
    	 	      default:
    	 	    	  break;
    		   } 			 
    		}
    	}; 

    	image1.setOnClickListener(mGlobalListener);
    	image2.setOnClickListener(mGlobalListener);
    	image3.setOnClickListener(mGlobalListener);
    	image4.setOnClickListener(mGlobalListener);
    	
		//Home button
		button = (Button) findViewById(R.id.puzzlehome_btn);
	        button.setOnClickListener(new OnClickListener() {
	        	@Override
	        	public void onClick(View arg0){
	        	    Intent intent = new Intent(Puzzle.this, MainActivity.class);
	        	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
	        	    startActivity(intent);
	        	  }
	        	});  
	        
	     // Refresh button
	     button = (Button) findViewById(R.id.refresh_btn);
	     button.setOnClickListener(new OnClickListener() {
	        	@Override
	        	public void onClick(View arg0){
	        		//Clear random Set
	        		mp.stop();
	        		clearRandomSet();
	        		displayChars();
	        	  }
	        	});  
	     
	  // Question button
	     question_image = (ImageView) findViewById(R.id.question);
	     question_image.setOnClickListener(new OnClickListener() {
	        	@Override
	        	public void onClick(View arg0){
	        		//Clear random Set
	        		mp.stop();
	        		mp = MediaPlayer.create(getBaseContext(), rawIds[question]);  
	    			mp.start();
	        	  }
	        	});  
	}

	public void displayChars(){
		for (int i = 0; i < 4; i++) {
		Rnd = new Random();
		alphabet = Rnd.nextInt(26);
		//check for duplicate random number
		if(i == 0){
			add(i, alphabet);
			value = Rnd.nextInt(4);
		}
		else{
			if(DuplicateFound(alphabet)){
				--i;
				continue;
			}else
				add(i, alphabet);
		}

		if(i == value){
			mp = MediaPlayer.create(getBaseContext(), rawIds[alphabet]);  
			mp.start();
			question = alphabet;
			answer = i;
			}
		
		switch(i)
		{
		case 0:
			final ImageView lefttop = (ImageView)findViewById(R.id.lefttop);
			Animation inFromTop = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, -1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
	        inFromTop.setDuration(500);
	        lefttop.startAnimation(inFromTop);
	        lefttop.setImageResource(CHAR_IDS[alphabet]);
	    
		case 1:
			final ImageView righttop = (ImageView)findViewById(R.id.righttop);
			Animation inFromRight = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, 1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
			inFromRight.setDuration(500);
			righttop.startAnimation(inFromRight);
			righttop.setImageResource(CHAR_IDS[alphabet]);
	        
		case 2:
			final ImageView leftbottom = (ImageView)findViewById(R.id.leftbottom);
			Animation inFromLeft = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, -1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
			inFromLeft.setDuration(500);
			leftbottom.startAnimation(inFromLeft);
			leftbottom.setImageResource(CHAR_IDS[alphabet]);
	        
		case 3:
			final ImageView rightbottom = (ImageView)findViewById(R.id.rightbottom);
			Animation inFromBottom = new TranslateAnimation(
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f,
	            Animation.RELATIVE_TO_PARENT, 1.0f,
	            Animation.RELATIVE_TO_PARENT, 0.0f);
			inFromBottom.setDuration(500);
			rightbottom.startAnimation(inFromBottom);
			rightbottom.setImageResource(CHAR_IDS[alphabet]);
	        
	    default:
	    	break;    
		}
		}
}

    public void add(int value, int random)
    {
    	RandomSet[value] = random;
    }
    
    public boolean DuplicateFound(int value)
    {
    	for(int j=0; j<4; j++)
    	{
    		if(RandomSet[j] == value)
    			return true;
    	}    	
    	return false;
    }
    
    public void clearRandomSet()
    {
    	for(int j=0; j<4; j++)
    		RandomSet[j] = -1;
    }
    
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.abc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
