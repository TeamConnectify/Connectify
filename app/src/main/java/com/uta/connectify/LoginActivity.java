package com.uta.connectify;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "UserData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button go = (Button) findViewById(R.id.btn_login);

        SharedPreferences userData = getSharedPreferences(PREFS_NAME, 0);
        final String uname = (String)userData.getString("username", "");
        final String password = (String)userData.getString("password", "");

        final EditText email = (EditText) findViewById(R.id.txt_login_email);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if (email.getText().toString().equalsIgnoreCase("UTA Email ID")) {email.setText("");}
                }else {
                    if (email.getText().toString().isEmpty()) {email.setText(R.string.login_email_id);}
                }
            }
        });

        final EditText pwd = (EditText) findViewById(R.id.txt_password);
        pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    if (pwd.getText().toString().equalsIgnoreCase("password")) {pwd.setText("");}
                }else {
                    if (pwd.getText().toString().isEmpty()) {pwd.setText(R.string.login_pwd);}
                }
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(uname.isEmpty() ||
                        password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No stored username or password", Toast.LENGTH_LONG).show();
                    return;
                }

                if(email.getText().toString().equalsIgnoreCase(uname) &&
                        pwd.getText().toString().equalsIgnoreCase(password)) {
                    Toast.makeText(getApplicationContext(), "Awesome ! You are IN !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Oops ! Username or Password is wrong !", Toast.LENGTH_LONG).show();
                }

            }
        });

        go.setFocusable(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
