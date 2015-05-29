package com.example.massimo.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DualSearch_Activity extends ListActivity {

    private String searchTitle;
    private String searchArtist;
    private String searchType;
    public final static String SONG = "massimo.SONG";
    private Client client =  null;

    /*public DualSearch_Activity()
    {
        client = new Client();
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        searchTitle = intent.getStringExtra(MainActivity.TITLE_STRING);
        searchArtist = intent.getStringExtra(MainActivity.ARTIST_STRING);
        searchType = intent.getStringExtra(MainActivity.SEARCH_TYPE);
        System.out.println(searchTitle);
        System.out.println(searchArtist);
        System.out.println(searchType);

        //setContentView(R.layout.activity_dual_search_);
        new Result().execute(searchTitle, searchArtist,searchType);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dual_search_, menu);
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Intent intent = new Intent(this, Player_Activity.class);
        if(searchType.equals("artist"))
            intent.putExtra(SONG, client.getSongByIDArtist(position));
        else if(searchType.equals("title"))
            intent.putExtra(SONG, client.getSongByIDTitle(position));
        else if(searchType.equals("dual"))
            intent.putExtra(SONG,client.getFile());

        startActivity(intent);
    }

    private class Result extends AsyncTask<String, Void, String[]>
    {

        @Override
        protected String[] doInBackground(String... params) {
            client = new Client();
            String[] results = new String[1];
            if(params[2].equals("dual"))
            {
                results[0] = client.dualSearch(params[0],params[1]);
                if(results[0].equals(""))
                {
                    return null;
                }
            }
            else if(params[2].equals("title"))
            {
                client.TitlesList(params[0]);
                results = client.getTitles();
                if(results.length == 0)
                    return null;
            }
            else if(params[2].equals("artist"))
            {
                client.ArtistsList(params[1]);
                results = client.getArtists();
                if(results.length == 0)
                    return null;
            }
            client.clientClose();
            return results;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);
            if (strings == null)
            {
                Toast.makeText(DualSearch_Activity.this, "No result", Toast.LENGTH_SHORT).show();
            }
            else
            {
                final String c1="file";

                List<HashMap<String, String>> data = new ArrayList<>();

                for (String s : strings) {
                    HashMap<String, String> e = new HashMap<>();

                    e.put(c1, s);
                    data.add(e);
                }

                SimpleAdapter adapter = new SimpleAdapter(DualSearch_Activity.this, data, android.R.layout.simple_list_item_1, new String[] { c1 }, new int[] { android.R.id.text1 });
                setListAdapter(adapter);
            }
        }
    }
}