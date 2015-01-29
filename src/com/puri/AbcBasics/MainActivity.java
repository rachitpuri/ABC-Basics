package com.puri.AbcBasics;

import com.puri.AbcBasics.ABC;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends Activity {

	int status = 0;
	MediaPlayer mp;
	ImageView imageview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  
		
		//rate app
		AppRater.app_launched(this);
		
		mp = MediaPlayer.create(getBaseContext(), R.raw.abc);
		if(status == 0 && !mp.isPlaying()){
			mp.setLooping(true);
			mp.start();
		}
	
		imageview = (ImageView)findViewById(R.id.volume);
		imageview.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				if (mp.isPlaying() && status == 0) {
					mp.stop();
					imageview.setImageResource(R.drawable.vol_off);
					status = 1;
				} else {
					mp = MediaPlayer.create(getBaseContext(), R.raw.abc);
					mp.isLooping();
					mp.start();
					imageview.setImageResource(R.drawable.vol_on);
					status = 0;
				}
	            }		
		});	
	
		final Button abc = (Button)findViewById(R.id.abc_main);
		abc.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				mp.stop();
				status = 1;
				imageview.setImageResource(R.drawable.vol_off);
                Intent myIntent = new Intent(MainActivity.this, ABC.class);
                startActivity(myIntent);
	            }
		});	
		
		final Button number = (Button)findViewById(R.id.number_main);
		number.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				mp.stop();
				status = 1;
				imageview.setImageResource(R.drawable.vol_off);
				 Intent myIntent = new Intent(MainActivity.this, Numbers.class);
	             startActivity(myIntent);
	            }
		});	
		
		final Button puzzle = (Button)findViewById(R.id.puzzle_main);
		puzzle.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				mp.stop();
				imageview.setImageResource(R.drawable.vol_off);
				Intent myIntent = new Intent(MainActivity.this, Puzzle.class);
	            startActivity(myIntent);
	            }
		});	
		
		final Button music = (Button)findViewById(R.id.music_main);
		music.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				mp.stop();
				imageview.setImageResource(R.drawable.vol_off);
				Intent myIntent = new Intent(MainActivity.this, Music.class);
	            startActivity(myIntent);
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
	
	@Override
	public void onResume(){
		super.onResume();
		if(!mp.isPlaying()){
			status = 0;
			imageview.setImageResource(R.drawable.vol_on);
			mp = MediaPlayer.create(getBaseContext(), R.raw.abc);
			mp.setLooping(true);
			mp.start();
		}
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
