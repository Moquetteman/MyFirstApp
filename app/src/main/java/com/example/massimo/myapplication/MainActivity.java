package com.example.massimo.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public final static String TITLE_STRING = "massimo.TITLE_STRING";
    public final static String ARTIST_STRING = "massimo.ARTIST_STRING";
    public final static String SEARCH_TYPE = "massimo.SEARCH_TYPE";
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

    public void SearchByArtist(View v)
    {
        EditText searchText = (EditText)findViewById(R.id.champ_auteur);
        String text = searchText.getText().toString();


        Intent intent = new Intent(MainActivity.this, DualSearch_Activity.class);
        intent.putExtra(ARTIST_STRING, text);
        intent.putExtra(SEARCH_TYPE, "artist");
        startActivity(intent);

    }

    public void SearchByTitle(View v)
    {
        //String[] test =  new String[0];
        EditText searchText = (EditText)findViewById(R.id.champ_titre);
        String text = searchText.getText().toString();


        Intent intent = new Intent(MainActivity.this, DualSearch_Activity.class);
        intent.putExtra(TITLE_STRING, text);
        intent.putExtra(SEARCH_TYPE, "title");
        startActivity(intent);
    }

    public boolean Add(View v)
    {
        return true;
    }
    public void DualSearch(View v)
    {
        EditText searchText1 = (EditText)findViewById(R.id.champ_titre);
        String text1 = searchText1.getText().toString();
        EditText searchText2 = (EditText)findViewById(R.id.champ_auteur);
        String text2 = searchText2.getText().toString();


        Intent intent = new Intent(MainActivity.this, DualSearch_Activity.class);
        intent.putExtra(TITLE_STRING, text1);
        intent.putExtra(ARTIST_STRING, text2);
        intent.putExtra(SEARCH_TYPE, "dual");
        startActivity(intent);
    }

    public void AddAction(View v)
    {
        Intent intent = new Intent(MainActivity.this, Add_Activity.class);
        startActivity(intent);
    }
}
