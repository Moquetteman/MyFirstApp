package com.example.massimo.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public String[] SearchByArtist(View v)
    {
        return null;
    }

    public String[] SearchByTitle(View v)
    {
        //String[] test =  new String[0];
        return null;
    }

    public boolean Add(View v)
    {
        return true;
    }
    public void DualSearch(View v)
    {
        Intent intent = new Intent(MainActivity.this, DualSearch_Activity.class);
        startActivity(intent);
    }

    public void AddAction(View v)
    {
        Intent intent = new Intent(MainActivity.this, Add_Activity.class);
        startActivity(intent);
    }
}
