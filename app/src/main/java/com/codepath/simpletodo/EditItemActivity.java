package com.codepath.simpletodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class EditItemActivity extends Activity {
    int position;
    String text;
    EditText etEditItem;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        position = getIntent().getIntExtra("pos", 0);
        text = getIntent().getStringExtra("text");

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etEditItem, InputMethodManager.SHOW_IMPLICIT);

        etEditItem = (EditText) findViewById(R.id.etEditText);
        etEditItem.setText(text.toCharArray(), 0, text.length());
        etEditItem.setSelection(etEditItem.getText().length());
        etEditItem.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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

    public void onSaveEditItem(View v) {
        imm.hideSoftInputFromInputMethod(etEditItem.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);

        Intent i = new Intent();
        i.putExtra("text", etEditItem.getText().toString());
        i.putExtra("pos", position);

        Log.d(this.getLocalClassName(), "text = " + etEditItem.getText());
        Log.d(this.getLocalClassName(), "pos = " + position);

        setResult(RESULT_OK, i);
        finish();
    }
}
