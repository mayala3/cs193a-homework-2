package com.example.miguelayala.to_dolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    /*
    An ArrayList of notes.
     */
    private ArrayList<String> notes = new ArrayList<String>();

    /*
    An ArrayAdapter of Strings
     */
    private ArrayAdapter<String> noteAdapter;

    /*
    A ListView.
     */
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        noteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        list = (ListView) findViewById(R.id.list);
        list.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
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

    /*
    This method adds the note to the ArrayList notes.
     */
    public void add_click(View view){
        EditText noteText = (EditText) findViewById(R.id.note_text);
        String word = noteText.getText().toString();
        notes.add(word);
        noteText.setText("Enter note here");
        displayList();
    }

    /*
    This method clears the editText when it is clicked.
     */
    public void edit_text(View view){
        EditText noteText = (EditText) findViewById(R.id.note_text);
        noteText.setText("");
    }
    /*
    This method displays the ArrayList in the app.
     */
    public void displayList(){
        noteAdapter.notifyDataSetChanged();
        list.setAdapter(noteAdapter);
    }

    /*
    This method deletes the note if it has been long clicked.
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        notes.remove(position);
        displayList();
        return false;
    }
}
