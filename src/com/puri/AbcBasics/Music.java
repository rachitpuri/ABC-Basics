package com.puri.AbcBasics;

import com.google.analytics.tracking.android.EasyTracker;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

public class Music extends Activity {

	MediaPlayer mp;
	RadioButton twinkle,johny,humpty,baba;
    private int[] MusicIds = { R.raw.twinkle, R.raw.baba, R.raw.johny, R.raw.humpty };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		twinkle = (RadioButton)findViewById(R.id.twinkle);
		baba = (RadioButton)findViewById(R.id.baba);
		johny = (RadioButton)findViewById(R.id.johny);
		humpty = (RadioButton)findViewById(R.id.humpty);
		
		mp = MediaPlayer.create(getBaseContext(), MusicIds[0]);
		twinkle.setChecked(true);
        mp.setLooping(true);
        mp.start();		
		
		// play music	
		twinkle.setOnClickListener(new OnClickListener() {	 
			@Override
			public void onClick(View v) {
				 baba.setChecked(false);
				 twinkle.setChecked(true);
				 johny.setChecked(false);
				 humpty.setChecked(false);
				 mp.stop();
				 mp = MediaPlayer.create(getBaseContext(), MusicIds[0]);
	             mp.setLooping(true);
	             mp.start();
	            }
		});
		

		baba.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				 baba.setChecked(true);
				 twinkle.setChecked(false);
				 johny.setChecked(false);
				 humpty.setChecked(false);
				 mp.stop();
				 mp = MediaPlayer.create(getBaseContext(), MusicIds[1]);
	             mp.setLooping(true);
	             mp.start();
	            }
		});
		

		johny.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				 baba.setChecked(false);
				 twinkle.setChecked(false);
				 johny.setChecked(true);
				 humpty.setChecked(false);
				 mp.stop();
				 mp = MediaPlayer.create(getBaseContext(), MusicIds[2]);
	             mp.setLooping(true);
	             mp.start();
	            }
		});
		

		humpty.setOnClickListener(new OnClickListener() {	 
			
			@Override
			public void onClick(View v) {
				 baba.setChecked(false);
				 twinkle.setChecked(false);
				 johny.setChecked(false);
				 humpty.setChecked(true);
				 mp.stop();
				 mp = MediaPlayer.create(getBaseContext(), MusicIds[3]);
	             mp.setLooping(true);
	             mp.start();
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
	public void onResume(){
		super.onResume();
		if(!mp.isPlaying()){
			baba.setChecked(false);
			twinkle.setChecked(true);
			johny.setChecked(false);
			humpty.setChecked(false);
			mp = MediaPlayer.create(getBaseContext(), MusicIds[0]);
			mp.setLooping(true);
			mp.start();
		}
	}
	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) 
        { //Back key pressed
            if(mp.isPlaying())
               mp.stop();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music, menu);
		return true;
	}

}
