package com.example.valerapelenskyi.androidmysql;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.valerapelenskyi.androidmysql.JsonRequest.JSONParser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSearche;
    private Button btnEdit;
    private EditText etNumber;
    public static TextView tvShowResult;
    private MainActivityFragment mainActivityFragment = new MainActivityFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //===============================================
        btnSearche = (Button) findViewById(R.id.btnSearch);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(this);
        btnSearche.setOnClickListener(this);

        etNumber = (EditText) findViewById(R.id.etNumber);
        tvShowResult = (TextView) findViewById(R.id.tvShowResulte);
        //===============================================




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:

            String number = etNumber.getText().toString();
            mainActivityFragment.runAsincTask(number);
                break;

            case R.id.btnEdit:
                Intent intent = new Intent(this,EditActivity.class);
                startActivity(intent);
                break;
        }
    }


}
