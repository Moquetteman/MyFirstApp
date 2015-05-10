package com.example.massimo.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Add_Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_, menu);
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

    public void AddAction(View v)
    {
        EditText champTitre = (EditText)findViewById(R.id.editText);
        String titre = champTitre.getText().toString();
        EditText champArtiste = (EditText)findViewById(R.id.editText2);
        String artiste = champArtiste.getText().toString();
        EditText champFichier = (EditText)findViewById(R.id.editText3);
        String fichier = champFichier.getText().toString();

        Client client = new Client();
        if(client.dualSearch(titre,artiste).equals(""))
        {
            client.AddFile(titre,artiste,fichier);
            if(client.dualSearch(titre,artiste).equals(""))
                Toast.makeText(Add_Activity.this, "Add fail", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(Add_Activity.this, "Add success", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(Add_Activity.this, "Already Exist", Toast.LENGTH_SHORT).show();
        }

        client.clientClose();
    }

    public void CancelAction(View v)
    {
        Intent intent = new Intent(Add_Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
