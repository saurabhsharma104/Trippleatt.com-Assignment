package com.example.trippleattassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void login(View view) {

        EditText editText;
        editText = findViewById(R.id.editText2);
        String email = editText.getText().toString();
        editText = findViewById(R.id.editText6);
        String p = editText.getText().toString();

        if(email.isEmpty()||p.isEmpty()) {

            Toast.makeText(this, "Please Enter All Data Fields....", Toast.LENGTH_SHORT).show();
            return;
        }

        if(validateRequest(email,p))
        {
            Toast.makeText(this, "Login Successfully.......", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,HomePage.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Data not matched....Try Again", Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean validateRequest(String e, String p) {

        Cursor cursor;
        try {
            DataStore dataStore = new DataStore(this);
            cursor = dataStore.readData(e);
        }
        catch (Exception exc) {
            Toast.makeText(this, "Some technical issue...."+exc, Toast.LENGTH_SHORT).show();
            return false;
        }
        while (cursor.moveToNext())
        {
            String pass =   cursor.getString(cursor.getColumnIndex(DataStore.PASSWORD));

            if(pass.equals(p))
                return true;
        }
        return false;
    }

    public void registerPage(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
