package com.example.massimo.myapplication;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Player_Activity extends ActionBarActivity {

    private static MediaPlayer mediaPlayer = null;
    private String song;
    private String server="178.62.148.22:8090/";
    private Client client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        song = intent.getStringExtra(DualSearch_Activity.SONG);
        client = new Client();
        //pause.setEnabled(false);
        setContentView(R.layout.activity_player);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void PlaySong(View v)
    {
        if (mediaPlayer == null)
        {
            mediaPlayer = new MediaPlayer();
            String link = "http://" +server + song;
            if (client.PlayMusic(song).equals(""))
                Toast.makeText(Player_Activity.this, "Fail  " + song, Toast.LENGTH_SHORT).show();
            else
            {
                try
                {
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(link);
                    Toast.makeText(Player_Activity.this, "Play " + song, Toast.LENGTH_SHORT).show();
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }
                catch(Exception e)
                {
                    mediaPlayer.release();
                    e.printStackTrace();
                }
            }
        }
        else
        {
            mediaPlayer.start();
        }
    }

    public void PauseSong(View v)
    {
        mediaPlayer.pause();
        Toast.makeText(Player_Activity.this, "Pause "+ song, Toast.LENGTH_SHORT).show();
    }

    public void StopSong(View v)
    {
        if (mediaPlayer != null)
        {
            client.StopMusic(song);
            mediaPlayer.stop();
            mediaPlayer.release();
            client = null;
            Toast.makeText(Player_Activity.this, "Stop " + song, Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}
